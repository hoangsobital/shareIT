package model.bean;

public class User {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private int active;
	private UserRank userRank;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String fullname, String email, int active,
			UserRank userRank) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.active = active;
		this.userRank = userRank;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public UserRank getUserRank() {
		return userRank;
	}
	public void setUserRank(UserRank userRank) {
		this.userRank = userRank;
	}
	
	
}
