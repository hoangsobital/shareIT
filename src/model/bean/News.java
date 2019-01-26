package model.bean;

public class News {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private String date_create;
	private int created_by;
	private String picture;
	private int is_slide;
	private Category category;
	private User user;
	public News() {
		// TODO Auto-generated constructor stub
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public News(int id, String name, String preview, String detail, String date_create, int created_by, String picture,
			int is_slide, Category category, User user) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_create = date_create;
		this.created_by = created_by;
		this.picture = picture;
		this.is_slide = is_slide;
		this.category = category;
		this.user = user;
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
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDate_create() {
		return date_create;
	}
	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int create_by) {
		this.created_by = create_by;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getIs_slide() {
		return is_slide;
	}
	public void setIs_slide(int is_slide) {
		this.is_slide = is_slide;
	}
	
}
