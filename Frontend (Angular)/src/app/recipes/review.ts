import { Moderator } from '../moderators/moderator';
import { Recipe } from './recipe'

export interface Review {
    id : number;
    reviewerName: string;
    reviewerEmail: string;
    review: string;
    recipe: Recipe;
    verifiedByModerator: boolean;
    moderator: Moderator;
    date: Date;
}

export interface SubmittedReview {
    id : number;
    reviewerName: string;
    reviewerEmail: string;
    review: string;
    recipeId: number;
    verifiedByModerator: boolean;
    moderator: Moderator;
    date: Date;
}