package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Subscriber;

@Repository
public class SubscriberDaoImpl implements SubscriberDao {
	
	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public List<Subscriber> getSubscribers() {
		return jdbcOperations.query("SELECT ID, EMAIL, NAME FROM SUBSCRIBERS;",
				(rs, rowNum) -> new Subscriber(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("NAME")));
	}

	@Override
	public Subscriber getSubscriber(int id) {
		return jdbcOperations.queryForObject("SELECT ID, EMAIL, NAME FROM SUBSCRIBERS WHERE ID = ?;",
				(rs, rowNum) -> new Subscriber(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("NAME")), id);
	}

	@Override
	public Subscriber saveSubscriber(Subscriber subscriber, int id) {
		this.jdbcOperations.update("UPDATE SUBSCRIBERS SET EMAIL = ?, NAME = ?, WHERE ID = ?;", 
				subscriber.getName(), id);
		return subscriber;
	}

	@Override
	public int addSubscriber(Subscriber subscriber) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcOperations.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO SUBSCRIBERS(EMAIL, NAME) VALUES (?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, subscriber.getEmail());
			ps.setString(2, subscriber.getName());
			return ps;
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void deleteSubscriber(int id) {
		this.jdbcOperations.update("DELETE FROM SUBSCRIBERS WHERE ID = ?;", id);

	}

}
