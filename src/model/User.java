package model;

public class User {
	
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String adresse;
	private int cp;
	private String ville;
	private String tel;
	
	//FK
	private Role id_role;
	
	public User(int id, String nom, String prenom, String email, String password, String adresse, int cp, String ville, String tel, Role id_role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.tel = tel;
		this.id_role = id_role;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Role getId_role() {
		return id_role;
	}
	public void setId_role(Role id_role) {
		this.id_role = id_role;
	}
}
