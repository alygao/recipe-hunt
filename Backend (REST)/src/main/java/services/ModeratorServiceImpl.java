package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ModeratorDao;
import domain.Moderator;
import domain.Recipe;
import domain.Review;

@Service
public class ModeratorServiceImpl implements ModeratorService {
	
	@Autowired
	private ModeratorDao moderatorDao;

	@Override
	public List<Moderator> getModerators() {
		return moderatorDao.getModerators();
	}

	@Override
	public Moderator getModerator(int id) {
		return moderatorDao.getModerator(id);
	}

	@Override
	public Moderator saveModerator(Moderator moderator, int id) {
		moderator.setId(id);
		return moderatorDao.saveModerator(moderator, id);
	}

	@Override
	public Moderator addModerator(Moderator moderator) {
		int id = moderatorDao.addModerator(moderator);
		moderator.setId(id);
		return moderator;
	}

	@Override
	public void deleteModerator(int id) {
		moderatorDao.deleteModerator(id);

	}

	@Override
	public void deactivateModerator(Moderator moderator) {
		moderator.setActive(false);
		this.saveModerator(moderator, moderator.getId());
	}
	
	@Override
	public void activateModerator(Moderator moderator) {
		moderator.setActive(true);
		this.saveModerator(moderator, moderator.getId());
	}

	@Override
	public Moderator getModeratorByUsername(String username) {
		return this.moderatorDao.getModeratorByUsername(username);
		
	}

}
