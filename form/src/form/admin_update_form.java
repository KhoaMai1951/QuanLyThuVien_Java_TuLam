package form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import object.admin;
import object.member;
import object.phieu_muon;
import object.sach;

public class admin_update_form extends JFrame {

	private JPanel contentPane;
	private JTable tableUpdateSach;
	private JTextField textFieldMaSach;
	private JTextField textFieldTenSach;
	private JTextField textFieldTenTacGia;
	private JTextField textFieldNXB;
	private JTextField textFieldGiaTien;
	private JTextField textFieldSoLuong;
	private JTable tableUpdatePhieuMuon;
	private JTextField textFieldMaPhieuMuon;
	private JTextField textFieldMaBanDoc;
	private JTextField textFieldMaSach2;
	private JTable tableAdminUpdate;
	private JTextField textFieldAdminUsername;
	private JTextField textFieldAdminPassword;
	private JTextField textFieldMaAdmin;
	private JTable tableMemberUpdate;
	private JTextField textFieldUsernameBanDoc;
	private JTextField textFieldPasswordBanDoc;
	private JTextField textFieldTenBanDoc;
	private JTextField textFieldDiaChiMember;
	private JTextField textFieldDienThoaiMember;
	private JTextField textFieldMaBanDocUpdateBanDoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_update_form frame = new admin_update_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Lấy dữ liệu từ JDateChooser chuyển thành String
	public String outputDate(JDateChooser jdatechooser)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		Date JDateChooserReturnAsDate = jdatechooser.getDate();
		LocalDateTime ldtDate = LocalDateTime.ofInstant(JDateChooserReturnAsDate.toInstant(), 
				ZoneId.systemDefault());
		String ngayMuonReturnAsString = dtf.format(ldtDate);
		return ngayMuonReturnAsString;
	}
	// Output table Sach
	public void outputTable (JTable table, String tableName) throws ClassNotFoundException, SQLException
	{
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_sach = (DefaultTableModel) table.getModel();		
	
		switch(tableName)
		{
			case "tableUpdatePhieuMuon":
				//Xuất danh sách data phiếu mượn ra table
				dtm_sach = phieu_muon.xuatTableAdminUpdate(dtm_sach);
				break;
			case "tableUpdateSach":
				//Xuất danh sách data sách ra table
				dtm_sach = sach.xuatTable(dtm_sach);
				break;
			case "tableAdminUpdate":
				//Xuất danh sách data sách ra table
				dtm_sach = admin.xuatTable(dtm_sach);
				break;
			case "tableMemberUpdate":
				//Xuất danh sách data sách ra table
				dtm_sach = member.xuatTable(dtm_sach);
				break;
		}
	}
	
	// Clear Table
	public void clearTable(JTable table) 
	{
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) 
		{
		    dm.removeRow(i);
		}
	}
	
	// Update Table
	public void updateTable(JTable table, String tableName) throws ClassNotFoundException, SQLException
	{
		clearTable(table);
		outputTable(table, tableName);
	}
		
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public admin_update_form() throws ClassNotFoundException, SQLException {
		setTitle("C\u1EADp Nh\u1EADt ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPaneUpdateSach = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneUpdateSach.setBounds(10, 11, 1164, 639);
		contentPane.add(tabbedPaneUpdateSach);
		
		JPanel panel_UpdateSach = new JPanel();
		tabbedPaneUpdateSach.addTab("Cập Nhật Sách", null, panel_UpdateSach, null);
		
		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TenSach = textFieldTenSach.getText();
				String TenTacGia = textFieldTenTacGia.getText();
				String NXB = textFieldNXB.getText();
				int GiaTien  = Integer.parseInt(textFieldGiaTien.getText());	
				int SoLuong  = Integer.parseInt(textFieldSoLuong.getText());
				
				try {
					sach.themSachMoi(TenSach, TenTacGia, NXB, GiaTien, SoLuong);
					updateTable(tableUpdateSach, "tableUpdateSach");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnXoa = new JButton("XÓA");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaSach = textFieldMaSach.getText();
				try {
					// Hàm xoaSach của sach
					sach.xoaSach(MaSach);
					// Hàm updateTable ở trên
					updateTable(tableUpdateSach, "tableUpdateSach");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoa.setEnabled(false);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaSach = Integer.parseInt(textFieldMaSach.getText());
				String TenTacGia = textFieldTenTacGia.getText();
				String NXB = textFieldNXB.getText();
				String TenSach = textFieldTenSach.getText();
				int SoLuong = Integer.parseInt(textFieldSoLuong.getText());
				int GiaTien = Integer.parseInt(textFieldGiaTien.getText());
				
				try {
					sach.suaSach(MaSach, TenSach, TenTacGia, NXB, GiaTien, SoLuong);
					updateTable(tableUpdateSach, "tableUpdateSach");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSua.setEnabled(false);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnThem = new JRadioButton("   Thêm");
		rdbtnThem.setSelected(true);
		rdbtnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnXoaSua = new JRadioButton("   Xóa, Sửa");
		rdbtnXoaSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblMSch = new JLabel("Mã Sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldMaSach = new JTextField();
		textFieldMaSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldMaSach.setEditable(false);
		textFieldMaSach.setColumns(10);
		
		textFieldTenSach = new JTextField();
		textFieldTenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldTenSach.setColumns(10);
		
		JLabel lblTnSch = new JLabel("Tên Sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldTenTacGia = new JTextField();
		textFieldTenTacGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldTenTacGia.setColumns(10);
		
		JLabel lblTnTcGa = new JLabel("Tên Tác Gỉa");
		lblTnTcGa.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldNXB = new JTextField();
		textFieldNXB.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldNXB.setColumns(10);
		
		JLabel lblNxb = new JLabel("NXB");
		lblNxb.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldGiaTien = new JTextField();
		textFieldGiaTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldGiaTien.setColumns(10);
		
		JLabel lblGaTin = new JLabel("Gía Tiền");
		lblGaTin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldSoLuong = new JTextField();
		textFieldSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldSoLuong.setColumns(10);
		
		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel_UpdateSach = new GroupLayout(panel_UpdateSach);
		gl_panel_UpdateSach.setHorizontalGroup(
			gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
				.addGroup(gl_panel_UpdateSach.createSequentialGroup()
					.addGap(257)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(87)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(268, Short.MAX_VALUE))
				.addGroup(gl_panel_UpdateSach.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnXoaSua, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnThem, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_UpdateSach.createSequentialGroup()
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addComponent(lblMSch, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldMaSach, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addComponent(lblTnSch, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldTenSach, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
							.addGap(57)
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTnTcGa, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldTenTacGia, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(42)
									.addComponent(lblNxb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldNXB)))
							.addGap(106)
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addComponent(lblGaTin, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldGiaTien, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldSoLuong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(108))
		);
		gl_panel_UpdateSach.setVerticalGroup(
			gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_UpdateSach.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_UpdateSach.createSequentialGroup()
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_UpdateSach.createSequentialGroup()
										.addGap(2)
										.addComponent(lblMSch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
									.addComponent(textFieldTenTacGia, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldMaSach, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTnTcGa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(19)
									.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldTenSach, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTnSch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldNXB, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNxb, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel_UpdateSach.createSequentialGroup()
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldGiaTien, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(2)
									.addComponent(lblGaTin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(2)
									.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldSoLuong, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
					.addGap(24)
					.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(rdbtnThem, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(rdbtnXoaSua, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
		);
		
		tableUpdateSach = new JTable();
		tableUpdateSach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "T\u00EAn T\u00E1c G\u1EC9a", "NXB", "G\u00EDa Ti\u1EC1n", "S\u1ED1 L\u01B0\u1EE3ng"
			}
		));
		scrollPane.setViewportView(tableUpdateSach);
		// Set row height
		tableUpdateSach.setRowHeight(30);
		// Align row center 
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0; x<6; x++)
		{
			tableUpdateSach.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}
		scrollPane.setViewportView(tableUpdateSach);
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_sach = (DefaultTableModel) tableUpdateSach.getModel();		
						
		//Xuất danh sách data phiếu mượn ra table
		dtm_sach = sach.xuatTable(dtm_sach);
		
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnThem);
        group.add(rdbtnXoaSua);
        
        rdbtnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnThem.isSelected() == true)
		        {
		        	btnXoa.setEnabled(false);
		        	btnSua.setEnabled(false);
		        	btnThem.setEnabled(true);
		        	
		        	textFieldMaSach.setText("");
		        	textFieldTenSach.setText("");
		        	textFieldNXB.setText("");
		        	textFieldTenTacGia.setText("");
		        	textFieldSoLuong.setText("");
		        	textFieldGiaTien.setText("");
		        }
			}
		});
        
        rdbtnXoaSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnXoaSua.isSelected() == true)
		        {
		        	btnThem.setEnabled(false);
		        	btnXoa.setEnabled(true);
		        	btnSua.setEnabled(true);
		        	
		        	//Lấy dữ liệu từ table xuất ra textfield
					tableUpdateSach.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked (MouseEvent e) 
						{
							int row = tableUpdateSach.getSelectedRow();
							textFieldMaSach.setText((String)tableUpdateSach.getModel().getValueAt(row, 0));
							textFieldTenSach.setText((String)tableUpdateSach.getModel().getValueAt(row, 1));
							textFieldTenTacGia.setText((String)tableUpdateSach.getModel().getValueAt(row, 2));
							textFieldNXB.setText((String)tableUpdateSach.getModel().getValueAt(row, 3));
							textFieldGiaTien.setText((String)tableUpdateSach.getModel().getValueAt(row, 4));
							textFieldSoLuong.setText((String)tableUpdateSach.getModel().getValueAt(row, 5));
						}
					});
		        }
			}
		});
		panel_UpdateSach.setLayout(gl_panel_UpdateSach);
		
		JPanel panel_UpdatePhieuMuon = new JPanel();
		tabbedPaneUpdateSach.addTab("Cập Nhật Phiếu Mượn", null, panel_UpdatePhieuMuon, null);
		panel_UpdatePhieuMuon.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 283, 1159, 328);
		panel_UpdatePhieuMuon.add(scrollPane_1);
		
		tableUpdatePhieuMuon = new JTable();
		tableUpdatePhieuMuon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Phi\u1EBFu M\u01B0\u1EE3n", "M\u00E3 B\u1EA1n \u0110\u1ECDc", "M\u00E3 S\u00E1ch", "Ng\u00E0y M\u01B0\u1EE3n", "H\u1EA1n Tr\u1EA3", "Ng\u00E0y Tr\u1EA3", "\u0110\u00E3 Tr\u1EA3 S\u00E1ch"
			}
		));
		scrollPane_1.setViewportView(tableUpdatePhieuMuon);
		// Set row height
		tableUpdatePhieuMuon.setRowHeight(30);
		
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_phieuMuon = (DefaultTableModel) tableUpdatePhieuMuon.getModel();
		
		//Xuất danh sách data phiếu mượn ra table update của admin
		dtm_phieuMuon = phieu_muon.xuatTableAdminUpdate(dtm_phieuMuon);
		
		JLabel lblMPhiuMn = new JLabel("Mã Phiếu Mượn");
		lblMPhiuMn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMPhiuMn.setBounds(26, 26, 110, 25);
		panel_UpdatePhieuMuon.add(lblMPhiuMn);
		
		textFieldMaPhieuMuon = new JTextField();
		textFieldMaPhieuMuon.setEditable(false);
		textFieldMaPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaPhieuMuon.setBounds(146, 27, 55, 23);
		panel_UpdatePhieuMuon.add(textFieldMaPhieuMuon);
		textFieldMaPhieuMuon.setColumns(10);
		
		textFieldMaBanDoc = new JTextField();
		textFieldMaBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaBanDoc.setColumns(10);
		textFieldMaBanDoc.setBounds(146, 85, 104, 23);
		panel_UpdatePhieuMuon.add(textFieldMaBanDoc);
		
		JLabel lblMBnc = new JLabel("Mã Bạn Đọc");
		lblMBnc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMBnc.setBounds(45, 84, 91, 25);
		panel_UpdatePhieuMuon.add(lblMBnc);
		
		textFieldMaSach2 = new JTextField();
		textFieldMaSach2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaSach2.setColumns(10);
		textFieldMaSach2.setBounds(441, 27, 75, 23);
		panel_UpdatePhieuMuon.add(textFieldMaSach2);
		
		JLabel lblMSch_1 = new JLabel("Mã Sách");
		lblMSch_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMSch_1.setBounds(363, 26, 68, 25);
		panel_UpdatePhieuMuon.add(lblMSch_1);
		
		JLabel lblNgyMn = new JLabel("Ngày Mượn");
		lblNgyMn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyMn.setBounds(340, 83, 91, 25);
		panel_UpdatePhieuMuon.add(lblNgyMn);
		
		JLabel lblHnTr = new JLabel("Hạn Trả");
		lblHnTr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHnTr.setBounds(711, 26, 61, 25);
		panel_UpdatePhieuMuon.add(lblHnTr);
		
		JLabel lblNgyTr = new JLabel("Ngày Trả");
		lblNgyTr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyTr.setBounds(704, 83, 68, 25);
		panel_UpdatePhieuMuon.add(lblNgyTr);
		
		JDateChooser dateChooserHanTra = new JDateChooser();
		dateChooserHanTra.setBounds(797, 26, 130, 20);
		panel_UpdatePhieuMuon.add(dateChooserHanTra);
		
		JDateChooser dateChooserNgayTra = new JDateChooser();
		dateChooserNgayTra.setBounds(797, 85, 130, 20);
		panel_UpdatePhieuMuon.add(dateChooserNgayTra);
		
		JDateChooser dateChooserNgayMuon = new JDateChooser();
		dateChooserNgayMuon.setBounds(441, 85, 130, 20);
		panel_UpdatePhieuMuon.add(dateChooserNgayMuon);
		
		//Xử lý button thêm phiếu mượn
		JButton btnThemPhieuMuon = new JButton("Thêm Phiếu Mượn");
		btnThemPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Lấy Ngày mượn
				String NgayMuon = outputDate(dateChooserNgayMuon);
				
				//Lấy Hạn trả
				String HanTra = outputDate(dateChooserHanTra);
				
				//Lấy Mã Bạn Đọc
				int MaBanDoc = Integer.parseInt(textFieldMaBanDoc.getText());
				
				//Lấy Mã Sách
				int MaSach = Integer.parseInt(textFieldMaSach2.getText());
				
				//Lấy Mã Bạn Đọc Hiện Hành
				try {
					int MaBanDocHienHanh;
					MaBanDocHienHanh = member.xuatMaHienHanh();
					//Gọi hàm nhập phiếu mượn
					phieu_muon.nhapPhieuMuon(NgayMuon, MaSach, HanTra, MaBanDoc, MaBanDocHienHanh);
					//Gọi hàm Update Table
					updateTable(tableUpdatePhieuMuon, "tableUpdatePhieuMuon");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnThemPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThemPhieuMuon.setBounds(340, 174, 147, 46);
		panel_UpdatePhieuMuon.add(btnThemPhieuMuon);
		
		JButton btnXoaPhieuMuon = new JButton("Xóa Phiếu Mượn");
		btnXoaPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaPhieuMuon =  Integer.parseInt(textFieldMaPhieuMuon.getText());
				try {
					phieu_muon.xoaPhieuMuon(MaPhieuMuon);
					updateTable(tableUpdatePhieuMuon, "tableUpdatePhieuMuon");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoaPhieuMuon.setEnabled(false);
		btnXoaPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoaPhieuMuon.setBounds(549, 174, 147, 46);
		panel_UpdatePhieuMuon.add(btnXoaPhieuMuon);
		
		JButton btnSuaPhieuMuon = new JButton("Sửa Phiếu Mượn");
		btnSuaPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaPhieuMuon = Integer.parseInt(textFieldMaPhieuMuon.getText());
				int MaSach = Integer.parseInt(textFieldMaSach2.getText());
				String Ngay_Muon = outputDate(dateChooserNgayMuon);
				String Han_Tra = outputDate(dateChooserHanTra);
				String Ngay_Tra = outputDate(dateChooserNgayTra);
				try {
					phieu_muon.suaPhieuMuon(MaPhieuMuon, MaSach, Ngay_Muon, Han_Tra, Ngay_Tra);
					updateTable(tableUpdatePhieuMuon, "tableUpdatePhieuMuon");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSuaPhieuMuon.setEnabled(false);
		btnSuaPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSuaPhieuMuon.setBounds(759, 174, 147, 46);
		panel_UpdatePhieuMuon.add(btnSuaPhieuMuon);
		
		JButton btnTraSach = new JButton("Trả Sách");
		btnTraSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaPhieuMuon = Integer.parseInt(textFieldMaPhieuMuon.getText());
				try {
					//Lấy ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
					LocalDateTime now = LocalDateTime.now();  		
					String dateReturn = dtf.format(now);
					
					phieu_muon.traSach(MaPhieuMuon, dateReturn);
					updateTable(tableUpdatePhieuMuon, "tableUpdatePhieuMuon");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTraSach.setEnabled(false);
		btnTraSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTraSach.setBounds(975, 174, 147, 46);
		panel_UpdatePhieuMuon.add(btnTraSach);
		
		JRadioButton rdbtnThemPhieuMuon = new JRadioButton("Thêm phiếu mượn");
		rdbtnThemPhieuMuon.setSelected(true);
		rdbtnThemPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnThemPhieuMuon.setBounds(26, 174, 137, 23);
		panel_UpdatePhieuMuon.add(rdbtnThemPhieuMuon);
		
		JRadioButton rdbtnXoaSuaTraPhieuMuon = new JRadioButton("Xóa, Sửa phiếu mượn / Trả sách");
		rdbtnXoaSuaTraPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnXoaSuaTraPhieuMuon.setBounds(26, 215, 224, 23);
		
		ButtonGroup group2 = new ButtonGroup();
        group2.add(rdbtnThemPhieuMuon);
        group2.add(rdbtnXoaSuaTraPhieuMuon);
        
        rdbtnThemPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnThemPhieuMuon.isSelected() == true)
		        {
		        	btnThemPhieuMuon.setEnabled(true);
		        	btnSuaPhieuMuon.setEnabled(false);
		        	btnXoaPhieuMuon.setEnabled(false);
		        	btnTraSach.setEnabled(false);
		        	
		        	textFieldMaPhieuMuon.setText("");
		        	textFieldMaBanDoc.setText("");
		        	textFieldMaSach2.setText("");
		        }
			}
		});
        
        rdbtnXoaSuaTraPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnXoaSuaTraPhieuMuon.isSelected() == true)
		        {
					btnThemPhieuMuon.setEnabled(false);
		        	btnSuaPhieuMuon.setEnabled(true);
		        	btnXoaPhieuMuon.setEnabled(true);
		        	btnTraSach.setEnabled(true);
		        	
		        	//Lấy dữ liệu từ table xuất ra textfield
					tableUpdatePhieuMuon.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked (MouseEvent e) 
						{
							int row = tableUpdatePhieuMuon.getSelectedRow();
							textFieldMaPhieuMuon.setText((String)tableUpdatePhieuMuon.getModel().getValueAt(row, 0));
							textFieldMaBanDoc.setText((String)tableUpdatePhieuMuon.getModel().getValueAt(row, 1));
							textFieldMaSach2.setText((String)tableUpdatePhieuMuon.getModel().getValueAt(row, 2));
						}
					});
		        }
			}
		});
        
		panel_UpdatePhieuMuon.add(rdbtnXoaSuaTraPhieuMuon);

		// Align row center 
		DefaultTableCellRenderer centerRenderer_PhieuMuon = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0; x<7; x++)
		{
			tableUpdatePhieuMuon.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}
		scrollPane.setViewportView(tableUpdateSach);
		
		JPanel panel_BanDoc = new JPanel();
		tabbedPaneUpdateSach.addTab("Bạn Đọc", null, panel_BanDoc, null);
		panel_BanDoc.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 280, 1159, 331);
		panel_BanDoc.add(scrollPane_5);
		
		tableMemberUpdate = new JTable();
		tableMemberUpdate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 B\u1EA1n \u0110\u1ECDc", "Username", "Password", "T\u00EAn B\u1EA1n \u0110\u1ECDc", "Ng\u00E0y Sinh", "\u0110\u1ECBa Ch\u1EC9", "\u0110i\u1EC7n Tho\u1EA1i"
			}
		));
		scrollPane_5.setViewportView(tableMemberUpdate);
		
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_member = (DefaultTableModel) tableMemberUpdate.getModel();		
						
		//Xuất danh sách data member ra table
		dtm_member = member.xuatTable(dtm_member);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(40, 59, 83, 17);
		panel_BanDoc.add(label_1);
		
		textFieldUsernameBanDoc = new JTextField();
		textFieldUsernameBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldUsernameBanDoc.setColumns(10);
		textFieldUsernameBanDoc.setBounds(133, 50, 111, 35);
		panel_BanDoc.add(textFieldUsernameBanDoc);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword_1.setBounds(40, 121, 83, 17);
		panel_BanDoc.add(lblPassword_1);
		
		textFieldPasswordBanDoc = new JTextField();
		textFieldPasswordBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldPasswordBanDoc.setColumns(10);
		textFieldPasswordBanDoc.setBounds(133, 112, 111, 35);
		panel_BanDoc.add(textFieldPasswordBanDoc);
		
		JLabel lblTnBnc = new JLabel("Tên Bạn Đọc");
		lblTnBnc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnBnc.setBounds(313, 59, 100, 17);
		panel_BanDoc.add(lblTnBnc);
		
		textFieldTenBanDoc = new JTextField();
		textFieldTenBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTenBanDoc.setColumns(10);
		textFieldTenBanDoc.setBounds(423, 50, 111, 35);
		panel_BanDoc.add(textFieldTenBanDoc);
		
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgySinh.setBounds(313, 118, 83, 26);
		panel_BanDoc.add(lblNgySinh);
		
		JDateChooser dateChooserNgaySinhBanDoc = new JDateChooser();
		dateChooserNgaySinhBanDoc.setBounds(423, 112, 111, 35);
		panel_BanDoc.add(dateChooserNgaySinhBanDoc);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaCh.setBounds(618, 59, 83, 17);
		panel_BanDoc.add(lblaCh);
		
		textFieldDiaChiMember = new JTextField();
		textFieldDiaChiMember.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDiaChiMember.setColumns(10);
		textFieldDiaChiMember.setBounds(711, 50, 381, 35);
		panel_BanDoc.add(textFieldDiaChiMember);
		
		JLabel lblinThoi = new JLabel("Điện Thoại");
		lblinThoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblinThoi.setBounds(618, 121, 83, 17);
		panel_BanDoc.add(lblinThoi);
		
		textFieldDienThoaiMember = new JTextField();
		textFieldDienThoaiMember.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDienThoaiMember.setColumns(10);
		textFieldDienThoaiMember.setBounds(711, 112, 136, 35);
		panel_BanDoc.add(textFieldDienThoaiMember);
		
		JLabel lblMBnc_1 = new JLabel("Mã Bạn Đọc");
		lblMBnc_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMBnc_1.setBounds(914, 121, 94, 17);
		panel_BanDoc.add(lblMBnc_1);
		
		textFieldMaBanDocUpdateBanDoc = new JTextField();
		textFieldMaBanDocUpdateBanDoc.setEditable(false);
		textFieldMaBanDocUpdateBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaBanDocUpdateBanDoc.setColumns(10);
		textFieldMaBanDocUpdateBanDoc.setBounds(1018, 112, 74, 35);
		panel_BanDoc.add(textFieldMaBanDocUpdateBanDoc);
		
		JRadioButton rdbtnThemBanDoc = new JRadioButton("Thêm bạn đọc");
		rdbtnThemBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnThemBanDoc.setSelected(true);
		rdbtnThemBanDoc.setBounds(40, 201, 146, 23);
		panel_BanDoc.add(rdbtnThemBanDoc);
		
		JRadioButton rdbtnXoaSuaBanDoc = new JRadioButton("Xóa Sửa bạn đọc");
		rdbtnXoaSuaBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnXoaSuaBanDoc.setBounds(40, 237, 157, 23);
		panel_BanDoc.add(rdbtnXoaSuaBanDoc);
		
		ButtonGroup btgBanDoc = new ButtonGroup();
		btgBanDoc.add(rdbtnThemBanDoc);
		btgBanDoc.add(rdbtnXoaSuaBanDoc);

		JButton btnThemBanDoc = new JButton("THÊM");
		btnThemBanDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUsernameBanDoc.getText();
				String password = textFieldPasswordBanDoc.getText();
				String TenBanDoc = textFieldTenBanDoc.getText();
				String DiaChi = textFieldDiaChiMember.getText();
				String SDT = textFieldDienThoaiMember.getText();
				String NgaySinh = outputDate(dateChooserNgaySinhBanDoc);
				
				try {
					member.themBanDoc(username, password, TenBanDoc, DiaChi, SDT, NgaySinh);
					updateTable(tableMemberUpdate, "tableMemberUpdate");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemBanDoc.setBounds(313, 187, 89, 39);
		panel_BanDoc.add(btnThemBanDoc);
		
		JButton btnXoaBanDoc = new JButton("XÓA");
		btnXoaBanDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaBanDoc = Integer.parseInt(textFieldMaBanDocUpdateBanDoc.getText());
				try {
					member.xoaBanDoc(MaBanDoc);
					updateTable(tableMemberUpdate, "tableMemberUpdate");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoaBanDoc.setEnabled(false);
		btnXoaBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaBanDoc.setBounds(445, 187, 89, 39);
		panel_BanDoc.add(btnXoaBanDoc);
		
		JButton btnSuaBanDoc = new JButton("SỬA");
		btnSuaBanDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaBanDoc = Integer.parseInt(textFieldMaBanDocUpdateBanDoc.getText());
				String username = textFieldUsernameBanDoc.getText();
				String password = textFieldPasswordBanDoc.getText();
				String TenBanDoc = textFieldTenBanDoc.getText();
				String DiaChi = textFieldDiaChiMember.getText();
				String SDT = textFieldDienThoaiMember.getText();
				String NgaySinh = outputDate(dateChooserNgaySinhBanDoc);
				
				try {
					member.suaBanDoc(MaBanDoc, username, password, TenBanDoc, DiaChi, SDT, NgaySinh);
					updateTable(tableMemberUpdate, "tableMemberUpdate");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSuaBanDoc.setEnabled(false);
		btnSuaBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSuaBanDoc.setBounds(567, 187, 89, 39);
		panel_BanDoc.add(btnSuaBanDoc);
		
		rdbtnThemBanDoc.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	btnThemBanDoc.setEnabled(true);
	            btnXoaBanDoc.setEnabled(false);
	            btnSuaBanDoc.setEnabled(false);
	            
	            textFieldUsernameBanDoc.setText("");
	            textFieldPasswordBanDoc.setText("");
	            textFieldTenBanDoc.setText("");
	            textFieldDiaChiMember.setText("");
	            textFieldDienThoaiMember.setText("");
	            textFieldMaBanDocUpdateBanDoc.setText("");
	        }
	    });
		
		rdbtnXoaSuaBanDoc.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            btnThemBanDoc.setEnabled(false);
	            btnXoaBanDoc.setEnabled(true);
	            btnSuaBanDoc.setEnabled(true);
	            
	           
	        }
	    });
					
		 tableMemberUpdate.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  if(rdbtnXoaSuaBanDoc.isEnabled())
					{
					int row = tableMemberUpdate.getSelectedRow();
					textFieldMaBanDocUpdateBanDoc.setText((String)tableMemberUpdate.getModel().getValueAt(row, 0));
					textFieldUsernameBanDoc.setText((String)tableMemberUpdate.getModel().getValueAt(row, 1));
					textFieldPasswordBanDoc.setText((String)tableMemberUpdate.getModel().getValueAt(row, 2));
					textFieldTenBanDoc.setText((String)tableMemberUpdate.getModel().getValueAt(row, 3));
					textFieldDiaChiMember.setText((String)tableMemberUpdate.getModel().getValueAt(row, 5));
					textFieldDienThoaiMember.setText((String)tableMemberUpdate.getModel().getValueAt(row, 6)); 
					}	 
			  }
			});
		
		JPanel panel_UpdateAdmin = new JPanel();
		tabbedPaneUpdateSach.addTab("Admin", null, panel_UpdateAdmin, null);
		panel_UpdateAdmin.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(575, 5, 2, 2);
		panel_UpdateAdmin.add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(582, 5, 2, 2);
		panel_UpdateAdmin.add(scrollPane_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 238, 1159, 373);
		panel_UpdateAdmin.add(scrollPane_4);
		
		tableAdminUpdate = new JTable();
		tableAdminUpdate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password", "Ma Admin"
			}
		));
		
		scrollPane_4.setViewportView(tableAdminUpdate);
		// Set row height
		tableAdminUpdate.setRowHeight(30);
		
		//tableAdminUpdate
		
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_admin = (DefaultTableModel) tableAdminUpdate.getModel();
		
		//Xuất danh sách data của admin ra table admin
		dtm_admin = admin.xuatTable(dtm_admin);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(41, 52, 83, 17);
		panel_UpdateAdmin.add(label);
		
		textFieldAdminUsername = new JTextField();
		textFieldAdminUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldAdminUsername.setColumns(10);
		textFieldAdminUsername.setBounds(134, 43, 111, 35);
		panel_UpdateAdmin.add(textFieldAdminUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(41, 107, 83, 17);
		panel_UpdateAdmin.add(lblPassword);
		
		textFieldAdminPassword = new JTextField();
		textFieldAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldAdminPassword.setColumns(10);
		textFieldAdminPassword.setBounds(134, 98, 111, 35);
		panel_UpdateAdmin.add(textFieldAdminPassword);
		
		JLabel lblMAdmin = new JLabel("Mã Admin");
		lblMAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAdmin.setBounds(326, 52, 83, 17);
		panel_UpdateAdmin.add(lblMAdmin);
		
		textFieldMaAdmin = new JTextField();
		textFieldMaAdmin.setEditable(false);
		textFieldMaAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMaAdmin.setColumns(10);
		textFieldMaAdmin.setBounds(419, 43, 111, 35);
		panel_UpdateAdmin.add(textFieldMaAdmin);
		
		JRadioButton rdbtnThemAdmin = new JRadioButton("Thêm admin");
		rdbtnThemAdmin.setSelected(true);
		rdbtnThemAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnThemAdmin.setBounds(41, 161, 161, 23);
		panel_UpdateAdmin.add(rdbtnThemAdmin);
		
		JRadioButton rdbtnXoaSuaAdmin = new JRadioButton("Xóa, Sửa admin");
		rdbtnXoaSuaAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnXoaSuaAdmin.setBounds(41, 193, 161, 23);
		panel_UpdateAdmin.add(rdbtnXoaSuaAdmin);
		
		ButtonGroup btgAdmin = new ButtonGroup();
		btgAdmin.add(rdbtnThemAdmin);
		btgAdmin.add(rdbtnXoaSuaAdmin);
		
		JButton btnThemAdmin = new JButton("THÊM ADMIN");
		btnThemAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textFieldAdminUsername.getText();
				String Password = textFieldAdminPassword.getText();
				if(Username!=null && Password!=null)
				{
					try {
						admin.themAdmin(Username, Password);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						updateTable(tableAdminUpdate, "tableAdminUpdate");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(null,"Chua nhap username hoac password!!!");
			}
		});
		btnThemAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemAdmin.setBounds(312, 154, 145, 36);
		panel_UpdateAdmin.add(btnThemAdmin);
		
		JButton btnXoaAdmin = new JButton("XÓA ADMIN");
		btnXoaAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaAdmin = Integer.parseInt(textFieldMaAdmin.getText());
				try {
					admin.xoaAdmin(MaAdmin);
					updateTable(tableAdminUpdate, "tableAdminUpdate");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXoaAdmin.setEnabled(false);
		btnXoaAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaAdmin.setBounds(728, 154, 131, 36);
		panel_UpdateAdmin.add(btnXoaAdmin);
		
		JButton btnSuaAdmin = new JButton("SỬA ADMIN");
		btnSuaAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Password = textFieldAdminPassword.getText();
				String Username = textFieldAdminUsername.getText();
				int MaAdmin = Integer.parseInt(textFieldMaAdmin.getText());
				
				if(Password != null && Username != null)
				{
					try {
						admin.suaAdmin(MaAdmin, Username, Password);
						updateTable(tableAdminUpdate, "tableAdminUpdate");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSuaAdmin.setEnabled(false);
		btnSuaAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSuaAdmin.setBounds(525, 154, 131, 36);
		panel_UpdateAdmin.add(btnSuaAdmin);
				
		// Xử lí radiobutton Xóa Sửa Admin
		rdbtnXoaSuaAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnXoaSuaAdmin.isSelected() == true)
		        {
		        	btnXoaAdmin.setEnabled(true);
		        	btnSuaAdmin.setEnabled(true);
		        	btnThemAdmin.setEnabled(false);
		        	
					//Lấy dữ liệu từ table xuất ra textfield
					tableAdminUpdate.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked (MouseEvent e) 
						{
							int row = tableAdminUpdate.getSelectedRow();
							textFieldAdminUsername.setText((String)tableAdminUpdate.getModel().getValueAt(row, 0));
							textFieldAdminPassword.setText((String)tableAdminUpdate.getModel().getValueAt(row, 1));
							textFieldMaAdmin.setText((String)tableAdminUpdate.getModel().getValueAt(row, 2));
						}
					});
		        }
			}
		});
		
		// Xử lý Radiobutton Thêm Admin
		rdbtnThemAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnThemAdmin.isSelected() == true)
		        {
		        	btnXoaAdmin.setEnabled(false);
		        	btnSuaAdmin.setEnabled(false);
		        	btnThemAdmin.setEnabled(true);
		        	
		        	textFieldAdminUsername.setText("");
					textFieldAdminPassword.setText("");
					textFieldMaAdmin.setText("");
		        }
			}
		});
		
	}
}
