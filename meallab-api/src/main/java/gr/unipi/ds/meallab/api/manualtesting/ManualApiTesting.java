package gr.unipi.ds.meallab.api.manualtesting;

import gr.unipi.ds.meallab.api.model.Meal;
import gr.unipi.ds.meallab.api.model.SimpleMeal;
import gr.unipi.ds.meallab.api.service.MealService;
import gr.unipi.ds.meallab.exception.ApiException;

import java.util.List;

public class ManualApiTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MealService sut = new MealService();
		
		try {
			// Test 1: Search for meals with "chicken"
            System.out.println("=== Searching for 'chicken' ===");
            List<SimpleMeal> meals = sut.searchMeals("chicken");
            System.out.println("Found " + meals.size() + " meals");
            
            // Print all meals
            if (!meals.isEmpty()) {
//                Meal firstMeal = meals.get(0);
//                System.out.println("First result: " + firstMeal.getName());
//                System.out.println("ID: " + firstMeal.getId());
            	for (int i = 0 ; i < meals.size() ; i ++) {
            		SimpleMeal meal = meals.get(i);
            		System.out.println((i + 1) + ". " + meal.toString());
            	}
            }
            
            // Test 2: Get details by ID
            System.out.println("\n=== Getting meal details ===");
            if (!meals.isEmpty()) {
//            	for (int i = 0 ; i < meals.size() ; i ++) {
//            		Meal meal = meals.get(i);
//            		System.out.println((i + 1) + ". " + meal.toString());
//            	}
                String mealId = meals.get(0).getId();
                Meal detailed = sut.getMealById(mealId);
                System.out.println("Full Meal object:  " + detailed);
                System.out.println("\nInstructions:  " + 
                        detailed.getInstructions());
                
                System.out.println("\nIngredients:");
                detailed.getIngredientsList().forEach(ing -> 
                    System.out.println("  - " + ing.getIngredientWithMeasure()));
//                System.out.println("Meal: " + detailed.getName());
//                System.out.println("Category: " + detailed.getCategory());
//                System.out.println("Area: " + detailed.getArea());
//                System.out.println("Ingredients:  " + detailed.getIngredientsList().size());
//                
//                // Print first X ingredients
//                detailed.getIngredientsList().stream()
//                        .limit(20)
//                        .forEach(ing -> System.out.println("  - " + ing.getIngredientWithMeasure()));
            }
            
            // Test 3: Random meal
            System.out.println("\n=== Random Meal ===");
            Meal random = sut.getRandomMeal();
            System.out.println("Random meal: " + random.toString());
            System.out.println("\nInstructions:  " + 
            		random.getInstructions());
            
            System.out.println("\nIngredients:");
            random.getIngredientsList().forEach(ing -> 
                System.out.println("  - " + ing.getIngredientWithMeasure()));
//            System.out.println("From: " + random.getArea());
		}
		catch (ApiException e) {
            System.err.println("API Error (" + e.getErrorType() + "): " + e.getMessage());
            e.printStackTrace();
        } finally {
            sut.Close();
        }
	}

}
