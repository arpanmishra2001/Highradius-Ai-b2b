package highradius;

import java.sql.*;

public class DBConnection {
	public static Connection createConnection() {
		Connection con = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/grey_goose";
		String user = "root";
		String password = "vatsa08";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Database Connection established");
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		return con;
	}
}
