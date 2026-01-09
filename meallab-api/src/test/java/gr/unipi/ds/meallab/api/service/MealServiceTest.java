package gr.unipi.ds.meallab.api.service;

import gr.unipi.ds.meallab.api.model.Meal;
import gr.unipi.ds.meallab.api.model.SimpleMeal;
import gr.unipi.ds.meallab.exception.ApiException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MealServiceTest {

	 private MealService service;
	    
	    @BeforeEach
	    void setUp() {
	        service = new MealService();
	    }
	    
	    @AfterEach
	    void tearDown() {
	        service.Close();
	    }
	    
	    @Test
	    void testSearchMeals_ValidQuery() throws ApiException {
	    	// Search for common ingredient
	    	List<SimpleMeal> meals = service.searchMeals("chicken");
	    	
	    	assertNotNull(meals);
	    	assertFalse(meals.isEmpty());
	    	
	    	// Verify first meal has required fields
	        SimpleMeal firstMeal = meals.get(0);
	        assertNotNull(firstMeal.getId());
	        assertNotNull(firstMeal.getName());
	        assertNotNull(firstMeal.getCategory());
	        assertNotNull(firstMeal.getArea());
	        assertNotNull(firstMeal.getThumbnailUrl());
	    }
	    
	    @Test
	    void testSearchMeals_NoResults() throws ApiException {
	        // Search for something that doesn't exist
//	        ApiException exception = assertThrows(ApiException.class, () -> {
//	            service.searchMeals("abcdefghijkl");
//	        });
//	        assertEquals(ApiException.ErrorType.NOT_FOUND, exception.getErrorType());
	        
	        List<SimpleMeal> meals = service.searchMeals("abcdefghijkl");
	        assertNotNull(meals);
	        assertTrue(meals.isEmpty());
	    }
	    
	    @Test
	    void testSearchMeals_EmptyQuery() {
	        // Empty query should throw exception
	        ApiException exception = assertThrows(ApiException.class, () -> {
	            service.searchMeals("");
	        });
	        
	        assertEquals(ApiException.ErrorType.INVALID_INPUT, exception.getErrorType());
	    }
	    
	    @Test
	    void testSearchMeals_NullQuery() {
	        // Null query should throw exception
	        ApiException exception = assertThrows(ApiException.class, () -> {
	            service.searchMeals(null);
	        });
	        
	        assertEquals(ApiException.ErrorType.INVALID_INPUT, exception.getErrorType());
	    }
	    
	    @Test
	    void testGetMealById_ValidId() throws ApiException {
	        // Using a valid meal ID (Teriyaki Chicken)
	        Meal meal = service.getMealById("52772");
	        
	        assertNotNull(meal);
	        assertEquals("52772", meal.getId());
	        assertNotNull(meal.getName());
	        assertNotNull(meal.getInstructions());
	        assertFalse(meal.getIngredientsList().isEmpty());
	    }
	    
	    @Test
	    void testGetMealById_InvalidId() {
	        // Invalid ID should throw NOT_FOUND exception
	        ApiException exception = assertThrows(ApiException.class, () -> {
	            service.getMealById("999999");
	        });
	        
	        assertEquals(ApiException.ErrorType.NOT_FOUND, exception.getErrorType());
	    }
	    
	    @Test
	    void testGetMealById_EmptyId() {
	        // Empty ID should throw exception
	        ApiException exception = assertThrows(ApiException.class, () -> {
	            service.getMealById("");
	        });
	        
	        assertEquals(ApiException.ErrorType.INVALID_INPUT, exception.getErrorType());
	    }
	    
	    @Test
	    void testGetRandomMeal() throws ApiException {
	        // Get random meal
	        Meal meal = service.getRandomMeal();
	        
	        assertNotNull(meal);
	        assertNotNull(meal.getId());
	        assertNotNull(meal.getName());
	        assertNotNull(meal.getCategory());
	        assertNotNull(meal.getArea());
	        assertNotNull(meal.getInstructions());
	        assertFalse(meal.getIngredientsList().isEmpty());
	    }
}
