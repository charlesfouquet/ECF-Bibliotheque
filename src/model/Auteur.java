package model;

public class Auteur {
	
	private int id;
	private String nom;
	private String prenom;
	private String bio;
	
	public Auteur(int id, String nom, String prenom, String bio) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.bio = bio;
	}
	
	public Auteur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
}
