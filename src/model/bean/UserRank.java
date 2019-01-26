package model.bean;

public class UserRank {
	private int id;
	private String name;
	public UserRank() {
		// TODO Auto-generated constructor stub
	}
	public UserRank(int id, String name) {
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
	
}
