package services;

import java.util.List;

import domain.Ingredient;
import domain.Meal;
import domain.Recipe;
import domain.Review;

public interface RecipeService {

	public List<Recipe> getRecipes();

	public Recipe getRecipe(int id);

	public Recipe saveRecipe(Recipe recipe, int id);

	public Recipe addRecipe(Recipe recipe);

	public void deleteRecipe(int id);
	
	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient);
	
	public List<Recipe> getRecipesWithSpecifiedIngredients(List<Ingredient> ingredients);
	
	public List<Recipe> getRecipesWithType(String type);
	
	public List<Recipe> getRecipesWithMeal(Meal meal);

	public List<Recipe> getAllVerifiedRecipes(); 

	public List<Recipe> getAllUnVerifiedRecipes(); 
	
	Recipe getRecipeForReview(Review review);

	public void deleteRecipesWithIngredient(int ingredientId);
	
	public Recipe getVerifiedRecipe(int recipeId);

}
