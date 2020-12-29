import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FindRecipeService } from 'src/app/find-recipe/find-recipe.service';
import { Ingredient } from 'src/app/ingredients/ingredient';
import { IngredientService } from 'src/app/ingredients/ingredient.service';

@Component({
  selector: 'app-edit-ingredients-list',
  templateUrl: './edit-ingredients-list.component.html',
  styleUrls: ['./edit-ingredients-list.component.css']
})
export class EditIngredientsListComponent implements OnInit {
  
  ingredients$: Observable<Ingredient[]>;
  selectedId: number;

  constructor(
    private ingredientService: IngredientService,
    private findRecipeService : FindRecipeService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.ingredients$ = this.ingredientService.getIngredients();
  }

  seeRecipes(name : string) {
    // make ingredient array to pass to service function
    let ingredientNames : string[] = [];
    ingredientNames.push(name);
    this.findRecipeService.findRecipes(ingredientNames);
    this.router.navigate(['/find-recipe/results']);
  }

  editIngredient(id : number ){
    this.router.navigate(['/moderator/ingredients', id]);
  }

  deleteIngredient(id: number, name: string) {
    if(confirm("Are you sure you want to delete "+ name)) {
      this.ingredientService.deleteIngredient(id).subscribe(i =>
      this.ingredients$ = this.ingredientService.getIngredients() );
    }
  }

  backToPreviousPage() {
    this.location.back();
  }

}
