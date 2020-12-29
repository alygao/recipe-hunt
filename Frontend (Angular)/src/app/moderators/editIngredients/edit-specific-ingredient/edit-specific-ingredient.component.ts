import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Ingredient } from 'src/app/ingredients/ingredient';
import { IngredientService } from 'src/app/ingredients/ingredient.service';

@Component({
  selector: 'app-edit-specific-ingredient',
  templateUrl: './edit-specific-ingredient.component.html',
  styleUrls: ['./edit-specific-ingredient.component.css']
})
export class EditSpecificIngredientComponent implements OnInit {
  ingredient$: Observable<Ingredient>;
  ingredient: Ingredient;
  editName : string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ingredientService: IngredientService,
    // private dialogService : DialogService
  ) { }

  ngOnInit(): void {
    this.ingredient$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.ingredientService.getIngredient(+params.get('id')))
    );
    this.ingredient$.subscribe(i => this.ingredient = i);
  }

  goToEditIngredients() {
    this.router.navigate(['/moderator/ingredients']);
  }

  cancel(ingredient : Ingredient) {
    if (this.canDeactivate()) {
      this.goToEditIngredients();
    }
  }
  
  save(ingredient : Ingredient) {
    if (this.editName.length > 0) {
      ingredient.name = this.editName;
      this.ingredientService.saveIngredient(ingredient).subscribe(i => this.goToEditIngredients());
    } else {
      alert('Invalid ingredient name.')
    }
  }

  canDeactivate(): Observable<boolean> | boolean {
    // Allow synchronous navigation (`true`) if no crisis or the crisis is unchanged
    if (!this.ingredient || this.ingredient.name === this.editName) {
      return true;
    }
    // Otherwise ask the user with the dialog service and return its
    // observable which resolves to true or false when the user decides
    return confirm('Leaving will not save your changes. Discard changes?');
  }

}
