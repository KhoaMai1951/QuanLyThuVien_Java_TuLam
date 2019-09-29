package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

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
	 String sql = "SELECT * FROM ban_doc WHERE username like '" + username + "' and Password like '" + password + "' and Da_Xoa like 0";
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
	
	//Xóa bạn đọc
	public static void xoaBanDoc(int MaBanDoc) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE ban_doc SET Da_Xoa = 1 WHERE Ma_Ban_Doc = "+MaBanDoc+";";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet>0)
		{
			JOptionPane.showMessageDialog(null,
					"Xóa bạn đọc thành công");
		}
	}
	
	//Sửa bạn đọc
	public static void suaBanDoc(int MaBanDoc, String username, String password, String TenBanDoc,
			String DiaChi, String SDT, String NgaySinh) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE ban_doc SET Username = '"+username+"', Password = '"+password+"', "
				+ "Ten_Ban_Doc = '"+TenBanDoc+"', Dia_Chi= '"+DiaChi+"', Dien_Thoai ='"+SDT+"', "
						+ "Ngay_Sinh='"+NgaySinh+"'  WHERE Ma_Ban_Doc = '"+MaBanDoc+"'";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet>0)
		{
			JOptionPane.showMessageDialog(null,
					"Sửa bạn đọc thành công");
		}
	}
	
	//Thêm bạn đọc
	public static void themBanDoc(String username, String password, String TenBanDoc,
			String DiaChi, String SDT, String NgaySinh) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		
		
		String sql = "INSERT INTO ban_doc (Username, Password, Ten_Ban_Doc, Dia_Chi, Dien_Thoai, Ngay_Sinh ) VALUES ('"+username+"', '"+password+"', '"+TenBanDoc+"', '"+DiaChi+"', '"+SDT+"', '"+NgaySinh+"');";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet>0)
		{
			JOptionPane.showMessageDialog(null,
					"Thêm bạn đọc thành công");
		}
	}
	
	//SELECT COUNT(Ma_Ban_Doc) FROM ban_doc WHERE Da_Xoa = 0
	//Xuất số lượng bạn đọc 
	public static int xuatSoBanDoc() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT COUNT(Ma_Ban_Doc) FROM ban_doc WHERE Da_Xoa = 0";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("COUNT(Ma_Ban_Doc)");
			return num;
		}
		return 0;
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
	
	//Xuất table bạn đọc ra form của Admin
	public static DefaultTableModel xuatTable(DefaultTableModel dtm) throws ClassNotFoundException, SQLException
	{
	Connection conn = null;
	
	conn = QLCSDL.connect();
	
	java.sql.Statement statement = null;
	
	statement = ((java.sql.Connection) conn).createStatement();
	
	String sql = "SELECT * FROM ban_doc WHERE da_xoa = 0";
	ResultSet resultSet = null;
	
	resultSet = statement.executeQuery(sql);

	while (resultSet.next()) 
	{
	 Vector v = new Vector();
	 v.add(resultSet.getString("Ma_Ban_Doc"));
	 v.add(resultSet.getString("username"));
	 v.add(resultSet.getString("password"));
	 v.add(resultSet.getString("Ten_Ban_Doc"));
	 v.add(resultSet.getString("Ngay_Sinh"));
	 v.add(resultSet.getString("Dia_Chi"));
	 v.add(resultSet.getString("Dien_Thoai"));
	 dtm.addRow(v);
	}
	
	return dtm;
	}
}
