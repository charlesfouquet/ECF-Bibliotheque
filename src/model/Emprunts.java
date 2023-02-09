package model;

import java.sql.Date;

public class Emprunts {
	
	private int id;
	private Date dateSortie;
	private Date dateRetour;
	
	//FK
	private Users id_user;
	
	public Emprunts(int id, Date dateSortie, Date dateRetour, Users id_user) {
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
	public Users getId_user() {
		return id_user;
	}
	public void setId_user(Users id_user) {
		this.id_user = id_user;
	}
}
