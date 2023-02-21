package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Auteur;
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

}
