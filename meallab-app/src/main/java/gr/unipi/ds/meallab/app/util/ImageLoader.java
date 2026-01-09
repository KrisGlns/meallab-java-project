package gr.unipi.ds.meallab.app.util;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//Loads and caches meal images
public class ImageLoader {

	private static final Map<String, Image> imageCache = new HashMap<>();
    private static final Image DEFAULT_IMAGE = createDefaultImage();
    
    // Create a simple default image (placeholder)
    private static Image createDefaultImage() {
        return null;
    }
    
    // Load image from URL with caching
    public static Image loadImage(String imageUrl, double width, double height) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return DEFAULT_IMAGE;
        }
        
        // Check cache first
        String cacheKey = imageUrl + "_" + width + "_" + height;
        if (imageCache.containsKey(cacheKey)) {
            return imageCache.get(cacheKey);
        }
        
        try {
            // Load image from URL
            Image image = new Image(imageUrl, width, height, true, true, true);
            
            // Cache it
            imageCache.put(cacheKey, image);
            
            return image;
            
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imageUrl);
            return DEFAULT_IMAGE;
        }
    }
    
    // Method overloading
    // Load image with default size
    public static Image loadImage(String imageUrl) {
        return loadImage(imageUrl, 200, 200); // Delegates to the method above
    }
    
    // Clear cache
    public static void clearCache() {
        imageCache.clear();
    }
}
