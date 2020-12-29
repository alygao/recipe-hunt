import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanActivateChild, NavigationExtras } from '@angular/router';
import { Observable } from 'rxjs';

import { AuthService} from '../auth.service'

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild  {

  constructor(private authService: AuthService, private router: Router) {}
  
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    console.log('in canActivateChild');
    return this.canActivate(route, state);
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      console.log('AuthGuard#canActivate called');
      const url: string = state.url;

      return this.checkLogin(url);
      
  }



  checkLogin(url: string): true|UrlTree {
    console.log('IS LOGGED IN = ', this.authService.isLoggedIn);
    if (this.authService.isLoggedIn) { return true; }

    // Store the attempted URL for redirecting
    this.authService.redirectURL = url;

    // Create a dummy session id
    // const sessionId = 123456789;

    // Set our navigation extras object
    // that contains our global query params and fragment
    // const navigationExtras: NavigationExtras = {
    //   queryParams: { session_id: sessionId },
    //   fragment: 'anchor'
    // };

    // const navigationExtras: NavigationExtras = {
    //   queryParamsHandling: 'preserve',
    //   preserveFragment: true
    // };

    // Redirect to the login page
    return this.router.parseUrl('/login');
    // return this.router.createUrlTree(['/login'], navigationExtras);
  }
  
}
