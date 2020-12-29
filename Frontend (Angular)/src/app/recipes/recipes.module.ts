import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { AllRecipesComponent } from './all-recipes/all-recipes.component';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';

import { RecipesRoutingModule } from './recipes-routing.module';
import { AddReviewComponent } from './add-review/add-review.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    RecipesRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddRecipeComponent,
    AllRecipesComponent,
    RecipeDetailComponent,
    AddReviewComponent
  ]
})
export class RecipesModule {}