package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Emprunt;
import model.User;
import sqlConnection.DBConnect;

public class EmpruntDAO implements IDAO<Emprunt> {
	Connection connect = DBConnect.getConnect();

	@Override
	public boolean create(Emprunt object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Emprunt> read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//verif si User a un emprunt en cours
	public int qtLivreARendre(User user) {
		String requete = ("SELECT COUNT(*) as qtLivreARendre FROM users INNER JOIN emprunts ON users.id = emprunts.id_user WHERE users.id=? AND dateRetour IS NULL;");
		try {
			PreparedStatement sql = connect.prepareStatement(requete);
			sql.setInt(1, user.getId());
			System.out.println("id de l'user : "+user.getId());
			ResultSet rs = sql.executeQuery();
			if(rs.next()) {
				System.out.println("nombre de livre : "+rs.getInt("qtLivreARendre"));
				return rs.getInt("qtLivreARendre");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(Emprunt object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Emprunt object) {
		// TODO Auto-generated method stub
		return false;
	}

}
