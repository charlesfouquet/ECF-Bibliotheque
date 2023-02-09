package model;

public class Series {
	
	private int id;
	private String nomSerie;
	
	public Series(int id, String nomSerie) {
		super();
		this.id = id;
		this.nomSerie = nomSerie;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomSerie() {
		return nomSerie;
	}
	public void setNomSerie(String nomSerie) {
		this.nomSerie = nomSerie;
	}
}
