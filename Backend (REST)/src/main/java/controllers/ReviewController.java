package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Recipe;
import domain.Review;
import domain.SubmittedReview;
import services.RecipeService;
import services.ReviewService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class ReviewController {

 @Autowired
 private ReviewService reviewService;
 @Autowired
 private RecipeService recipeService;
 
 @RequestMapping(value = "/reviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Review> getAllReviews ( ) {
  try {
   List<Review> reviewList = this.reviewService.getReviews();
   for ( Review review : reviewList ) {
    System.out.println( review );
   }
   return reviewList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/reviews/verified", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Review> getAllVerifiedReviews ( ) {
  try {
   List<Review> reviewList = this.reviewService.getVerfiedReviews();
   for ( Review review : reviewList ) {
    System.out.println( review );
   }
   return reviewList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/reviews/unverified", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<SubmittedReview> getAllUnverifiedReviews ( ) {
	 System.out.println(" ++++++++++ getting all unverified reviews +++++");
  try {
   List<Review> reviewList = this.reviewService.getUnverfiedReviews();
   List<SubmittedReview> submittedReviewList = new ArrayList<>();
   for ( Review r : reviewList ) {
    System.out.println( r );
    SubmittedReview submittedReview = new SubmittedReview(r.getId(), r.getReviewerName(), r.getReviewerEmail(), 
    		r.getReview(), r.getRecipe().getId(), r.isVerifiedByModerator(), r.getModerator(), r.getDate());
    submittedReviewList.add(submittedReview);
   }
   System.out.println("REVIEWS: " + submittedReviewList);
   
   return submittedReviewList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/reviews/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Review getReview (@PathVariable int id) {
	 return reviewService.getReview(id);
 }
 
 @RequestMapping(value = "/reviews/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody SubmittedReview saveReview (@PathVariable int id, @RequestBody SubmittedReview submittedReview) {
	 System.out.println(" ++++++++++ saving review (unverify->verify) +++++");
	 Review reviewToBeSaved = new Review(submittedReview.getId(), submittedReview.getReviewerName(), submittedReview.getReviewerEmail(), 
			 submittedReview.getReview(), recipeService.getRecipe(submittedReview.getRecipeId()), submittedReview.isVerifiedByModerator(), submittedReview.getModerator(), 
			 submittedReview.getDate());
	 System.out.println("review being saved = " + reviewToBeSaved);
	 Review r = reviewService.saveReview(reviewToBeSaved, id);
	 SubmittedReview result = new SubmittedReview(r.getId(), r.getReviewerName(), r.getReviewerEmail(), 
	    		r.getReview(), r.getRecipe().getId(), r.isVerifiedByModerator(), r.getModerator(), r.getDate());
	 return result;
 }
 
 @RequestMapping(value = "/reviews", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 @ResponseStatus (HttpStatus.CREATED)
 public @ResponseBody Review addReview (@RequestBody SubmittedReview review) {
	 System.out.println(" **************** IN SPINRG - REVIEW CONTROLLER ***********");	
	 System.out.println("review: " + review);
	 Recipe recipe = recipeService.getRecipe(review.getRecipeId());
	 Review review2 = new Review (review.getId(), review.getReviewerName(), review.getReviewerEmail(), 
			 review.getReview(), recipe, review.isVerifiedByModerator(), review.getModerator(), review.getDate());
	 System.out.println("review's recipe: " + recipe);
	 return reviewService.addReview(review2);
 }
 
 @RequestMapping(value = "/reviews/{id}", method = RequestMethod.DELETE)
 @ResponseStatus (HttpStatus.NO_CONTENT)
 public @ResponseBody void deleteReview (@PathVariable int id) {
	 reviewService.deleteReview(id);
 }
 
// @RequestMapping(value = "/searchReviews", method = RequestMethod.GET)
// public @ResponseBody List<Review> searchReviews (@RequestParam(value = "term") String term) {
//	 return reviewService.searchReviews(term);
// }

}

