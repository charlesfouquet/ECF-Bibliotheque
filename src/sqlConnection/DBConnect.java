package sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection getConnect() {
		Connection myConnection = null;
		
		String url = "jdbc:mysql://localhost/";
		String dbName = "ecf_bibliotheque";
		String user = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConnection = DriverManager.getConnection(url+dbName,user,password);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KO");
		}
		return myConnection;
	}
}
