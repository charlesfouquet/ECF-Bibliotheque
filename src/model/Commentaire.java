package model;

import java.sql.Timestamp;

public class Commentaire {
	private int id;
	private String contenu;
	private Timestamp dateCom;
	
	//FK
	private User user;
	private Livre livre;
	
	public Commentaire(String contenu, User user, Livre livre) {
		this.contenu = contenu;
		this.user = user;
		this.livre = livre;
	}
	
	public Commentaire(String contenu, Timestamp dateCom, User user, Livre livre) {
		this.contenu = contenu;
		this.dateCom = dateCom;
		this.user = user;
		this.livre = livre;
	}
	
	public Commentaire(int id, String contenu, Timestamp dateCom, User user, Livre livre) {
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
	public Timestamp getDateCom() {
		return dateCom;
	}
	public void setDateCom(Timestamp dateCom) {
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
