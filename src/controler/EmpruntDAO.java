package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Emprunt;
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
	public int readUserEmprunt(int user) {
		String requete = ("SELECT * FROM users INNER JOIN emprunts ON users.id = emprunts.id_user WHERE users.id=? AND dateRetour IS NULL;");
		try {
			PreparedStatement sql = connect.prepareStatement(requete);
			sql.setString(1, user.getId());
			ResultSet rs = requete.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
