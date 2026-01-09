package gr.unipi.ds.meallab.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.unipi.ds.meallab.app.model.UserMeal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Handles saving and loading user meal lists
public class StorageService {

	private static final String DEFAULT_DATA_DIR = "meallab-data";
	private final String dataDir;
	private final String favoritesFile;
    private final String cookedFile;
    
    private final ObjectMapper objectMapper;
    
    public StorageService() {
    	this(DEFAULT_DATA_DIR);
    }
    
    // Constructor with custom directory - for testing
    public StorageService(String dataDir) {
        this. dataDir = dataDir;
        this.favoritesFile = dataDir + "/favorites.json";
        this.cookedFile = dataDir + "/cooked.json";
        this.objectMapper = new ObjectMapper();
        ensureDataDirectoryExists();
    }
    
    // Create data directory if it doesn't exist
    private void ensureDataDirectoryExists() {
        File dir = new File(dataDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    // Save favorites list
    public void saveFavorites(List<UserMeal> favorites) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(favoritesFile), favorites);
    }
    
    // Load favorites list
    public List<UserMeal> loadFavorites() {
        try {
            File file = new File(favoritesFile);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            
            return objectMapper.readValue(file, new TypeReference<List<UserMeal>>() {});
            
        } catch (IOException e) {
            System.err.println("Error loading favorites: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // Save cooked list
    public void saveCooked(List<UserMeal> cooked) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(cookedFile), cooked);
    }
    
    // Load cooked list
    public List<UserMeal> loadCooked() {
        try {
            File file = new File(cookedFile);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            
            return objectMapper.readValue(file, new TypeReference<List<UserMeal>>() {});
            
        } catch (IOException e) {
            System.err.println("Error loading cooked meals: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
