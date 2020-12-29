import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth.service';
import { Recipe } from 'src/app/recipes/recipe';
import { RecipeService } from 'src/app/recipes/recipe.service';
import { Review, SubmittedReview } from 'src/app/recipes/review';
import { ModeratorService } from '../moderator.service';

@Component({
  selector: 'app-verify-reviews',
  templateUrl: './verify-reviews.component.html',
  styleUrls: ['./verify-reviews.component.css']
})
export class VerifyReviewsComponent implements OnInit {

  submittedReviews$ : Observable<SubmittedReview[]>;
  unverifiedReviews: Review[] = [];

  constructor(
    private recipeService: RecipeService,
    private moderatorService: ModeratorService,
    private router: Router,
    private location: Location,
    private authService : AuthService
    ) { }

  ngOnInit(): void {
    this.submittedReviews$ = this.recipeService.getUnverifiedReviews();
    this.submittedReviews$.subscribe(r => {
        for (var s of r) {
          let review: Review = {id: 0, reviewerName:'', reviewerEmail:'', review:'', recipe:null, verifiedByModerator:false, moderator:null, date:null};
          review.id = s.id;
          review.reviewerName = s.reviewerName;
          review.reviewerEmail = s.reviewerEmail;
          review.review = s.review;
          let recipe$ = this.recipeService.getRecipe(s.recipeId);
          recipe$.subscribe(recipe => review.recipe = recipe);
          review.verifiedByModerator = s.verifiedByModerator;
          review.moderator = s.moderator;
          review.date = s.date;
          this.unverifiedReviews.push(review);
        }
    })
    }

  accept (review: Review) {
    // console.log( JSON.stringify(this.unverifiedReviews));
    review.verifiedByModerator = true;
    review.moderator = this.authService.currentModerator;
    this.recipeService.saveReview(review).subscribe(a => {
          let index = this.unverifiedReviews.indexOf(review);
          this.unverifiedReviews.splice(index, 1);
    });
  }
  reject (review: Review) {
    this.recipeService.deleteReview(review.id).subscribe(r => {
      let index = this.unverifiedReviews.indexOf(review);
      this.unverifiedReviews.splice(index, 1);
    });
    
  }
  viewRecipe (recipe : Recipe) {
    this.router.navigate(['/recipes', recipe.id]);
  }

  backToPreviousPage() {
    this.location.back();
  }

}
