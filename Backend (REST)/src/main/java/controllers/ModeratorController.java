package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Moderator;
import domain.ModeratorApplication;
import services.ModeratorService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/admin")
public class ModeratorController {

	@Autowired
	private ModeratorService moderatorService;

	@RequestMapping(value = "/moderators", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Moderator> getAllModerators() {
		try {
			List<Moderator> moderatorList = this.moderatorService.getModerators();
			for (Moderator moderator : moderatorList) {
				System.out.println(moderator);
			}
			return moderatorList;
		} catch (Throwable t) {
			t.printStackTrace(System.out);
			return null;
		}
	}

	@RequestMapping(value = "/moderators/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Moderator getModerator(@PathVariable int id) {
		return moderatorService.getModerator(id);
	}

	@RequestMapping(value = "/moderators/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Moderator saveModerator(@PathVariable int id, @RequestBody Moderator moderator) {
		return moderatorService.saveModerator(moderator, id);
	}

	@RequestMapping(value = "/moderators", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Moderator addModerator(@RequestBody Moderator moderator) {
		return moderatorService.addModerator(moderator);
	}

	@RequestMapping(value = "/moderators/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteModerator(@PathVariable int id) {
		moderatorService.deleteModerator(id);
	}
	
//	@RequestMapping(value = "/moderator-applications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseStatus(HttpStatus.CREATED)
//	public @ResponseBody ModeratorApplication addModeratorApplication(@RequestBody ModeratorApplication moderatorApplication) {
//		return moderatorService.addModeratorApplication(moderatorApplication);
//	}
//	
//	@RequestMapping(value = "/moderator-applications/{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public @ResponseBody void deleteModeratorApplication(@PathVariable int id) {
//		moderatorService.deleteModeratorApplication(id);
//	}

}
