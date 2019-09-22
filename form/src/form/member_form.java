package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bussiness.QLCSDL;
import object.admin;
import object.member;
import object.phieu_muon;
import object.sach;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JSpinner;

public class member_form extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNgayMuon;
	private JTextField textFieldTenBanDoc;
	private JTextField textFieldMaBanDoc;
	private JTable table_sach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_form frame = new member_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public member_form() throws SQLException, ClassNotFoundException {
		setTitle("Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 964, 550);
		contentPane.add(tabbedPane);
		
		JPanel panel_ds_sach = new JPanel();
		tabbedPane.addTab("Sách", null, panel_ds_sach, null);
		panel_ds_sach.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 181, 939, 330);
		panel_ds_sach.add(scrollPane_1);
		
		//Table Sách
		table_sach = new JTable();
		table_sach.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_sach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "T\u00EAn T\u00E1c G\u1EC9a", "NXB", "G\u00EDa Ti\u1EC1n", "S\u1ED1 L\u01B0\u1EE3ng"
			}
		));
		// Set row height
		table_sach.setRowHeight(30);
		// Align row center 
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0; x<6; x++)
		{
			table_sach.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}
		scrollPane_1.setViewportView(table_sach);
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_sach = (DefaultTableModel) table_sach.getModel();		
						
		//Xuất danh sách data phiếu mượn ra table
		dtm_sach = sach.xuatTable(dtm_sach);
		
		JPanel panel_lap_phieu_muon = new JPanel();
		tabbedPane.addTab("Lập phiếu mượn", null, panel_lap_phieu_muon, null);
		panel_lap_phieu_muon.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 247, 959, 275);
		panel_lap_phieu_muon.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EAn S\u00E1ch", "M\u00E3 Phi\u1EBFu M\u01B0\u1EE3n", "M\u00E3 B\u1EA1n \u0110\u1ECDc", "M\u00E3 S\u00E1ch", "Ng\u00E0y M\u01B0\u1EE3n", "H\u1EA1n Tr\u1EA3", "Ng\u00E0y Tr\u1EA3"
			}
		));
		// Set row height
		table.setRowHeight(30);
		// Align row center 
		DefaultTableCellRenderer centerRenderer_sach = new DefaultTableCellRenderer();
		centerRenderer_sach.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0; x<7; x++)
		{
			table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer_sach );
	    }
		scrollPane.setViewportView(table);
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();		
				
		//Xuất danh sách data phiếu mượn ra table
		dtm = phieu_muon.xuatTable(dtm);
		
		textFieldNgayMuon = new JTextField();
		textFieldNgayMuon.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNgayMuon.setEditable(false);
		textFieldNgayMuon.setBounds(554, 38, 113, 31);
		panel_lap_phieu_muon.add(textFieldNgayMuon);
		textFieldNgayMuon.setColumns(10);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/ MM/ yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		textFieldNgayMuon.setText(dtf.format(now));
		
		JLabel lblNgyMn = new JLabel("Ngày mượn");
		lblNgyMn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyMn.setBounds(454, 45, 96, 17);
		panel_lap_phieu_muon.add(lblNgyMn);
		
		JLabel lblNgyTr = new JLabel("Ngày trả");
		lblNgyTr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyTr.setBounds(472, 92, 78, 20);
		panel_lap_phieu_muon.add(lblNgyTr);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(554, 84, 113, 28);
		
		//Setup date trả sách nhỏ nhất được chọn, là 1 ngày sau ngày hiện tại
		LocalDate date =  LocalDate.now().plusDays(1);
		Date min = java.sql.Date.valueOf(date);
		dateChooser.setMinSelectableDate(min);
		panel_lap_phieu_muon.add(dateChooser);
		
		JLabel lblMSch = new JLabel("Mã Sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMSch.setBounds(99, 87, 66, 14);
		panel_lap_phieu_muon.add(lblMSch);
		
		JLabel lblTnBnc = new JLabel("Tên Bạn Đọc:");
		lblTnBnc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnBnc.setBounds(69, 38, 113, 28);
		panel_lap_phieu_muon.add(lblTnBnc);
		
		textFieldTenBanDoc = new JTextField();
		textFieldTenBanDoc.setEditable(false);
		textFieldTenBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTenBanDoc.setBounds(180, 41, 193, 26);
		panel_lap_phieu_muon.add(textFieldTenBanDoc);
		textFieldTenBanDoc.setColumns(10);
		textFieldTenBanDoc.setText(member.xuatTen());
		
		JSpinner spinnerMaSach = new JSpinner();
		spinnerMaSach.setBounds(180, 81, 59, 31);
		panel_lap_phieu_muon.add(spinnerMaSach);
		
		JLabel lblMBnc = new JLabel("Mã bạn đọc");
		lblMBnc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMBnc.setBounds(720, 45, 96, 17);
		panel_lap_phieu_muon.add(lblMBnc);
		
		textFieldMaBanDoc = new JTextField();
		textFieldMaBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaBanDoc.setEditable(false);
		textFieldMaBanDoc.setColumns(10);
		textFieldMaBanDoc.setBounds(809, 38, 37, 31);
		textFieldMaBanDoc.setText(Integer.toString(member.xuatMaHienHanh()));
		
		//Xử lý nhập phiếu mượn
		JButton btnLapPhieuMuon = new JButton("Lập phiếu mượn");
		btnLapPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLapPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("d-M-YYYY");
				textFieldNgayMuon.setText(df.format(dateChooser.getDate()));
				
				//Lấy ngày mượn sách		
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
				LocalDateTime now = LocalDateTime.now();  		
				String dateBorrow = dtf.format(now);
				//Lấy hạn trả sách
				Date dateReturn = dateChooser.getDate();
				LocalDateTime ldt = LocalDateTime.ofInstant(dateReturn.toInstant(), ZoneId.systemDefault());
				String dateReturnAsString = dtf.format(ldt);
				//Lấy mã sách
				int MaSach = (Integer) spinnerMaSach.getValue();
				
				try {
					int MaBanDoc = member.xuatMaHienHanh();	
					//Nếu số lượng tồn của sách được chọn = 0, không cho mượn 
					if(sach.soLuongTon(MaSach) == 0)
					{
						JOptionPane.showMessageDialog(null, "Hết Sách!");
					}
					//Kiểm tra sách đã mượn hay chưa
					//Lấy mã bạn đọc hiện hành
					else if (phieu_muon.kiemTraDaMuon(MaSach, MaBanDoc) != null)
					{
						JOptionPane.showMessageDialog(null, "Sách Đã Mượn!");
					}
					//Thỏa đk sẽ cho mượn sách
					else if( MaSach != 0 && dateReturn != null)
					{
						try {
							//Lấy mã bạn đọc hiện hành
							int MaBanDoc1 = member.xuatMaHienHanh();	
							//gọi hàm nhập phiếu mượn
							phieu_muon.nhapPhieuMuon(dateBorrow, MaSach, dateReturnAsString, MaBanDoc, MaBanDoc);
							//gọi hàm trừ 1 sách tồn
							sach.xoaMotSach(MaSach);
							//thông báo
							JOptionPane.showMessageDialog(null, "Mượn thành công");
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}									
					}
				} catch (HeadlessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		btnLapPhieuMuon.setBounds(372, 170, 178, 54);
		panel_lap_phieu_muon.add(btnLapPhieuMuon);
		
		
		
		panel_lap_phieu_muon.add(textFieldMaBanDoc);
		 
	}
}
