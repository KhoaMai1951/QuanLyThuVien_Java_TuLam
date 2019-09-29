package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.member;
import object.sach;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

public class admin_report_form extends JFrame {

	private JPanel contentPane;

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
			labelSoSach.setBounds(195, 66, 71, 32);
			contentPane.add(labelSoSach);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
