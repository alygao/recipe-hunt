package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Ingredient;
import domain.Recipe;
import services.IngredientService;
import services.RecipeService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class RecipeController {

 @Autowired
 private RecipeService recipeService;
 
 @Autowired
 private IngredientService ingredientService;
 
 @RequestMapping(value = "/recipes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Recipe> getAllRecipes ( ) {
  try {
   List<Recipe> recipeList = this.recipeService.getRecipes();
   for ( Recipe recipe : recipeList ) {
    System.out.println( recipe );
   }
   return recipeList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Recipe getRecipe (@PathVariable int id) {
	 return recipeService.getRecipe(id);
 }
 
 @RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Recipe saveRecipe (@PathVariable int id, @RequestBody Recipe recipe) {
	 return recipeService.saveRecipe(recipe, id);
 }
 
 @RequestMapping(value = "/recipes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 @ResponseStatus (HttpStatus.CREATED)
 public @ResponseBody Recipe addRecipe (@RequestBody Recipe recipe) {
	System.out.println(" **************** IN SPINRG - RECIPE CONTROLLER ***********");	
	 System.out.println("Recipe received" + recipe);
	 return recipeService.addRecipe(recipe);
 }
 
 @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
 @ResponseStatus (HttpStatus.NO_CONTENT)
 public @ResponseBody void deleteRecipe (@PathVariable int id) {
	 recipeService.deleteRecipe(id);
 }
 
@RequestMapping(value = "/findRecipes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public @ResponseBody List<Recipe> searchRecipes (@RequestBody String[] ingredientNames ) {
	System.out.println("Ingredient names: " + ingredientNames);
	List<Ingredient> ingredients = ingredientService.convertIngredients(ingredientNames);
	System.out.println("Ingredients: " + ingredients);
	List<Recipe> recipes = recipeService.getRecipesWithSpecifiedIngredients(ingredients);
	System.out.println("Recipes Found: " + recipes);
	return recipes;
}

@RequestMapping(value = "/recipes/unverified", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public @ResponseBody List<Recipe> getAllUnverifiedRecipes ( ) {
 try {
  List<Recipe> recipeList = this.recipeService.getAllUnVerifiedRecipes();
  for ( Recipe recipe : recipeList ) {
   System.out.println( recipe );
  }
  return recipeList;
 } catch ( Throwable t ) {
  t.printStackTrace( System.out );
  return null;
 }
}

@RequestMapping(value = "/recipes/verified", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public @ResponseBody List<Recipe> getAllVerifiedRecipes ( ) {
 try {
  List<Recipe> recipeList = this.recipeService.getAllVerifiedRecipes();
  for ( Recipe recipe : recipeList ) {
   System.out.println( recipe );
  }
  return recipeList;
 } catch ( Throwable t ) {
  t.printStackTrace( System.out );
  return null;
 }
}

@RequestMapping(value = "/recipes/verified/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public @ResponseBody Recipe getVerifiedRecipe (@PathVariable int id) {
	 return recipeService.getVerifiedRecipe(id);
}

 
// @RequestMapping(value = "/searchRecipes", method = RequestMethod.GET)
// public @ResponseBody List<Recipe> searchRecipes (@RequestParam(value = "term") String term) {
//	 return recipeService.searchRecipes(term);
// }

}

