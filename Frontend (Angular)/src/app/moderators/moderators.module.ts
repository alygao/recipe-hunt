import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ModeratorsRoutingModule} from './moderators-routing.module';
import { LoginComponent } from './login/login.component'
import { ModeratorHomeComponent } from './moderator-home/moderator-home.component';
import { EditIngredientsListComponent } from './editIngredients/edit-ingredients-list/edit-ingredients-list.component';
import { EditSpecificIngredientComponent } from './editIngredients/edit-specific-ingredient/edit-specific-ingredient.component';
import { VerifyReviewsComponent } from './verify-reviews/verify-reviews.component';
import { RecipeListComponent } from './recipes/recipe-list/recipe-list.component';
import { EditSpecificRecipeComponent } from './recipes/edit-specific-recipe/edit-specific-recipe.component';
import { ViewReviewsForSpecificRecipeComponent } from './recipes/view-reviews-for-specific-recipe/view-reviews-for-specific-recipe.component';
import { ViewSpecificRecipeComponent } from './recipes/view-specific-recipe/view-specific-recipe.component';
import { ModeratorsListComponent } from './moderator/moderators-list/moderators-list.component';
import { ModeratorApplicationsComponent } from './moderator/moderator-applications/moderator-applications.component';
import { ModeratorSignUpComponent } from './moderator/moderator-sign-up/moderator-sign-up.component'
import { AuthService } from '../auth.service';



@NgModule({
  declarations: [
    LoginComponent,
    ModeratorHomeComponent,
    EditIngredientsListComponent,
    EditSpecificIngredientComponent,
    VerifyReviewsComponent,
    RecipeListComponent,
    EditSpecificRecipeComponent,
    ViewReviewsForSpecificRecipeComponent,
    ViewSpecificRecipeComponent,
    ModeratorsListComponent,
    ModeratorApplicationsComponent,
    ModeratorSignUpComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    ModeratorsRoutingModule,
  ],
  providers: [AuthService],
})
export class ModeratorsModule { }
