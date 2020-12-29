package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Moderator;

@Repository
public class ModeratorDaoImpl implements ModeratorDao {
	
	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public List<Moderator> getModerators() {
		return jdbcOperations.query("SELECT ID, USERNAME, PASSWORD, NAME, LEVEL, ACTIVE FROM MODERATORS;",
				(rs, rowNum) -> new Moderator(
						rs.getInt("ID"), 
						rs.getString("USERNAME"), 
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getInt("LEVEL"),
						rs.getBoolean("ACTIVE")));
	}
	

	@Override
	public Moderator getModerator(int id) {
		return jdbcOperations.queryForObject("SELECT ID, USERNAME, PASSWORD, NAME, LEVEL, ACTIVE FROM MODERATORS WHERE ID = ?;",
				(rs, rowNum) -> new Moderator(
						rs.getInt("ID"), 
						rs.getString("USERNAME"), 
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getInt("LEVEL"),
						rs.getBoolean("ACTIVE")), id);
	
	}

	@Override
	public Moderator saveModerator(Moderator moderator, int id) {
		this.jdbcOperations.update("UPDATE MODERATORS SET USERNAME = ?, PASSWORD = ?, NAME = ?, LEVEL = ?, ACTIVE = ? WHERE ID = ?;", 
				moderator.getUsername(), moderator.getPassword(), moderator.getName(), moderator.getLevel(), moderator.isActive(), id);
		return moderator;
	}

	@Override
	public int addModerator(Moderator moderator) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcOperations.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO MODERATORS(USERNAME, PASSWORD, NAME, LEVEL, ACTIVE) VALUES (?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, moderator.getUsername());
			ps.setString(2, moderator.getPassword());
			ps.setString(3, moderator.getName());
			ps.setInt(4, moderator.getLevel());
			ps.setBoolean(5, moderator.isActive());
			return ps;
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public void deleteModerator(int id) {
		this.jdbcOperations.update("DELETE FROM MODERATORS WHERE ID = ?;", id);

	}

	@Override
	public Moderator getModeratorForRecipe(int recipeId) {
		int moderatorId = jdbcOperations.queryForObject("SELECT MODERATOR_ID FROM RECIPES WHERE ID = ?;",
				(rs, rowNum) -> 
						rs.getInt("MODERATOR_ID"), recipeId);
		System.out.println("moderator id is " + moderatorId);
		if (moderatorId == 0) {
			return null;
		} else {
			return getModerator(moderatorId);
		}
	}

	@Override
	public Moderator getModeratorForReview(int reviewId) {
		int moderatorId = jdbcOperations.queryForObject("SELECT MODERATOR_ID FROM REVIEWS WHERE ID = ?;",
				(rs, rowNum) -> 
						rs.getInt("MODERATOR_ID"), reviewId);
		return getModerator(moderatorId);
	}


	@Override
	public Moderator getModeratorByUsername(String username) {
		return jdbcOperations.queryForObject("SELECT ID, NAME, LEVEL, ACTIVE FROM MODERATORS WHERE USERNAME = ?;",
				(rs, rowNum) -> new Moderator(
						rs.getInt("ID"), 
						username, 
						"",
						rs.getString("NAME"),
						rs.getInt("LEVEL"),
						rs.getBoolean("ACTIVE")), username);
		
	}

}
