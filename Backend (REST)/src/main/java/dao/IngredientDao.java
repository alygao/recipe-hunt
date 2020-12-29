package dao;

import java.util.List;

import domain.Ingredient;
import domain.Recipe;

public interface IngredientDao {

	List<Ingredient> getIngredients();

	Ingredient getIngredient(int id);

	Ingredient saveIngredient(Ingredient ingredient, int id);

	int addIngredient(Ingredient ingredient);

	void deleteIngredient(int id);
	
	List<Ingredient> getIngredientsInRecipe(int recipeId);

	List<Ingredient> searchIngredients(String term);
	
	List<Ingredient> searchIngredientsExactName(String term);

	Ingredient findIngredient(String name);
	
	Ingredient findIngredientExactName(String name);
	
}