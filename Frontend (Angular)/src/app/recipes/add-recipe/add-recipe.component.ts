import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef, AfterContentChecked} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FindRecipeService } from 'src/app/find-recipe/find-recipe.service';
import { RecipeService } from '../recipe.service';
import { Recipe } from '../recipe'
import { Ingredient } from 'src/app/ingredients/ingredient';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Validators } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { Meal } from '../meal';
import { IngredientService } from 'src/app/ingredients/ingredient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit, AfterContentChecked {

  recipeForm = this.fb.group({
    name: ['', Validators.required],
    link: ['', Validators.required],
    type: 'none',
    ingredients: this.fb.array([
      this.fb.control('')
    ], Validators.required)
  });

  ingredientNames : string[] = [];

  mealOptions$ : Observable<Meal[]>;
  meals : Meal[]; // TODO: DELETE LATER?
  mealsSelected : Meal[] = [];

  constructor(
    private fb: FormBuilder, 
    private router: Router, 
    private recipeService : RecipeService, 
    private ingredientService : IngredientService, 
    private cdref: ChangeDetectorRef ) { }
    
  ngAfterContentChecked(): void {
    this.cdref.detectChanges();
  }

  ngOnInit(): void {
    this.mealOptions$ = this.recipeService.getMeals();
    this.mealOptions$.subscribe(m => this.meals = m);
  }

  addIngredient() : void {
    this.ingredients.push(this.fb.control(''));
  }

  deleteIngredient(i: number) {
    this.ingredients.removeAt(i);
  }


  onSubmit() { 
    this.ingredientNames = [];
    for (let ingredient of this.ingredients.controls) {
      this.ingredientNames.push(ingredient.value);
    }

    let name = this.recipeForm.get('name').value;
    let link = this.recipeForm.get('link').value;
    let type = this.recipeForm.get('type').value;

    let ingredientsInRecipe : Ingredient[] = [];
    let ingredients$:Observable<Ingredient[]> = this.ingredientService.convertToIngredients(this.ingredientNames);
    ingredients$.subscribe(i => {
      ingredientsInRecipe = i;
      let recipe : Recipe = {
        id : -1, 
        name: name, 
        ingredients: ingredientsInRecipe, 
        link: link, 
        verifiedByModerator: false, 
        moderator: null,
        unverifiedReviews: [], 
        verifiedReviews: [], 
        type: type, 
        meals: this.mealsSelected
      };
  
      this.recipeService.createRecipe(recipe)
      .subscribe ( r => this.router.navigate(['/recipes']),
                   error => alert ( "Communication Error: " + error ) ) ;
    });
  }


  updateCheckedOptions(chBox, event) {
    var cbIdx = this.mealsSelected.indexOf(chBox);
    if(event.target.checked) {
        if(cbIdx < 0 )
          this.mealsSelected.push(chBox);
    } else {
        if(cbIdx >= 0 )
          this.mealsSelected.splice(cbIdx,1);
    }
  }

  get ingredients() {
    return this.recipeForm.get('ingredients') as FormArray;
  }
}
