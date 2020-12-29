import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IngredientListComponent } from './ingredient-list/ingredient-list.component';

const ingredientsRoutes: Routes = [
    { path: 'ingredients',  component: IngredientListComponent, /*data: { animation: 'heroes'* }*/ },
];

@NgModule({
  imports: [
    RouterModule.forChild(ingredientsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class IngredientRoutingModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/