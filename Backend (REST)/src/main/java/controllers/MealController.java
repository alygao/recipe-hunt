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

import domain.Meal;
import services.MealService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class MealController {

 @Autowired
 private MealService mealService;
 
 @RequestMapping(value = "/meals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Meal> getAllMeals ( ) {
  try {
   List<Meal> mealList = this.mealService.getMeals();
   for ( Meal meal : mealList ) {
    System.out.println( meal );
   }
   return mealList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/meals/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Meal getMeal (@PathVariable int id) {
	 return mealService.getMeal(id);
 }
 
 @RequestMapping(value = "/meals/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Meal saveMeal (@PathVariable int id, @RequestBody Meal meal) {
	 return mealService.saveMeal(meal, id);
 }
 
 @RequestMapping(value = "/meals", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 @ResponseStatus (HttpStatus.CREATED)
 public @ResponseBody Meal addMeal (@RequestBody Meal meal) {
	 return mealService.addMeal(meal);
 }
 
 @RequestMapping(value = "/meals/{id}", method = RequestMethod.DELETE)
 @ResponseStatus (HttpStatus.NO_CONTENT)
 public @ResponseBody void deleteMeal (@PathVariable int id) {
	 mealService.deleteMeal(id);
 }
 
// @RequestMapping(value = "/searchMeals", method = RequestMethod.GET)
// public @ResponseBody List<Meal> searchMeals (@RequestParam(value = "term") String term) {
//	 return mealService.searchMeals(term);
// }

}

