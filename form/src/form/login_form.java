package form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.jdi.connect.spi.Connection;

import bussiness.QLCSDL;
import object.admin;
import object.member;
import object.phieu_muon;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class login_form {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JTextField txtPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_form window = new login_form();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public login_form() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100, 100, 272, 426);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblUsername.setBounds(44, 38, 103, 28);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("M\u1EADt kh\u1EA9u");
		lblPassword.setBounds(44, 105, 103, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(44, 74, 167, 20);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(44, 134, 167, 20);
		frmLogin.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		
		
		JButton btnLogin = new JButton("\u0110\u0103ng nh\u1EADp");
		
		btnLogin.setBounds(67, 314, 122, 47);
		frmLogin.getContentPane().add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 184, 167, 90);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(30, 7, 109, 23);
		panel.add(rdbtnAdmin);
		
		JRadioButton rdbtnMember = new JRadioButton("Th\u00E0nh vi\u00EAn");
		rdbtnMember.setBounds(30, 55, 109, 23);
		panel.add(rdbtnMember);
		
		//Create ButtonGroup
		ButtonGroup bg1 = new ButtonGroup( );
		bg1.add(rdbtnAdmin);
		bg1.add(rdbtnMember);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLCSDL qlcsdl = new QLCSDL();
				String username = txtUsername.getText();
				String password = txtPassword.getText();

				try {
					if(rdbtnAdmin.isSelected())
					{
						admin h = admin.timUser(username, password);
						if(h==null)
						{
							JOptionPane.showMessageDialog(null, "Invalid UserName or Password");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Login is successfully!");
							admin_function form = new admin_function();
							form.setVisible(true);
						}
					}
					if(rdbtnMember.isSelected())
					{
						member k = member.timUser(username, password);
						if(k==null)
						{
							JOptionPane.showMessageDialog(null, "Invalid UserName or Password");	 
						}
						else
						{
							
							int ma_ban_doc_hien_hanh = k.getMa_Ban_Doc();
							phieu_muon.luuMaBanDocHienHanh(ma_ban_doc_hien_hanh);
							member.luuMaBanDocHienHanh(ma_ban_doc_hien_hanh);
							JOptionPane.showMessageDialog(null, "Login Successful");
							member_form form = new member_form();
							form.setVisible(true);
						}
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
					
				
			}
		});
		
		
		
	}

}
