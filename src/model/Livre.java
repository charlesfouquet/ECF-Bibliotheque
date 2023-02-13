package model;

import java.sql.Date;

public class Livre {
	
	private int id;
	private String ISBN;
	private String titre;
	private String resume;
	private Date datePubli;
	private int nbPages;
	private String couverture;
	
	//FK
	private Editeur editeur;
	private Auteur auteur;

	public Livre(int id, String ISBN, String titre, String resume, Date datePubli, int nbPages, String couverture, Editeur editeur) {
		super();
		this.id = id;
		this.ISBN = ISBN;
		this.titre = titre;
		this.resume = resume;
		this.datePubli = datePubli;
		this.nbPages = nbPages;
		this.couverture = couverture;
		this.editeur = editeur;
	}
	
	public Livre(String couverture, String titre, Auteur auteur, int nbPages, String ISBN) {
		this.couverture = couverture;
		this.titre = titre;
		this.auteur = auteur;
		this.ISBN = ISBN;
		this.nbPages = nbPages;
	}
	
	public Livre(String couverture, String titre, Auteur auteur, String ISBN) {
		this.couverture = couverture;
		this.titre = titre;
		this.auteur = auteur;
		this.ISBN = ISBN;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Date getDatePubli() {
		return datePubli;
	}
	public void setDatePubli(Date datePubli) {
		this.datePubli = datePubli;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public String getCouverture() {
		return couverture;
	}
	public void setCouverture(String couverture) {
		this.couverture = couverture;
	}
	public Editeur getEditeur() {
		return editeur;
	}
	public void setEditeur(Editeur id_editeur) {
		this.editeur = id_editeur;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
	
	
	
	
	
}
