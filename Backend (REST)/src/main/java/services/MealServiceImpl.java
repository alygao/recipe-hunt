package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MealDao;
import domain.Meal;
import domain.Recipe;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	private MealDao mealDao;

	@Override
	public List<Meal> getMeals() {
		return mealDao.getMeals();
	}

	@Override
	public Meal getMeal(int id) {
		return mealDao.getMeal(id);
	}

	@Override
	public Meal saveMeal(Meal meal, int id) {
		meal.setId(id);
		return mealDao.saveMeal(meal, id);
	}

	@Override
	public Meal addMeal(Meal meal) {
		int id = mealDao.addMeal(meal);
		meal.setId(id);
		return meal;
	}

	@Override
	public void deleteMeal(int id) {
		mealDao.deleteMeal(id);

	}

	@Override
	public List<Meal> getMealsInRecipe(int recipeId) {
		return mealDao.getMealsInRecipe(recipeId);
	}
	
	

}
