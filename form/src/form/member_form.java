package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import bussiness.QLCSDL;
import object.admin;
import object.phieu_muon;

import javax.swing.JScrollPane;

public class member_form extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_lap_phieu_muon = new JPanel();
		tabbedPane.addTab("Lập phiếu mượn", null, panel_lap_phieu_muon, null);
		panel_lap_phieu_muon.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 247, 959, 275);
		panel_lap_phieu_muon.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu M\u01B0\u1EE3n", "M\u00E3 B\u1EA1n \u0110\u1ECDc", "M\u00E3 S\u00E1ch", "Ng\u00E0y M\u01B0\u1EE3n", "H\u1EA1n Tr\u1EA3", "Ng\u00E0y Tr\u1EA3"
			}
		));
		scrollPane.setViewportView(table);
		
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();		
		
		//Xuất danh sách data phiếu mượn ra table
		dtm = phieu_muon.xuatTable(dtm);
		
//		 Connection conn = QLCSDL.connect();
//		 java.sql.Statement statement = ((java.sql.Connection) conn).createStatement();
//		 String sql = "SELECT * FROM phieu_muon";
//		 ResultSet resultSet = statement.executeQuery(sql);
//		 while (resultSet.next()) 
//		 {
//			 Vector v = new Vector();
//			 v.add(resultSet.getString("Ma_Phieu_Muon"));
//			 dtm.addRow(v);
//		 }
		 
	}
}
