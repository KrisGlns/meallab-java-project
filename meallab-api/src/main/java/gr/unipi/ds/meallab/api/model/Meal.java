package gr.unipi.ds.meallab.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meal {

	// Properties
	@JsonProperty("idMeal")
    private String id;
    
    @JsonProperty("strMeal")
    private String name;
    
    @JsonProperty("strCategory")
    private String category;
    
    @JsonProperty("strArea")
    private String area;
    
    @JsonProperty("strInstructions")
    private String instructions;
    
    @JsonProperty("strMealThumb")
    private String thumbnailUrl;
    
    @JsonProperty("strTags")
    private String tags;
    
    @JsonProperty("strIngredient1")
    private String ingredient1;
    @JsonProperty("strIngredient2")
    private String ingredient2;
    @JsonProperty("strIngredient3")
    private String ingredient3;
    @JsonProperty("strIngredient4")
    private String ingredient4;
    @JsonProperty("strIngredient5")
    private String ingredient5;
    @JsonProperty("strIngredient6")
    private String ingredient6;
    @JsonProperty("strIngredient7")
    private String ingredient7;
    @JsonProperty("strIngredient8")
    private String ingredient8;
    @JsonProperty("strIngredient9")
    private String ingredient9;
    @JsonProperty("strIngredient10")
    private String ingredient10;
    @JsonProperty("strIngredient11")
    private String ingredient11;
    @JsonProperty("strIngredient12")
    private String ingredient12;
    @JsonProperty("strIngredient13")
    private String ingredient13;
    @JsonProperty("strIngredient14")
    private String ingredient14;
    @JsonProperty("strIngredient15")
    private String ingredient15;
    @JsonProperty("strIngredient16")
    private String ingredient16;
    @JsonProperty("strIngredient17")
    private String ingredient17;
    @JsonProperty("strIngredient18")
    private String ingredient18;
    @JsonProperty("strIngredient19")
    private String ingredient19;
    @JsonProperty("strIngredient20")
    private String ingredient20;
    
    @JsonProperty("strMeasure1")
    private String measure1;
    @JsonProperty("strMeasure2")
    private String measure2;
    @JsonProperty("strMeasure3")
    private String measure3;
    @JsonProperty("strMeasure4")
    private String measure4;
    @JsonProperty("strMeasure5")
    private String measure5;
    @JsonProperty("strMeasure6")
    private String measure6;
    @JsonProperty("strMeasure7")
    private String measure7;
    @JsonProperty("strMeasure8")
    private String measure8;
    @JsonProperty("strMeasure9")
    private String measure9;
    @JsonProperty("strMeasure10")
    private String measure10;
    @JsonProperty("strMeasure11")
    private String measure11;
    @JsonProperty("strMeasure12")
    private String measure12;
    @JsonProperty("strMeasure13")
    private String measure13;
    @JsonProperty("strMeasure14")
    private String measure14;
    @JsonProperty("strMeasure15")
    private String measure15;
    @JsonProperty("strMeasure16")
    private String measure16;
    @JsonProperty("strMeasure17")
    private String measure17;
    @JsonProperty("strMeasure18")
    private String measure18;
    @JsonProperty("strMeasure19")
    private String measure19;
    @JsonProperty("strMeasure20")
    private String measure20;
    
    // Default constructor for Jackson
    public Meal() {
    }
    
    // Getters and Setters
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
    
    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    // Helper method to get all ingredients as a list
//    public List<String> getIngredients() {
//        List<String> ingredients = new ArrayList<>();
//        addStringToList(ingredients, ingredient1);
//        addStringToList(ingredients, ingredient2);
//        addStringToList(ingredients, ingredient3);
//        addStringToList(ingredients, ingredient4);
//        addStringToList(ingredients, ingredient5);
//        addStringToList(ingredients, ingredient6);
//        addStringToList(ingredients, ingredient7);
//        addStringToList(ingredients, ingredient8);
//        addStringToList(ingredients, ingredient9);
//        addStringToList(ingredients, ingredient10);
//        addStringToList(ingredients, ingredient11);
//        addStringToList(ingredients, ingredient12);
//        addStringToList(ingredients, ingredient13);
//        addStringToList(ingredients, ingredient14);
//        addStringToList(ingredients, ingredient15);
//        addStringToList(ingredients, ingredient16);
//        addStringToList(ingredients, ingredient17);
//        addStringToList(ingredients, ingredient18);
//        addStringToList(ingredients, ingredient19);
//        addStringToList(ingredients, ingredient20);
//        return ingredients;
//    }
    
    // Helper method to get all measures as a list
//    public List<String> getMeasures() {
//        List<String> measures = new ArrayList<>();
//        addStringToList(measures, measure1);
//        addStringToList(measures, measure2);
//        addStringToList(measures, measure3);
//        addStringToList(measures, measure4);
//        addStringToList(measures, measure5);
//        addStringToList(measures, measure6);
//        addStringToList(measures, measure7);
//        addStringToList(measures, measure8);
//        addStringToList(measures, measure9);
//        addStringToList(measures, measure10);
//        addStringToList(measures, measure11);
//        addStringToList(measures, measure12);
//        addStringToList(measures, measure13);
//        addStringToList(measures, measure14);
//        addStringToList(measures, measure15);
//        addStringToList(measures, measure16);
//        addStringToList(measures, measure17);
//        addStringToList(measures, measure18);
//        addStringToList(measures, measure19);
//        addStringToList(measures, measure20);
//        return measures;
//    }
    
    // Helper method to get ingredients with measures
//    public List<String> getIngredientsWithMeasures() {
//    	List<String> result = new ArrayList<>();
//        List<String> ingredients = getIngredients();
//        List<String> measures = getMeasures();
//        
//        for (int i = 0; i < ingredients.size(); i++) {
//            String measure = i < measures.size() ? measures.get(i) : "";
//            result.add(measure + " " + ingredients.get(i));
//        }
//        return result;
//    }
    
    // Add string to list only if not null or empty
//    private void addStringToList(List<String> list, String value) {
//        if (value != null && !value.trim().isEmpty()) {
//            list.add(value.trim());
//        }
//    }
    
    public List<Ingredient> getIngredientsList() {
        List<Ingredient> ingredients = new ArrayList<>();
        
        addIngredient(ingredients, ingredient1, measure1);
        addIngredient(ingredients, ingredient2, measure2);
        addIngredient(ingredients, ingredient3, measure3);
        addIngredient(ingredients, ingredient4, measure4);
        addIngredient(ingredients, ingredient5, measure5);
        addIngredient(ingredients, ingredient6, measure6);
        addIngredient(ingredients, ingredient7, measure7);
        addIngredient(ingredients, ingredient8, measure8);
        addIngredient(ingredients, ingredient9, measure9);
        addIngredient(ingredients, ingredient10, measure10);
        addIngredient(ingredients, ingredient11, measure11);
        addIngredient(ingredients, ingredient12, measure12);
        addIngredient(ingredients, ingredient13, measure13);
        addIngredient(ingredients, ingredient14, measure14);
        addIngredient(ingredients, ingredient15, measure15);
        addIngredient(ingredients, ingredient16, measure16);
        addIngredient(ingredients, ingredient17, measure17);
        addIngredient(ingredients, ingredient18, measure18);
        addIngredient(ingredients, ingredient19, measure19);
        addIngredient(ingredients, ingredient20, measure20);
        
        return ingredients;
    }
    
    // Helper method to add ingredient if not empty
    private void addIngredient(List<Ingredient> list, String name, String measure) {
        if (name != null && !name.trim().isEmpty()) {
        	list.add(new Ingredient(name.trim(), measure != null ? measure.trim() : ""));
        }
    }
    
    @Override
    public String toString() {
        return "Meal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", instructions=" + (instructions != null ? instructions.length() + " chars" : "none") +
                ", ingredients=" + getIngredientsList().size() + " items" +
                ", tags='" + tags + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
