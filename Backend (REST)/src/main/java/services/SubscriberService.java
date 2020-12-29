package services;

import java.util.List;

import domain.Subscriber;

public interface SubscriberService {

	List<Subscriber> getSubscribers();

	Subscriber getSubscriber(int id);

	Subscriber saveSubscriber(Subscriber subscriber, int id);

	Subscriber addSubscriber(Subscriber subscriber);

	void deleteSubscriber(int id);

}
