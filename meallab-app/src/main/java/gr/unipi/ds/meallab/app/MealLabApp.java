package gr.unipi.ds.meallab.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MealLabApp extends Application {

	// Stage
    static Stage primaryStage;
    
    // Main Scene
    static Scene mainScene;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        
        // Create main scene
        SceneCreator mainSceneCreator = new MainSceneCreator(900, 600);
        mainScene = mainSceneCreator.createScene();
        
        // Setup and show stage
        primaryStage.setTitle("MealLab - Recipe Manager");
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
