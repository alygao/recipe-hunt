package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ModeratorApplication implements Serializable{
	private int id;
	private String username;
	private String password;
	private String name;
	private boolean accepted;
	private String reasonForApplying;
	
	public ModeratorApplication() {}

	public ModeratorApplication(int id, String username, String password, String name, boolean accepted,
			String reasonForApplying) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.accepted = accepted;
		this.reasonForApplying = reasonForApplying;
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getReasonForApplying() {
		return reasonForApplying;
	}

	public void setReasonForApplying(String reasonForApplying) {
		this.reasonForApplying = reasonForApplying;
	}

	@Override
	public String toString() {
		return "ModeratorApplication [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", accepted=" + accepted + ", reasonForApplying=" + reasonForApplying + "]";
	}
	
	
}


