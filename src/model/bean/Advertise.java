package model.bean;

public class Advertise {
	private int id;
	private String name;
	private String picture;
	private String link;
	public Advertise() {
		// TODO Auto-generated constructor stub
	}
	public Advertise(int id, String name, String picture, String link) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.link = link;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
