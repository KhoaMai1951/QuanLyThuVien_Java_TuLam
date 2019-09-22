package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class admin_function extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_function frame = new admin_function();
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
	public admin_function() {
		setTitle("ADMIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpdate = new JButton("C\u1EADp Nh\u1EADt");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_update_form form = null;
				try {
					form = new admin_update_form();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				form.setVisible(true);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(86, 45, 254, 63);
		contentPane.add(btnUpdate);
		
		JButton btnReport = new JButton("B\u00E1o C\u00E1o");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_report_form form = new admin_report_form();
				form.setVisible(true);
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReport.setBounds(86, 146, 254, 63);
		contentPane.add(btnReport);
	}
}
