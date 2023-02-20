package model;

import java.sql.Date;

public class Emprunt {
	
	private int id;
	private Date dateSortie;
	private Date dateRetour;
	
	//FK
	private User user;
	private int exemplaire;
	private String titre;
	
	public Emprunt(int id, Date dateSortie, Date dateRetour, User user) {
		this.id = id;
		this.dateSortie = dateSortie;
		this.dateRetour = dateRetour;
		this.user = user;
	}
	
	public Emprunt(Date dateSortie, Date dateRetour, int exemplaire, String titre) {
		this.dateSortie = dateSortie;
		this.dateRetour = dateRetour;
		this.exemplaire = exemplaire;
		this.titre = titre;
	}
	
	public Emprunt(int id, Date dateSortie, Date dateRetour, User user, int exemplaire) {
		this.id = id;
		this.dateSortie = dateSortie;
		this.dateRetour = dateRetour;
		this.user = user;
		this.exemplaire = exemplaire;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	public User getId_user() {
		return user;
	}
	public void setId_user(User user) {
		this.user = user;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getExemplaire() {
		return exemplaire;
	}
	public void setExemplaire(int exemplaire) {
		this.exemplaire = exemplaire;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
