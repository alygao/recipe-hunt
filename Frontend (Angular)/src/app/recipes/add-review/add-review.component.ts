import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { Review } from '../review';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  recipe$ : Observable<Recipe>;
  recipe : Recipe;
  recipeName: string = '';

  reviewForm = this.fb.group({
    reviewerName: ['', Validators.required],
    reviewerEmail: ['', Validators.required],
    review: ['', Validators.required],
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private recipeService: RecipeService,
    private fb: FormBuilder, 
  ) { }

  ngOnInit(): void {
    this.recipe$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.recipeService.getVerifiedRecipe(+params.get('id')))
    );
    this.recipe$.subscribe(r => {
      this.recipe = r;
      this.recipeName = this.recipe.name;
    });
  }

  goBackToRecipeDetails() {
    if (confirm("Anything written will be lost. Are you sre you want to leave?")) {
      this.router.navigate(['/recipes/', this.recipe.id ]);
    }
  }

  onSubmit() { 

    this.recipe$.subscribe(r => {
      this.recipe = r;
      let reviewerName = this.reviewForm.get('reviewerName').value;
      let reviewerEmail = this.reviewForm.get('reviewerEmail').value;
      let review = this.reviewForm.get('review').value;
      let date = new Date() ;
  
      console.log('REVIEW\'S RECIPE: ' + JSON.stringify(this.recipe));
      let reviewToBeAdded = {
        id : -1, 
        reviewerName: reviewerName, 
        reviewerEmail: reviewerEmail, 
        review: review, 
        recipeId: this.recipe.id,
        verifiedByModerator: false, 
        moderator: null,
        date: date,
      };
      this.recipeService.addReview(reviewToBeAdded)
      .subscribe ( r => this.router.navigate(['/recipes/verified/', this.recipe.id ]),
                   error => alert ( "Communication Error: " + error ) ) ;
    });

  }

}
