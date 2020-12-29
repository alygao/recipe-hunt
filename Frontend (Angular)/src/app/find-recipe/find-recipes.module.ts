import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FindRecipeComponent } from './find-recipe/find-recipe.component';
import { RecipesQueryResultsComponent } from './recipes-query-results/recipes-query-results.component';

import { FindRecipesRoutingModule } from './find-recipes-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FindRecipesRoutingModule
  ],
  declarations: [
    FindRecipeComponent,
    RecipesQueryResultsComponent,
  ]
})
export class FindRecipesModule {}