package services;

import java.util.List;

import domain.Meal;
import domain.Recipe;

public interface MealService {

	List<Meal> getMeals();

	Meal getMeal(int id);

	Meal saveMeal(Meal meal, int id);

	Meal addMeal(Meal meal);

	void deleteMeal(int id);
	
	List<Meal> getMealsInRecipe (int recipeId);

}
