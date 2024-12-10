package metier;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

public class Compte extends JFrame {
	static Compte  frame = new Compte();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	Connection con;
	Statement st;
	ResultSet rs;
	JComboBox comboBox = new JComboBox();
	private JTable table;
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				   
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
	
	public Compte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1360, 800);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(0, 0, 1344, 749);
		getContentPane().add(desktopPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(208, 11, 912, 71);
		panel.setBackground(Color.WHITE);
		Color col=new Color(21, 27, 84);
		desktopPane.setLayout(null);
		panel.setBackground(col);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUniqBanque = new JLabel("Uniq-BANQUE");
		lblUniqBanque.setBounds(10, 24, 117, 23);
		panel.add(lblUniqBanque);
		lblUniqBanque.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniqBanque.setFont(new Font("MingLiU-ExtB", Font.BOLD, 18));
		lblUniqBanque.setForeground(Color.WHITE);
		
		JMenuBar Menu = new JMenuBar();
		Menu.setBounds(1215, 24, 97, 21);
		Menu.setToolTipText("Menu");
		panel.add(Menu);
		
		JMenu mnMenu = new JMenu("New menu");
		Menu.add(mnMenu);
		
		JMenuItem mntmAproposdenous = new JMenuItem("A propos de nous");
		mnMenu.add(mntmAproposdenous);
		
		JMenuItem mntmProduitEtService = new JMenuItem("Produits et Services");
		mnMenu.add(mntmProduitEtService);
		
		JMenuItem mntmFormulaire = new JMenuItem("Formulaire");
		mnMenu.add(mntmFormulaire);
		
		JMenuItem mntmContactezNous = new JMenuItem("Contactezo-nous");
		mnMenu.add(mntmContactezNous);
		
		JButton btnEmploye = new JButton("Employe");
		btnEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employe f1= new Employe();                               
				f1.setVisible(true);
				
				frame.setVisible(false);
			}
		});
		btnEmploye.setBounds(616, 26, 89, 23);
		panel.add(btnEmploye);
		//btnEmploye.setBackground(new Color(21, 27, 84));
		
		JButton btnClient =  new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client f1= new Client();                               
				f1.setVisible(true);
				
				frame.setVisible(false);
			}
		});
		btnClient.setBounds(740, 26, 89, 23);
		panel.add(btnClient);
		
		JButton button = new JButton("Account Detail");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String vId;
				  String opt=null;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				  opt = jp1.showInputDialog(null, "Veuillez indiquer l'ID !", "CompteClient Banque-UNIQ !",JOptionPane.QUESTION_MESSAGE);
					 if(opt!=null)
					 {
						vId= opt;
						con = Connexion.getConnection();
						String requete ="select * from Compte where idCompte ='" +vId+"'";
						try {
							 Statement st = con.createStatement();
							 ResultSet  rrs = st.executeQuery(requete);
							 if (rrs.next()){	
								//*****
									AccountDetail f1= new AccountDetail();      
									desktopPane.add(f1);                        
									f1.toFront(); 
									f1.idCompte.setText(vId);
									f1.label.setText(rrs.getString("montantCompte"));
									String requete1="select * from Transaction where idCompte1 ='"+vId+"' or idCompte2 ='"+vId +"'";
									try {
									   con= Connexion.getConnection();
									   st = con.createStatement();
									   rs = st.executeQuery(requete);
									   
									   if (rs.next()){
										   con= Connexion.getConnection();
										   st = con.createStatement();
										   rs = st.executeQuery(requete1);
										   f1.table.setModel(DbUtils.resultSetToTableModel(rs));	
									 }
									} catch (SQLException e1) {
								  	  e1.printStackTrace();
									}
									
									f1.setVisible(true);
									
							 }
							 else
							 {
								 jp2.showMessageDialog(null, "Introuvable", "Attention", JOptionPane.WARNING_MESSAGE);      
							 }
							 st.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					 }
					 else{
						 jp2.showMessageDialog(null, "Recherche annulee", "Attention", JOptionPane.WARNING_MESSAGE);
					 }
			}
		});
		button.setForeground(Color.WHITE);
		button.setBounds(464, 311, 116, 31);
		button.setBackground(new Color(21, 27, 84));
		desktopPane.add(button);
		
		JButton button_1 = new JButton("Deposit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deposit f1= new Deposit();      //Création de l’objet
				desktopPane.add(f1);                        // ajouter l’objet   au JDesktopPane
				f1.toFront();                                // place  l’objet en premier  plan
				f1.setVisible(true);                    // rendre l’objet visible  sur la fenêtre .
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBounds(464, 259, 116, 31);
		button_1.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_1);
		
		JButton button_2 = new JButton("Withdrow");
		button_2.setForeground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Withdrow f1= new Withdrow();      //Création de l’objet
				desktopPane.add(f1);                        // ajouter l’objet   au JDesktopPane
				f1.toFront();                                // place  l’objet en premier  plan
				f1.setVisible(true);
			}
		});
		button_2.setBounds(464, 361, 116, 31);
		button_2.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_2);
		
		JButton btnTransfert = new JButton("Transfert");
		btnTransfert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransfertMoney f1= new TransfertMoney();      //Création de l’objet
				desktopPane.add(f1);                        // ajouter l’objet   au JDesktopPane
				f1.toFront();                                // place  l’objet en premier  plan
				f1.setVisible(true);

			}
		});
		btnTransfert.setForeground(Color.WHITE);
		btnTransfert.setBounds(464, 413, 116, 31);
		btnTransfert.setBackground(new Color(21, 27, 84));
		desktopPane.add(btnTransfert);
		
		JButton button_4 = new JButton("Transaction List");
		button_4.setForeground(Color.WHITE);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransactionList f1= new TransactionList();      //Création de l’objet
				desktopPane.add(f1);                        // ajouter l’objet   au JDesktopPane
				f1.toFront();                                // place  l’objet en premier  plan
				f1.setVisible(true);
			}
		});
		button_4.setBounds(464, 465, 116, 31);
		button_4.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_4);
		
		JLabel label = new JLabel("CompteClient");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setBounds(208,142, 126, 25);
		desktopPane.add(label);
		
		JButton button_5 = new JButton("ListeCompte");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vId;
				  String opt=null;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				  opt = jp1.showInputDialog(null, "Veuillez indiquer l'ID du Client !", "CompteClient Banque-UNIQ !",JOptionPane.QUESTION_MESSAGE);
					 if(opt!=null)
					 {
						vId= Integer.parseInt(opt);
						String requete ="select * from Compte where idClient =" +vId;
						try {
							   con= Connexion.getConnection();
							   st = con.createStatement();
							   rs = st.executeQuery(requete);
							      
							 if (rs.next()){
								   con= Connexion.getConnection();
								   st = con.createStatement();
								   rs = st.executeQuery(requete);
								   table.setModel(DbUtils.resultSetToTableModel(rs));	
							 }
							 else
							 {
								 jp2.showMessageDialog(null, "Introuvable", "Attention", JOptionPane.WARNING_MESSAGE);      
							 }
							 st.close();
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
					
					 }
					 else{
						 jp2.showMessageDialog(null, "Recherche annulee", "Attention", JOptionPane.WARNING_MESSAGE);
					 }

			}
		});
		button_5.setForeground(Color.WHITE);
		button_5.setBounds(590, 210, 92, 31);
		button_5.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_5);
		
		JButton button_6 = new JButton("Nouveau");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_6.setForeground(Color.WHITE);
		button_6.setBounds(307, 465, 85, 31);
		button_6.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_6);
		
		JButton button_7 = new JButton("Modifier");
		button_7.setForeground(Color.WHITE);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				  int vId;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  Date date= new Date();
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
				  
				 int opt = jp1.showConfirmDialog(null, "Voulez vous vraiment Modifier??","Attention", JOptionPane.OK_CANCEL_OPTION);
				 if(textField.getText().equals(""))
					{
						jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
					}
				 else{
				 if(opt==0){
				  vId= Integer.parseInt(textField.getText());				
				  String requete="Update Compte set idClient ="+ textField_1.getText()+ ",montantCompte =" + textField_2.getText() +",dateCreation ='" +sqldate+"' where idCompte = '" + vId+ "'";
								  				  				  
				  try {
				     con= Connexion.getConnection();
				     st = con.createStatement();
				     st.executeUpdate(requete);
				     st.close();
				     list();
				     jp2.showMessageDialog(null, "Modification reussie", "Attention", JOptionPane.WARNING_MESSAGE);
				     clear();

				 } catch (SQLException ee) {
				     ee.printStackTrace();
				 }
				  
				 }
				 else{
					 jp2.showMessageDialog(null, "Modification annulee", "Attention", JOptionPane.WARNING_MESSAGE);
					 clear();
				 }
				}
			}
		});
		button_7.setBounds(860, 210, 102, 31);
		button_7.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_7);
		
		JButton button_8 = new JButton("Suprimer");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int vId,opt;
				JOptionPane jp1 = new JOptionPane();
				JOptionPane jp2 = new JOptionPane();
							  
				opt= jp1.showConfirmDialog(null,"Voulez vous vraiment supprimer??", "CompteClient Banque-Uniq IUO !",JOptionPane.OK_CANCEL_OPTION);
		
				Connection cn = Connexion.getConnection();
				if(textField.getText().equals(""))
				{
					jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
				}
				else{
				if(opt==0){
					vId=Integer.parseInt(textField.getText());
					String requete ="delete from Compte where idCompte =" +vId ;
					try {
					   Statement st = cn.createStatement();
					 int xx=(int)st.executeUpdate(requete);
					 if (xx > 0){
						 jp2.showMessageDialog(null, "Suppression reussie", "Attention", JOptionPane.WARNING_MESSAGE);
						 clear();
					 }
					 else
					 {
						 jp2.showMessageDialog(null, "Introuvable", "Attention", JOptionPane.WARNING_MESSAGE);
						 clear();
					 }
					 	list();
					 st.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					 jp2.showMessageDialog(null, "Suppression annulee", "Attention", JOptionPane.WARNING_MESSAGE);
					 clear();
				}
				}

			}
		});
		button_8.setForeground(Color.WHITE);
		button_8.setBounds(1008, 210, 97, 31);
		button_8.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_8);
		
		JLabel PrenomClient = new JLabel("Prenom");
		PrenomClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		PrenomClient.setBounds(713, 145, 73, 25);
		desktopPane.add(PrenomClient);
		
		JLabel NomClient = new JLabel("Nom");
		NomClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		NomClient.setBounds(797, 142, 73, 25);
		desktopPane.add(NomClient);
		
		JLabel AdresseClient = new JLabel("Adresse");
		AdresseClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		AdresseClient.setBounds(890, 142, 73, 25);
		desktopPane.add(AdresseClient);
		
		JLabel PhoneClient = new JLabel("Phone");
		PhoneClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		PhoneClient.setBounds(1008, 142, 73, 25);
		desktopPane.add(PhoneClient);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(295, 260, 97, 20);
		desktopPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(295, 312, 97, 20);
		desktopPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(295, 366, 97, 20);
		desktopPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(295, 418, 97, 20);
		desktopPane.add(textField_3);
		
		JLabel label_6 = new JLabel("IdCompte");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_6.setBounds(208, 263, 65, 14);
		desktopPane.add(label_6);
		
		JLabel label_7 = new JLabel("IdClient");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_7.setBounds(208, 315, 65, 14);
		desktopPane.add(label_7);
		
		JLabel label_8 = new JLabel("Montant");
		label_8.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_8.setBounds(208, 369, 73, 14);
		desktopPane.add(label_8);
		
		JLabel label_9 = new JLabel("Date");
		label_9.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_9.setBounds(208, 421, 92, 14);
		desktopPane.add(label_9);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date date= new Date();
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
			
				JOptionPane jp1 =new JOptionPane();
				JOptionPane jp2 =new JOptionPane();
				
				if(textField_1.getText().equals("") || textField_2.getText().equals("")){
					jp2.showMessageDialog(null,"Veuillez ajouter les informations!!");	
					
					
				}
				else if(recherche(textField_1.getText())==1)
				{
					jp2.showMessageDialog(null,"Ce client n'existe pas!!");
					clear();
				}
				else{
				
				  String requete="Insert into Compte (idCompte, idClient, montantCompte,dateCreation)  values (null,";
				  requete = requete + textField_1.getText()+ ","+ textField_2.getText()+",'"+sqldate+ "')";
				  
								  System.out.println(requete);
								  System.out.println(sqldate);
								  				  
				 try {
				     con= Connexion.getConnection();
				     st = con.createStatement();
				     st.executeUpdate(requete);
				     clear();
				     list();
				     jp1.showMessageDialog(null, "Ajouter Avec Succes");
				     //showMessageDialog(null,"Ajoute","Succes",JOptionPane.INPUT_VALUE_PROPERTY);
				     st.close();
				 } catch (SQLException ee) {
				     ee.printStackTrace();
				 }				
				}
				
			
			}
		});
		btnAjouter.setBounds(208, 465, 77, 31);
		btnAjouter.setBackground(new Color(21, 27, 84));
		desktopPane.add(btnAjouter);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		Color col2=new Color(21, 27, 84);
		panel_1.setBackground(col2);
		panel_1.setBounds(208, 572, 912, 71);
		desktopPane.add(panel_1);
		
		JLabel lblCreerNouveauCompte = new JLabel("Creer Nouveau Compte");
		lblCreerNouveauCompte.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCreerNouveauCompte.setBounds(208, 213, 194, 14);
		desktopPane.add(lblCreerNouveauCompte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(622, 263, 483, 225);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ligne=table.getSelectedRow();
				  int id = Integer.parseInt((table.getModel().getValueAt(ligne,0)).toString());
				  String requete="select * from Compte where idCompte="+id;
				  try {
				     con= Connexion.getConnection();
				     st = con.createStatement();
				     rs = st.executeQuery(requete);			
				     while(rs.next())
				     {
					   textField.setText(rs.getString("idCompte"));
					   textField_1.setText(rs.getString("idClient"));
					   textField_2.setText(rs.getString("montantCompte"));
					   textField_3.setText(rs.getString("dateCreation"));			   
				       }
				        st.close();
					} catch (SQLException ee) {
					     ee.printStackTrace();
				    }

			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Recherche");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  int vId;
				  String opt=null;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				  opt = jp1.showInputDialog(null, "Veuillez indiquer l'ID !", "CompteClient Banque-UNIQ !",JOptionPane.QUESTION_MESSAGE);
					 if(opt!=null)
					 {
						vId= Integer.parseInt(opt);
						con = Connexion.getConnection();
						String requete ="select * from Compte where idCompte =" +vId;
						try {
							 Statement st = con.createStatement();
							 ResultSet  rrs = st.executeQuery(requete);
							 if (rrs.next()){
								textField.setText(rrs.getInt(1)+"");
								textField_1.setText(rrs.getInt(2)+""); 
								textField_2.setText(rrs.getString(3));
								textField_3.setText(rrs.getString(4));		
								
										
							 }
							 else
							 {
								 jp2.showMessageDialog(null, "Introuvable", "Attention", JOptionPane.WARNING_MESSAGE);      
							 }
							 st.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					 }
					 else{
						 jp2.showMessageDialog(null, "Recherche annulee", "Attention", JOptionPane.WARNING_MESSAGE);
					 }
				  	
			}
		});
		btnNewButton.setBounds(729, 210, 89, 31);
		btnNewButton.setBackground(new Color(21, 27, 84));
		desktopPane.add(btnNewButton);
		
		JButton btnListegenerale = new JButton("ListeGenerale");
		btnListegenerale.setForeground(Color.WHITE);
		btnListegenerale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list();
			}
		});
		btnListegenerale.setBounds(464, 210, 116, 31);
		btnListegenerale.setBackground(new Color(21, 27, 84));
		desktopPane.add(btnListegenerale);
		
		
		    remplirCombo();
			comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					String ref =(String)comboBox.getSelectedItem();
					String requete = "Select * from Client where idClient='" + ref + "'";
					con= Connexion.getConnection();
					st=con.createStatement();
					rs = st.executeQuery(requete);
					if(ref.equals(""))
					{
						   PrenomClient.setText("Prenom");
						   NomClient.setText("Nom");
						   AdresseClient.setText("Adresse");
						   PhoneClient.setText("Phone");
					}
					else{
						while (rs.next())
						{	
						 						            
						   //t1.setText(rs.getString("refEtudiant"));
						   PrenomClient.setText(rs.getString("prenomClient"));
						   NomClient.setText(rs.getString("nomClient"));
						   AdresseClient.setText(rs.getString("adresseClient"));
						   PhoneClient.setText(rs.getString("phoneClient"));
				
						 }
					}
					
					  st.close();
					} catch (SQLException ee) {
					    ee.printStackTrace();				
					}

			}
		});
		comboBox.setBounds(593, 147, 92, 20);
		desktopPane.add(comboBox);
		
		JLabel lblRechercheclient = new JLabel("RechercheClient");
		lblRechercheclient.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblRechercheclient.setBounds(464, 144, 116, 20);
		desktopPane.add(lblRechercheclient);
		
	}
	public void clear()
	{
		  textField.setText("");
		  textField_1.setText("");
		  textField_2.setText("");
		  textField_3.setText("");

		  textField.requestFocus();
	}
	public void list()
	{
		String requete="select * from Compte";
		try {
		   con= Connexion.getConnection();
		   st = con.createStatement();
		   rs = st.executeQuery(requete);
	 	   table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
	  	  e1.printStackTrace();
		}
		
	}
		public void remplirCombo()
	{
	  try {
	      String requete="select * from Client";
	      con= Connexion.getConnection();
	      st = con.createStatement();
	      rs = st.executeQuery(requete);

	      comboBox.removeAllItems();
	      comboBox.addItem("");
	     
	 while(rs.next())
	      {
	        
	         comboBox.addItem(rs.getString("idClient"));
	      }
	      st.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

		public int recherche( String opt)
		{
			   int resultat = 0;
					con = Connexion.getConnection();
					String requete ="select * from Client where idClient ='" +opt+"'";
					try {
						 Statement st = con.createStatement();
						 ResultSet  rrs = st.executeQuery(requete);
						 if (rrs.next()){
							resultat = 0;
						 }
						 else 
						 {
							resultat=1; 
						 }
						 st.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return resultat;
		}
}
