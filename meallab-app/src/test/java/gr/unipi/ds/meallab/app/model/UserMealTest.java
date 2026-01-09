package gr.unipi.ds.meallab.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserMealTest {

	@Test
    void testUserMealCreation() {
        UserMeal meal = new UserMeal("123", "Chicken Curry", "Chicken", "Indian", "http://example.com/image.jpg");
        
        assertEquals("123", meal.getMealId());
        assertEquals("Chicken Curry", meal.getMealName());
        assertEquals("Chicken", meal.getCategory());
        assertEquals("Indian", meal.getArea());
        assertEquals("http://example.com/image.jpg", meal.getThumbnailUrl());
        assertFalse(meal.isFavorite());
        assertFalse(meal.isCooked());
    }
	
	@Test
    void testToString() {
        UserMeal meal = new UserMeal("456", "Beef Tacos", "Beef", "Mexican", "http://example.com/tacos.jpg");
        
        String result = meal.toString();
        
        assertEquals("Beef Tacos (Mexican)", result);
    }
    
    @Test
    void testEquals_SameMealId() {
        UserMeal meal1 = new UserMeal("123", "Meal A", "Cat1", "Area1", "url1");
        UserMeal meal2 = new UserMeal("123", "Meal B", "Cat2", "Area2", "url2");
        
        // Same mealId = equal, regardless of other fields
        assertTrue(meal1.equals(meal2));
    }
    
    @Test
    void testEquals_DifferentMealId() {
        UserMeal meal1 = new UserMeal("123", "Meal A", "Cat1", "Area1", "url1");
        UserMeal meal2 = new UserMeal("456", "Meal A", "Cat1", "Area1", "url1");
        
        // Different mealId = not equal
        assertFalse(meal1.equals(meal2));
    }
    
    @Test
    void testEquals_SameObject() {
        UserMeal meal = new UserMeal("123", "Meal A", "Cat1", "Area1", "url1");
        
        assertTrue(meal.equals(meal));
    }
    
    @Test
    void testHashCode_SameMealId() {
        UserMeal meal1 = new UserMeal("123", "Meal A", "Cat1", "Area1", "url1");
        UserMeal meal2 = new UserMeal("123", "Meal B", "Cat2", "Area2", "url2");
        
        // Same mealId = same hashCode
        assertEquals(meal1.hashCode(), meal2.hashCode());
    }
}
