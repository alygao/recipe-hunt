import { Component, OnInit } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Recipe } from '../../recipes/recipe'
import { FindRecipeService} from '../find-recipe.service'
import { IngredientService} from '../../ingredients/ingredient.service'
import { Ingredient } from 'src/app/ingredients/ingredient';
import { Router } from '@angular/router';

@Component({
  selector: 'app-find-recipe',
  templateUrl: './find-recipe.component.html',
  styleUrls: ['./find-recipe.component.css']
})
export class FindRecipeComponent implements OnInit {

  // recipes: Observable<Recipe[]>;
  ingredients : Observable<Ingredient[]>;
  private ingredientSearchTerm = new Subject<string>();
  searchBoxValue : string = '';
  ingredientsEntered : string[] = [];

  constructor( 
    private findRecipeService: FindRecipeService, 
    private ingredientService : IngredientService,
    private router: Router
    ) { }

  // Push a search term into the observable stream.
  search( term: string ): void {
      this.ingredientSearchTerm.next( term );
  }

  ngOnInit(): void {
    this.ingredients = this.ingredientSearchTerm.pipe(
        debounceTime( 100 ),      // wait 300ms after each keystroke before considering the term
        distinctUntilChanged(),   // ignore if next search term is same as previous
        switchMap( term =>    // switch to new observable each time the term changes
            // return the http search observable
            // ? 
            this.findRecipeService.search( term )));
    }

  addToSearchBox(ingredient:Ingredient) : void {
    // console.log(this.ingredientSearchTerm)
    // this.ingredientSearchTerm.next('');
    this.ingredientSearchTerm.next( ingredient.name ); // TODO: do i need it?
    this.searchBoxValue = ingredient.name;
    this.addIngredient();
    this.ingredients = this.ingredientSearchTerm.pipe(
      debounceTime( 100 ),      // wait 300ms after each keystroke before considering the term
      distinctUntilChanged(),   // ignore if next search term is same as previous
      switchMap( term =>    // switch to new observable each time the term changes
          // return the http search observable
          // ? 
          this.findRecipeService.search( term )));
  
  }

  addIngredient() : void {
    if (this.ingredientsEntered.indexOf(this.searchBoxValue) == -1 && this.searchBoxValue.length > 0) {
      this.ingredientsEntered.push(this.searchBoxValue);
    }
    this.searchBoxValue = '';
    // console.log(this.ingredientsEntered);
  }

  deleteIngredient(name : string) : void {
    const index: number = this.ingredientsEntered.indexOf(name);
    if (index !== -1) {
      this.ingredientsEntered.splice(index, 1);
    }   
  }

  searchForRecipes() : void {
    // console.log("in searchForRecipes in component");
    this.findRecipeService.findRecipes(this.ingredientsEntered);
    this.router.navigate(['/find-recipe/results']);
  }
}


