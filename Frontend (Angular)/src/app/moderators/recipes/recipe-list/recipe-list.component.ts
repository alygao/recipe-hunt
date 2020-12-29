import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth.service';
import { Recipe } from 'src/app/recipes/recipe';
import { RecipeService } from 'src/app/recipes/recipe.service';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  verifiedRecipes$: Observable<Recipe[]>;
  unverifiedRecipes$: Observable<Recipe[]>;
  selectedId: number;

  constructor(
    private recipeService: RecipeService,
    private moderatorService: ModeratorService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router,
    private authService : AuthService
  ) {}

  ngOnInit(): void {
    // this.recipes$ = this.recipeService.getRecipes();
    this.verifiedRecipes$ = this.recipeService.getVerifiedRecipes();
    this.unverifiedRecipes$ = this.recipeService.getUnverifiedRecipes();
  }

  editRecipe(id : number ){
    this.router.navigate(['/moderator/recipes/edit', id]);
  }

  seeReviewsForRecipe(id : number ){
    this.router.navigate(['/moderator/recipes/reviews', id]);
  }

  viewRecipe(id: number) {
    this.router.navigate(['/moderator/recipes/view', id]);
  }

  deleteRecipe(id: number, name: string) {
    if(confirm("Are you sure you want to delete "+ name)) {
      this.recipeService.deleteRecipe(id).subscribe(i => {
        this.verifiedRecipes$ = this.recipeService.getVerifiedRecipes();
        this.unverifiedRecipes$ = this.recipeService.getUnverifiedRecipes();
      });
    }
  }

  acceptRecipe(recipe: Recipe) {
    recipe.moderator = this.authService.currentModerator;
    recipe.verifiedByModerator = true;
    this.recipeService.saveRecipe(recipe).subscribe( r=> {
      this.verifiedRecipes$ = this.recipeService.getVerifiedRecipes();
      this.unverifiedRecipes$ = this.recipeService.getUnverifiedRecipes();
    });
  }

  
  backToPreviousPage() {
    this.location.back();
  }

}
