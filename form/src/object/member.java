package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bussiness.QLCSDL;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

public class member {
	public String Username;
	public String Password;
	public int Ma_Ban_Doc;
	public String Ten_Ban_Doc;
	


	public String getTen_Ban_Doc() {
		return Ten_Ban_Doc;
	}

	public void setTen_Ban_Doc(String ten_Ban_Doc) {
		Ten_Ban_Doc = ten_Ban_Doc;
	}

	public int getMa_Ban_Doc() {
		return Ma_Ban_Doc;
	}

	public void setMa_Ban_Doc(int ma_Ban_Doc) {
		Ma_Ban_Doc = ma_Ban_Doc;
	}

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
		 member.setPassword(resultSet.getString("password"));
		 member.setMa_Ban_Doc(resultSet.getInt("ma_ban_doc"));
	 break;
	 }
	 return member;
	}
	//Lưu mã bạn đọc hiện hành vào cột Ma_Ban_Doc_Hien_Hanh
		public static void luuMaBanDocHienHanh(int ma_hien_hanh) throws ClassNotFoundException, SQLException {
			Connection conn = QLCSDL.connect();
			java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
			String sql = "UPDATE ban_doc SET Ma_Ban_Doc_Hien_Hanh = '"+ma_hien_hanh+"'";
			int resultSet = statement.executeUpdate(sql);
		}	
	//Xuất tên bạn đọc từ tên hiện hành
	public static String xuatTen() throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT * FROM ban_doc WHERE Ma_Ban_Doc = Ma_Ban_Doc_Hien_Hanh ";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			String name = resultSet.getString("Ten_Ban_Doc");
			return name;
		}
		return "";
	}
	
	//Xuất mã bạn đọc hiện hành
	public static int xuatMaHienHanh() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT * FROM ban_doc LIMIT 1";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("Ma_Ban_Doc_Hien_Hanh");
			return num;
		}
		return 0;
	}
		
}
