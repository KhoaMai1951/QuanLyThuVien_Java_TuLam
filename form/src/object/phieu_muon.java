package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import bussiness.QLCSDL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class phieu_muon {
	
	public int Ma_Phieu_Muon;
	public int Ma_Ban_Doc;
	public int Ma_Sach;
	public Date Ngay_Muon;  
	public Date Han_Tra;  
	public Date Ngay_Tra;  
	
	 
	public void Phieu_Muon() 
	{
	}
	 
	public void Phieu_Muon(int Ma_Phieu_Muon, int Ma_Ban_Doc,
			int Ma_Sach, Date Ngay_Muon, Date Han_Tra, Date Ngay_Tra) 
	{
	 this.Ma_Phieu_Muon = Ma_Phieu_Muon;
	 this.Ma_Ban_Doc = Ma_Ban_Doc;
	 this.Ma_Sach =  Ma_Sach;
	 this.Ngay_Muon = Ngay_Muon;
	 this.Han_Tra = Han_Tra;
	 this.Ngay_Tra = Ngay_Tra;
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
	
	
	//Xuất table
	public static DefaultTableModel xuatTable(DefaultTableModel dtm)
	{
		Connection conn = null;
		try {
			conn = QLCSDL.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 java.sql.Statement statement = null;
		try {
			statement = ((java.sql.Connection) conn).createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String sql = "SELECT * FROM phieu_muon";
		 ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			while (resultSet.next()) 
			 {
				 Vector v = new Vector();
				 v.add(resultSet.getString("Ma_Phieu_Muon"));
				 v.add(resultSet.getString("Ma_Ban_Doc"));
				 v.add(resultSet.getString("Ma_Sach"));
				 v.add(resultSet.getString("Ngay_Muon"));
				 v.add(resultSet.getString("Han_Tra"));
				 v.add(resultSet.getString("Ngay_Tra"));
				 dtm.addRow(v);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}
	
	public static int xuatPhieuMuon(){
		return 0;
	}

	public int getMa_Phieu_Muon() {
		return Ma_Phieu_Muon;
	}

	public void setMa_Phieu_Muon(int ma_Phieu_Muon) {
		Ma_Phieu_Muon = ma_Phieu_Muon;
	}

	public int getMa_Ban_Doc() {
		return Ma_Ban_Doc;
	}

	public void setMa_Ban_Doc(int ma_Ban_Doc) {
		Ma_Ban_Doc = ma_Ban_Doc;
	}

	public int getMa_Sach() {
		return Ma_Sach;
	}

	public void setMa_Sach(int ma_Sach) {
		Ma_Sach = ma_Sach;
	}

	public Date getNgay_Muon() {
		return Ngay_Muon;
	}

	public void setNgay_Muon(Date ngay_Muon) {
		Ngay_Muon = ngay_Muon;
	}

	public Date getHan_Tra() {
		return Han_Tra;
	}

	public void setHan_Tra(Date han_Tra) {
		Han_Tra = han_Tra;
	}

	public Date getNgay_Tra() {
		return Ngay_Tra;
	}

	public void setNgay_Tra(Date ngay_Tra) {
		Ngay_Tra = ngay_Tra;
	}
}
