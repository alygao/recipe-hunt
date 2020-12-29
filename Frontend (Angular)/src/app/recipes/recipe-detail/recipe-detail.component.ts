import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  recipe$ : Observable<Recipe>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: RecipeService,
    private location: Location
  ) {   }

  ngOnInit(): void {
    this.recipe$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.service.getVerifiedRecipe(+params.get('id')))
    );
    this.recipe$.subscribe(r => {
      if (r == null) {
        this.gotoRecipes();
      }
    })
  }

  gotoRecipes() {
    this.router.navigate(['/recipes']);
  }

  gotoWriteReview (recipe: Recipe) {
    const recipeId = recipe ? recipe.id : null;
    this.router.navigate(['/recipes/add-review/', recipeId]);
  }

  backToPreviousPage() {
    this.location.back();
  }

}
