package domain;

import java.io.Serializable;

public class Meal implements Serializable{
	private int id;
	private String name;
	
	public Meal(){}
	
	public Meal(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + "]";
	}
	
	
}
