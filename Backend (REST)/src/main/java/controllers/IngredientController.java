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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Ingredient;
import domain.Recipe;
import services.IngredientService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class IngredientController {

 @Autowired
 private IngredientService ingredientService;
 
 @RequestMapping(value = "/ingredients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Ingredient> getAllIngredients ( ) {
  try {
   List<Ingredient> ingredientList = this.ingredientService.getIngredients();
   for ( Ingredient ingredient : ingredientList ) {
    System.out.println( ingredient );
   }
   return ingredientList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Ingredient getIngredient (@PathVariable int id) {
	 return ingredientService.getIngredient(id);
 }
 
 @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Ingredient saveIngredient (@PathVariable int id, @RequestBody Ingredient ingredient) {
//	 System.out.println("in saveIngredient, new name = " + ingredient.getName());
	 return ingredientService.saveIngredient(ingredient, id);
 }
 
 @RequestMapping(value = "/ingredients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 @ResponseStatus (HttpStatus.CREATED)
 public @ResponseBody Ingredient addIngredient (@RequestBody Ingredient ingredient) {
	 return ingredientService.addIngredient(ingredient);
 }
 
 @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.DELETE)
 @ResponseStatus (HttpStatus.NO_CONTENT)
 public @ResponseBody void deleteIngredient (@PathVariable int id) {
	 ingredientService.deleteIngredient(id);
 }
 

 
 @RequestMapping(value = "/searchIngredients", method = RequestMethod.GET)
 public @ResponseBody List<Ingredient> searchIngredients (@RequestParam(value = "term") String term) {
	 return ingredientService.searchIngredients(term);
 }
 
 @RequestMapping(value = "/convertToIngredients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
 public @ResponseBody List<Ingredient> convertToIngredients (@RequestBody String[] ingredientNames ) {
	System.out.println(" **************** IN SPINRG - INGREDIENT CONTROLLER ***********");	
	for (int i = 0; i < ingredientNames.length; i++ ) {
		System.out.println("Ingredient names: " + ingredientNames[i]);		
	}
		List<Ingredient> ingredients = ingredientService.convertIngredients(ingredientNames);
		System.out.println("Ingredients: " + ingredients);
		return ingredients;
	}
 
// @RequestMapping(value = "/findIngredient", method = RequestMethod.GET)
// public @ResponseBody Ingredient findIngredient (@RequestParam(value = "name") String name) {
//	 return ingredientService.findIngredient(name);
// }

}

