import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { subscribeOn } from 'rxjs/operators';
import { AuthService } from 'src/app/auth.service';
import { Moderator } from '../moderator';
import { ModeratorService } from '../moderator.service';

@Component({
  selector: 'app-moderator-home',
  templateUrl: './moderator-home.component.html',
  styleUrls: ['./moderator-home.component.css']
})
export class ModeratorHomeComponent implements OnInit {

  moderator : Moderator;

  constructor(private router: Router, private authService : AuthService) { 
    this.moderator = this.authService.currentModerator;
  }

  ngOnInit(): void {
  }

  gotoModeratorRecipes() {
    console.log('clicked')
    this.router.navigate(['/moderator/recipes']);
  }

  gotoModeratorIngredients() {
    this.router.navigate(['/moderator/ingredients']);
  }

  gotoModeratorReviews() {
    this.router.navigate(['/moderator/reviews']);
  }

  gotoModeratorApplications() {
    this.router.navigate(['/moderator/moderators']);
  }

}
