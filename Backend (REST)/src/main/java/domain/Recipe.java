package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
	private int id;
	private String name;
	private List<Ingredient> ingredients;
	private String link;
	private boolean verifiedByModerator;
	private Moderator moderator;
	private List<Review> unverifiedReviews = new ArrayList<>();
	private List<Review> verifiedReviews;
	private List<Meal> meals;
	private String type;
	
	public Recipe() {}
	
	public Recipe(int id, String name, List<Ingredient> ingredients, String link, boolean verifiedByModerator,
			Moderator moderator, List<Review> unverifiedReviews, List<Review> verifiedReviews, List<Meal> meals,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.link = link;
		this.verifiedByModerator = verifiedByModerator;
		this.moderator = moderator;
		this.unverifiedReviews = unverifiedReviews;
		this.verifiedReviews = verifiedReviews;
		this.meals = meals;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public boolean isVerifiedByModerator() {
		return verifiedByModerator;
	}
	public void setVerifiedByModerator(boolean verifiedByModerator) {
		this.verifiedByModerator = verifiedByModerator;
	}
	public Moderator getModerator() {
		return moderator;
	}
	public void setModerator(Moderator moderator) {
		this.moderator = moderator;
	}
	public List<Review> getUnverifiedReviews() {
		return unverifiedReviews;
	}
	public void setUnverifiedReviews(List<Review> unverifiedReviews) {
		this.unverifiedReviews.clear();
		for (Review r : unverifiedReviews) {
			this.addUnverifiedReview(r);
		}
	}
	public List<Review> getVerifiedReviews() {
		return verifiedReviews;
	}
	public void setVerifiedReviews(List<Review> verifiedReviews) {
		this.verifiedReviews = verifiedReviews;
		for (Review r : verifiedReviews) {
			r.setRecipe(this);
		}
	}
	public List<Meal> getMeals() {
		return meals;
	}
	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", link=" + link
				+ ", verifiedByModerator=" + verifiedByModerator + ", moderator=" + moderator
				+ ", unverifiedReviews=" + unverifiedReviews + ", verifiedReviews=" + verifiedReviews + ", meals="
				+ meals + ", type=" + type + "]";
	}
	
	public void addUnverifiedReview(Review review) {
		if (review != null) {
			if (review.getRecipe() != null ) {
				review.getRecipe().getUnverifiedReviews().remove(review);
			}
			this.unverifiedReviews.add(review);
			review.setRecipe(this);
		}
	}

	public void addVerifiedReview(Review review) {
		if (review != null) {
			if (review.getRecipe() != null ) {
				review.getRecipe().getVerifiedReviews().remove(review);
			}
			this.verifiedReviews.add(review);
			review.setRecipe(this);
		}
		
	}
	
	
	
	
	
	
}