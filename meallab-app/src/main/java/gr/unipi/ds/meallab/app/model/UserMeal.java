package gr.unipi.ds.meallab.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMeal {

	// Represents a meal saved by the user
	
	// Properties
	@JsonProperty("mealId")
    private String mealId;
    
    @JsonProperty("mealName")
    private String mealName;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("area")
    private String area;
    
    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;
    
    @JsonProperty("isFavorite")
    private boolean isFavorite;
    
    @JsonProperty("isCooked")
    private boolean isCooked;
    
    // Default constructor
    public UserMeal() {
    }
    
    // Constructor from SimpleMeal
    public UserMeal(String mealId, String mealName, String category, String area, String thumbnailUrl) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.category = category;
        this.area = area;
        this.thumbnailUrl = thumbnailUrl;
        this.isFavorite = false;
        this.isCooked = false;
    }
    
 // Getters and setters
    public String getMealId() {
        return mealId;
    }
    
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    
    public String getMealName() {
        return mealName;
    }
    
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public boolean isFavorite() {
        return isFavorite;
    }
    
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    public boolean isCooked() {
        return isCooked;
    }
    
    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }
    
    // Display format for ListView - not serialized to JSON
    @JsonIgnore
    public String getDisplayText() {
        return mealName + " (" + area + ")";
    }
    
    @Override
    public String toString() {
        return getDisplayText();
    }
    
    // We are overriding equals() because of this line in private void addToFavorites(Meal meal)
    //if (!favoriteMeals.contains(userMeal))
    // Default equals() checks if two objects are the same object in memory
    // And keeping the base equal method would result in adding the same meal (same name, same id, etc...) to the favorites/cooked multiple times
    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        UserMeal userMeal = (UserMeal) o;
        return mealId != null && mealId.equals(userMeal.mealId);
    }
    
    // We override this method because If you override equals(), you must override hashCode().
    // it's a Java best practice and prevents bugs if we use the class in a HashSet or HashMap.
    @Override
    public int hashCode() {
        return mealId != null ? mealId.hashCode() : 0;
    }
}
