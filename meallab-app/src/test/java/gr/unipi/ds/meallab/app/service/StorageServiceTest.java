package gr.unipi.ds.meallab.app.service;

import gr.unipi.ds.meallab.app.model.UserMeal;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {

	private StorageService storageService;
	private static final String TEST_DATA_DIR = "meallab-data-test";
    private static final String TEST_FAVORITES_FILE = TEST_DATA_DIR + "/favorites.json";
    private static final String TEST_COOKED_FILE = TEST_DATA_DIR + "/cooked.json";
    
    @BeforeEach
    void setUp() {
    	// Clean up test files before each test
        cleanupTestFiles();
        
    	// Use test directory instead of production directory
        storageService = new StorageService(TEST_DATA_DIR);   
    }
    
    @AfterEach
    void tearDown() {
        // Clean up test files after each test
        cleanupTestFiles();
    }
    
    private void cleanupTestFiles() {
        File favFile = new File(TEST_FAVORITES_FILE);
        File cookedFile = new File(TEST_COOKED_FILE);
        File testDir = new File(TEST_DATA_DIR);
        
        if (favFile.exists()) favFile.delete();
        if (cookedFile.exists()) cookedFile.delete();
        if (testDir.exists()) testDir.delete();
    }
    
    @Test
    void testSaveAndLoadFavorites() throws IOException {
        // Create test data
        List<UserMeal> favorites = new ArrayList<>();
        favorites.add(new UserMeal("123", "Test Meal 1", "Chicken", "Indian", "http://example.com/1.jpg"));
        favorites.add(new UserMeal("456", "Test Meal 2", "Beef", "American", "http://example.com/2.jpg"));
        
        // Save
        storageService.saveFavorites(favorites);
        
        // Load
        List<UserMeal> loaded = storageService.loadFavorites();
        
        // Verify
        assertNotNull(loaded);
        assertEquals(2, loaded.size());
        assertEquals("Test Meal 1", loaded.get(0).getMealName());
        assertEquals("Test Meal 2", loaded.get(1).getMealName());
    }
    
    @Test
    void testLoadFavorites_NoFile() {
        // Load when no file exists
        List<UserMeal> loaded = storageService.loadFavorites();
        
        // Should return empty list, not null
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }
    
    @Test
    void testLoadCooked_NoFile() {
        // Load when no file exists
        List<UserMeal> loaded = storageService.loadCooked();
        
        // Should return empty list, not null
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }
    
    @Test
    void testUserMealFields() throws IOException {
        // Create meal with all fields
    	UserMeal meal = new UserMeal("999", "Complete Meal", "Dessert", "French", "http://example.com/dessert.jpg");
        meal.setFavorite(true);
        meal.setCooked(false);
        
        List<UserMeal> meals = new ArrayList<>();
        meals.add(meal);
        
        // Save and load
        storageService.saveFavorites(meals);
        List<UserMeal> loaded = storageService.loadFavorites();
        
        // Verify all fields
        UserMeal loadedMeal = loaded.get(0);
        assertEquals("999", loadedMeal.getMealId());
        assertEquals("Complete Meal", loadedMeal.getMealName());
        assertEquals("Dessert", loadedMeal.getCategory());
        assertEquals("French", loadedMeal.getArea());
        assertEquals("http://example.com/dessert.jpg", loadedMeal.getThumbnailUrl());
    }
}
