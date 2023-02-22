package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

}
