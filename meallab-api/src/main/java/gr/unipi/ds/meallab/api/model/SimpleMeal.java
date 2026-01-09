package gr.unipi.ds.meallab.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Lightweight meal object for search results
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleMeal {

	// Properties
	@JsonProperty("idMeal")
    private String id;
    
    @JsonProperty("strMeal")
    private String name;
    
    @JsonProperty("strCategory")
    private String category;
    
    @JsonProperty("strArea")
    private String area;
    
    @JsonProperty("strMealThumb")
    private String thumbnailUrl;
    
    // Default constructor
    public SimpleMeal() {
    }
    
    // Constructor with all fields
    public SimpleMeal(String id, String name, String category, String area, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.thumbnailUrl = thumbnailUrl;
    }
    
    // Getters Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        return "SimpleMeal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
