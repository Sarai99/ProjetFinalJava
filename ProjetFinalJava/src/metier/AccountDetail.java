package metier;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

public class AccountDetail extends JInternalFrame {
	JLabel label = new JLabel("0");
	JLabel idCompte = new JLabel("idCompte");
	JTable table;
	Connection con;
	Statement st;
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountDetail frame = new AccountDetail();
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
	public AccountDetail() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 11, 486, 55);
		panel.setBackground(new Color(21, 27, 84));
		getContentPane().add(panel);
		
		JLabel lblDetailAccount = new JLabel("DetailAccount");
		lblDetailAccount.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDetailAccount.setBounds(25, 91, 111, 14);
		getContentPane().add(lblDetailAccount);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSolde.setBounds(165, 92, 39, 14);
		getContentPane().add(lblSolde);
		
		label.setFont(new Font("Calibri", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(214, 87, 111, 23);
		getContentPane().add(label);
		
		JLabel lblGourdes = new JLabel("Gourdes");
		lblGourdes.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblGourdes.setBounds(331, 92, 60, 14);
		getContentPane().add(lblGourdes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 27, 84));
		panel_1.setBounds(25, 291, 486, 55);
		getContentPane().add(panel_1);
		
		JLabel lblCompte = new JLabel("Compte");
		lblCompte.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblCompte.setBounds(25, 128, 60, 14);
		getContentPane().add(lblCompte);
		
		
		idCompte.setFont(new Font("Calibri", Font.BOLD, 18));
		idCompte.setBounds(90, 128, 83, 14);
		getContentPane().add(idCompte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 164, 486, 119);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 554, 387);

	
	}
}
