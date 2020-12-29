import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IngredientRoutingModule } from './ingredient-routing.module';
import { IngredientListComponent } from './ingredient-list/ingredient-list.component';

@NgModule({
  declarations: [IngredientListComponent],
  imports: [
    CommonModule,
    IngredientRoutingModule
  ]
})
export class IngredientModule { }
