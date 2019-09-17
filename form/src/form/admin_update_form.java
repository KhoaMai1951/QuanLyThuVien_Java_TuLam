package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import object.sach;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class admin_update_form extends JFrame {

	private JPanel contentPane;
	private JTable tableUpdateSach;
	private JTextField textFieldMaSach;
	private JTextField textFieldTenSach;
	private JTextField textFieldTenTacGia;
	private JTextField textFieldNXB;
	private JTextField textFieldGiaTien;
	private JTextField textFieldSoLuong;

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

	// Output table Sach
	public void outputTable ()
	{
		//----------set variable table as DefaultTableModel and add row--------------------
		DefaultTableModel dtm_sach = (DefaultTableModel) tableUpdateSach.getModel();		
						
		//Xuất danh sách data phiếu mượn ra table
		dtm_sach = sach.xuatTable(dtm_sach);
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
	public void updateTable(JTable table)
	{
		clearTable(table);
		outputTable();
	}
		
	/**
	 * Create the frame.
	 */
	public admin_update_form() {
		setTitle("C\u1EADp Nh\u1EADt ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1164, 639);
		contentPane.add(tabbedPane);
		
		JPanel panel_UpdateSach = new JPanel();
		tabbedPane.addTab("Cập Nhật Sách", null, panel_UpdateSach, null);
		
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
					updateTable(tableUpdateSach);
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
					updateTable(tableUpdateSach);
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
									.addComponent(lblTnTcGa, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldTenTacGia, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(42)
									.addComponent(lblNxb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldNXB)))
							.addGap(106)
							.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addComponent(lblGaTin, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
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
										.addGroup(gl_panel_UpdateSach.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblMSch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTnTcGa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
									.addComponent(textFieldTenTacGia, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldMaSach, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
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
								.addGroup(gl_panel_UpdateSach.createSequentialGroup()
									.addGap(2)
									.addComponent(lblGaTin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldGiaTien, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
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

	}
}
