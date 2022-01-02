import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Ingredient } from './ingredient';
import { AuthService } from '../auth.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private ingredientsURL = `${environment.apiURL}/recipe-hunt/ingredients`;  // URL to REST api
  constructor(private http: HttpClient, private authService: AuthService) { }

  convertToIngredients(ingredientNames : string[]) : Observable<Ingredient[]>  {

    console.log('IN INGREDIENT SERVICE: ', ingredientNames);
    const url = `${environment.apiURL}/recipe-hunt/convertToIngredients`;
    // return this.http.get<Ingredient[]>(this.ingredientsURL).pipe(
    //   catchError(this.handleError<Ingredient[]>('getIngredients'))
    // );

    
    return this.http.post(url, JSON.stringify(ingredientNames), {headers: this.headers}).pipe(
      catchError(this.handleError<any>('convertToIngredients')));

  }
  
  getIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.ingredientsURL).pipe(
      catchError(this.handleError<Ingredient[]>('getIngredients'))
    );
  }

  getIngredient(id: number): Observable<Ingredient> {
    const url = `${this.ingredientsURL}/${id}`;
    return this.http.get<Ingredient>(url).pipe(
      catchError(this.handleError<Ingredient>('getIngredient'))
    );
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  saveIngredient(ingredient: Ingredient): Observable<any> {
    const url = `${this.ingredientsURL}/${ingredient.id}`;
    return this.http
      .put(url, JSON.stringify(ingredient), this.authService.generateHeadersForApp())
      .pipe(
        catchError(this.handleError<any>('saveIngredient'))
      );
  }

  createIngredient(ingredient:Ingredient) : Observable<Ingredient> {
    const url = `${this.ingredientsURL}`;
    return this.http
      .post(url, JSON.stringify(ingredient), {headers: this.headers})
      .pipe(
        catchError(this.handleError<any>('createIngredient'))
      );
  }

  deleteIngredient(id : number) : Observable<any> {
    console.log('deleting ingredient...');
    const url = `${this.ingredientsURL}/${id}`;
    return this.http
      .delete(url, this.authService.generateHeadersForApp() )
      .pipe(
        catchError(this.handleError<any>('deleteIngredient'))
      );
  } 

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      // Let the app keep running by returning an empty result.
      return throwError('An error occurred in ' + operation);
    };
  }
}
