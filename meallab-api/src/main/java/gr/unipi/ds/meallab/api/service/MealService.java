package gr.unipi.ds.meallab.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.unipi.ds.meallab.api.model.Meal;
import gr.unipi.ds.meallab.api.model.MealResponse;
import gr.unipi.ds.meallab.api.model.SimpleMeal;
import gr.unipi.ds.meallab.api.model.SimpleMealResponse;
import gr.unipi.ds.meallab.exception.ApiException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealService {

	private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    // Constructor
    public MealService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }
    
    // Search meals by ingredient or meal name and return a simplified response
    public List<SimpleMeal> searchMeals(String query) throws ApiException {
        if (query == null || query.trim().isEmpty()) {
            throw new ApiException("Search query cannot be empty", 
                    ApiException.ErrorType.INVALID_INPUT);
        }
        
        try {
        	String url = BASE_URL + "search.php?s=" + query.trim();
            String jsonResponse = makeApiCall(url);
            
            SimpleMealResponse response = objectMapper.readValue(jsonResponse, SimpleMealResponse.class);
            
            // API returns null if no meals found
            if (response.getMeals() == null) {
                return new ArrayList<>();
//            	throw new ApiException("API call found no meals", 
//                        ApiException.ErrorType.NOT_FOUND);
            }
            
            return response.getMeals();
        }
        catch (IOException e) {
            throw new ApiException("Failed to search meals:  " + e.getMessage(), 
                    ApiException.ErrorType.NETWORK_ERROR, e);
        }
        
    }
    
    // Get meal details by Id
    public Meal getMealById(String mealId) throws ApiException {
        if (mealId == null || mealId.trim().isEmpty()) {
        	throw new ApiException("Meal ID cannot be empty", 
                    ApiException.ErrorType.INVALID_INPUT);
        }
        
        try {
        	String url = BASE_URL + "lookup.php?i=" + mealId.trim();
            String jsonResponse = makeApiCall(url);
            
            MealResponse response = objectMapper.readValue(jsonResponse, MealResponse.class);
            
            // API returns array with one meal
            if (response.getMeals() == null || response.getMeals().isEmpty()) {
            	throw new ApiException("Meal not found with ID: " + mealId, 
                        ApiException.ErrorType.NOT_FOUND);
            }
            
            return response.getMeals().get(0);
        }
        catch (IOException e) {
            throw new ApiException("Failed to fetch meal details: " + e.getMessage(), 
                    ApiException.ErrorType.NETWORK_ERROR, e);
        }
        
    }
    
    // Get random meal with full details
    public Meal getRandomMeal() throws ApiException {
    	
    	try {
    		String url = BASE_URL + "random.php";
        	String jsonResponse = makeApiCall(url);
        	
        	MealResponse response = objectMapper.readValue(jsonResponse, MealResponse.class);
        			
        	// API always returns one random meal
            if (response.getMeals() == null || response. getMeals().isEmpty()) {
            	throw new ApiException("Failed to fetch random meal", 
                        ApiException.ErrorType.INVALID_RESPONSE);
            }
            
            return response.getMeals().get(0);
    	}
    	catch (IOException e) {
            throw new ApiException("Failed to fetch random meal: " + e.getMessage(), 
            		ApiException.ErrorType.NETWORK_ERROR, e);
        }
    	
    }
    
    // Make HTTP GET request to API
    private String makeApiCall(String url) throws IOException{
    	Request req = new Request.Builder().url(url).get().build();
    	
    	try(Response res = httpClient.newCall(req).execute()){
    		if (!res.isSuccessful()) {
    			throw new IOException("API call failed with code: " + res.code());
    		}
    		
    		if (res.body() == null) {
    			throw new IOException("Empty response from API");
    		}
    		
    		return res.body().string();
    	}
    }
    
    // Close httpClient when done
    public void Close() {
    	if (httpClient != null) {
    		httpClient.dispatcher().executorService().shutdown();
    		httpClient.connectionPool().evictAll();
    	}
    }
}
