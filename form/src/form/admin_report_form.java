package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.admin;
import object.member;
import object.phieu_muon;
import object.sach;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class admin_report_form extends JFrame {

	private JPanel contentPane;
	private JTable tablePhieuMuonQuaHan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_report_form frame = new admin_report_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Output table 
	public void outputTable (JTable table, String tableName) throws ClassNotFoundException, SQLException
	{
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_sach = (DefaultTableModel) table.getModel();		
	
		switch(tableName)
		{
			case "tablePhieuMuonQuaHan":
				//Xuất danh sách data phiếu mượn ra table
				dtm_sach = phieu_muon.xuatTablePhieuMuonQuaHan(dtm_sach);
				break;
			
		}
	}

	/**
	 * Create the frame.
	 */
	public admin_report_form() {
		setTitle("B\u00C1O C\u00C1O");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTngSBn = new JLabel("T\u1ED5ng s\u1ED1 b\u1EA1n \u0111\u1ECDc: ");
		lblTngSBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSBn.setBounds(59, 23, 126, 32);
		contentPane.add(lblTngSBn);
		
		// Xuất số bạn đọc
		JLabel labelSoBanDoc = new JLabel("0");
		labelSoBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSoBanDoc.setBounds(195, 23, 71, 32);
		contentPane.add(labelSoBanDoc);
		try {
			labelSoBanDoc.setText(Integer.toString(member.xuatSoBanDoc()));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JLabel lblTngSSch = new JLabel("Tổng số sách: ");
		lblTngSSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTngSSch.setBounds(59, 66, 126, 32);
		contentPane.add(lblTngSSch);
		
		//Xuất số sách
		JLabel labelSoSach;
		try {
			labelSoSach = new JLabel(sach.xuatSoSach());
			
			labelSoSach.setFont(new Font("Tahoma", Font.BOLD, 14));
			labelSoSach.setBounds(170, 66, 71, 32);
			contentPane.add(labelSoSach);
			
			JLabel lblTngSPhiu = new JLabel("Tổng số phiếu mượn:");
			lblTngSPhiu.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTngSPhiu.setBounds(59, 113, 166, 32);
			contentPane.add(lblTngSPhiu);
			
			JLabel labelSoPhieuMuon = new JLabel(Integer.toString(phieu_muon.xuatSoPhieuMuon()));
			labelSoPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 14));
			labelSoPhieuMuon.setBounds(224, 113, 71, 32);
			contentPane.add(labelSoPhieuMuon);
			
			JLabel lblTngSBn_1 = new JLabel("Tổng số người mượn sách:");
			lblTngSBn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTngSBn_1.setBounds(59, 153, 186, 32);
			contentPane.add(lblTngSBn_1);
			
			JLabel labelSoNguoiMuonSach = new JLabel(Integer.toString(phieu_muon.xuatSoNguoiMuon()));
			labelSoNguoiMuonSach.setFont(new Font("Tahoma", Font.BOLD, 14));
			labelSoNguoiMuonSach.setBounds(267, 153, 71, 32);
			contentPane.add(labelSoNguoiMuonSach);
			
			JLabel lblTngSPhiu_1 = new JLabel("Tổng số phiếu mượn quá hạn:");
			lblTngSPhiu_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTngSPhiu_1.setBounds(59, 196, 218, 32);
			contentPane.add(lblTngSPhiu_1);
			
			JLabel labelSoPhieuMuonQuaHan = new JLabel(Integer.toString(phieu_muon.xuatPhieuMuonQuaHan()));
			labelSoPhieuMuonQuaHan.setFont(new Font("Tahoma", Font.BOLD, 14));
			labelSoPhieuMuonQuaHan.setBounds(287, 196, 71, 32);
			contentPane.add(labelSoPhieuMuonQuaHan);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 315, 1184, 321);
			contentPane.add(scrollPane);
			
			tablePhieuMuonQuaHan = new JTable();
			tablePhieuMuonQuaHan.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Ma Phieu Muon", "Ma Ban Doc", "Ma Sach", "Ngay Muon", "Han Tra", "Ngay Tra"
				}
			));
			scrollPane.setViewportView(tablePhieuMuonQuaHan);
			outputTable(tablePhieuMuonQuaHan, "tablePhieuMuonQuaHan");
			
			JLabel lblDanhSchPhiu = new JLabel("Danh sách phiếu mượn quá hạn");
			lblDanhSchPhiu.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblDanhSchPhiu.setBounds(372, 253, 339, 32);
			contentPane.add(lblDanhSchPhiu);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
