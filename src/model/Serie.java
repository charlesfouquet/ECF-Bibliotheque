package model;

public class Serie {
	
	private int id;
	private String nomSerie;
	
	public Serie(int id, String nomSerie) {
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
