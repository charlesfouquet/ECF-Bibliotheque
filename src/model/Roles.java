package model;

public class Roles {
	
	private int id;
	private String poste;
	
	public Roles(int id, String poste) {
		super();
		this.id = id;
		this.poste = poste;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
}
