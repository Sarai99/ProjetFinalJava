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
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class TransfertMoney extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	Connection con;
	Statement st;
	ResultSet rs;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JLabel label = new JLabel("0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransfertMoney frame = new TransfertMoney();
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
	public TransfertMoney() {
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
		
		JLabel lblTransfertmoney = new JLabel("TransfertMoney");
		lblTransfertmoney.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTransfertmoney.setBounds(25, 91, 145, 14);
		getContentPane().add(lblTransfertmoney);
		
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
		
		JLabel lblCompte = new JLabel("Compte1");
		lblCompte.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblCompte.setBounds(25, 152, 60, 14);
		getContentPane().add(lblCompte);
		
		JLabel lblTransfererA = new JLabel("Transferer  a");
		lblTransfererA.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblTransfererA.setBounds(223, 152, 82, 14);
		getContentPane().add(lblTransfererA);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblDate.setBounds(25, 232, 46, 14);
		getContentPane().add(lblDate);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblMontant.setBounds(331, 232, 60, 14);
		getContentPane().add(lblMontant);
		
		JLabel lblCompte_1 = new JLabel("Compte2");
		lblCompte_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblCompte_1.setBounds(331, 152, 60, 14);
		getContentPane().add(lblCompte_1);
		
		remplirCombo();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					String ref =(String)comboBox.getSelectedItem();
					String ref1 =(String)comboBox_1.getSelectedItem();
					
					JOptionPane jp2 =new JOptionPane();
				    if(ref.equals(ref1) && ref!="")
					{
					jp2.showMessageDialog(null,"Veuillez Choisir un autre compte");
					comboBox.removeAllItems();
					remplirCombo();
			        }
				    else
					  {
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
							
					}
					  st.close();
					} catch (SQLException ee) {
					    ee.printStackTrace();				
					}
			}
		});
		comboBox.setBounds(108, 149, 82, 20);
		getContentPane().add(comboBox);
		
		remplirCombo_1();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					String ref =(String)comboBox_1.getSelectedItem();
					String ref1 =(String)comboBox.getSelectedItem();
					
					JOptionPane jp2 =new JOptionPane();
					if(ref.equals(ref1) && ref!="")
					{
						jp2.showMessageDialog(null,"Veuillez Choisir un autre compte");
						comboBox_1.removeAllItems();
						remplirCombo_1();
					}
					
					else{
						String requete = "Select * from Compte where idCompte='" + ref + "'";
						con= Connexion.getConnection();
						st=con.createStatement();
						rs = st.executeQuery(requete);
						if(ref.equals(""))
						{
							   //label.setText("0");
						}
						else{
							while (rs.next())
							{
							   //label.setText(rs.getString("montantCompte"));
					
							 }
						} 
					}
						
					  st.close();
					} catch (SQLException ee) {
					    ee.printStackTrace();				
					}

			}
		});
		
		
		comboBox_1.setBounds(429, 149, 82, 20);
		getContentPane().add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(108, 229, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(425, 229, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnTransferer = new JButton("Transferer");
		btnTransferer.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 Double Solde = 0.00;
				 Double Solde1 = 0.00;
					String TypeTrans="Transfert";
					String opt =(String)comboBox.getSelectedItem();
					String opt1 =(String)comboBox_1.getSelectedItem();
					Date date= new Date();
					java.sql.Date sqldate = new java.sql.Date(date.getTime());
				
					JOptionPane jp1 =new JOptionPane();
					JOptionPane jp2 =new JOptionPane();
					if(textField_1.getText().equals("") || opt.equals(null) || opt.equals(null)){
					jp2.showMessageDialog(null,"Veuillez Choisir un compte et mettre une somme!!");
					}
					else{
						double montant;
						montant = Double.parseDouble(textField_1.getText());
						if(montant<0)
						{
							jp2.showMessageDialog(null,"Veuillez mettre une somme positive!!");
							clear();
						}
						else
						{
							int ref =Integer.parseInt(opt);
							int ref1 =Integer.parseInt(opt1);
						  String requete="Insert into Transaction (idTransaction, typeTransaction, idCompte1, idCompte2, dateTransaction, Montant)  values (null,'";
						  requete = requete + TypeTrans+ "'," +ref+ ","+ ref1+",'" +sqldate+ "',"+textField_1.getText()+")";
						  
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
								Solde=Double.parseDouble(rs.getString("montantCompte")) - Double.parseDouble(textField_1.getText()) ;
								 }
								if(Solde<0)
								{
									jp1.showMessageDialog(null, "Balance insuffisante!!");
									clear();
								}
								else{
									String requete2 = "Update Compte set montantCompte ="+ Solde+" where idCompte=" + ref;
									 st.executeUpdate(requete2);
				
									 ResultSet rs1;
									 rs1=st.executeQuery(requete1);
				
									 while (rs1.next())
										{ 
										 label.setText(rs1.getString("montantCompte"));
								
										}
									 
									 String requete3 = "Select * from Compte where idCompte=" + ref1;
									 
									 rs = st.executeQuery(requete3);
										while (rs.next())
										{
									    System.out.println(rs.getString("montantCompte")+textField_1.getText());
										Solde1=Double.parseDouble(rs.getString("montantCompte")) + Double.parseDouble(textField_1.getText()) ;
										 }
										String requete4 = "Update Compte set montantCompte ="+ Solde1+" where idCompte=" + ref1;
										st.executeUpdate(requete4);
										
									 jp1.showMessageDialog(null, "Transfert Reussi!!");
									 clear();
								}
						        st.close();
								 
						 } catch (SQLException ee) {
						     ee.printStackTrace();
						 }	 
							
						}
					}
			}
		});
		btnTransferer.setBounds(425, 88, 86, 23);
		getContentPane().add(btnTransferer);

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
	public void remplirCombo_1()
	{
	  try {
	      String requete="select * from Compte";
	      con= Connexion.getConnection();
	      st = con.createStatement();
	      rs = st.executeQuery(requete);

	      comboBox_1.removeAllItems();
	      comboBox_1.addItem("");
	     
	 while(rs.next())
	      {
	        
	         comboBox_1.addItem(rs.getString("idCompte"));
	      }
	      st.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

}
