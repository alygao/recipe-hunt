package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SubscriberDao;
import domain.Subscriber;

@Service
public class SubscriberServiceImpl implements SubscriberService {
	
	@Autowired
	SubscriberDao subscriberDao;
	

	@Override
	public List<Subscriber> getSubscribers() {
		return subscriberDao.getSubscribers();
	}

	@Override
	public Subscriber getSubscriber(int id) {
		return subscriberDao.getSubscriber(id);
	}

	@Override
	public Subscriber saveSubscriber(Subscriber subscriber, int id) {
		subscriber.setId(id);
		return subscriberDao.saveSubscriber(subscriber, id);
	}

	@Override
	public Subscriber addSubscriber(Subscriber subscriber) {
		int id = subscriberDao.addSubscriber(subscriber);
		subscriber.setId(id);
		return subscriber;
	}

	@Override
	public void deleteSubscriber(int id) {
		subscriberDao.deleteSubscriber(id);

	}

}
