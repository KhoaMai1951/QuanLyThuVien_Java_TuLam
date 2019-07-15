package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bussiness.QLCSDL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class member {
	public String Username;
	public String Password;
	 
	public void Member() 
	{
	}
	 
	public void Member(String Username, String Password) 
	{
	 this.Username = Username;
	 this.Password = Password;
	}
	 
	public String getUsername() 
	{
	 return Username;
	}
	
	public void setUsername(String Username) 
	{
	 this.Username = Username;
	}
	 
	public String getPassword() 
	{
	 return Password;
	}
	 
	public void setPassword(String Password) 
	{
	 this.Password = Password;
	 }

	public static member timUser(String username, String password) throws SQLException, ClassNotFoundException
	{
	 member member = null;
	 
	 Connection conn = QLCSDL.connect();
	 java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
	 String sql = "SELECT * FROM ban_doc WHERE username like '" + username + "' and Password like '" + password + "'";
	 ResultSet resultSet = statement.executeQuery(sql);
	 while (resultSet.next()) 
	 {
		 member = new member();
		 member.setUsername(resultSet.getString("username"));
		 member.setUsername(resultSet.getString("password"));
	 break;
	 }
	 
	 return member;
	}
}
