package dao;

import java.util.List;

import domain.Ingredient;
import domain.Meal;
import domain.Recipe;

public interface RecipeDao {

	List<Recipe> getRecipes();

	Recipe getRecipe(int id);

	Recipe saveRecipe(Recipe recipe, int id);

	int addRecipe(Recipe recipe);

	void deleteRecipe(int id);

	List<Recipe> getRecipesWithIngredient(Ingredient ingredient);

	List<Recipe> getRecipesWithSpecifiedIngredients(List<Ingredient> ingredients);

	List<Recipe> getRecipesWithType(String type);

	List<Recipe> getRecipesWithMeal(Meal meal);

	List<Recipe> getAllVerifiedRecipes();

	List<Recipe> getAllUnVerifiedRecipes();

	Recipe getRecipeForReview(int reviewId);

	void deleteRecipesWithIngredient(int ingredientId);

	Recipe getVerifiedRecipe(int recipeId);

}
