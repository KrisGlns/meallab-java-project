package gr.unipi.ds.meallab.api.model;

public class Ingredient {

	private String name;
    private String measure;
    
    // Constructor
    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

	// Setters and Getters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMeasure() {
        return measure;
    }
    
    public void setMeasure(String measure) {
        this.measure = measure;
    }
    
    // Returns formatted string including ingredient name and measure
    public String getIngredientWithMeasure() {
        if (measure != null && !measure.trim().isEmpty()) {
            return measure + " " + name;
        }
        return name;
    }
    
    @Override
    public String toString() {
        return getIngredientWithMeasure();
    }
}
