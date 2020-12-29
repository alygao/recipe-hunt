import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { AuthService } from 'src/app/auth.service';
import { Recipe } from 'src/app/recipes/recipe';
import { RecipeService } from 'src/app/recipes/recipe.service';
import { Review } from 'src/app/recipes/review';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-view-reviews-for-specific-recipe',
  templateUrl: './view-reviews-for-specific-recipe.component.html',
  styleUrls: ['./view-reviews-for-specific-recipe.component.css']
})
export class ViewReviewsForSpecificRecipeComponent implements OnInit {
  recipe$ : Observable<Recipe>;
  recipe: Recipe = {id: 0, name: '', ingredients: [], link: '', verifiedByModerator: false, moderator: null, unverifiedReviews: [], verifiedReviews: [], meals: [], type: ''};
  verifiedReviews: Review[] = [];
  unverifiedReviews: Review[] = [];


  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private moderatorService: ModeratorService,
    private authService : AuthService
  ) { }

  ngOnInit(): void {
    this.recipe$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.recipeService.getRecipe(+params.get('id')))
    );
    this.recipe$.subscribe(i => {
      this.recipe = i;
      this.verifiedReviews = i.verifiedReviews;
      this.unverifiedReviews = i.unverifiedReviews;
    });
  }

  backToPreviousPage() {
    this.location.back();
  }

  deleteReview(review:Review) {
    this.recipeService.deleteReview(review.id).subscribe(r => {
      if (review.verifiedByModerator) {
        let index = this.verifiedReviews.indexOf(review);
        this.verifiedReviews.splice(index, 1); 
      } else {
        let index = this.unverifiedReviews.indexOf(review);
        this.unverifiedReviews.splice(index, 1);
      }
    });
  }

  acceptUnverifiedReview(review:Review) {
    review.verifiedByModerator = true;
    console.log('recipe.id = ', this.recipe.id);
    review.recipe = this.recipe;
    console.log('review.recipe.id  = ', review.recipe.id );
    review.moderator = this.authService.currentModerator;
    this.recipeService.saveReview(review).subscribe(a => {
          let index = this.unverifiedReviews.indexOf(review);
          this.unverifiedReviews.splice(index, 1);
    });
    this.verifiedReviews.push(review);
  }


}
