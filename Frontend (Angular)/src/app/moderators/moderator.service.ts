import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { tap, delay, catchError } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Moderator, ModeratorApplication } from './moderator';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ModeratorService {

  // currentModerator : Moderator = null;

  // private moderatorURL = 'http://agserver:18080/recipe-hunt/admin/moderators';  // URL to REST api
  // private moderatorApplicationURL = 'http://agserver:18080/recipe-hunt/moderator-applications';  // URL to REST api
  private moderatorURL = `${environment.apiURL}/recipe-hunt/admin/moderators`;  // URL to REST api
  private moderatorApplicationURL = `${environment.apiURL}//recipe-hunt/moderator-applications`;  // URL to REST api
  constructor(private http: HttpClient, private authService : AuthService) { 

    // // TODO: modify to accept logged in moderator 
    // this.getModerator(1).subscribe(m => {
    //   this.currentModerator = m;
    //   console.log('CURRENT MODERATOR = ' + this.currentModerator.name);
    // });
  }

  createModeratorApplication(moderatorApplication: ModeratorApplication) : Observable<ModeratorApplication> {
    const url = `${this.moderatorApplicationURL}`;
    return this.http
    .post(url, JSON.stringify(moderatorApplication), this.authService.generateHeadersForApp())
    .pipe(
      catchError(this.handleError<any>('createModeratorApplication'))
    );
  }

  deleteModeratorApplication(id : number) : Observable<any> {
    const url = `${this.moderatorApplicationURL}/${id}`;
    return this.http
      .delete(url, this.authService.generateHeadersForApp() )
      .pipe(
        catchError(this.handleError<any>('deleteModeratorApplication'))
      );
  } 

  addModerator(moderator: Moderator) : Observable<Moderator> {
    const url = `${this.moderatorURL}`;
    return this.http
    // .post(url, JSON.stringify(moderator), {headers: this.headers})
    .post(url, JSON.stringify(moderator), this.authService.generateHeadersForApp())
    .pipe(
      catchError(this.handleError<any>('addModerator'))
    );
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  saveModerator(moderator: Moderator) : Observable<Moderator> {
      const url = `${this.moderatorURL}/${moderator.id}`;
      return this.http
        .put(url, JSON.stringify(moderator), this.authService.generateHeadersForApp())
        .pipe(
          catchError(this.handleError<any>('saveModerator'))
        );
  }

  getModerator(id: number): Observable<Moderator> {
    const url = `${this.moderatorURL}/${id}`;
    return this.http.get<Moderator>(url).pipe(
      catchError(this.handleError<Moderator>('getModerator'))
    );
  }

  getModerators() {
    return this.http.get<Moderator[]>(this.moderatorURL).pipe(
      tap(m => {

      }),
      catchError(this.handleError<Moderator[]>('getModerators'))
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



  // LOGIN
    // store the URL so we can redirect after logging in
    redirectUrl: string;
    isLoggedIn = false;

    login(): Observable<boolean> {
      return of(true).pipe(
        delay(1000),
        tap(val => this.isLoggedIn = true)
      );
    }
  
    logout(): void {
      this.isLoggedIn = false;
    }



}
