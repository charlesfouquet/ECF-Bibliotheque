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
	private Editeur id_editeur;

	public Livre(int id, String iSBN, String titre, String resume, Date datePubli, int nbPages, String couverture, Editeur id_editeur) {
		super();
		this.id = id;
		ISBN = iSBN;
		this.titre = titre;
		this.resume = resume;
		this.datePubli = datePubli;
		this.nbPages = nbPages;
		this.couverture = couverture;
		this.id_editeur = id_editeur;
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
	public Editeur getId_editeur() {
		return id_editeur;
	}
	public void setId_editeur(Editeur id_editeur) {
		this.id_editeur = id_editeur;
	}
	
	
	
	
	
}
