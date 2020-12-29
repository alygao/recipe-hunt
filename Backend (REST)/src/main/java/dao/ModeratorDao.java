package dao;

import java.util.List;

import domain.Moderator;

public interface ModeratorDao {

	List<Moderator> getModerators();

	Moderator getModerator(int id);

	Moderator saveModerator(Moderator moderator, int id);

	int addModerator(Moderator moderator);

	void deleteModerator(int id);

	Moderator getModeratorForRecipe(int recipeId);

	Moderator getModeratorForReview(int reviewId);

	Moderator getModeratorByUsername(String username);

}
