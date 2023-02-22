package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

}
