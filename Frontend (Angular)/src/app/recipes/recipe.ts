import { Ingredient } from './../ingredients/ingredient'
import { Meal } from './meal'
import { Review } from './review'
import { Moderator} from './../moderators/moderator'

export interface Recipe {
    id: number;
    name: string;
    ingredients: Ingredient[];
    link: string;
    verifiedByModerator: boolean;
    moderator: Moderator;
    unverifiedReviews: Review[];
    verifiedReviews: Review[];
    meals: Meal[]
    type: string;
  }

  

