package services;

import java.util.List;

import domain.Ingredient;
import domain.Recipe;

public interface IngredientService {
	
	List<Ingredient> getIngredients();

	Ingredient getIngredient(int id);

	Ingredient saveIngredient(Ingredient ingredient, int id);

	Ingredient addIngredient(Ingredient ingredient);

	void deleteIngredient(int id);
	
	List<Ingredient> getIngredientsInRecipe(Recipe recipe);

	List<Ingredient> searchIngredients(String term);

	Ingredient findIngredient(String name);

	List<Ingredient> convertIngredients(String[] ingredientNames);
	
}