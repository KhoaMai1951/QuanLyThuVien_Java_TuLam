package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bussiness.QLCSDL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class admin {
	public String Username;
	public String Password;
	 
	public void Admin() 
	{
	}
	 
	public void Admin(String Username, String Password) 
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

	public static admin timUser(String username, String password) throws SQLException, ClassNotFoundException
	{
	 admin admin = null;
	 
	 Connection conn = QLCSDL.connect();
	 java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
	 String sql = "SELECT * FROM quan_tri WHERE username like '" + username + "' and Password like '" + password + "' and Da_xoa like 0";
	 ResultSet resultSet = statement.executeQuery(sql);
	 while (resultSet.next()) 
	 {
	 admin = new admin();
	 admin.setUsername(resultSet.getString("username"));
	 admin.setUsername(resultSet.getString("password"));
	 break;
	 }
	 
	 return admin;
	}
	
	//Xuất table admin cho table admin
	public static DefaultTableModel xuatTable(DefaultTableModel dtm) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		
		conn = QLCSDL.connect();
		
		java.sql.Statement statement = null;
		
		statement = ((java.sql.Connection) conn).createStatement();
		
		String sql = "SELECT * FROM quan_tri WHERE Da_Xoa = 0";
		ResultSet resultSet = null;
		
		resultSet = statement.executeQuery(sql);
		
		
		while (resultSet.next()) 
		 {
			 Vector v = new Vector();
			 v.add(resultSet.getString("username"));
			 v.add(resultSet.getString("password"));
			 v.add(resultSet.getString("Ma_Admin"));
			 dtm.addRow(v);
		 }
		return dtm;
	}
	
	// Thêm Admin
	public static void themAdmin(String Username, String Password) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		
		conn = QLCSDL.connect();
		
		java.sql.Statement statement = null;
		
		statement = ((java.sql.Connection) conn).createStatement();
		
		String sql = "INSERT INTO quan_tri(username, password) VALUES ('"+Username+"','"+Password+"') ";
		
		int resultSet = statement.executeUpdate(sql);
		
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Them Admin thanh cong!");
	}
	
	//Xóa Admin
	public static void xoaAdmin(int MaAdmin) throws ClassNotFoundException, SQLException 
	{
		Connection conn = null;
		
		conn = QLCSDL.connect();
		
		java.sql.Statement statement = null;
		
		statement = ((java.sql.Connection) conn).createStatement();
		
		String sql = "UPDATE quan_tri SET Da_Xoa = 1 WHERE Ma_Admin = "+MaAdmin+"";
		
		int resultSet = statement.executeUpdate(sql);
		
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Xoa Admin thanh cong!");
	}
	
	//Sửa Admin
	public static void suaAdmin(int MaAdmin, String Username, String Password) throws ClassNotFoundException, SQLException 
	{
		Connection conn = null;
		
		conn = QLCSDL.connect();
		
		java.sql.Statement statement = null;
		
		statement = ((java.sql.Connection) conn).createStatement();
		
		String sql = "UPDATE quan_tri SET username = '"+Username+"', Password = '"+Password+"' WHERE Ma_Admin = "+MaAdmin+"";
		
		int resultSet = statement.executeUpdate(sql);
		
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Sua Admin thanh cong!");
	}
}
