package gr.unipi.ds.meallab.api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

	@Test
    void testIngredientCreation() {
        // Test constructor
        Ingredient ingredient = new Ingredient("Flour", "2 cups");
        
        assertEquals("Flour", ingredient.getName());
        assertEquals("2 cups", ingredient.getMeasure());
    }
	
	@Test
    void testGetIngredientWithMeasure() {
        // Test with both name and measure
        Ingredient ingredient1 = new Ingredient("Sugar", "1 tablespoon");
        assertEquals("1 tablespoon Sugar", ingredient1.getIngredientWithMeasure());
        
        // Test with only name (empty measure)
        Ingredient ingredient2 = new Ingredient("Salt", "");
        assertEquals("Salt", ingredient2.getIngredientWithMeasure());
        
        // Test with null measure
        Ingredient ingredient3 = new Ingredient("Pepper", null);
        assertEquals("Pepper", ingredient3.getIngredientWithMeasure());
    }
}
