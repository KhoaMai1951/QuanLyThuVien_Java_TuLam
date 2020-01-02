package bussiness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QLCSDL {
	//private static final String JDBC_URL = "jdbc:mysql://localhost:3306/nhasach?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String JDBC_URL = "jdbc:mysql://remotemysql.com:3306/NUMK10OFLJ";
	private static final String JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
//	private static final String JDBC_USERNAME = "root";
//	private static final String JDBC_PASSWORD = "";
	private static final String JDBC_USERNAME = "NUMK10OFLJ";
	private static final String JDBC_PASSWORD = "FVKS2A5kuU";
	
	//Kết nối CSDL
	public static Connection connect() throws SQLException, ClassNotFoundException 
	{
		Class.forName(JDBC_DRIVER_CLASS);
		Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		return conn;
	}

}
