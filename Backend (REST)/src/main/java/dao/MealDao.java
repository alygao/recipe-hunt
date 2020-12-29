package dao;

import java.util.List;

import domain.Meal;
import domain.Recipe;

public interface MealDao {

	List<Meal> getMeals();

	Meal getMeal(int id);

	Meal saveMeal(Meal meal, int id);

	int addMeal(Meal meal);

	void deleteMeal(int id);

	List<Meal> getMealsInRecipe(int recipeId);

}
