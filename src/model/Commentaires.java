package model;

import java.sql.Date;

public class Commentaires {
	private int id;
	private String contenu;
	private Date dateCom;
	
	//FK
	private Users id_user;
	private Livres ISBN_livre;
	
	public Commentaires(int id, String contenu, Date dateCom, Users id_user, Livres iSBN_livre) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.dateCom = dateCom;
		this.id_user = id_user;
		ISBN_livre = iSBN_livre;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDateCom() {
		return dateCom;
	}
	public void setDateCom(Date dateCom) {
		this.dateCom = dateCom;
	}
	public Users getId_user() {
		return id_user;
	}
	public void setId_user(Users id_user) {
		this.id_user = id_user;
	}
	public Livres getISBN_livre() {
		return ISBN_livre;
	}
	public void setISBN_livre(Livres iSBN_livre) {
		ISBN_livre = iSBN_livre;
	}
	
	
	
	

}
