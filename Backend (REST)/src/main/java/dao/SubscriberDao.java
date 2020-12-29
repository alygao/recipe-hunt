package dao;

import java.util.List;

import domain.Subscriber;

public interface SubscriberDao {

	List<Subscriber> getSubscribers();

	Subscriber getSubscriber(int id);

	Subscriber saveSubscriber(Subscriber subscriber, int id);

	int addSubscriber(Subscriber subscriber);

	void deleteSubscriber(int id);


}
