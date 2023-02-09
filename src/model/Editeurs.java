package model;

public class Editeurs {
	
	private int id;
	private String nomsocial;
	
	public Editeurs(int id, String nomsocial) {
		this.id = id;
		this.nomsocial = nomsocial;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomsocial() {
		return nomsocial;
	}
	public void setNomsocial(String nomsocial) {
		this.nomsocial = nomsocial;
	}
}
