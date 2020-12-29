package domain;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Review implements Serializable{
	private int id;
	private String reviewerName;
	private String reviewerEmail;
	private String review;
	private Recipe recipe;
	private boolean verifiedByModerator;
	private Moderator moderator;
	private Date date;
	
	public Review() {}
	
	public Review(int id, String reviewerName, String reviewerEmail, String review, Recipe recipe,
			boolean verifiedByModerator, Moderator moderator, Date date) {
		super();
		this.id = id;
		this.reviewerName = reviewerName;
		this.reviewerEmail = reviewerEmail;
		this.review = review;
		this.recipe = recipe;
		this.verifiedByModerator = verifiedByModerator;
		this.moderator = moderator;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@JsonIgnore
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewerName=" + reviewerName + ", reviewerEmail=" + reviewerEmail + ", review="
				+ review + ", verifiedByModerator=" + verifiedByModerator + ", moderator="
				+ moderator + ", date=" + date + "]";
	}
	
	
}
