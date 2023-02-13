package model;

import java.sql.Date;

public class Emprunt {
	
	private int id;
	private Date dateSortie;
	private Date dateRetour;
	
	//FK
	private User id_user;
	
	public Emprunt(int id, Date dateSortie, Date dateRetour, User id_user) {
		super();
		this.id = id;
		this.dateSortie = dateSortie;
		this.dateRetour = dateRetour;
		this.id_user = id_user;
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
	public Date getDateretour() {
		return dateRetour;
	}
	public void setDateretour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public User getId_user() {
		return id_user;
	}
	public void setId_user(User id_user) {
		this.id_user = id_user;
	}
}
