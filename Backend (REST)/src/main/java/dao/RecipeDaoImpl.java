package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import controllers.NoRecipeFoundException;
import domain.Ingredient;
import domain.Meal;
import domain.Moderator;
import domain.Recipe;
import domain.Review;

@Repository
public class RecipeDaoImpl implements RecipeDao {
	
	@Autowired
	private JdbcOperations jdbcOperations;
	@Autowired
	private MealDao mealDao;
	@Autowired
	private IngredientDao ingredientDao;
	@Autowired
	private ModeratorDao moderatorDao;
	@Autowired
	private ReviewDao reviewDao;

	@Override
	public List<Recipe> getRecipes() {
		return jdbcOperations.query("SELECT ID FROM RECIPES;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					return getRecipe(id);
					});
	}

	@Override
	public Recipe getRecipe(int id) {
		// get meals array
		// get ingredients array 
		// return recipe object
		
		List<Meal> meals = mealDao.getMealsInRecipe(id);
		List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
		List<Review> verifiedReviews = new ArrayList<>();
		List<Review> unverifiedReviews = new ArrayList<>();
//		Recipe recipe = null;
		try {
			Recipe recipe = jdbcOperations.queryForObject("SELECT R.ID AS RECIPE_ID, R.NAME AS RECIPE_NAME, LINK, VERIFIED_BY_MODERATOR, MODERATOR_ID, TYPE, M.USERNAME, M.PASSWORD, M.NAME AS MODERATOR_NAME, M.LEVEL, M.ACTIVE FROM RECIPES R LEFT OUTER JOIN MODERATORS M ON R.MODERATOR_ID = M.ID WHERE R.ID = ?;",
				(rs, rowNum) -> {
					Integer moderatorId = rs.getInt("MODERATOR_ID");
					
						return new Recipe(
						rs.getInt("RECIPE_ID"), 
						rs.getString("RECIPE_NAME"),
						ingredients,
						rs.getString("LINK"),
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderatorId == null ? null :
						new Moderator(
								rs.getInt("MODERATOR_ID"), 
								rs.getString("USERNAME"), 
								rs.getString("PASSWORD"),
								rs.getString("MODERATOR_NAME"),
								rs.getInt("LEVEL"),
								rs.getBoolean("ACTIVE")),
						new ArrayList<>(),
						new ArrayList<>(),
						meals,
						rs.getString("TYPE"));
						}, id);
		 System.out.println("retrieiving recipe id# " + id );
		 
		 verifiedReviews = jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, REVIEW_DATE, MODERATOR_ID FROM REVIEWS WHERE RECIPE_ID = ? AND VERIFIED_BY_MODERATOR = true;",
				 (rs, rowNum) -> {
					 Integer moderatorIdForReview = rs.getInt("MODERATOR_ID");
					 Moderator moderatorForReview;
					 if (moderatorIdForReview == null) {
						 moderatorForReview = null;
					 } else {
						 moderatorForReview = moderatorDao.getModerator(rs.getInt("MODERATOR_ID"));
					 }
					 return new Review(
							 rs.getInt("ID"), 
							 rs.getString("REVIEWER_NAME"),
							 rs.getString("REVIEWER_EMAIL"),
							 rs.getString("REVIEW"),
							 recipe,
							 true,
							 moderatorForReview,
							 rs.getDate("REVIEW_DATE"));
				 }, id);
		 
		 unverifiedReviews = jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, REVIEW_DATE FROM REVIEWS WHERE RECIPE_ID = ? AND VERIFIED_BY_MODERATOR = false;",
				 (rs, rowNum) -> {
					 return new Review(
							 rs.getInt("ID"), 
							 rs.getString("REVIEWER_NAME"),
							 rs.getString("REVIEWER_EMAIL"),
							 rs.getString("REVIEW"),
							 recipe,
							 false,
							 null,
							 rs.getDate("REVIEW_DATE"));
				 }, id);
		 
		 recipe.setVerifiedReviews(verifiedReviews);
		 recipe.setUnverifiedReviews(unverifiedReviews);
		 
		 return recipe;
		} catch (EmptyResultDataAccessException e) {
			throw new NoRecipeFoundException();
		}
		
	}

	
	// helper function
	// check if ingredient in edited recipe is already in ingredient database, if not, add new ingredient
	public boolean ingredientInDatabase(Ingredient ingredient, List<Ingredient> allIngredients) {
		for (Ingredient ingredientInDatabase : allIngredients) {
			if (ingredientInDatabase.getName().equalsIgnoreCase(ingredient.getName())) {
				return true;
			}
		}
		return false;
	}
	
	// helper function
	// check if review in edited recipe is already in review database, if not, add new review
	public boolean reviewInDatabase(Review review, List<Review> allReviews) {
		for (Review reviewInDatabase : allReviews) {
			if (reviewInDatabase.getId() == (review.getId())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Recipe saveRecipe(Recipe recipe, int id) {
		List <Ingredient> ingredientsCurrentlyInDatabase = ingredientDao.getIngredientsInRecipe(id);
		List<Ingredient> allIngredients = ingredientDao.getIngredients();
		for (Ingredient i : recipe.getIngredients()) {
			if (!ingredientInDatabase(i, allIngredients)) {
				int idIngredient = ingredientDao.addIngredient(i);
				i.setId(idIngredient);
			} else {
				ingredientDao.saveIngredient(i, i.getId());
			}
		}
		if (recipe.isVerifiedByModerator()) {
			if (moderatorDao.getModeratorForRecipe(id) == null) {
				moderatorDao.addModerator(recipe.getModerator());
			} else {
				moderatorDao.saveModerator(recipe.getModerator(), recipe.getModerator().getId());
			}
			this.jdbcOperations.update("UPDATE RECIPES SET NAME = ?, LINK = ?, VERIFIED_BY_MODERATOR = ?, MODERATOR_ID = ?, TYPE = ? WHERE ID = ?", 
					recipe.getName(), recipe.getLink(), recipe.isVerifiedByModerator(), recipe.getModerator().getId(), recipe.getType(),
					id);
		} else {
			this.jdbcOperations.update("UPDATE RECIPES SET NAME = ?, LINK = ?, VERIFIED_BY_MODERATOR = ?, TYPE = ? WHERE ID = ?", 
					recipe.getName(), recipe.getLink(), recipe.isVerifiedByModerator(), recipe.getType(),
					id);
		}

		
		//Method 2:
		List <Meal> mealsCurrentlyInDatabase = mealDao.getMealsInRecipe(id);
		for (Meal m : mealsCurrentlyInDatabase) {
			this.jdbcOperations.update("DELETE FROM RECIPES_MEALS WHERE MEAL_ID = ? AND RECIPE_ID = ?;", m.getId(), id);
		}
		for (Meal m : recipe.getMeals()) {
			this.jdbcOperations.update("INSERT INTO RECIPES_MEALS(RECIPE_ID, MEAL_ID) VALUES (?, ?);", id, m.getId());
		}
		
		// Update RECIPES_INGREDIENTS
		for (Ingredient i : ingredientsCurrentlyInDatabase) {
			this.jdbcOperations.update("DELETE FROM RECIPES_INGREDIENTS WHERE INGREDIENT_ID = ? AND RECIPE_ID = ?;", i.getId(), id);
		}
		for (Ingredient i : recipe.getIngredients()) {
				this.jdbcOperations.update("INSERT INTO RECIPES_INGREDIENTS(RECIPE_ID, INGREDIENT_ID) VALUES (?, ?);", id, i.getId());
		}
		
		// Update Reviews
		List<Review> reviewsCurrentlyInDatabase = reviewDao.getReviewsForRecipe(id);
		removeDeletedReviews(reviewsCurrentlyInDatabase, recipe);
		
		List<Review> allReviews = reviewDao.getReviews();
		for (Review r : recipe.getUnverifiedReviews()) {
			if (reviewInDatabase(r, allReviews)) {
				reviewDao.saveReview(r, r.getId());
			} else {
				reviewDao.addReview(r);
			}
		}	
		for (Review r : recipe.getVerifiedReviews()) {
			if (reviewInDatabase(r, allReviews)) {
				reviewDao.saveReview(r, r.getId());
			} else {
				reviewDao.addReview(r);
			}
		}	
		
		return recipe;
	}

	private void removeDeletedReviews(List<Review> reviewsCurrentlyInDatabase, Recipe recipe) {
		for (Review r : reviewsCurrentlyInDatabase) {
			if (!reviewInDatabase(r, recipe.getUnverifiedReviews()) && !reviewInDatabase(r, recipe.getVerifiedReviews())) {
				reviewDao.deleteReview(r.getId());
			}
		}
		
	}

	@Override
	public int addRecipe(Recipe recipe) {
//		List<Ingredient> allIngredients = ingredientDao.getIngredients();
//		for (Ingredient i : recipe.getIngredients()) {
//			if (!allIngredients.contains(i)) {
//				ingredientDao.addIngredient(i);
//			}
//		}
		
		int recipeId = 0;
		if (recipe.isVerifiedByModerator()) {
//			moderatorDao.addModerator(recipe.getModerator());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.jdbcOperations.update(connection -> {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO RECIPES(NAME, LINK, VERIFIED_BY_MODERATOR, MODERATOR_ID, TYPE ) VALUES (?, ?, ?, ?, ?);",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, recipe.getName());
				ps.setString(2, recipe.getLink());
				ps.setBoolean(3, recipe.isVerifiedByModerator());
				ps.setInt(4, recipe.getModerator().getId());
				ps.setString(5, recipe.getType());
				return ps;
			}, keyHolder);
			recipeId = keyHolder.getKey().intValue();
		} else {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.jdbcOperations.update(connection -> {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO RECIPES(NAME, LINK, VERIFIED_BY_MODERATOR, TYPE ) VALUES (?, ?, ?, ?);",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, recipe.getName());
				ps.setString(2, recipe.getLink());
				ps.setBoolean(3, recipe.isVerifiedByModerator());
				ps.setString(4, recipe.getType());
				return ps;
			}, keyHolder);
			recipeId = keyHolder.getKey().intValue();
		}
		
		
		for (Meal m : recipe.getMeals()) {
			this.jdbcOperations.update("INSERT INTO RECIPES_MEALS(RECIPE_ID, MEAL_ID) VALUES (?, ?);", recipeId, m.getId());
		}
		for (Ingredient i : recipe.getIngredients()) {
			this.jdbcOperations.update("INSERT INTO RECIPES_INGREDIENTS(RECIPE_ID, INGREDIENT_ID) VALUES (?, ?);", recipeId, i.getId());
		}	
		for (Review r : recipe.getUnverifiedReviews()) {
			reviewDao.addReview(r);
		}	
		for (Review r : recipe.getVerifiedReviews()) {
			reviewDao.addReview(r);
		}	
		return recipeId;
	}

	@Override
	public void deleteRecipe(int id) {
		this.jdbcOperations.update("DELETE FROM RECIPES_INGREDIENTS WHERE RECIPE_ID = ?;", id);
		this.jdbcOperations.update("DELETE FROM RECIPES_MEALS WHERE RECIPE_ID = ?;", id);
		this.jdbcOperations.update("DELETE FROM REVIEWS WHERE RECIPE_ID = ?;", id);
		this.jdbcOperations.update("DELETE FROM RECIPES WHERE ID = ?;", id);


	}

	@Override
	// only gets verified recipes
	public List<Recipe> getRecipesWithIngredient(Ingredient ingredient) {
		return jdbcOperations.query("SELECT r.ID, r.NAME, r.LINK, r.VERIFIED_BY_MODERATOR, r.MODERATOR_ID, r.TYPE FROM RECIPES_INGREDIENTS ri INNER JOIN RECIPES r ON ri.recipe_id = r.id WHERE ri.INGREDIENT_ID = ? AND r.VERIFIED_BY_MODERATOR = true;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);	
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						rs.getString("TYPE"));}, ingredient.getId());
	}

	@Override
	public List<Recipe> getRecipesWithSpecifiedIngredients(List<Ingredient> ingredients) {
		List<Recipe> resultRecipes = getRecipesWithIngredient(ingredients.get(0));
		for (Ingredient i : ingredients) {
			List<Recipe> recipes = getRecipesWithIngredient(i);
			for (Iterator<Recipe> iterator = resultRecipes.iterator(); iterator.hasNext();) {
				Recipe r = iterator.next();
				if (!isRecipeInArray(r, recipes)) {
					iterator.remove();
				}
			}
		}
		return resultRecipes;
	}
	
	public boolean isRecipeInArray(Recipe recipe, List<Recipe> recipeArray) {
		for (Recipe r : recipeArray) {
			if (r.getId() == recipe.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Recipe> getRecipesWithType(String type) {
		return jdbcOperations.query("SELECT ID, NAME, LINK, VERIFIED_BY_MODERATOR, MODERATOR_ID, FROM RECIPES WHERE TYPE = ?;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);	
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						type);}, type);
	}

	@Override
	public List<Recipe> getRecipesWithMeal(Meal meal) {
		return jdbcOperations.query("SELECT r.ID, r.NAME, r.LINK, r.VERIFIED_BY_MODERATOR, r.MODERATOR_ID, r.TYPE FROM RECIPES_MEALS rm INNER JOIN RECIPES r ON ri.recipe_id = r.id WHERE rm.MEAL_ID = ?;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);	
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						rs.getString("TYPE"));}, meal.getId());
	}

	@Override
	public List<Recipe> getAllVerifiedRecipes() {
		return jdbcOperations.query("SELECT ID, NAME, LINK, MODERATOR_ID, TYPE FROM RECIPES WHERE VERIFIED_BY_MODERATOR = true;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);	
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						true,
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						rs.getString("TYPE"));});
	}

	@Override
	public List<Recipe> getAllUnVerifiedRecipes() {
		return jdbcOperations.query("SELECT ID, NAME, LINK, MODERATOR_ID, TYPE FROM RECIPES WHERE VERIFIED_BY_MODERATOR = false;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);	
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						false,
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						rs.getString("TYPE"));});
	}

	@Override
	public Recipe getRecipeForReview(int reviewId) {		
		return jdbcOperations.queryForObject("SELECT r.ID, r.NAME, r.LINK, r.VERIFIED_BY_MODERATOR, r.MODERATOR_ID, r.TYPE FROM REVIEWS rw INNER JOIN RECIPES r ON rw.recipe_id = r.id WHERE rw.ID= ?;",
				(rs, rowNum) -> {
					int id = rs.getInt("ID");
					List<Meal> meals = mealDao.getMealsInRecipe(id);
					List<Ingredient> ingredients = ingredientDao.getIngredientsInRecipe(id);
					List<Review> verifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					List<Review> unVerifiedReviews = reviewDao.getVerfiedReviewsForRecipe(id);
					Moderator moderator = moderatorDao.getModeratorForRecipe(id);
					return new Recipe(
						rs.getInt("ID"), 
						rs.getString("NAME"),
						ingredients,
						rs.getString("LINK"),
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						verifiedReviews,
						unVerifiedReviews,
						meals,
						rs.getString("TYPE"));
					}, reviewId);
	}

	@Override
	public void deleteRecipesWithIngredient(int ingredientId) {
		List<Integer> recipeIds = jdbcOperations.query("SELECT R.ID FROM RECIPES R JOIN RECIPES_INGREDIENTS RI WHERE R.ID = RI.RECIPE_ID AND RI.INGREDIENT_ID IN (?);",
				(rs, rowNum) -> {
					return rs.getInt("ID");}, ingredientId);
		recipeIds.forEach(id -> this.deleteRecipe(id));
		
	}

	@Override
	public Recipe getVerifiedRecipe(int recipeId) {
		Recipe recipe = this.getRecipe(recipeId);
		if (recipe.isVerifiedByModerator()) {
			return recipe;
		} else {
			return null;
		}
	}

}
