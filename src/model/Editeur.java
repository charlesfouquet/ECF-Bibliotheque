package model;

public class Editeur {
	
	private int id;
	private String nomsocial;
	
	public Editeur(int id, String nomsocial) {
		this.id = id;
		this.nomsocial = nomsocial;
	}
	
	public Editeur(String nomsocial) {
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
