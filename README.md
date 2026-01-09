# meallab-java-project
MSc Java Course Final Project - MealLab Recipe Management System using TheMealDB API

# 🍽️ MealLab - Recipe Manager

A Java-based recipe management application that integrates with TheMealDB API to search, discover, and manage your favorite meals.

## 📋 Project Overview

MealLab is a multi-module Maven project consisting of:
- **meallab-api**: Java library for interacting with TheMealDB REST API
- **meallab-app**: JavaFX desktop application for managing recipes

## ✨ Features

### MealLab API (Part A)
- 🔍 **Search meals** by ingredient or name
- 🎲 **Get random meal** suggestions
- 📖 **Retrieve detailed meal information** including ingredients and instructions
- ⚠️ **Custom exception handling** for API errors
- 🧪 **Comprehensive unit tests** with JUnit 5

### MealLab Application (Part B)
- 🔎 **Search Tab**:  Find meals by ingredient or name with real-time results
- 🎰 **Random Tab**:  Discover new recipes with a single click
- ⭐ **Favorites Tab**: Save meals you want to cook later
- ✅ **Cooked Tab**: Track meals you've already prepared
- 💾 **Persistent storage**:  Your lists are saved locally in JSON format
- 🖼️ **Image display**:  View meal thumbnails and full images
- 📱 **Intuitive UI**: Clean tabbed interface with status updates

## 🛠️ Technologies Used

- **Java 21**
- **JavaFX 21** (Desktop UI)
- **Maven** (Build & dependency management)
- **Jackson** (JSON serialization/deserialization)
- **JUnit 5** (Unit testing)
- **TheMealDB API** (External REST API)

## 📁 Project Structure

```
meallab-java-project/
├── meallab-api/                    # API Library Module
│   ├── src/main/java/
│   │   └── gr/unipi/ds/meallab/
│   │       ├── api/
│   │       │   ├── model/          # POJOs (Meal, SimpleMeal, Ingredient)
│   │       │   └── service/        # MealService (API calls)
│   │       └── exception/          # Custom exceptions
│   └── src/test/java/              # JUnit tests
├── meallab-app/                    # JavaFX Application Module
│   ├── src/main/java/
│   │   └── gr/unipi/ds/meallab/app/
│   │       ├── MealLabApp.java     # Main application
│   │       ├── SceneCreator.java   # Abstract base class
│   │       ├── MainSceneCreator.java # UI builder & event handler
│   │       ├── model/              # UserMeal class
│   │       ├── service/            # StorageService (JSON persistence)
│   │       └── util/               # ImageLoader utility
│   └── src/test/java/              # JUnit tests
└── pom.xml                         # Parent POM
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Internet connection** (for API calls and image loading)

### Installation & Running

#### Option 1: Build and Run with Maven (Recommended)

1. **Clone the repository**
```bash
git clone https://github.com/KrisGlns/meallab-java-project.git
cd meallab-java-project
```

2. **Build the entire project**
```bash
mvn clean install
```

3. **Run the application**
```bash
cd meallab-app
mvn javafx:run
```

#### Option 2: Run from Eclipse

1. **Import the project**
   - File → Import → Existing Maven Projects
   - Select the `meallab-java-project` folder
   - Import all modules

2. **Build the API library**
   - Right-click `meallab-api` → Run As → Maven install

3. **Run the application**
   - Right-click `MealLabApp. java` → Run As → Java Application
   - Or:  Right-click `meallab-app` → Run As → Maven build...  → Goals: `javafx:run`

## 📖 Usage Guide

### Searching for Meals
1. Click the **🔍 Search** tab
2. Enter an ingredient (e.g., "chicken") or meal name (e.g., "pasta")
3. Click **Search** or press Enter
4. **Double-click** any result to view full details

### Discovering Random Meals
1. Click the **🎲 Random** tab
2. Click **Get Random Meal**
3. Browse the recipe with ingredients and instructions
4. Click **Add to Favorites** or **Mark as Cooked**

### Managing Favorites
1. Click the **⭐ Favorites** tab
2. View your saved meals
3. **Double-click** to view details
4. Use buttons to move to cooked list or remove

### Tracking Cooked Meals
1. Click the **✅ Cooked** tab
2. View meals you've prepared
3. Move back to favorites or remove from list

### Data Persistence
- Your favorites and cooked lists are automatically saved to `meallab-data/` folder
- Data persists between application restarts
- Files:  `favorites.json` and `cooked.json`

## 🧪 Running Tests

### Run all tests
```bash
mvn test
```

### Run API tests only
```bash
cd meallab-api
mvn test
```

### Run App tests only
```bash
cd meallab-app
mvn test
```

### Test Coverage
- **meallab-api**: 13 tests (Model + Service layer)
- **meallab-app**: 16 tests (Model + Storage service)
- **Total**: 29 unit tests

## 🏗️ Building JAR Files

### Build API library
```bash
cd meallab-api
mvn clean package
```
Output: `meallab-api/target/meallab-api-1.0.0.jar`

### Build JavaFX application
```bash
cd meallab-app
mvn clean package
```
Output: `meallab-app/target/meallab-app-0.0.1-SNAPSHOT.jar`

## 🔧 Configuration

### API Endpoints
The application uses TheMealDB API (free tier):
- Base URL: `https://www.themealdb.com/api/json/v1/1/`
- No API key required
- Rate limits: None for free tier

### Data Storage
- Location: `meallab-data/` (created automatically)
- Format: JSON
- Files: 
  - `favorites.json` - Saved favorite meals
  - `cooked.json` - Meals marked as cooked

## 🐛 Troubleshooting

### JavaFX Runtime Error
**Error**: `Error: JavaFX runtime components are missing`

**Solution**: Use Maven to run:  `mvn javafx:run`

### Module Not Found
**Error**: `Module gr.unipi.ds.meallab.api not found`

**Solution**: 
```bash
cd meallab-api
mvn clean install
cd ../meallab-app
mvn clean install
```

### Images Not Loading
- Check internet connection
- Images load asynchronously - wait a few seconds
- Some meals may have broken image URLs from the API

### Data Not Persisting
- Check if `meallab-data/` folder has write permissions
- Verify JSON files are not corrupted
- Delete JSON files to reset (they'll be recreated)

## 📝 API Documentation

### MealService Methods

```java
// Search for meals by ingredient or name
List<SimpleMeal> searchMeals(String query) throws ApiException

// Get detailed meal information by ID
Meal getMealById(String mealId) throws ApiException

// Get a random meal
Meal getRandomMeal() throws ApiException

// Close resources
void close()
```

### Exception Types
- `INVALID_INPUT` - Empty or null query
- `NOT_FOUND` - Meal not found by ID
- `NETWORK_ERROR` - API connection issues
- `PARSING_ERROR` - JSON parsing failures

## 👨‍💻 Development

### Architecture Pattern
- **API Module**: Repository pattern with service layer
- **App Module**: Scene Creator pattern (from course labs)
- **Separation of Concerns**: UI, Business Logic, Data Access

### Design Decisions
- Java UI following course lab structure
- Jackson for JSON (consistent with API module)
- Observable lists for reactive UI updates
- Custom exceptions for better error handling

## 📄 License

This project is for educational purposes as part of a university assignment. 

## 🙏 Acknowledgments

- **TheMealDB API**:  https://www.themealdb.com/api.php
- **JavaFX**:  https://openjfx.io/

## 📧 Contact

- **Student**: Christos Galanis
- **GitHub**: https://github.com/KrisGlns/meallab-java-project

---

