package services;

import java.util.List;

import domain.Moderator;
import domain.ModeratorApplication;
import domain.Recipe;
import domain.Review;

public interface ModeratorService {

	List<Moderator> getModerators();

	Moderator getModerator(int id);

	Moderator saveModerator(Moderator moderator, int id);

	Moderator addModerator(Moderator moderator);

	void deleteModerator(int id);	
	
	void deactivateModerator(Moderator moderator);
	
	void activateModerator(Moderator moderator);

	Moderator getModeratorByUsername(String username);

//	ModeratorApplication addModeratorApplication(ModeratorApplication moderatorApplication);
//
//	void deleteModeratorApplication(int id);
//	

}
