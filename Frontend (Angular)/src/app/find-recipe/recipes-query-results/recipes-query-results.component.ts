import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Recipe } from 'src/app/recipes/recipe';
import { FindRecipeService } from '../find-recipe.service';

@Component({
  selector: 'app-recipes-query-results',
  templateUrl: './recipes-query-results.component.html',
  styleUrls: ['./recipes-query-results.component.css']
})
export class RecipesQueryResultsComponent implements OnInit {

  recipes$: Observable<Recipe[]>;
  selectedId: number;

  constructor(
    private service: FindRecipeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.recipes$ = this.service.recipesFound$;
    // console.log("aaa");
    // this.recipes$.subscribe(r => console.log("Recipe: " , r));
  }

  goBack() : void {
    this.router.navigate(['/find-recipe']);
  }

  viewRecipe(id: number) {
    this.router.navigate(['/recipes/verified/', id]);
  }

}

