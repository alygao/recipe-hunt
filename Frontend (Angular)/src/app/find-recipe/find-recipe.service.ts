import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Recipe } from '../recipes/recipe';
import {Ingredient} from '../ingredients/ingredient'

@Injectable({
  providedIn: 'root'
})
export class FindRecipeService {
  recipesFound$ : Observable<Recipe[]>;

  private findrecipesURL = 'http://localhost:8080/recipe-hunt/find-recipes';  // URL to REST api
  constructor(private http: HttpClient) { }
  
  search(term:string) : Observable<Ingredient[]> {
    const url = `http://localhost:8080/recipe-hunt/searchIngredients?term=${term}`;
    return this.http
      .get(url)
      .pipe(
        catchError(this.handleError<any>('searchTerm'))
      );
  }


  findRecipes(ingredientNames : string[]) : void{
    const url = `http://localhost:8080/recipe-hunt/findRecipes`;
    // console.log(JSON.stringify(ingredientNames));
    // console.log("in findRecipes");
    if (ingredientNames.length == 0) {
      this.recipesFound$ = null;
    } else {
    this.recipesFound$ = this.http.post(url, JSON.stringify(ingredientNames), {headers: this.headers}).pipe(
      catchError(this.handleError<any>('findRecipes')));
    }
    // let recipesFound:Recipe[];
    // this.recipesFound$.subscribe(r  => recipesFound = r);
    // console.log(recipesFound);

  }

  findRecipe(id: number): Observable<Recipe> {
    const url = `${this.findrecipesURL}/${id}`;
    return this.http.get<Recipe>(url).pipe(
      catchError(this.handleError<Recipe>('findRecipe'))
    );
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  // saveRecipe(recipe: Recipe): Observable<any> {
  //   const url = `${this.findrecipesURL}/${recipe.id}`;
  //   return this.http
  //     .put(url, JSON.stringify(recipe), {headers: this.headers})
  //     .pipe(
  //       catchError(this.handleError<any>('saveRecipe'))
  //     );
  // }

  // createRecipe(recipe: Recipe) : Observable<Recipe> {
  //   const url = `${this.findrecipesURL}`;
  //   return this.http
  //     .post(url, JSON.stringify(recipe), {headers: this.headers})
  //     .pipe(
  //       catchError(this.handleError<any>('createRecipe'))
  //     );
  // }

  // deleteRecipe(id : number) : Observable<any> {
  //   const url = `${this.findrecipesURL}/${id}`;
  //   return this.http
  //     .delete(url )
  //     .pipe(
  //       catchError(this.handleError<any>('deleteRecipe'))
  //     );
  // } 

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      // Let the app keep running by returning an empty result.
      return throwError('An error occurred in ' + operation);
    };
  }
}
