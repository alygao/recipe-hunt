package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import dao.IngredientDao;
import domain.Ingredient;
import domain.Recipe;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientDao ingredientDao;
	@Autowired
	private RecipeService recipeService;

	@Override
	public List<Ingredient> getIngredients() {
		return ingredientDao.getIngredients();
	}

	@Override
	public Ingredient getIngredient(int id) {
		return ingredientDao.getIngredient(id);
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient, int id) {
		ingredient.setId(id);
		return ingredientDao.saveIngredient(ingredient, id);
	}

	@Override
	public Ingredient addIngredient(Ingredient ingredient) {
		int id = ingredientDao.addIngredient(ingredient);
		ingredient.setId(id);
		return ingredient;
	}

	@Override
	public void deleteIngredient(int id) {
		// delete all recipes with ingredient
		recipeService.deleteRecipesWithIngredient(id);
		// delete ingredient
		ingredientDao.deleteIngredient(id);
		
	}

	@Override
	public List<Ingredient> getIngredientsInRecipe(Recipe recipe) {
		int recipeId = recipe.getId();
		return ingredientDao.getIngredientsInRecipe(recipeId);
	}

	@Override
	public List<Ingredient> searchIngredients(String term) {
		return ingredientDao.searchIngredients(term);
	}

	public boolean hasLetter(String word) {
		for (char ch : word.toCharArray()) {
		  if (Character.isLetter(ch)) {
			  return true;
		  }
		}
		return false;
	}
	
	@Override
	public Ingredient findIngredient(String name) {
		if ((name.equals("")) || (!hasLetter(name))) {
			return null;
		}
		
		Ingredient ingredient = ingredientDao.findIngredientExactName(name);
		if (ingredient == null) {
			ingredient = addIngredient(new Ingredient(name));
		}
		return ingredient;
	}

	@Override
	public List<Ingredient> convertIngredients(String[] ingredientNames) {
		List<Ingredient> ingredients = new ArrayList<>();
		for (String n : ingredientNames) {
			Ingredient ingredient = findIngredient(n);
			if (ingredient != null) {
				ingredients.add(ingredient);
			}
		}
		return ingredients;
	}


}