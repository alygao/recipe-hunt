package dao;

import java.util.List;

import domain.Review;

public interface ReviewDao {

	List<Review> getReviews();

	Review getReview(int id);

	Review saveReview(Review review, int id);

	int addReview(Review review);

	void deleteReview(int id);

	List<Review> getUnverfiedReviews();

	List<Review> getVerfiedReviews();

	List<Review> getUnverfiedReviewsForRecipe(int recipeId);

	List<Review> getVerfiedReviewsForRecipe(int recipeId);

	List<Review> getReviewsForRecipe(int recipeId);

}
