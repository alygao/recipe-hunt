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
import domain.Moderator;
import domain.Recipe;
import domain.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Autowired 
	private ModeratorDao moderatorDao;
	
	@Autowired 
	private RecipeDao recipeDao;
	


	@Override		
	public List<Review> getReviews() {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, RECIPE_ID, MODERATOR_ID FROM REVIEWS;",
				(rs, rowNum) -> {
//					int reviewId = rs.getInt("ID");
					Moderator moderator = moderatorDao.getModerator(rs.getInt("MODERATOR_ID"));
					Recipe recipe = recipeDao.getRecipe(rs.getInt("RECIPE_ID"));
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						rs.getDate("REVIEW_DATE"));
					});
	}

	@Override
	public Review getReview(int id) {
		Moderator moderator = moderatorDao.getModeratorForReview(id);
		Recipe recipe = recipeDao.getRecipeForReview(id);
		return jdbcOperations.queryForObject("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, FROM REVIEWS WHERE ID = ?;",
				(rs, rowNum) -> new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						rs.getDate("REVIEW_DATE")
				), id);
	}

	@Override
	public Review saveReview(Review review, int id) {
		this.jdbcOperations.update("UPDATE REVIEWS SET REVIEWER_NAME = ?, REVIEWER_EMAIL = ?, REVIEW = ?, VERIFIED_BY_MODERATOR = ?, REVIEW_DATE = ?, RECIPE_ID = ?, MODERATOR_ID = ?  WHERE ID = ?", 
				review.getReviewerName(), 
				review.getReviewerEmail(),
				review.getReview(),
				review.isVerifiedByModerator(),
				review.getDate(),
				review.getRecipe().getId(),
				review.getModerator().getId(),
				id);
		return review;
	}

	@Override
	public int addReview(Review review) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (!review.isVerifiedByModerator()) {
			this.jdbcOperations.update(connection -> {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO REVIEWS(REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, RECIPE_ID) VALUES (?, ?, ?, ?, ?, ?);",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, review.getReviewerName());
				ps.setString(2, review.getReviewerEmail());
				ps.setString(3, review.getReview());
				ps.setBoolean(4, review.isVerifiedByModerator());
				ps.setDate(5, review.getDate());
				ps.setInt(6, review.getRecipe().getId());
				return ps;
			}, keyHolder);
			int reviewId = keyHolder.getKey().intValue();
			return reviewId;	
		} else {
		this.jdbcOperations.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO REVIEWS(REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, RECIPE_ID, MODERATOR_ID) VALUES (?, ?, ?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, review.getReviewerName());
			ps.setString(2, review.getReviewerEmail());
			ps.setString(3, review.getReview());
			ps.setBoolean(4, review.isVerifiedByModerator());
			ps.setDate(5, review.getDate());
			ps.setInt(6, review.getRecipe().getId());
			ps.setInt(7, review.getModerator().getId());
			return ps;
		}, keyHolder);
		int reviewId = keyHolder.getKey().intValue();
		return reviewId;
		}
	}

	@Override
	public void deleteReview(int id) {
		this.jdbcOperations.update("DELETE FROM REVIEWS WHERE ID = ?;", id);

	}

	@Override
	public List<Review> getUnverfiedReviews() {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, REVIEW_DATE, RECIPE_ID FROM REVIEWS WHERE VERIFIED_BY_MODERATOR = FALSE;",
				(rs, rowNum) -> {
					Recipe recipe = recipeDao.getRecipe(rs.getInt("RECIPE_ID"));
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						false,
						null,
						rs.getDate("REVIEW_DATE"));
					});
	}

	@Override
	public List<Review> getVerfiedReviews() {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, REVIEW_DATE, RECIPE_ID FROM REVIEWS WHERE VERIFIED_BY_MODERATOR = TRUE;",
				(rs, rowNum) -> {
					Recipe recipe = recipeDao.getRecipe(rs.getInt("RECIPE_ID"));
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						true,
						null,
						rs.getDate("REVIEW_DATE"));
					});
	}

	@Override
	public List<Review> getUnverfiedReviewsForRecipe(int recipeId) {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, MODERATOR_ID FROM REVIEWS WHERE VERIFIED_BY_MODERATOR = FALSE AND RECIPE_ID = ?;",
				(rs, rowNum) -> {
					Moderator moderator = moderatorDao.getModerator(rs.getInt("MODERATOR_ID"));
					Recipe recipe = recipeDao.getRecipe(recipeId);
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						rs.getDate("REVIEW_DATE"));
					}, recipeId);
	}

	@Override
	public List<Review> getVerfiedReviewsForRecipe(int recipeId) {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, MODERATOR_ID FROM REVIEWS WHERE VERIFIED_BY_MODERATOR = TRUE AND RECIPE_ID = ?;",
				(rs, rowNum) -> {
					Moderator moderator = moderatorDao.getModerator(rs.getInt("MODERATOR_ID"));
					Recipe recipe = recipeDao.getRecipe(recipeId);
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						rs.getDate("REVIEW_DATE"));
					}, recipeId);
	}

	@Override
	public List<Review> getReviewsForRecipe(int recipeId) {
		return jdbcOperations.query("SELECT ID, REVIEWER_NAME, REVIEWER_EMAIL, REVIEW, VERIFIED_BY_MODERATOR, REVIEW_DATE, MODERATOR_ID FROM REVIEWS WHERE RECIPE_ID = ?;",
				(rs, rowNum) -> {
					Moderator moderator = moderatorDao.getModerator(rs.getInt("MODERATOR_ID"));
					Recipe recipe = recipeDao.getRecipe(recipeId);
					return new Review(
						rs.getInt("ID"), 
						rs.getString("REVIEWER_NAME"),
						rs.getString("REVIEWER_EMAIL"),
						rs.getString("REVIEW"),
						recipe,
						rs.getBoolean("VERIFIED_BY_MODERATOR"),
						moderator,
						rs.getDate("REVIEW_DATE"));
					}, recipeId);
	}

}
