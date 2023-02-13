package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Auteur;
import model.Livre;
import sqlConnection.DBConnect;

public class LivreDAO implements IDAO<Livre> {
	
	Connection connect = DBConnect.getConnect();

	@Override
	public void create(Livre object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Livre> read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Livre> readDispo() {
		ArrayList<Livre> listeLivresDispos = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM livres_dispos");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getInt("nbPagesL"), rs.getString("ISBN_livreL"));
               
				listeLivresDispos.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeLivresDispos;
	}
	
	public ArrayList<Livre> readDispo(String searchString) {
		ArrayList<Livre> listeLivresDispos = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM livres_dispos WHERE titreL LIKE ? OR resumeL LIKE ? OR nomA LIKE ? OR prenomA LIKE ? OR bioA LIKE ? OR nomSocialE LIKE ? OR themeG LIKE ? OR nomSerieS LIKE ? OR ISBN_livreL LIKE ?");
			
			searchString = "%" + searchString.replace(" ", "%") + "%";
			
			for (int i = 1; i <= 9; i++) {
				req.setString(i, searchString);
			}
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getInt("nbPagesL"), rs.getString("ISBN_livreL"));
               
				listeLivresDispos.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeLivresDispos;
	}
	
	public ArrayList<Livre> newestBooks() {
		ArrayList<Livre> listeNewBooks = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, ISBN_livreL FROM catalogue_complet ORDER BY idL DESC LIMIT 4");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getString("ISBN_livreL"));
               
				listeNewBooks.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeNewBooks;
	}
	
	public ArrayList<String> getListeTri(String triString) {
		ArrayList<String> listeTri = new ArrayList<>();
		
		try {

			switch (triString) {
				case "Auteurs": {
					triString = "CONCAT(nomA, ', ', prenomA)";
					break;
				}
				case "Genres": {
					triString = "themeG";
					break;
				}
				case "Series": {
					triString = "nomSerieS";
					break;
				}
			}
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT " + triString + " FROM livres_dispos ORDER BY " + triString + " ASC");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				listeTri.add(rs.getString(triString));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeTri;
	}
	
	public ArrayList<Livre> readDispoViaTri(String triString, String subTriString) {
		ArrayList<Livre> listeLivresDispos = new ArrayList<>();
		
		try {

			switch (triString) {
				case "Auteurs": {
					triString = "nomA";
					String[] nomAuteur = subTriString.split(",");
					subTriString = nomAuteur[0];
					break;
				}
				case "Genres": {
					triString = "themeG";
					break;
				}
				case "Series": {
					triString = "nomSerieS";
					break;
				}
			}
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM livres_dispos WHERE " + triString + " LIKE ? ORDER BY idL ASC");
			req.setString(1, "%"+subTriString+"%");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getInt("nbPagesL"), rs.getString("ISBN_livreL"));
               
				listeLivresDispos.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeLivresDispos;
	}

	@Override
	public void update(Livre object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Livre object) {
		// TODO Auto-generated method stub
		
	}

}
