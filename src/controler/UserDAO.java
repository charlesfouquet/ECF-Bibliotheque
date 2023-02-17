package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.User;
import sqlConnection.DBConnect;

public class UserDAO implements IDAO<User>{
	Connection connect = DBConnect.getConnect();
	
	//déclaration d'un currentUser en static pour appel a différent endroit
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
	
	public boolean updateInfo(User user) {
		try {
			PreparedStatement req = connect.prepareStatement("UPDATE users SET nom = ?, prenom = ?, email = ? WHERE id = ?");
			
			req.setString(1, user.getNom());
			req.setString(2, user.getPrenom());
			req.setString(3, user.getEmail());
			req.setInt(4, user.getId());
			
			req.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateAddress(User user) {
		try {
			PreparedStatement req = connect.prepareStatement("UPDATE users SET adresse = ?, cp = ?, ville = ?, tel = ? WHERE id = ?");
			
			if (user.getAdresse().isEmpty()) {
				req.setNull(1, Types.VARCHAR);			
			} else {
				req.setString(1, user.getAdresse());			
			}

			if (!(user.getCp() > 0)) {
				req.setNull(2, Types.INTEGER);			
			} else {
				req.setInt(2, user.getCp());			
			}
			
			if (user.getVille().isEmpty()) {
				req.setNull(3, Types.VARCHAR);			
			} else {			
				req.setString(3, user.getVille());
			}
			
			if (user.getTel().isEmpty()) {
				req.setNull(4, Types.VARCHAR);			
			} else {			
				req.setString(4, user.getTel());
			}
			
			req.setInt(5, user.getId());
			
			req.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePassword(int userID, User userIn, User userOut) {
		try {
			PreparedStatement req1 = connect.prepareStatement("SELECT id FROM users WHERE email = ? AND password = PASSWORD(?)");
			
			req1.setString(1, userIn.getEmail());
			req1.setString(2, userIn.getPassword());
			
			ResultSet rs = req1.executeQuery();
			
			int checkedID = 0;
			
			while(rs.next()) {
				checkedID = rs.getInt("id");
			}
			
			if (userID == checkedID) {
				PreparedStatement req2 = connect.prepareStatement("UPDATE users SET password = PASSWORD(?) WHERE id = ? AND email = ?");
				req2.setString(1, userOut.getPassword());
				req2.setInt(2, userID);
				req2.setString(3, userOut.getEmail());
				req2.execute();
			} else {
				JOptionPane.showMessageDialog(null,"Votre ancien mot de passe ne correspond pas à votre saisie.\nVeuillez réessayer.","Mise à jour du mot de passe", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//vérification de mail : aaa @ aaa . aa : débute pas lettre, suivi de min 3 lettres @ min 3 lettres . min 2 lettres fini par lettres
	public boolean emailValidator(String email) {
		String regex = "^[A-Za-z0-9][A-Za-z0-9.-]+[A-Za-z0-9][@][A-Za-z0-9][A-Za-z0-9.-]+[A-Za-z0-9][.][A-Za-z0-9]{2,3}$";
		Pattern compilRegex = Pattern.compile(regex);
		Matcher verifEmail = compilRegex.matcher(email);
		boolean mailCheck = verifEmail.matches();
		System.out.println("email valide : "+mailCheck);
		return mailCheck;
	}
	
	//vérification de password : 8 caractères, 1 maj, 1 minus, 1 chiffre, 1 spécial
	public boolean passValidator(String pass) {
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[?!$%\\-_]).{8,}$";
		Pattern compileRegex = Pattern.compile(regex);
		Matcher verifPass = compileRegex.matcher(pass);
		boolean passCheck = verifPass.matches();
		System.out.println("password : "+passCheck);
		return passCheck;
	}
	
	//vérification si mail déjà existant dans la bdd
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
	
	//connexion sur la page login
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

	@Override
	public boolean delete(User user) {
		String requete = ("DELETE FROM users WHERE id=(?)");
		try {
			PreparedStatement sql = connect.prepareStatement(requete);
			sql.setInt(1, user.getId());
			sql.execute();
			System.out.println("suppression de : "+currentUser.getEmail());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
