package model;

import java.sql.Date;

public class Commentaire {
	private int id;
	private String contenu;
	private Date dateCom;
	
	//FK
	private User user;
	private Livre livre;
	
	public Commentaire(String contenu, Date dateCom, User user, Livre livre) {
		this.contenu = contenu;
		this.dateCom = dateCom;
		this.user = user;
		this.livre = livre;
	}
	
	public Commentaire(int id, String contenu, Date dateCom, User user, Livre livre) {
		this.id = id;
		this.contenu = contenu;
		this.dateCom = dateCom;
		this.user = user;
		this.livre = livre;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	
	
	
	

}
