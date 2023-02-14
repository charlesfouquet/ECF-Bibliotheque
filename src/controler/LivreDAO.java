package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Auteur;
import model.Editeur;
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
		ArrayList<Livre> listeLivres = new ArrayList<>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM catalogue_complet");
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getInt("nbPagesL"), rs.getString("ISBN_livreL"));
               
				listeLivres.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeLivres;
	}
	
	public ArrayList<Livre> read(String searchString) {
		ArrayList<Livre> listeLivres = new ArrayList<>();
		
		try {			
			String[] searchStringParts = searchString.split(" ");
			ArrayList<String> queryFilters = new ArrayList<>();
			queryFilters.add("titreL");
			queryFilters.add("resumeL");
			queryFilters.add("nomA");
			queryFilters.add("prenomA");
			queryFilters.add("bioA");
			queryFilters.add("nomSocialE");
			queryFilters.add("themeG");
			queryFilters.add("nomSerieS");
			queryFilters.add("ISBN_livreL");
			
			int inputDataCounter = 1;
			String queryWhere = " WHERE ";
			
			for (int i = 0; i < queryFilters.size(); i++) {
				for (int j = 0; j < searchStringParts.length; j++) {
					queryWhere += queryFilters.get(i) + " LIKE ?";
					if (i != queryFilters.size() - 1) {
							queryWhere += " OR ";
					} else if (i == queryFilters.size() - 1) {
						if (j != searchStringParts.length - 1) {
							queryWhere += " OR ";
						}
					}
				}
			}
			
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM catalogue_complet" + queryWhere);
			for (int i = 0; i < queryFilters.size(); i++) {
				for (int j = 0; j < searchStringParts.length; j++) {
					req.setString(inputDataCounter, "%"+searchStringParts[j]+"%");
					inputDataCounter++;
				}
			}

			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Livre livre = new Livre(rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getInt("nbPagesL"), rs.getString("ISBN_livreL"));
               
				listeLivres.add(livre);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeLivres;
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
			String[] searchStringParts = searchString.split(" ");
			ArrayList<String> queryFilters = new ArrayList<>();
			queryFilters.add("titreL");
			queryFilters.add("resumeL");
			queryFilters.add("nomA");
			queryFilters.add("prenomA");
			queryFilters.add("bioA");
			queryFilters.add("nomSocialE");
			queryFilters.add("themeG");
			queryFilters.add("nomSerieS");
			queryFilters.add("ISBN_livreL");
			
			int inputDataCounter = 1;
			String queryWhere = " WHERE ";
			
			for (int i = 0; i < queryFilters.size(); i++) {
				for (int j = 0; j < searchStringParts.length; j++) {
					queryWhere += queryFilters.get(i) + " LIKE ?";
					if (i != queryFilters.size() - 1) {
							queryWhere += " OR ";
					} else if (i == queryFilters.size() - 1) {
						if (j != searchStringParts.length - 1) {
							queryWhere += " OR ";
						}
					}
				}
			}
			
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM livres_dispos" + queryWhere);
			for (int i = 0; i < queryFilters.size(); i++) {
				for (int j = 0; j < searchStringParts.length; j++) {
					req.setString(inputDataCounter, "%"+searchStringParts[j]+"%");
					inputDataCounter++;
				}
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
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT " + triString + " FROM catalogue_complet ORDER BY " + triString + " ASC");
			
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
	
	public ArrayList<Livre> readViaTri(String triString, String subTriString, Boolean dispoState) {
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
			
			String fromTable = "";
			
			if (dispoState) {
				fromTable = "livres_dispos";
			} else {
				fromTable = "catalogue_complet";
			}
			
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT couvertureL, titreL, nomA, prenomA, nbPagesL, ISBN_livreL FROM " + fromTable + " WHERE " + triString + " LIKE ? ORDER BY idL ASC");
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
	
	public Livre findByISBN(String ISBN) {
		Livre livre = null;
		try {
			PreparedStatement req = connect.prepareStatement("SELECT couvertureL, titreL, nomA, prenomA, resumeL, datePubliL, nbPagesL, ISBN_livreL, nomSocialE, themeG, nomSerieS, tomeLS, idEx FROM catalogue_complet WHERE ISBN_livreL = ?");

			req.setString(1, ISBN);
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Auteur auteur = new Auteur(rs.getString("nomA"), rs.getString("prenomA"));
				Editeur editeur = new Editeur(rs.getString("nomSocialE"));
				livre = new Livre(rs.getString("ISBN_livreL"), rs.getString("couvertureL"), rs.getString("titreL"), auteur, rs.getString("resumeL"), rs.getDate("datePubliL"), rs.getInt("nbPagesL"), editeur);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return livre;
	}
	
	public ArrayList<Integer> getStock(String ISBN) {
		ArrayList<Integer> stockInfo = new ArrayList<>();
		
		int stock = 0;
		int stockTotal = 0;
		
		try {
			PreparedStatement req1 = connect.prepareStatement("SELECT COUNT(DISTINCT ex.id) FROM exemplaires ex LEFT JOIN emprunts em ON ex.id = em.id_exemplaire WHERE ISBN_livre = ? AND (dateRetour IS NOT NULL OR ex.id NOT IN (SELECT id_exemplaire FROM emprunts))");
			PreparedStatement req2 = connect.prepareStatement("SELECT COUNT(DISTINCT id) FROM exemplaires WHERE ISBN_livre = ? GROUP BY ISBN_livre");

			req1.setString(1, ISBN);
			req2.setString(1, ISBN);
			ResultSet rs1 = req1.executeQuery();
			ResultSet rs2 = req2.executeQuery();
			
			while(rs1.next()) {
				stock = rs1.getInt("COUNT(DISTINCT ex.id)");
			}
			
			while(rs2.next()) {
				stockTotal = rs2.getInt("COUNT(DISTINCT id)");
			}
			
			stockInfo.add(stock);
			stockInfo.add(stockTotal);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockInfo;
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
