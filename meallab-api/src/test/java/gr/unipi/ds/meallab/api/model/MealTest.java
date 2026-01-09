package gr.unipi.ds.meallab.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MealTest {

	@Test
    void testMealDeserialization() throws Exception {
        // Sample JSON from API
        String json = """
                {
                    "idMeal": "52772",
                    "strMeal": "Teriyaki Chicken Casserole",
                    "strCategory": "Chicken",
                    "strArea": "Japanese",
                    "strInstructions": "Preheat oven to 350 degrees.. .",
                    "strMealThumb": "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                    "strTags": "Meat,Casserole",
                    "strIngredient1": "soy sauce",
                    "strIngredient2": "water",
                    "strIngredient3": "brown sugar",
                    "strMeasure1": "3/4 cup",
                    "strMeasure2": "1/2 cup",
                    "strMeasure3": "1/4 cup"
                }
                """;
        
        ObjectMapper mapper = new ObjectMapper();
        Meal meal = mapper.readValue(json, Meal.class);
        
        // Verify basic fields
        assertEquals("52772", meal.getId());
        assertEquals("Teriyaki Chicken Casserole", meal.getName());
        assertEquals("Chicken", meal.getCategory());
        assertEquals("Japanese", meal.getArea());
        assertEquals("Meat,Casserole", meal. getTags());
        assertNotNull(meal.getInstructions());
        assertNotNull(meal.getThumbnailUrl());
    }
	
	@Test
    void testGetIngredientsList() throws Exception {
        // JSON with 3 ingredients
        String json = """
                {
                    "idMeal": "12345",
                    "strMeal": "Test Meal",
                    "strIngredient1": "Chicken",
                    "strIngredient2": "Rice",
                    "strIngredient3": "Onion",
                    "strIngredient4": "",
                    "strMeasure1": "500g",
                    "strMeasure2": "2 cups",
                    "strMeasure3": "1 large"
                }
                """;
        
        ObjectMapper mapper = new ObjectMapper();
        Meal meal = mapper.readValue(json, Meal.class);
        
        List<Ingredient> ingredients = meal. getIngredientsList();
        
        // Should have exactly 3 ingredients (empty ones filtered)
        assertEquals(3, ingredients. size());
        
        // Verify first ingredient
        assertEquals("Chicken", ingredients.get(0).getName());
        assertEquals("500g", ingredients.get(0).getMeasure());
        
        // Verify formatted output
        assertEquals("500g Chicken", ingredients.get(0).getIngredientWithMeasure());
    }
}
