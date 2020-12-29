import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../moderators/auth.guard';
import { ModeratorHomeComponent} from '../moderators/moderator-home/moderator-home.component'
import { LoginComponent } from './login/login.component'
import { EditIngredientsListComponent } from './editIngredients/edit-ingredients-list/edit-ingredients-list.component';
import { EditSpecificIngredientComponent } from './editIngredients/edit-specific-ingredient/edit-specific-ingredient.component'
import { VerifyReviewsComponent } from './verify-reviews/verify-reviews.component'
import { RecipeListComponent } from './recipes/recipe-list/recipe-list.component';
import { EditSpecificRecipeComponent } from './recipes/edit-specific-recipe/edit-specific-recipe.component';
import { ViewReviewsForSpecificRecipeComponent } from './recipes/view-reviews-for-specific-recipe/view-reviews-for-specific-recipe.component'
import { ViewSpecificRecipeComponent } from './recipes/view-specific-recipe/view-specific-recipe.component'
import { ModeratorsListComponent } from './moderator/moderators-list/moderators-list.component';
import { ModeratorApplicationsComponent } from './moderator/moderator-applications/moderator-applications.component'
import { ModeratorSignUpComponent } from './moderator/moderator-sign-up/moderator-sign-up.component'


const moderatorsRoutes: Routes = [
  { path: 'mrecipes', component:RecipeListComponent },
  { path: 'login', component:LoginComponent },
  {
    path: 'moderator',
    // component: ModeratorHomeComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      // {
      //   path: '',
      //   children: [
          { path: '', component: ModeratorHomeComponent },
          { path: 'ingredients', component: EditIngredientsListComponent },
          { path: 'ingredients/:id', component:EditSpecificIngredientComponent },
          { path: 'reviews', component:VerifyReviewsComponent },
          { path: 'recipes', component:RecipeListComponent },
          { path: 'recipes/edit/:id', component:EditSpecificRecipeComponent },
          { path: 'recipes/view/:id', component:ViewSpecificRecipeComponent },
          { path: 'recipes/reviews/:id', component:ViewReviewsForSpecificRecipeComponent },
          { path: 'moderators', component:ModeratorsListComponent },
          { path: 'moderators/applications', component:ModeratorApplicationsComponent },
          { path: 'apply', component:ModeratorSignUpComponent },
      //   ]
      // }
    ]
  },



    // { path: 'moderators/add-moderator',  component: AddModeratorComponent, /*data: { animation: 'add-heroes' }*/ },
    // { path: 'moderators',  component: AllModeratorsComponent, /*data: { animation: 'heroes'* }*/ },
    // { path: 'moderators/:id', component: ModeratorDetailComponent, /*data: { animation: 'hero' }*/ }
];

@NgModule({
  imports: [
    RouterModule.forChild(moderatorsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ModeratorsRoutingModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/