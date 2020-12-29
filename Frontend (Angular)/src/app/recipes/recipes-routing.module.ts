import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { AllRecipesComponent } from './all-recipes/all-recipes.component';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { AddReviewComponent } from './add-review/add-review.component';

const recipesRoutes: Routes = [
    { path: 'recipes/add-recipe',  component: AddRecipeComponent, /*data: { animation: 'add-heroes' }*/ },
    { path: 'recipes',  component: AllRecipesComponent, /*data: { animation: 'heroes'* }*/ },
    { path: 'recipes/verified/:id', component: RecipeDetailComponent, /*data: { animation: 'hero' }*/ },
    { path: 'recipes/add-review/:id', component: AddReviewComponent,}
];

@NgModule({
  imports: [
    RouterModule.forChild(recipesRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class RecipesRoutingModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/