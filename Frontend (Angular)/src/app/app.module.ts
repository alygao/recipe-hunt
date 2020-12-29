import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';  
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { IngredientModule } from './ingredients/ingredient.module'
import { FindRecipesModule } from './find-recipe/find-recipes.module';
import { RecipesModule} from './recipes/recipes.module'
import { ModeratorsModule } from './moderators/moderators.module'
import { AppComponent } from './app.component';
import { AuthService } from './auth.service';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ModeratorsModule,
    RecipesModule,
    IngredientModule,
    FindRecipesModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
