package object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
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
	
	//Lưu mã bạn đọc hiện hành vào cột Ma_Ban_Doc_Hien_Hanh
	public static void luuMaBanDocHienHanh(int ma_hien_hanh) throws ClassNotFoundException, SQLException {
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE phieu_muon SET Ma_Ban_Doc_Hien_Hanh = '"+ma_hien_hanh+"'";
		int resultSet = statement.executeUpdate(sql);
	}
	//Xuất table thông tin phiếu mượn cho bảng Bạn Đọc
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
		 String sql = "SELECT * FROM phieu_muon, sach WHERE ma_ban_doc = ma_ban_doc_hien_hanh "
		 		+ "AND phieu_muon.Ma_Sach = sach.Ma_Sach";
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
				 v.add(resultSet.getString("Ten_Sach"));
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
	
	//Xuất table thông tin phiếu mượn cho bảng Admin Update phiếu mượn
	public static DefaultTableModel xuatTableAdminUpdate(DefaultTableModel dtm)
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
		 String sql = "SELECT * FROM phieu_muon WHERE Da_Xoa = 0";
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
				 String zero = "0";
				 if(resultSet.getInt("Tra_Sach") == 0)
					 v.add("Chưa trả sách");
				 if(resultSet.getInt("Tra_Sach") == 1)
					 v.add("Đã trả sách");
				 dtm.addRow(v);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtm;
	}

	//Nhập dữ liệu phiếu mượn mới từ form người đọc
	public static void nhapPhieuMuon(String dateBorrow, int MaSach, String dateReturn,
			int MaBanDoc, int MaBanDocHienHanh) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "INSERT INTO phieu_muon(Ngay_Muon, Ma_Sach, Han_Tra, Ma_Ban_Doc, Ma_Ban_Doc_Hien_Hanh) "
				+ "VALUES('"+dateBorrow+"','"+MaSach+"', "
				+ "'"+dateReturn+"', '"+MaBanDoc+"', '"+MaBanDocHienHanh+"')";
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
		{
			JOptionPane.showMessageDialog(null, "Mượn Sách Thành Công!");
		}
	}
	
	//Kiểm tra bạn đọc đã mượn 1 cuốn sách đã chọn rồi hay chưa
	public static phieu_muon kiemTraDaMuon(int Ma_Sach, int Ma_Ban_Doc_Hien_Hanh) throws ClassNotFoundException, SQLException
	{
		phieu_muon phieu_muon = null;
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT * FROM phieu_muon WHERE Ma_Sach = "+Ma_Sach+" "
				+ "AND Ma_Ban_Doc = "+Ma_Ban_Doc_Hien_Hanh+ " AND Tra_Sach = 0" ;
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) 
		{
		phieu_muon = new phieu_muon();
		phieu_muon.setMa_Sach(Ma_Sach);
		break;
		}
		 
		return phieu_muon;
	}
	
	// Xóa phiếu mượn
	public static void xoaPhieuMuon(int MaPhieuMuon) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE phieu_muon SET Da_Xoa = 1 WHERE Ma_Phieu_Muon = "+MaPhieuMuon+"" ;
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công!");
	}
	
	// Trả sách
	public static void traSach(int MaPhieuMuon, String NgayTra) throws ClassNotFoundException, SQLException
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE phieu_muon SET Tra_Sach = 1, Ngay_Tra = '"+NgayTra+"' WHERE Ma_Phieu_Muon = "+MaPhieuMuon+"" ;
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Trả sách thành công!");
	}
	
	// Sửa thông tin phiếu mượn
	public static void suaPhieuMuon(int MaPhieuMuon, int MaSach, String Ngay_Muon, String Han_Tra, String Ngay_Tra ) throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "UPDATE phieu_muon SET Ma_Sach = "+MaSach+", Ngay_Muon = '"+Ngay_Muon+"', Han_Tra = '"+Han_Tra+"', Ngay_Tra = '"+Ngay_Tra+"' WHERE Ma_Phieu_Muon = "+MaPhieuMuon+"" ;
		int resultSet = statement.executeUpdate(sql);
		if(resultSet > 0)
			JOptionPane.showMessageDialog(null, "Sửa thông tin phiếu mượn thành công!");
	}
	
	//Xuất số phiếu mượn
	public static int xuatSoPhieuMuon() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT COUNT(Ma_Phieu_Muon) FROM phieu_muon WHERE Da_Xoa = 0";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("COUNT(Ma_Phieu_Muon)");
			return num;
		}
		return 0;
	}
	
	//Xuất số người mượn
	public static int xuatSoNguoiMuon() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT COUNT( DISTINCT Ma_Ban_Doc) FROM phieu_muon WHERE Da_Xoa = 0";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("COUNT( DISTINCT Ma_Ban_Doc)");
			return num;
		}
		return 0;
	}
	
	//SELECT COUNT(Ma_Phieu_Muon) FROM phieu_muon WHERE Ngay_Tra > Han_Tra
	//Xuất số phiếu mượn trả quá hạn
	public static int xuatPhieuMuonQuaHan() throws ClassNotFoundException, SQLException 
	{
		Connection conn = QLCSDL.connect();
		java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
		String sql = "SELECT COUNT(Ma_Phieu_Muon) FROM phieu_muon WHERE Ngay_Tra > Han_Tra AND Da_Xoa = 0";
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next())
		{
			int num = resultSet.getInt("COUNT(Ma_Phieu_Muon)");
			return num;
		}
		return 0;
	}
	
	//Xuất table danh sách phiếu mượn quá hạn
	public static DefaultTableModel xuatTablePhieuMuonQuaHan(DefaultTableModel dtm) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		
		conn = QLCSDL.connect();
		
		java.sql.Statement statement = null;
		
		statement = ((java.sql.Connection) conn).createStatement();
		
		String sql = "SELECT * FROM phieu_muon WHERE Ngay_Tra > Han_Tra AND Da_Xoa = 0";
		ResultSet resultSet = null;
		
		resultSet = statement.executeQuery(sql);
		
		
		while (resultSet.next()) 
		 {
			 Vector v = new Vector();
			 v.add(resultSet.getString("ma_phieu_muon"));
			 v.add(resultSet.getString("ma_ban_doc"));
			 v.add(resultSet.getString("Ma_sach"));
			 v.add(resultSet.getString("Ngay_Muon"));
			 v.add(resultSet.getString("Han_Tra"));
			 v.add(resultSet.getString("Ngay_Tra"));
			 dtm.addRow(v);
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
