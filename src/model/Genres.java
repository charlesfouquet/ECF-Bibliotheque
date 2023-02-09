package model;

public class Genres {
	
	private int id;
	private String theme;
	
	public Genres(int id, String theme) {
		super();
		this.id = id;
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
