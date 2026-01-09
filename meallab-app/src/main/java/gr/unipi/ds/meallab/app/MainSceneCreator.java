package gr.unipi.ds.meallab.app;

import gr.unipi.ds.meallab.api.model.Meal;
import gr.unipi.ds.meallab.api.model.SimpleMeal;
import gr.unipi.ds.meallab.api.service.MealService;
import gr.unipi.ds.meallab.app.model.UserMeal;
import gr.unipi.ds.meallab.app.service.StorageService;
import gr.unipi.ds.meallab.app.util.ImageLoader;
import gr.unipi.ds.meallab.exception.ApiException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{

	// Root layout
    BorderPane rootBorderPane;
    
    // Tab pane and tabs
    TabPane tabPane;
    Tab searchTab, randomTab, favoritesTab, cookedTab;
    
    // Search Tab Components
    TextField searchField;
    Button searchButton;
    Label searchResultLabel;
    ListView<SimpleMeal> searchResultsList;
    
    // Random Tab Components
    Button getRandomMealButton;
    VBox randomMealContainer;
    ScrollPane randomScrollPane;
    
    // Favorites Tab Components
    ListView<UserMeal> favoritesList;
    Label favoritesCountLabel;
    Button viewFavoriteDetailsBtn, moveToCookedFromFavoriteBtn, removeFavoriteBtn;
    
    // Cooked Tab Components
    ListView<UserMeal> cookedList;
    Label cookedCountLabel;
    Button viewCookedDetailsBtn, moveToFavoriteFromCookedBtn, removeCookedBtn;
    
    // Status bar
    Label statusLabel;
    
    // Services
    MealService mealService;
    StorageService storageService;
    
    // Observable lists
    ObservableList<UserMeal> favoriteMeals;
    ObservableList<UserMeal> cookedMeals;
    
    // Constructor 
    public MainSceneCreator(double width, double height) {
        super(width, height);
        
        // Initialize services
        mealService = new MealService();
        storageService = new StorageService();
        
        // Load saved data
        favoriteMeals = FXCollections.observableArrayList(storageService.loadFavorites());
        cookedMeals = FXCollections.observableArrayList(storageService.loadCooked());
        
        // Initialize components
        initializeComponents();
        
        // Setup layouts
        setupSearchTab();
        setupRandomTab();
        setupFavoritesTab();
        setupCookedTab();
        setupMainLayout();
        
        // Attach event handlers
        attachEventHandlers();
        
        // Initial status
        setStatus("Application loaded. Ready to search!");
    }
    
    private void initializeComponents() {
        rootBorderPane = new BorderPane();
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Search tab components
        searchField = new TextField();
        searchButton = new Button("Search");
        searchResultLabel = new Label("Enter a search term to find meals");
        searchResultsList = new ListView<>();
        
        // Random tab components
        getRandomMealButton = new Button("Get Random Meal");
        randomMealContainer = new VBox(10);
        randomScrollPane = new ScrollPane();
        
        // Favorites tab components
        favoritesList = new ListView<>();
        favoritesCountLabel = new Label("(0 meals)");
        viewFavoriteDetailsBtn = new Button("View Details");
        moveToCookedFromFavoriteBtn = new Button("Move to Cooked");
        removeFavoriteBtn = new Button("Remove");
        
        // Cooked tab components
        cookedList = new ListView<>();
        cookedCountLabel = new Label("(0 meals)");
        viewCookedDetailsBtn = new Button("View Details");
        moveToFavoriteFromCookedBtn = new Button("Move to Favorites");
        removeCookedBtn = new Button("Remove");
        
        // Status bar
        statusLabel = new Label("Ready");
    }
    
    private void setupSearchTab() {
        searchTab = new Tab("Search");
        
        VBox searchVBox = new VBox(10);
        searchVBox.setPadding(new Insets(15));
        
        // Search box
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchField.setPromptText("Enter ingredient or meal name (e.g., chicken, pasta)");
        searchField.setPrefWidth(400);
        searchButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        searchBox.getChildren().addAll(searchField, searchButton);
        
        // Results list
        searchResultsList.setPrefHeight(400);
        
        // Custom cell factory
        searchResultsList.setCellFactory(lv -> new ListCell<SimpleMeal>() {
            @Override
            protected void updateItem(SimpleMeal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - " + item.getCategory() + " (" + item.getArea() + ")");
                }
            }
        });
        
        searchVBox.getChildren().addAll(searchBox, searchResultLabel, searchResultsList);
        searchTab.setContent(searchVBox);
    }
    
    private void setupRandomTab() {
        randomTab = new Tab("Random");
        
        VBox randomVBox = new VBox(15);
        randomVBox.setPadding(new Insets(20));
        randomVBox.setAlignment(Pos.TOP_CENTER);
        
        Label titleLabel = new Label("Discover a Random Recipe!");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        getRandomMealButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        
        randomMealContainer.setAlignment(Pos.TOP_CENTER);
        randomMealContainer.setPadding(new Insets(10));
        
        randomScrollPane.setContent(randomMealContainer);
        randomScrollPane.setFitToWidth(true);
        randomScrollPane.setPrefHeight(450);
        
        randomVBox.getChildren().addAll(titleLabel, getRandomMealButton, randomScrollPane);
        randomTab.setContent(randomVBox);
    }
    
    private void setupFavoritesTab() {
        favoritesTab = new Tab("Favorites");
        
        VBox favoritesVBox = new VBox(10);
        favoritesVBox.setPadding(new Insets(15));
        
        // Header
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);
        Label titleLabel = new Label("Your Favorite Meals");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        header.getChildren().addAll(titleLabel, favoritesCountLabel);
        
        // List
        favoritesList.setItems(favoriteMeals);
        favoritesList.setPrefHeight(450);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        removeFavoriteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        buttonBox.getChildren().addAll(viewFavoriteDetailsBtn, moveToCookedFromFavoriteBtn, removeFavoriteBtn);
        
        favoritesVBox.getChildren().addAll(header, favoritesList, buttonBox);
        favoritesTab.setContent(favoritesVBox);
        
        updateFavoritesCount();
    }
    
    private void setupCookedTab() {
        cookedTab = new Tab("Cooked");
        
        VBox cookedVBox = new VBox(10);
        cookedVBox.setPadding(new Insets(15));
        
        // Header
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);
        Label titleLabel = new Label("Meals You've Cooked");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        header.getChildren().addAll(titleLabel, cookedCountLabel);
        
        // List
        cookedList.setItems(cookedMeals);
        cookedList.setPrefHeight(450);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        removeCookedBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        buttonBox.getChildren().addAll(viewCookedDetailsBtn, moveToFavoriteFromCookedBtn, removeCookedBtn);
        
        cookedVBox.getChildren().addAll(header, cookedList, buttonBox);
        cookedTab.setContent(cookedVBox);
        
        updateCookedCount();
    }
    
    private void setupMainLayout() {
        // Header
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20;");
        Label titleLabel = new Label("MealLab - Recipe Manager");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
        header.getChildren().add(titleLabel);
        
        // Add tabs to tab pane
        tabPane.getTabs().addAll(searchTab, randomTab, favoritesTab, cookedTab);
        
        // Status bar
        HBox statusBar = new HBox(20);
        statusBar.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 10;");
        statusLabel.setStyle("-fx-font-size: 12px;");
        statusBar.getChildren().add(statusLabel);
        
        // Set border pane sections
        rootBorderPane.setTop(header);
        rootBorderPane.setCenter(tabPane);
        rootBorderPane.setBottom(statusBar);
    }
    
    private void attachEventHandlers() {
        // Search tab
        searchButton.setOnMouseClicked(this);
        searchField.setOnAction(e -> handleSearch());
        searchResultsList.setOnMouseClicked(this);
        
        // Random tab
        getRandomMealButton.setOnMouseClicked(this);
        
        // Favorites tab
        viewFavoriteDetailsBtn.setOnMouseClicked(this);
        moveToCookedFromFavoriteBtn.setOnMouseClicked(this);
        removeFavoriteBtn.setOnMouseClicked(this);
        favoritesList.setOnMouseClicked(this);
        
        // Cooked tab
        viewCookedDetailsBtn.setOnMouseClicked(this);
        moveToFavoriteFromCookedBtn.setOnMouseClicked(this);
        removeCookedBtn.setOnMouseClicked(this);
        cookedList.setOnMouseClicked(this);
    }
    
    @Override
    Scene createScene() {
        return new Scene(rootBorderPane, width, height);
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() == searchButton) {
            handleSearch();
        } else if (event.getSource() == searchResultsList) {
            if (event.getClickCount() == 2) {
                handleSearchResultClick();
            }
        } else if (event.getSource() == getRandomMealButton) {
            handleGetRandomMeal();
        } else if (event.getSource() == viewFavoriteDetailsBtn) {
            handleViewFavoriteDetails();
        } else if (event.getSource() == moveToCookedFromFavoriteBtn) {
            handleMoveToCookedFromFavorite();
        } else if (event.getSource() == removeFavoriteBtn) {
            handleRemoveFavorite();
        } else if (event.getSource() == favoritesList) {
            if (event.getClickCount() == 2) {
                handleViewFavoriteDetails();
            }
        } else if (event.getSource() == viewCookedDetailsBtn) {
            handleViewCookedDetails();
        } else if (event.getSource() == moveToFavoriteFromCookedBtn) {
            handleMoveToFavoriteFromCooked();
        } else if (event.getSource() == removeCookedBtn) {
            handleRemoveCooked();
        } else if (event.getSource() == cookedList) {
            if (event.getClickCount() == 2) {
                handleViewCookedDetails();
            }
        }
    }
    
    // Event handler methods
    
    private void handleSearch() {
        String query = searchField.getText().trim();
        
        if (query.isEmpty()) {
            showAlert("Input Required", "Please enter a search term", Alert.AlertType.WARNING);
            return;
        }
        
        setStatus("Searching for: " + query + "...");
        
        new Thread(() -> {
            try {
                List<SimpleMeal> results = mealService.searchMeals(query);
                
                Platform.runLater(() -> {
                    searchResultsList.setItems(FXCollections.observableArrayList(results));
                    searchResultLabel.setText("Found " + results.size() + " meals");
                    setStatus("Search completed:  " + results.size() + " results");
                });
                
            } catch (ApiException e) {
                Platform.runLater(() -> {
                    showAlert("Search Error", "Failed to search meals: " + e.getMessage(), 
                            Alert.AlertType.ERROR);
                    setStatus("Search failed");
                });
            }
        }).start();
    }
    
    private void handleSearchResultClick() {
        SimpleMeal selected = searchResultsList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showMealDetailsDialog(selected.getId());
        }
    }
    
    private void handleGetRandomMeal() {
        setStatus("Fetching random meal...");
        randomMealContainer.getChildren().clear();
        
        new Thread(() -> {
            try {
                Meal meal = mealService.getRandomMeal();
                
                Platform.runLater(() -> {
                    displayRandomMeal(meal);
                    setStatus("Random meal loaded: " + meal.getName());
                });
                
            } catch (ApiException e) {
                Platform.runLater(() -> {
                    showAlert("Error", "Failed to fetch random meal: " + e.getMessage(), 
                            Alert.AlertType.ERROR);
                    setStatus("Failed to load random meal");
                });
            }
        }).start();
    }
    
    private void displayRandomMeal(Meal meal) {
        randomMealContainer.getChildren().clear();
        
        Label nameLabel = new Label(meal.getName());
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        Label infoLabel = new Label(meal.getCategory() + " | " + meal.getArea());
        infoLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
        
        ImageView imageView = new ImageView(ImageLoader.loadImage(meal.getThumbnailUrl(), 300, 300));
        
        Label ingredientsTitle = new Label("Ingredients:");
        ingredientsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        VBox ingredientsBox = new VBox(5);
        meal.getIngredientsList().forEach(ing -> {
            Label ingredientLabel = new Label("• " + ing.getIngredientWithMeasure());
            ingredientsBox.getChildren().add(ingredientLabel);
        });
        
        Label instructionsTitle = new Label("Instructions:");
        instructionsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 0 0 0;");
        
        Text instructionsText = new Text(meal.getInstructions());
        instructionsText.setWrappingWidth(500);
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        
        Button addToFavoritesBtn = new Button("Add to Favorites");
        addToFavoritesBtn.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        addToFavoritesBtn.setOnAction(e -> addToFavorites(meal));
        
        Button markAsCookedBtn = new Button("Mark as Cooked");
        markAsCookedBtn.setStyle("-fx-background-color:  #27ae60; -fx-text-fill: white;");
        markAsCookedBtn.setOnAction(e -> addToCooked(meal));
        
        buttonBox.getChildren().addAll(addToFavoritesBtn, markAsCookedBtn);
        
        randomMealContainer.getChildren().addAll(
            nameLabel, infoLabel, imageView, 
            ingredientsTitle, ingredientsBox,
            instructionsTitle, instructionsText,
            buttonBox
        );
    }
    
    private void showMealDetailsDialog(String mealId) {
        setStatus("Loading meal details...");
        
        new Thread(() -> {
            try {
                Meal meal = mealService.getMealById(mealId);
                
                Platform.runLater(() -> {
                    Dialog<Void> dialog = new Dialog<>();
                    dialog.setTitle(meal.getName());
                    dialog.setHeaderText(meal.getCategory() + " - " + meal.getArea());
                    
                    VBox content = new VBox(10);
                    content.setPrefWidth(600);
                    
                    ImageView imageView = new ImageView(ImageLoader.loadImage(meal.getThumbnailUrl(), 250, 250));
                    
                    Label ingredientsLabel = new Label("Ingredients:");
                    ingredientsLabel.setStyle("-fx-font-weight: bold;");
                    VBox ingredientsBox = new VBox(3);
                    meal.getIngredientsList().forEach(ing -> 
                        ingredientsBox.getChildren().add(new Label("• " + ing.getIngredientWithMeasure())));
                    
                    Label instructionsLabel = new Label("Instructions:");
                    instructionsLabel.setStyle("-fx-font-weight: bold;");
                    Text instructionsText = new Text(meal.getInstructions());
                    instructionsText.setWrappingWidth(550);
                    
                    ScrollPane scrollPane = new ScrollPane();
                    VBox scrollContent = new VBox(10);
                    scrollContent.getChildren().addAll(
                        imageView, ingredientsLabel, ingredientsBox, 
                        instructionsLabel, instructionsText
                    );
                    scrollPane.setContent(scrollContent);
                    scrollPane.setPrefHeight(400);
                    scrollPane.setFitToWidth(true);
                    
                    content.getChildren().add(scrollPane);
                    
                    HBox buttons = new HBox(10);
                    Button favBtn = new Button("Add to Favorites");
                    favBtn.setOnAction(e -> {
                        addToFavorites(meal);
                        dialog.close();
                    });
                    
                    Button cookedBtn = new Button("Mark as Cooked");
                    cookedBtn.setOnAction(e -> {
                        addToCooked(meal);
                        dialog.close();
                    });
                    
                    buttons.getChildren().addAll(favBtn, cookedBtn);
                    content.getChildren().add(buttons);
                    
                    dialog.getDialogPane().setContent(content);
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                    
                    dialog.showAndWait();
                    setStatus("Ready");
                });
                
            } catch (ApiException e) {
                Platform.runLater(() -> {
                    showAlert("Error", "Failed to load meal details: " + e.getMessage(), 
                            Alert.AlertType.ERROR);
                    setStatus("Failed to load details");
                });
            }
        }).start();
    }
    
    private void addToFavorites(Meal meal) {
        UserMeal userMeal = new UserMeal(meal.getId(), meal.getName(), 
            meal.getCategory(), meal.getArea(), meal.getThumbnailUrl());
        
        if (! favoriteMeals.contains(userMeal)) {
            favoriteMeals.add(userMeal);
            saveFavorites();
            updateFavoritesCount();
            setStatus("Added to favorites:  " + meal.getName());
            showInfo("Added to Favorites", meal.getName() + " has been added to your favorites!");
        } else {
            showInfo("Already in Favorites", meal.getName() + " is already in your favorites.");
        }
    }
    
    private void addToCooked(Meal meal) {
        UserMeal userMeal = new UserMeal(meal.getId(), meal.getName(), 
            meal.getCategory(), meal.getArea(), meal.getThumbnailUrl());
        
        if (!cookedMeals.contains(userMeal)) {
            cookedMeals.add(userMeal);
            saveCooked();
            updateCookedCount();
            setStatus("Marked as cooked: " + meal.getName());
            showInfo("Marked as Cooked", meal.getName() + " has been marked as cooked!");
        } else {
            showInfo("Already Cooked", meal.getName() + " is already in your cooked list.");
        }
    }
    
    private void handleViewFavoriteDetails() {
        UserMeal selected = favoritesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showMealDetailsDialog(selected.getMealId());
        } else {
            showAlert("No Selection", "Please select a meal to view details", Alert.AlertType.WARNING);
        }
    }
    
    private void handleMoveToCookedFromFavorite() {
        UserMeal selected = favoritesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (! cookedMeals.contains(selected)) {
                cookedMeals.add(selected);
                saveCooked();
                updateCookedCount();
            }
            favoriteMeals.remove(selected);
            saveFavorites();
            updateFavoritesCount();
            setStatus("Moved to cooked: " + selected.getMealName());
        }
    }
    
    private void handleRemoveFavorite() {
        UserMeal selected = favoritesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            favoriteMeals.remove(selected);
            saveFavorites();
            updateFavoritesCount();
            setStatus("Removed from favorites: " + selected.getMealName());
        }
    }
    
    private void handleViewCookedDetails() {
        UserMeal selected = cookedList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showMealDetailsDialog(selected.getMealId());
        } else {
            showAlert("No Selection", "Please select a meal to view details", Alert.AlertType.WARNING);
        }
    }
    
    private void handleMoveToFavoriteFromCooked() {
        UserMeal selected = cookedList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!favoriteMeals.contains(selected)) {
                favoriteMeals.add(selected);
                saveFavorites();
                updateFavoritesCount();
            }
            cookedMeals.remove(selected);
            saveCooked();
            updateCookedCount();
            setStatus("Moved to favorites: " + selected.getMealName());
        }
    }
    
    private void handleRemoveCooked() {
        UserMeal selected = cookedList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cookedMeals.remove(selected);
            saveCooked();
            updateCookedCount();
            setStatus("Removed from cooked: " + selected.getMealName());
        }
    }
    
    // Helper methods
    
    private void saveFavorites() {
        try {
            storageService.saveFavorites(favoriteMeals);
        } catch (Exception e) {
            showAlert("Save Error", "Failed to save favorites: " + e.getMessage(), 
                    Alert.AlertType.ERROR);
        }
    }
    
    private void saveCooked() {
        try {
            storageService.saveCooked(cookedMeals);
        } catch (Exception e) {
            showAlert("Save Error", "Failed to save cooked meals:  " + e.getMessage(), 
                    Alert.AlertType.ERROR);
        }
    }
    
    private void updateFavoritesCount() {
        favoritesCountLabel.setText("(" + favoriteMeals.size() + " meals)");
    }
    
    private void updateCookedCount() {
        cookedCountLabel.setText("(" + cookedMeals.size() + " meals)");
    }
    
    private void setStatus(String message) {
        statusLabel.setText(message);
    }
    
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void showInfo(String title, String content) {
        showAlert(title, content, Alert.AlertType.INFORMATION);
    }
    
    public void shutdown() {
        if (mealService != null) {
            mealService.Close();
        }
    }
}
