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

public class sach {
	public int MaSach;
	public String TenSach;
	public String TenTacGia;
	public String NXB;
	public int GiaTien;
	public int SoLuong;
	
	 
	public void Sach() 
	{
	}
	 
	public void Sach(int MaSach, String TenSach, String TenTacGia, String NXB,
			int GiaTien, int SoLuong) 
	{
	 this.MaSach = MaSach;
	 this.TenSach = TenSach;
	 this.TenTacGia = TenTacGia;
	 this.NXB = NXB;
	 this.GiaTien = GiaTien;
	 this.SoLuong = SoLuong;
	}

	public int getMaSach() {
		return MaSach;
	}

	public void setMaSach(int maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}

	public String getTenTacGia() {
		return TenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		TenTacGia = tenTacGia;
	}

	public String getNXB() {
		return NXB;
	}

	public void setNXB(String nXB) {
		NXB = nXB;
	}

	public int getGiaTien() {
		return GiaTien;
	}

	public void setGiaTien(int giaTien) {
		GiaTien = giaTien;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	 
	//Lấy số lượng tồn của sách được chọn
	public static int soLuongTon(int MaSach) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT so_luong FROM sach WHERE ma_sach="+MaSach;
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("so_luong");
			return num;
		}
		return 0;
	}
	
	//Thêm sách mới
	public static void themSachMoi(String TenSach, String TenTacGia,
			String NXB, int GiaTien, int SoLuong) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "INSERT INTO sach (ten_sach, ten_tac_gia, nha_xb, gia_tien, so_luong) "
				+ "VALUES ('"+TenSach+"', '"+TenTacGia+"', '"+NXB+"', "+GiaTien+", "+SoLuong+");";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Đã thêm sách!");
	}
	
	//Xóa sách 					
	public static void xoaSach(String MaSach) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE sach SET da_xoa = 1 WHERE Ma_Sach = "+MaSach+";";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Đã xóa sách!");
	}
	
	//Sửa sách 					
	public static void suaSach(int MaSach, String TenSach, String TenTacGia,
			String NXB, int GiaTien, int SoLuong) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE sach SET Ten_Sach = '"+TenSach+"', Ten_Tac_Gia = '"+TenTacGia+"', Nha_XB = '"+NXB+"', Gia_Tien = "+GiaTien+", So_Luong = "+SoLuong+" WHERE Ma_Sach = "+MaSach+";";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Đã sửa thông tin sách!");
	}
	
	//Xóa số lượng tồn bớt 1 khi sách được chọn để mượn
	public static void xoaMotSach(int MaSach) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE sach SET so_luong = so_luong - 1 WHERE Ma_Sach = 2";
		int resultSet = statement.executeUpdate(sql);
	}
	//Xuất table sách
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
		 String sql = "SELECT * FROM sach WHERE da_xoa = 0";
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
				 v.add(resultSet.getString("Ma_Sach"));
				 v.add(resultSet.getString("Ten_Sach"));
				 v.add(resultSet.getString("Ten_Tac_Gia"));
				 v.add(resultSet.getString("Nha_XB"));
				 v.add(resultSet.getString("Gia_Tien"));
				 v.add(resultSet.getString("So_Luong"));
				 dtm.addRow(v);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}
	//Xuất số sách
	public static String xuatSoSach() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT COUNT(Ma_Sach) FROM sach WHERE Da_Xoa = 0";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			String num = Integer.toString(resultSet.getInt("COUNT(Ma_Sach)"));
			return num;
		}
		return null;
	}
}
