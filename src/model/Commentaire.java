package model;

import java.sql.Date;

public class Commentaire {
	private int id;
	private String contenu;
	private Date dateCom;
	
	//FK
	private User id_user;
	private Livre ISBN_livre;
	
	public Commentaire(int id, String contenu, Date dateCom, User id_user, Livre iSBN_livre) {
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
	public User getId_user() {
		return id_user;
	}
	public void setId_user(User id_user) {
		this.id_user = id_user;
	}
	public Livre getISBN_livre() {
		return ISBN_livre;
	}
	public void setISBN_livre(Livre iSBN_livre) {
		ISBN_livre = iSBN_livre;
	}
	
	
	
	

}
