package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JLabel labelSoBanDoc = new JLabel("0");
		labelSoBanDoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSoBanDoc.setBounds(218, 23, 93, 32);
		contentPane.add(labelSoBanDoc);
	}
}
