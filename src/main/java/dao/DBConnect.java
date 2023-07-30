package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	protected static Connection connection = null;
	public DBConnect() {
		try {
			String url = "jdbc:mysql://localhost:3306/projecteshoponweb";
			String user = "root";
			String pass = "K5633413";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			System.out.println("Connect Success !");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	};
}
