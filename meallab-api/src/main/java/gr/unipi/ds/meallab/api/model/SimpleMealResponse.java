package gr.unipi.ds.meallab.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//Wrapper for simplified search results
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMealResponse {

	@JsonProperty("meals")
    private List<SimpleMeal> meals;
    
    public SimpleMealResponse() {
    }
    
    public List<SimpleMeal> getMeals() {
        return meals;
    }
    
    public void setMeals(List<SimpleMeal> meals) {
        this.meals = meals;
    }
    
    @Override
    public String toString() {
        return "SimpleMealResponse{" +
                "meals=" + (meals != null ? meals.size() : 0) +
                '}';
    }
}
