package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Commentaire;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Commentaire> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Commentaire object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Commentaire object) {
		// TODO Auto-generated method stub
		return false;
	}

}
