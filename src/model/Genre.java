package model;

public class Genre {
	
	private int id;
	private String theme;
	
	public Genre(int id, String theme) {
		this.id = id;
		this.theme = theme;
	}
	
	public Genre(String theme) {
		this.theme = theme;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
}
