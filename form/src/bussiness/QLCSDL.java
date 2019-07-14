package bussiness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QLCSDL {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "";
	
	//Kết nối CSDL
	public Connection connect() throws SQLException, ClassNotFoundException 
	{
		Class.forName(JDBC_DRIVER_CLASS);
		Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		return conn;

	}

	//Tìm user
	public user timUser(String username, String password) throws SQLException, ClassNotFoundException
	{
	 user user = null;
	 
	 Connection conn = (this).connect();
	 java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
	 String sql = "SELECT * FROM admin WHERE username like '" + username + "' and password like '" + password + "'";
	 ResultSet resultSet = statement.executeQuery(sql);
	 while (resultSet.next()) 
	 {
	 user = new user();
	 user.setUsername(resultSet.getString("username"));
	 user.setUsername(resultSet.getString("password"));
	 break;
	 }
	 
	 return user;
	}

}
