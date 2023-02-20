package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import model.Emprunt;
import model.Livre;
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
	
	public ArrayList<Emprunt> read(User user, boolean histoState) {
		ArrayList<Emprunt> listeEmprunts = new ArrayList<>();
		try {
			PreparedStatement req = null;
			
			if (histoState) {
				req = connect.prepareStatement("SELECT DISTINCT idEx, titreL, dateSortie, dateRetour FROM catalogue_complet cc JOIN emprunts e ON e.id_exemplaire = cc.idEx WHERE id_user = ? AND dateRetour IS NOT NULL ORDER BY dateRetour DESC");
			} else {
				req = connect.prepareStatement("SELECT DISTINCT idEx, titreL, dateSortie, dateRetour FROM catalogue_complet cc JOIN emprunts e ON e.id_exemplaire = cc.idEx WHERE id_user = ? AND dateRetour IS NULL ORDER BY dateSortie DESC");
			}

			req.setInt(1, user.getId());
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				listeEmprunts.add(new Emprunt(rs.getDate("dateSortie"), rs.getDate("dateRetour"), rs.getInt("idEx"), rs.getString("titreL")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeEmprunts;
	}
	
	public boolean emprunterLivre(User user, Livre livre) {
		try {			
			int selectedBook = 0;
			PreparedStatement getExemplaire = connect.prepareStatement("SELECT DISTINCT idEx FROM livres_dispos WHERE ISBN_livreL = ? LIMIT 1");
			
			getExemplaire.setString(1, livre.getISBN());
			ResultSet rs = getExemplaire.executeQuery();
			
			if (rs.next()) {
				selectedBook = rs.getInt("idEx");
			} else {
				return false;
			}
			
			PreparedStatement req = connect.prepareStatement("INSERT INTO emprunts (dateSortie, id_user, id_exemplaire) VALUES (NOW(), ?, ?)");

			req.setInt(1, user.getId());
			req.setInt(2, selectedBook);
			req.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean rendreLivre(User user, int exemplaire) {
		try {
			PreparedStatement req = connect.prepareStatement("UPDATE emprunts SET dateRetour = NOW() WHERE id_user = ? AND id_exemplaire = ?");

			req.setInt(1, user.getId());
			req.setInt(2, exemplaire);
			req.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkPenalty(User user) {
		ArrayList<Emprunt> listeEmprunts = read(user, false);
		
		for (Emprunt emp : listeEmprunts) {
			Date dateSortie = emp.getDateSortie();
			LocalDate dateRetour = dateSortie.toLocalDate().plusMonths(1);
			LocalDate today = LocalDate.now();
						
			Period difference = Period.between(dateRetour, today);
			
			if (difference.getDays() > 0) {
				return true;				
			}
		}
		return false;
	}
	
	//verif si User a un emprunt en cours
//	public int readUserEmprunt(int user) {
//		String requete = ("SELECT * FROM users INNER JOIN emprunts ON users.id = emprunts.id_user WHERE users.id=? AND dateRetour IS NULL;");
//		try {
//			PreparedStatement sql = connect.prepareStatement(requete);
//			sql.setString(1, user.getId());
//			ResultSet rs = requete.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

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
