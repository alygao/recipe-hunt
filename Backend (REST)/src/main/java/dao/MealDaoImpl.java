package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Meal;

@Repository
public class MealDaoImpl implements MealDao {

	@Autowired
	private JdbcOperations jdbcOperations;	
	
	@Override
	public List<Meal> getMeals() {
		return jdbcOperations.query("SELECT ID, NAME FROM MEALS;",
				(rs, rowNum) -> new Meal(rs.getInt("ID"), rs.getString("NAME")));
	}

	@Override
	public Meal getMeal(int id) {
		return jdbcOperations.queryForObject("SELECT NAME FROM MEALS WHERE ID = ?;",
				(rs, rowNum) -> new Meal(id, rs.getString("NAME")), id);
	}

	@Override
	public Meal saveMeal(Meal meal, int id) {
		this.jdbcOperations.update("UPDATE MEALS SET NAME = ? WHERE ID = ?", 
				meal.getName(), id);
		return meal;
	}

	@Override
	public int addMeal(Meal meal) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcOperations.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO MEALS(NAME) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, meal.getName());
			return ps;
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void deleteMeal(int id) {
		this.jdbcOperations.update("DELETE FROM MEALS WHERE ID = ?;", id);

	}

	@Override
	public List<Meal> getMealsInRecipe(int recipeId) {		
		return jdbcOperations.query("SELECT MEAL_ID FROM RECIPES_MEALS WHERE RECIPE_ID = ?;",
				(rs, rowNum) -> {return getMeal(rs.getInt("MEAL_ID"));
				}, recipeId);
	}

}
