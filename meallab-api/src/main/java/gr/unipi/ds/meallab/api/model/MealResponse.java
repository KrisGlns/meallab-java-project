package gr.unipi.ds.meallab.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MealResponse {

	// Properties
	@JsonProperty("meals")
    private List<Meal> meals;
    
    // Default constructor
    public MealResponse() {
    }
    
    // Getters and Setters
    public List<Meal> getMeals() {
        return meals;
    }
    
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    
    @Override
    public String toString() {
        return "MealResponse{" +
                "meals=" + (meals != null ? meals.size() : 0) +
                '}';
    }
}
