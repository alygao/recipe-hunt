import { Injectable } from '@angular/core';
import { Moderator } from './moderators/moderator'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError, tap} from 'rxjs/operators'
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private AUTH_URL = `${environment.apiURL}/recipe-hunt/auth/authenticate`;
  private _currentModerator: Moderator;

  public isLoggedIn = false;
  public redirectURL : string = 'moderator';

  constructor( private http : HttpClient) { }

  private generateHeaders (username: string, password: string) : { headers?: HttpHeaders | { [header: string]: string | string[]; }} {
    let headers = {headers: new HttpHeaders ({
      'Content-Type': 'application/json',
      'Authorization' : 'Basic ' + btoa(username + ":" + password)
    })};
    console.log("headers = ", headers);
    return headers;
  }

  get currentModerator() {
    return this._currentModerator;
  }

  public generateHeadersForApp () : { headers?: HttpHeaders | { [header: string]: string | string[]; }}  {
    if (this.currentModerator) {
      return {headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization' : 'Basic ' + btoa(this.currentModerator.username + ":" + this.currentModerator.password)
      })};
    } else {
      return {headers: new HttpHeaders ({
        'Content-Type': 'application/json'
      })};
    }
  }

  authenticate(username: string, password: string) : Observable<Moderator> {
     return this.http.get<Moderator>( `${this.AUTH_URL}/${username}`,
      this.generateHeaders(username, password)).pipe(
        tap((mod) => {
          this._currentModerator = mod;
          this._currentModerator.password = password;
          this.isLoggedIn = true;
        }),
        catchError(this.handleError<Moderator>('authenticate'))
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

  logout() {
    this.isLoggedIn = false;
    this._currentModerator = undefined;
  }
  
}
