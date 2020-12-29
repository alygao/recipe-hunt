import { Location } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { FindRecipeService } from 'src/app/find-recipe/find-recipe.service';
import { Ingredient } from 'src/app/ingredients/ingredient';
import { IngredientService } from 'src/app/ingredients/ingredient.service';
import { Meal } from 'src/app/recipes/meal';
import { Recipe } from 'src/app/recipes/recipe';
import { RecipeService } from 'src/app/recipes/recipe.service';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-edit-specific-recipe',
  templateUrl: './edit-specific-recipe.component.html',
  styleUrls: ['./edit-specific-recipe.component.css']
})
export class EditSpecificRecipeComponent implements OnInit {



  ingredientNames : string[] = [];

  mealOptions$ : Observable<Meal[]>;
  meals : Meal[] = []; // TODO: DELETE LATER?
  mealsSelected : Meal[] = [];

  recipe$ : Observable<Recipe>;
  recipe: Recipe = {id: 0, name: '', ingredients: [], link: '', verifiedByModerator: false, moderator: null, unverifiedReviews: [], verifiedReviews: [], meals: [], type: ''};

  recipeForm = this.fb.group({
    name: ['', Validators.required],
    link: ['', Validators.required],
    type: 'none',
    ingredients: this.fb.array([
      this.fb.control('')
    ], Validators.required)
  });

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private moderatorService: ModeratorService,
    private fb: FormBuilder, 
    private router: Router, 
    private ingredientService : IngredientService, 
    private cdref: ChangeDetectorRef 
  ) { }

  ngOnInit(): void {
    this.recipe$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.recipeService.getRecipe(+params.get('id')))
    );
    this.recipe$.subscribe(i => {
      this.recipe = i;
      console.log('recipe: ', this.recipe);
      this.mealOptions$ = this.recipeService.getMeals();
      this.mealOptions$.subscribe(m => {
        this.meals = m
        for (var ingredient of i.ingredients) {
          this.ingredientNames.push(ingredient.name);
        }
  
        for (var meal of i.meals) {
          this.mealsSelected.push(meal);
        }
  
        this.recipeForm.controls['name'].setValue(i.name);
        this.recipeForm.controls['link'].setValue(i.link);
        this.recipeForm.controls['type'].setValue(i.type);
  
        this.recipeForm.setControl('ingredients', this.fb.array(this.ingredientNames || []));
      });
    });
  }


  inMealsSelected(meal:Meal) : boolean {
    return this.mealsSelected.some (m =>
      m.id === meal.id && m.name === meal.name
    );
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
      console.log('ingredients converted: ', ingredientsInRecipe);

      this.recipe.name = name;
      this.recipe.ingredients = ingredientsInRecipe;
      this.recipe.link = link;
      this.recipe.type = type;
      this.recipe.meals = this.mealsSelected;
  
      this.recipeService.saveRecipe(this.recipe)
      .subscribe ( r =>  this.location.back(),
                   error => alert ( "Communication Error: " + error ) ) ;
    });
  }

  addIngredient() : void {
    this.ingredients.push(this.fb.control(''));
  }

  deleteIngredient(i: number) {
    this.ingredients.removeAt(i);
  }
    
  backToPreviousPage() {
    if (confirm('Any changes will not be saved. Discard changes?')) {
      this.location.back();
    }
  }

  updateCheckedOptions(chBox, event) {
    var cbIdx = this.mealsSelected.indexOf(chBox);
    if(event.target.checked) {
        if(cbIdx < 0 && !this.inMealsSelected(chBox))
          this.mealsSelected.push(chBox);
    } else {
        if(cbIdx >= 0 || this.inMealsSelected(chBox))
          this.mealsSelected.splice(cbIdx,1);
    }
    console.log('UPDATE CHECKBOX - MEALS SELECTED: ' , this.mealsSelected);
  }

  get ingredients() {
    return this.recipeForm.get('ingredients') as FormArray;
  }
  

}
