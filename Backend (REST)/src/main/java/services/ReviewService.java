package services;

import java.util.List;

import domain.Recipe;
import domain.Review;

public interface ReviewService {

	List<Review> getReviews();

	Review getReview(int id);

	Review saveReview(Review review, int id);

	Review addReview(Review review);

	void deleteReview(int id);
	
	public List<Review> getUnverfiedReviews();
	
	public List<Review> getVerfiedReviews();
	
	public List<Review> getUnverfiedReviewsForRecipe(Recipe recipe);
	
	public List<Review> getVerfiedReviewsForRecipe(Recipe recipe);
	
	public List<Review> getReviewsForRecipe(Recipe recipe);

}
