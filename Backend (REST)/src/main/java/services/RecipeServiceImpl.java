package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controllers.NoRecipeFoundException;
import dao.RecipeDao;
import domain.Ingredient;
import domain.Meal;
import domain.Recipe;
import domain.Review;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeDao recipeDao;
	
	@Override
	public List<Recipe> getRecipes() {
		return recipeDao.getRecipes();
	}

	@Override
	public Recipe getRecipe(int id) {
		Recipe recipe = recipeDao.getRecipe(id);
//		if (recipe == null) {
//			throw new NoRecipeFoundException();
//		}
		return recipe;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe, int id) {
		return recipeDao.saveRecipe(recipe, id);
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		int id = recipeDao.addRecipe(recipe);
		recipe.setId(id);
		return recipe;
	}

	@Override
	public void deleteRecipe(int id) {
		recipeDao.deleteRecipe(id);

	}

	@Override
	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient) {
		return recipeDao.getRecipesWithIngredient(ingredient);
	}

	@Override
	public List<Recipe> getRecipesWithSpecifiedIngredients(List<Ingredient> ingredients) {
		return recipeDao.getRecipesWithSpecifiedIngredients(ingredients);
	}

	@Override
	public List<Recipe> getRecipesWithType(String type) {
		return recipeDao.getRecipesWithType(type);
	}

	@Override
	public List<Recipe> getRecipesWithMeal(Meal meal) {
		return recipeDao.getRecipesWithMeal(meal);
	}

	@Override
	public List<Recipe> getAllVerifiedRecipes() {
		return recipeDao.getAllVerifiedRecipes();
	}

	@Override
	public List<Recipe> getAllUnVerifiedRecipes() {
		return recipeDao.getAllUnVerifiedRecipes();
	}

	@Override
	public Recipe getRecipeForReview(Review review) {
		return recipeDao.getRecipeForReview(review.getId());
	}

	@Override
	public void deleteRecipesWithIngredient(int ingredientId) {
		recipeDao.deleteRecipesWithIngredient(ingredientId);
		
	}

	@Override
	public Recipe getVerifiedRecipe(int recipeId) {
		return recipeDao.getVerifiedRecipe(recipeId);
	}

}
