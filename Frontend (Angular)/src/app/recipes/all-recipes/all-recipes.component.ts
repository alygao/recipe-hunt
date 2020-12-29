import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Recipe } from '../recipe';
import {RecipeService} from '../recipe.service'

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit {

  recipes$: Observable<Recipe[]>;

  constructor(
    private service: RecipeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.recipes$ = this.service.getVerifiedRecipes();
  }

  viewRecipe(id: number) {
    this.router.navigate(['/recipes/verified', id]);
  }

}
