import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Recipe } from './recipe';
import { Meal } from './meal';
import { Review } from './review';
import {SubmittedReview}  from './review'
import { AuthService } from '../auth.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  // private reviewsURL = 'http://agserver:18080/recipe-hunt/reviews';
  // private mealsURL = 'http://agserver:18080/recipe-hunt/meals';
  // private recipesURL = 'http://agserver:18080/recipe-hunt/recipes';  // URL to REST api
  private reviewsURL = `${environment.apiURL}/recipe-hunt/reviews`;
  private mealsURL = `${environment.apiURL}/recipe-hunt/meals`;
  private recipesURL = `${environment.apiURL}/recipe-hunt/recipes`;  // URL to REST api
  constructor(private http: HttpClient, private authService: AuthService) { }


  getUnverifiedRecipes() : Observable<Recipe[]> {
    const url = this.recipesURL + '/unverified';
    // console.log('HEADERS = ', this.authService.generateHeadersForApp());
    return  this.http.get<Recipe[]>(url, this.authService.generateHeadersForApp()).pipe(
      catchError(this.handleError<Recipe[]>('getUnverifiedRecipes'))
    );
  }

  getVerifiedRecipes() : Observable<Recipe[]> {
    const url = this.recipesURL + '/verified';
    return  this.http.get<Recipe[]>(url).pipe(
      catchError(this.handleError<Recipe[]>('getUnverifiedRecipes'))
    );
  }


  getUnverifiedReviews() : Observable<SubmittedReview[]> {
    const url = this.reviewsURL + '/unverified';
    // console.log('HEADERS = ', this.authService.generateHeadersForApp());
    return  this.http.get<SubmittedReview[]>(url, this.authService.generateHeadersForApp()).pipe(
      catchError(this.handleError<SubmittedReview[]>('getUnverifiedReviews'))
    );
  }

  saveReview(review: Review) : Observable<SubmittedReview>{
    let submittedReview: SubmittedReview = {
      id : review.id,
      reviewerName: review.reviewerName, 
      reviewerEmail: review.reviewerEmail,
      review: review.review,
      recipeId: review.recipe.id,
      verifiedByModerator: review.verifiedByModerator,
      moderator: review.moderator,
      date: review.date
    }
    // console.log('RECIPE BEING SAVED ', JSON.stringify(submittedReview));
    const url = `${this.reviewsURL}/${review.id}`;
    return this.http
      .put(url, JSON.stringify(submittedReview), this.authService.generateHeadersForApp())
      .pipe(
        catchError(this.handleError<any>('saveReview'))
      );
  }

  deleteReview(reviewId: number) {
    const url = `${this.reviewsURL}/${reviewId}`;
    return this.http
      .delete(url, this.authService.generateHeadersForApp() )
      .pipe(
        catchError(this.handleError<any>('deleteReview'))
      );
  }
  
  getMeals(): Observable<Meal[]> {
    return this.http.get<Meal[]>(this.mealsURL).pipe(
      catchError(this.handleError<Meal[]>('getMeals'))
    );
  }

  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipesURL, this.authService.generateHeadersForApp()).pipe(
      catchError(this.handleError<Recipe[]>('getRecipes'))
    );
  }

  getRecipe(id: number): Observable<Recipe> {
    const url = `${this.recipesURL}/${id}`;
    return this.http.get<Recipe>(url, this.authService.generateHeadersForApp()).pipe(
      catchError(this.handleError<Recipe>('getRecipe'))
    );
  }

  getVerifiedRecipe(id: number): Observable<Recipe> {
    const url = `${this.recipesURL}/verified/${id}`;
    return this.http.get<Recipe>(url).pipe(
      catchError(this.handleError<Recipe>('getVerifiedRecipe'))
    );
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  saveRecipe(recipe: Recipe): Observable<any> {
    const url = `${this.recipesURL}/${recipe.id}`;
    return this.http
      .put(url, JSON.stringify(recipe), this.authService.generateHeadersForApp())
      .pipe(
        catchError(this.handleError<any>('saveRecipe'))
      );
  }

  addReview(review: any) : Observable<Review> {
    // console.log('SERVICE - REVIEW: ' + JSON.stringify(review));
    const url = `${this.reviewsURL}`;
    return this.http
    .post(url, JSON.stringify(review), {headers: this.headers})
    .pipe(
      catchError(this.handleError<any>('addReview'))
    );
  }

  createRecipe(recipe:Recipe) : Observable<Recipe> {
    const url = `${this.recipesURL}`;
    // console.log ( 'url', url );
    // console.log ( 'receipe json body', JSON.stringify(recipe) );
    return this.http
      .post(url, JSON.stringify(recipe), {headers: this.headers})
      .pipe(
        catchError(this.handleError<any>('createRecipe'))
      );
  }

  deleteRecipe(id : number) : Observable<any> {
    const url = `${this.recipesURL}/${id}`;
    return this.http
      .delete(url, this.authService.generateHeadersForApp() )
      .pipe(
        catchError(this.handleError<any>('deleteRecipe'))
      );
  } 

  private handleError<T>(operation = 'operation', result?: T) {
    // console.log ( 'error ****************', result, operation );
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      // Let the app keep running by returning an empty result.
      console.log ( 'real error', error );
      return throwError('An error occurred in ' + operation);
    };
  }
}
