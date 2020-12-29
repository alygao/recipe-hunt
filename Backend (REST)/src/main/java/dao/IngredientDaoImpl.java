package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Ingredient;
import domain.Recipe;

@Repository
public class IngredientDaoImpl implements IngredientDao {
	
	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public List<Ingredient> getIngredients() {
		return jdbcOperations.query("SELECT ID, NAME FROM INGREDIENTS;",
				(rs, rowNum) -> new Ingredient(rs.getInt("ID"), rs.getString("NAME")));
	}

	@Override
	public Ingredient getIngredient(int id) {
		return jdbcOperations.queryForObject("SELECT NAME FROM INGREDIENTS WHERE ID = ?;",
				(rs, rowNum) -> new Ingredient(id, rs.getString("NAME")), id);
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient, int id) {
		this.jdbcOperations.update("UPDATE INGREDIENTS SET NAME = ? WHERE ID = ?;", 
				ingredient.getName(), id);
		return ingredient;
	}

	@Override
	public int addIngredient(Ingredient ingredient) {
		System.out.println("adding new ingredient");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcOperations.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO INGREDIENTS(NAME) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ingredient.getName());
			return ps;
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void deleteIngredient(int id) {
		this.jdbcOperations.update("DELETE FROM INGREDIENTS WHERE ID = ?;", id);

	}

	@Override
	public List<Ingredient> getIngredientsInRecipe(int recipeId) {	
		return jdbcOperations.query("SELECT INGREDIENT_ID FROM RECIPES_INGREDIENTS WHERE RECIPE_ID = ?;",
				(rs, rowNum) -> {return getIngredient(rs.getInt("INGREDIENT_ID"));
				}, recipeId);
		
	}

	@Override
	public List<Ingredient> searchIngredients(String term) {
		List<Ingredient> ingredientList =  this.jdbcOperations.query("SELECT ID, NAME FROM INGREDIENTS WHERE NAME LIKE ?",
				new Object[] {term.toLowerCase() + "%"},
				(rs, rowNum) -> { return new Ingredient(rs.getInt("ID"), rs.getString("NAME")); });
		return ingredientList;
	}
	
	@Override
	public List<Ingredient> searchIngredientsExactName(String term) {
		List<Ingredient> ingredientList =  this.jdbcOperations.query("SELECT ID, NAME FROM INGREDIENTS WHERE NAME LIKE ?",
				new Object[] {term.toLowerCase()},
				(rs, rowNum) -> { return new Ingredient(rs.getInt("ID"), rs.getString("NAME")); });
		return ingredientList;
	}

	@Override
	public Ingredient findIngredient(String name) {
		if (searchIngredients(name).isEmpty()) {
			return null;
		}
		Ingredient ingredient = this.jdbcOperations.queryForObject("SELECT ID, NAME FROM INGREDIENTS WHERE NAME LIKE ?",
				new Object[] {name.toLowerCase() + "%"},
				(rs, rowNum) -> { return new Ingredient(rs.getInt("ID"), rs.getString("NAME")); });
		return ingredient;
	}
	
	@Override
	public Ingredient findIngredientExactName(String name) {
		if (searchIngredientsExactName(name).isEmpty()) {
			return null;
		}
		Ingredient ingredient = this.jdbcOperations.queryForObject("SELECT ID, NAME FROM INGREDIENTS WHERE NAME LIKE ?",
				new Object[] {name.toLowerCase()},
				(rs, rowNum) -> { return new Ingredient(rs.getInt("ID"), rs.getString("NAME")); });
		return ingredient;
	}

}
