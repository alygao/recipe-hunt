package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReviewDao;
import domain.Recipe;
import domain.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public List<Review> getReviews() {
		return reviewDao.getReviews();
	}

	@Override
	public Review getReview(int id) {
		return reviewDao.getReview(id);
	}

	@Override
	public Review saveReview(Review review, int id) {
		review.setId(id);
		return reviewDao.saveReview(review, id);
	}

	@Override
	public Review addReview(Review review) {
		int id = reviewDao.addReview(review);
		review.setId(id);
		Recipe recipe = review.getRecipe();
		if (review.isVerifiedByModerator()) {
			recipe.addVerifiedReview(review);
		} else {
			recipe.addUnverifiedReview(review);
		}
		return review;
	}

	@Override
	public void deleteReview(int id) {
		reviewDao.deleteReview(id);

	}

	@Override
	public List<Review> getUnverfiedReviews() {
		return reviewDao.getUnverfiedReviews();
	}

	@Override
	public List<Review> getVerfiedReviews() {
		return reviewDao.getVerfiedReviews();
	}

	@Override
	public List<Review> getUnverfiedReviewsForRecipe(Recipe recipe) {
		return reviewDao.getUnverfiedReviewsForRecipe(recipe.getId());
	}

	@Override
	public List<Review> getVerfiedReviewsForRecipe(Recipe recipe) {
		return reviewDao.getVerfiedReviewsForRecipe(recipe.getId());
	}

	@Override
	public List<Review> getReviewsForRecipe(Recipe recipe) {
		return reviewDao.getReviewsForRecipe(recipe.getId());
	}

	


}
