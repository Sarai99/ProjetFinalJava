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
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

public class TransactionList extends JInternalFrame {
	private JTable table;
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
					TransactionList frame = new TransactionList();
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
	public TransactionList() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 11, 486, 55);
		panel.setBackground(new Color(21, 27, 84));
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 27, 84));
		panel_1.setBounds(25, 291, 486, 55);
		getContentPane().add(panel_1);
		
		JLabel lblTransactionList = new JLabel("TransactionList");
		lblTransactionList.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTransactionList.setBounds(25, 91, 128, 14);
		getContentPane().add(lblTransactionList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 117, 486, 163);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		list();
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 554, 387);

	}
	public void list()
	{
		String requete="select * from Transaction";
		try {
		   con= Connexion.getConnection();
		   st = con.createStatement();
		   rs = st.executeQuery(requete);
	 	   table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
	  	  e1.printStackTrace();
		}
		
	}
}
