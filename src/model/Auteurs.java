package model;

public class Auteurs {
	
	private int id;
	private String nom;
	private String prenom;
	private String bio;
	
	public Auteurs(int id, String nom, String prenom, String bio) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.bio = bio;
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
