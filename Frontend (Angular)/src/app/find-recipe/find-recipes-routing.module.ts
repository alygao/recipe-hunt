import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FindRecipeComponent } from './find-recipe/find-recipe.component';
import { RecipesQueryResultsComponent } from './recipes-query-results/recipes-query-results.component';

const findRecipesRoutes: Routes = [
    { path: 'find-recipe',  component: FindRecipeComponent, /*data: { animation: 'add-heroes' }*/ },
    { path: 'find-recipe/results',  component: RecipesQueryResultsComponent, /*data: { animation: 'heroes'* }*/ },
    // { path: 'recipes/:id', component: RecipeDetailComponent, /*data: { animation: 'hero' }*/ }
];

@NgModule({
  imports: [
    RouterModule.forChild(findRecipesRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class FindRecipesRoutingModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/