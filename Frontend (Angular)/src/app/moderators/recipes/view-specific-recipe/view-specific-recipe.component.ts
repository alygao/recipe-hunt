import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Recipe } from 'src/app/recipes/recipe';
import { RecipeService } from 'src/app/recipes/recipe.service';

@Component({
  selector: 'app-view-specific-recipe',
  templateUrl: './view-specific-recipe.component.html',
  styleUrls: ['./view-specific-recipe.component.css']
})
export class ViewSpecificRecipeComponent implements OnInit {

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
        this.service.getRecipe(+params.get('id')))
    );
  }

  backToPreviousPage() {
    this.location.back();
  }

}
