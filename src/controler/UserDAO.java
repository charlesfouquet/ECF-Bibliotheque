package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;
import sqlConnection.DBConnect;

public class UserDAO implements IDAO<User>{
	Connection connect = DBConnect.getConnect();
	
	public static User currentUser = null;

	@Override
	public boolean create(User user) {
		String requete = ("INSERT INTO users (nom, prenom, email, password) VALUES (?, ?, ?, PASSWORD(?))");
		try {
			PreparedStatement sql = connect.prepareStatement(requete);
			sql.setString(1, user.getNom());
			sql.setString(2, user.getPrenom());
			sql.setString(3, user.getEmail());
			sql.setString(4, user.getPassword());
			sql.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<User> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(User user) {
		String requete = ("DETLETE FROM users WHERE id=(?)");
		try {
			PreparedStatement sql = connect.prepareStatement(requete);
			sql.setInt(1, user.getId());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//méthode vérification de mail : aaa @ aaa . aa : débute pas lettre, suivi de min 3 lettres @ min 3 lettres . min 2 lettres fini par lettres
	public boolean emailValidator(String email) {
		String regex = "^[A-Za-z0-9][A-Za-z0-9.-]+[A-Za-z0-9][@][A-Za-z0-9][A-Za-z0-9.-]+[A-Za-z0-9][.][A-Za-z0-9]{2,3}$";
		Pattern compilRegex = Pattern.compile(regex);
		Matcher verifEmail = compilRegex.matcher(email);
		boolean mailCheck = verifEmail.matches();
		System.out.println("email : "+mailCheck);
		return mailCheck;
	}
	
	//vérification de password : 8 caractères, 1 maj, 1 minus, 1 chiffre, 1 spécial
	public boolean passValidator(String pass) {
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[?!$%-_]).{8,}$";
		Pattern compileRegex = Pattern.compile(regex);
		Matcher verifPass = compileRegex.matcher(pass);
		boolean passCheck = verifPass.matches();
		System.out.println("password : "+passCheck);
		return passCheck;
	}
	
	//méthodes pour vérifié si mail déjà existant dans la bdd
	public boolean isExist(String email) {
		try {
			PreparedStatement requete = connect.prepareStatement("SELECT * FROM users WHERE email=?");
			requete.setString(1, email);
			ResultSet rs = requete.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//méthode connexion
	public User connexion(String email, String password) {
		try {
			PreparedStatement requete = connect.prepareStatement("SELECT * FROM users WHERE email = ? AND password = PASSWORD(?)");
			requete.setString(1, email);
			requete.setString(2, password);
			ResultSet rs = requete.executeQuery();
			if (rs.next()) {
				currentUser = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getInt("cp"), rs.getString("ville"), rs.getString("tel"), rs.getInt("id_role")); 
				return currentUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
