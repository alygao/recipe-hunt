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

import domain.Subscriber;
import services.SubscriberService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/")
public class SubscriberController {

 @Autowired
 private SubscriberService subscriberService;
 
 @RequestMapping(value = "/subscribers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody List<Subscriber> getAllSubscribers ( ) {
  try {
   List<Subscriber> subscriberList = this.subscriberService.getSubscribers();
   for ( Subscriber subscriber : subscriberList ) {
    System.out.println( subscriber );
   }
   return subscriberList;
  } catch ( Throwable t ) {
   t.printStackTrace( System.out );
   return null;
  }
 }
 
 @RequestMapping(value = "/subscribers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Subscriber getSubscriber (@PathVariable int id) {
	 return subscriberService.getSubscriber(id);
 }
 
 @RequestMapping(value = "/subscribers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 public @ResponseBody Subscriber saveSubscriber (@PathVariable int id, @RequestBody Subscriber subscriber) {
	 return subscriberService.saveSubscriber(subscriber, id);
 }
 
 @RequestMapping(value = "/subscribers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE )
 @ResponseStatus (HttpStatus.CREATED)
 public @ResponseBody Subscriber addSubscriber (@RequestBody Subscriber subscriber) {
	 return subscriberService.addSubscriber(subscriber);
 }
 
 @RequestMapping(value = "/subscribers/{id}", method = RequestMethod.DELETE)
 @ResponseStatus (HttpStatus.NO_CONTENT)
 public @ResponseBody void deleteSubscriber (@PathVariable int id) {
	 subscriberService.deleteSubscriber(id);
 }
 
// @RequestMapping(value = "/searchSubscribers", method = RequestMethod.GET)
// public @ResponseBody List<Subscriber> searchSubscribers (@RequestParam(value = "term") String term) {
//	 return subscriberService.searchSubscribers(term);
// }

}

