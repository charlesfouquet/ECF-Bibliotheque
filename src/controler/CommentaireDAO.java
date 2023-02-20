package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Commentaire;
import model.Livre;
import model.User;
import sqlConnection.DBConnect;

public class CommentaireDAO implements IDAO<Commentaire> {
	
	Connection connect = DBConnect.getConnect();

	@Override
	public boolean create(Commentaire commentaire) {
		try {
			PreparedStatement sql = connect.prepareStatement("INSERT INTO commentaires (contenu, dateCom, id_user, ISBN_livre) VALUES (?, NOW(), ?, ?)");
			
			sql.setString(1, commentaire.getContenu());
			sql.setInt(2, commentaire.getUser().getId());
			sql.setString(3, commentaire.getLivre().getISBN());
			
			sql.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Commentaire> read() {
		return null;
	}
	
	public ArrayList<Commentaire> read(Livre livre) {
		ArrayList<Commentaire> listeCommentaires = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT u.id, nom, prenom, contenu, dateCom, ISBN_livre FROM users u JOIN commentaires c ON c.id_user = u.id WHERE ISBN_livre = ? ORDER BY dateCom DESC");
			
			req.setString(1, livre.getISBN());
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getInt("u.id"), rs.getString("nom"), rs.getString("prenom"));
				
				Commentaire commentaire = new Commentaire(rs.getString("contenu"), rs.getTimestamp("dateCom"), user, livre);
				listeCommentaires.add(commentaire);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCommentaires;
	}

	@Override
	public void update(Commentaire object) {
		
	}

	@Override
	public boolean delete(Commentaire object) {
		return false;
	}

}
