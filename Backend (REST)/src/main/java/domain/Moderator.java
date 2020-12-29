package domain;

import java.io.Serializable;

public class Moderator implements Serializable{
	private int id;
	private String username;
	private String password;
	private String name;
	private int level;
	private boolean active;
	
	public Moderator() {}
	
	public Moderator(int id, String username, String password, String name, int level, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.level = level;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Moderator [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", level=" + level + ", active=" + active + "]";
	}
	
	
	
	
	
	
}


