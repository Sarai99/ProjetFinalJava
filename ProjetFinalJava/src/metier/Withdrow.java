package metier;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;

public class Withdrow extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	Connection con;
	Statement st;
	ResultSet rs;
	JComboBox comboBox = new JComboBox();
	JLabel label = new JLabel("0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdrow frame = new Withdrow();
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
	public Withdrow() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 554, 387);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 11, 486, 55);
		panel.setBackground(new Color(21, 27, 84));
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 27, 84));
		panel_1.setBounds(25, 291, 486, 55);
		getContentPane().add(panel_1);
		
		JLabel lblWithdrow = new JLabel("Withdrow");
		lblWithdrow.setFont(new Font("Calibri", Font.BOLD, 18));
		lblWithdrow.setBounds(25, 91, 111, 14);
		getContentPane().add(lblWithdrow);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSolde.setBounds(165, 92, 39, 14);
		getContentPane().add(lblSolde);
		
		JLabel lblGourdes = new JLabel("Gourdes");
		lblGourdes.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblGourdes.setBounds(331, 92, 60, 14);
		getContentPane().add(lblGourdes);
		
		
		label.setFont(new Font("Calibri", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(214, 87, 111, 23);
		getContentPane().add(label);
		
		JLabel Compte = new JLabel("Compte");
		Compte.setFont(new Font("Calibri", Font.PLAIN, 15));
		Compte.setBounds(25, 152, 60, 14);
		getContentPane().add(Compte);
		
		JLabel Date = new JLabel("Date");
		Date.setFont(new Font("Calibri", Font.PLAIN, 15));
		Date.setBounds(25, 232, 46, 14);
		getContentPane().add(Date);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblMontant.setBounds(331, 232, 60, 14);
		getContentPane().add(lblMontant);
		
		remplirCombo();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String ref =(String)comboBox.getSelectedItem();
				
				String requete = "Select * from Compte where idCompte='" + ref + "'";
				con= Connexion.getConnection();
				st=con.createStatement();
				rs = st.executeQuery(requete);
				if(ref.equals(""))
				{
					   label.setText("0");
				}
				else{
					while (rs.next())
					{
					   label.setText(rs.getString("montantCompte"));
			
					 }
				}
				
				  st.close();
				} catch (SQLException ee) {
				    ee.printStackTrace();				
				}
			}
		});
		comboBox.setBounds(108, 149, 82, 20);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(108, 229, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(425, 229, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRetrait = new JButton("Retrait");
		btnRetrait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Double Solde = 0.0;
				
				String TypeTrans="Retrait";
				String opt =(String)comboBox.getSelectedItem();
				
				Date date= new Date();
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
			
				JOptionPane jp1 =new JOptionPane();
				JOptionPane jp2 =new JOptionPane();
				if(textField_1.getText().equals("") || opt.equals(null)){
				jp2.showMessageDialog(null,"Veuillez Choisir un compte et mettre une somme!!");
				}
				else{
					double montant;
					montant= Double.parseDouble(textField_1.getText());
					if(montant<0)
					{
						jp2.showMessageDialog(null,"Veuillez mettre une somme positve!!");
						clear();
					}
					else
					{
						int ref =Integer.parseInt(opt);
						  String requete="Insert into Transaction (idTransaction, typeTransaction, idCompte1, idCompte2, dateTransaction, Montant)  values (null,'";
						  requete = requete + TypeTrans+ "'," +ref+ ",null,'" +sqldate+ "',"+textField_1.getText()+")";
						  
										  System.out.println(requete);
										  				  
						 try {
						     con= Connexion.getConnection();
						     st = con.createStatement();
						     st.executeUpdate(requete);
						     
						    
						     //showMessageDialog(null,"Ajoute","Succes",JOptionPane.INPUT_VALUE_PROPERTY);
						        String requete1 = "Select * from Compte where idCompte=" + ref;
								rs = st.executeQuery(requete1);
								while (rs.next())
								{
							    System.out.println(rs.getString("montantCompte")+textField_1.getText());
								Solde=Double.parseDouble(rs.getString("montantCompte")) -  Double.parseDouble(textField_1.getText()) ;
						
								 }
								if(Solde<0)
								{
									jp1.showMessageDialog(null, "Balance insuffisante!!");
									clear();
								}
								else 
								{
									String requete2 = "Update Compte set montantCompte ="+ Solde+" where idCompte=" + ref;
									 st.executeUpdate(requete2);
							
									 
									 jp1.showMessageDialog(null, "Retrait Reussi!!");
									 clear();
				
									 ResultSet rs1;
									 rs1=st.executeQuery(requete1);
				
									 while (rs1.next())
										{ 
										 label.setText(rs1.getString("montantCompte"));
								
										}	
								}
						        st.close();
								 
						 } catch (SQLException ee) {
						     ee.printStackTrace();
						 }			
					}
				  	
				}	
			}
		});
		btnRetrait.setBounds(422, 88, 89, 23);
		getContentPane().add(btnRetrait);

	}
	public void clear()
	{
		  textField.setText("");
		  textField_1.setText("");

		  textField.requestFocus();
	}
	public void remplirCombo()
	{
	  try {
	      String requete="select * from Compte";
	      con= Connexion.getConnection();
	      st = con.createStatement();
	      rs = st.executeQuery(requete);

	      comboBox.removeAllItems();
	      comboBox.addItem("");
	     
	 while(rs.next())
	      {
	        
	         comboBox.addItem(rs.getString("idCompte"));
	      }
	      st.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
}
