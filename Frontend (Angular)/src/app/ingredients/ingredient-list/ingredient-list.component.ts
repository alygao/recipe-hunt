import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FindRecipeService } from 'src/app/find-recipe/find-recipe.service';
import { Ingredient } from '../ingredient';
import { IngredientService } from '../ingredient.service'

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: [ './ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {

  ingredients$: Observable<Ingredient[]>;
  selectedId: number;

  constructor(
    private service: IngredientService,
    private findRecipeService : FindRecipeService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ingredients$ = this.service.getIngredients();
  }

  seeRecipes(name : string) {
    // make ingredient array to pass to service function
    let ingredientNames : string[] = [];
    ingredientNames.push(name);
    this.findRecipeService.findRecipes(ingredientNames);
    this.router.navigate(['/find-recipe/results']);
  }
}
