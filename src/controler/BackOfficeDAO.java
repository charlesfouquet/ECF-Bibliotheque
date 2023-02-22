package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Livre;
import sqlConnection.DBConnect;

public class BackOfficeDAO {
	Connection connect = DBConnect.getConnect();

//	public boolean create(String table, String args, Object values, String condition ) {
//		try {
//			PreparedStatement sql = connect.prepareStatement("INSERT INTO ? (?) VALUES (?) WHERE ?;");
//			sql.setString(1, table);
//			sql.setString(2, args);
//			sql.setObject(3, values);
//			sql.setString(4, condition);
//			sql.execute();
//			System.out.println(sql);
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public ArrayList<String> getList(int i) {
	    ArrayList<String> liste = new ArrayList<>();
	    String requete = "";

	    switch (i) {
	        case 0: {
	            requete = "SELECT * FROM livres ORDER BY titre ASC";
	            break;
	        }
	        case 1: {
	            requete = "SELECT * FROM auteurs ORDER BY nom ASC";
	            break;
	        }
	        case 2: {
	            requete = "SELECT * FROM genres ORDER BY theme ASC";
	            break;
	        }
	        case 3: {
	            requete = "SELECT * FROM series ORDER BY nomSerie ASC";
	            break;
	        }
	        case 4: {
	            requete = "SELECT * FROM editeurs ORDER BY nomSocial ASC";
	            break;
	        }
	    };

	    PreparedStatement req;
		try {
			req = connect.prepareStatement(requete);
			ResultSet rs = req.executeQuery();
			
			switch (i) {
			case 0: {
				while(rs.next()) {
					liste.add(rs.getString("titre") + " (ISBN: " + rs.getString("ISBN") + ")");
				}
				break;
			}
			case 1: {
				while(rs.next()) {
					liste.add(rs.getString("nom") + ", " + rs.getString("prenom") + " (#" + rs.getInt("id") + ")");
				}
				break;
			}
			case 2: {
				while(rs.next()) {
					liste.add(rs.getString("theme") + " (#" + rs.getInt("id") + ")");
				}
				break;
			}
			case 3: {
				while(rs.next()) {
					liste.add(rs.getString("nomSerie") + " (#" + rs.getInt("id") + ")");
				}
				break;
			}
			case 4: {
				while(rs.next()) {
					liste.add(rs.getString("nomSocial") + " (#" + rs.getInt("id") + ")");
				}
				break;
			}
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return liste;
	}
	
	public String getISBN(String selectedBook) {
		String regExISBN = "(?<=ISBN: )[^)]*";
		Pattern compilRegex = Pattern.compile(regExISBN);
		Matcher ISBN = compilRegex.matcher(selectedBook);
		ISBN.find();
		return ISBN.group();
	}
	
	public String getItemID(String selectedItem) {
		String regExID = "(?<=#)[^)]*";
		Pattern compilRegex = Pattern.compile(regExID);
		Matcher item = compilRegex.matcher(selectedItem);
		item.find();
		return item.group();
	}
	
	public int readRelation(String ISBN, String type) {
		int relationID = 0;
		String requete = "";
		
		switch (type) {
			case "Auteur": {
				requete = "SELECT id_auteur FROM livres_auteurs WHERE ISBN_livre = ?";
				break;
			}
			case "Genre": {
				requete = "SELECT id_genre FROM livres_genres WHERE ISBN_livre = ?";
				break;
			}
			case "Serie": {			
				requete = "SELECT id_serie FROM livres_series WHERE ISBN_livre = ?";
				break;
			}
			case "Editeur": {				
				requete = "SELECT id_editeur FROM livres WHERE ISBN = ?";
				break;
			}
		}
		
		PreparedStatement req;
		try {
			req = connect.prepareStatement(requete);
			req.setString(1, ISBN);
			ResultSet rs = req.executeQuery();
			
			if (rs.next()) {
				switch (type) {
					case "Auteur": {
						relationID = rs.getInt("id_auteur");
						break;
					}
					case "Genre": {
						relationID = rs.getInt("id_genre");
						break;
					}
					case "Serie": {	
						relationID = rs.getInt("id_serie");
						break;
					}
					case "Editeur": {	
						relationID = rs.getInt("id_editeur");
						break;
					}
				};
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relationID;
	}
	
	public int getPositionInSeries(String ISBN) {
		int position = 0;
		
		PreparedStatement req;
		try {
			req = connect.prepareStatement("SELECT position FROM livres_series WHERE ISBN_livre = ?");
			req.setString(1, ISBN);
			ResultSet rs = req.executeQuery();
			if (rs.next()) {
				position = rs.getInt("position");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return position;
	}
	
	public ArrayList<String[]> getExemplaires(String ISBN) {
		ArrayList<String[]> listeExemplaires = new ArrayList<String[]>();
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT DISTINCT idEx, ISBN_LivreL, titreL FROM catalogue_complet WHERE ISBN_livreL = ?");

			req.setString(1, ISBN);
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				listeExemplaires.add(new String[]{String.format("%05d", rs.getInt("idEx")), rs.getString("ISBN_livreL"), rs.getString("titreL")});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeExemplaires;
	}
	
	public boolean smallAddToDB(String type, String paramA, String paramB) {
		String table = "";
		String valuesSet = "";
		String values = "";
		
		switch (type) {
			case "Auteur": {
				table = "auteurs";
				valuesSet = "(nom, prenom)";
				values = "(\"" + paramA + "\", \"" + paramB + "\")";
				break;
			}
			case "Genre": {
				table = "genres";
				valuesSet = "(theme)";
				values = "(\"" + paramA + "\")";
				break;
			}
			case "Serie": {	
				table = "series";
				valuesSet = "(nomSerie)";
				values = "(\"" + paramA + "\")";
				break;
			}
			case "Editeur": {	
				table = "editeurs";
				valuesSet = "(nomSocial)";
				values = "(\"" + paramA + "\")";
				break;
			}
			case "Exemplaire": {	
				table = "exemplaires";
				valuesSet = "(ISBN_livre)";
				values = "(\"" + paramA + "\")";
				break;
			}
		};
		
		try {
			PreparedStatement req = connect.prepareStatement("INSERT INTO " + table + " " + valuesSet + " VALUES " + values);
			req.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean bigAddToDB(Livre livre, String date, int auteur, int genre, int serie, int position, int editeur) {
		
		try {
			PreparedStatement insLivre = connect.prepareStatement("INSERT INTO livres (ISBN, titre, resume, datePubli, nbPages, id_editeur) VALUES (?, ?, ?, ?, ?, ?)");
			insLivre.setString(1, livre.getISBN());
			insLivre.setString(2, livre.getTitre());
			insLivre.setString(3, livre.getResume());
			insLivre.setString(4, date);
			insLivre.setInt(5, livre.getNbPages());
			insLivre.setInt(6, editeur);
			insLivre.execute();
			
			PreparedStatement insRelAuteur = connect.prepareStatement("INSERT INTO livres_auteurs (ISBN_livre, id_auteur) VALUES (?, ?)");
			insRelAuteur.setString(1, livre.getISBN());
			insRelAuteur.setInt(2, auteur);
			insRelAuteur.execute();
			
			PreparedStatement insRelGenre = connect.prepareStatement("INSERT INTO livres_genres (ISBN_livre, id_genre) VALUES (?, ?)");
			insRelGenre.setString(1, livre.getISBN());
			insRelGenre.setInt(2, genre);
			insRelGenre.execute();
			
			if ((serie > 0) && (position > 0)) {
				PreparedStatement insRelSerie = connect.prepareStatement("INSERT INTO livres_series (ISBN_livre, id_serie, position) VALUES (?, ?, ?)");
				insRelSerie.setString(1, livre.getISBN());
				insRelSerie.setInt(2, serie);
				insRelSerie.setInt(3, position);
				insRelSerie.execute();
			}
			
			PreparedStatement addOneBook = connect.prepareStatement("INSERT INTO exemplaires (ISBN_livre) VALUES (?)");
			addOneBook.setString(1, livre.getISBN());
			addOneBook.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteExemplaire(String idEx, String ISBN) {
		try {
			PreparedStatement upd = connect.prepareStatement("DELETE FROM emprunts WHERE id_exemplaire = ?");
			PreparedStatement del = connect.prepareStatement("DELETE FROM exemplaires WHERE id = ? AND ISBN_livre = ?");
			upd.setInt(1, Integer.parseInt(idEx));
			del.setInt(1, Integer.parseInt(idEx));
			del.setString(2, ISBN);
			upd.execute();
			del.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
