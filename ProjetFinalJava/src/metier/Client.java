package metier;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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



public class Client extends JFrame {
	 static Client frame = new Client();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Connection con;
	Statement st;
	ResultSet rs;
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
	public Client() {
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
		
		JButton btnEmploye = new JButton("Employe");
		btnEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				frame.setVisible(false);
				
				Employe f1= new Employe();                               
				f1.setVisible(true);
				
				
			}
		});
		btnEmploye.setBounds(616, 26, 89, 23);
		panel.add(btnEmploye);
		
		JButton btnClient =  new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				
				Client f1= new Client();                               
				f1.setVisible(true);
				
				
			}
		});
		btnClient.setBounds(740, 26, 89, 23);
		panel.add(btnClient);
		
		JLabel Client = new JLabel("Client");
		Client.setHorizontalAlignment(SwingConstants.CENTER);
		Client.setFont(new Font("Georgia", Font.PLAIN, 18));
		Client.setBounds(208,139, 73, 25);
		desktopPane.add(Client);
		
		JButton ListeClient = new JButton("ListeClient");
		ListeClient.setForeground(Color.WHITE);
		ListeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		ListeClient.setBounds(577, 210, 92, 32);
		ListeClient.setBackground(new Color(21, 27, 84));
		desktopPane.add(ListeClient);
		
		JButton button_6 = new JButton("Nouveau");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_6.setForeground(Color.WHITE);
		button_6.setBounds(313, 506, 85, 32);
		button_6.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_6);
		
		JButton button_7 = new JButton("Modifier");
		button_7.setForeground(Color.WHITE);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int vId;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				 int opt = jp1.showConfirmDialog(null, "Voulez vous vraiment Modifier??","Attention", JOptionPane.OK_CANCEL_OPTION);
				 if(textField.getText().equals(""))
					{
						jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
					}
				 else{
				 if(opt==0){
				  vId= Integer.parseInt(textField.getText());				
				  String requete="Update Client set prenomClient ='"+ textField_1.getText()+ "',nomClient ='" + textField_2.getText() +"',adresseClient ='" + textField_3.getText()+"',phoneClient ='" + textField_4.getText()  +"'  where idClient = '" + vId + "'";
								  				  				  
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
		button_7.setBounds(857, 210, 102, 32);
		button_7.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_7);
		
		JButton button_8 = new JButton("Suprimer");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vId,opt;
				JOptionPane jp1 = new JOptionPane();
				JOptionPane jp2 = new JOptionPane();
							  
				opt= jp1.showConfirmDialog(null,"Voulez vous vraiment supprimer?? Tous les Comptes seront supprimes", "Client Banque-Uniq IUO !",JOptionPane.OK_CANCEL_OPTION);
		
				Connection cn = Connexion.getConnection();
				if(textField.getText().equals(""))
				{
					jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
				}
				else{
				if(opt==0){
					vId=Integer.parseInt(textField.getText());
					String requete ="delete from Client where idClient =" +vId ;
					try {
					   Statement st = cn.createStatement();
					   suppressionCompte(vId);
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
		button_8.setBounds(1008, 210, 97, 32);
		button_8.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_8);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(295, 260, 97, 20);
		desktopPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(295, 304, 97, 20);
		desktopPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(295, 347, 97, 20);
		desktopPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(295, 390, 97, 20);
		desktopPane.add(textField_3);
		
		JLabel idClient = new JLabel("idClient");
		idClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		idClient.setBounds(208, 263, 65, 14);
		desktopPane.add(idClient);
		
		JLabel prenomClient = new JLabel("Prenom");
		prenomClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		prenomClient.setBounds(208, 307, 65, 14);
		desktopPane.add(prenomClient);
		
		JLabel adresseClient = new JLabel("Adresse");
		adresseClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		adresseClient.setBounds(208, 393, 73, 14);
		desktopPane.add(adresseClient);
		
		JLabel nomClient = new JLabel("Nom");
		nomClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		nomClient.setBounds(208, 350, 92, 14);
		desktopPane.add(nomClient);
		
		JButton AjouterClient = new JButton("Ajouter");
		AjouterClient.setForeground(Color.WHITE);
		AjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jp1 =new JOptionPane();
				JOptionPane jp2 =new JOptionPane();
				if(textField_1.getText().equals("")|| textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals("")){
				jp2.showMessageDialog(null,"Veuillez ajouter les informations!!");
				}
				else{
				  String requete="Insert into Client (idClient, prenomClient, nomClient, adresseClient,phoneClient)  values (null,'";
				  requete = requete + textField_1.getText()+ "','" + textField_2.getText()+"','" + textField_3.getText()+"','";
				  requete = requete + textField_4.getText()+"')";
								  System.out.println(requete);
								  				  
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
		AjouterClient.setBounds(208, 506, 85, 32);
		AjouterClient.setBackground(new Color(21, 27, 84));
		desktopPane.add(AjouterClient);
		
		JLabel phoneClient = new JLabel("Phone");
		phoneClient.setFont(new Font("Calibri", Font.PLAIN, 15));
		phoneClient.setBounds(208, 441, 46, 14);
		desktopPane.add(phoneClient);
		
		textField_4 = new JTextField();
		textField_4.setBounds(295, 438, 97, 20);
		desktopPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton Recherche = new JButton("Recherche");
		Recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vId;
				  String opt=null;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				  opt = jp1.showInputDialog(null, "Veuillez indiquer l'ID !", "Client Banque-UNIQ !",JOptionPane.QUESTION_MESSAGE);
					 if(opt!=null)
					 {
						vId= Integer.parseInt(opt);
						con = Connexion.getConnection();
						String requete ="select * from Client where idClient =" +vId;
						try {
							 Statement st = con.createStatement();
							 ResultSet  rrs = st.executeQuery(requete);
							 if (rrs.next()){
								textField.setText(rrs.getInt(1)+"");
								textField_1.setText(rrs.getString(2)); 
								textField_2.setText(rrs.getString(3));
								textField_3.setText(rrs.getString(4));		
								textField_4.setText(rrs.getString(5));
										
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
		Recherche.setForeground(Color.WHITE);
		Recherche.setBounds(714, 210, 89, 32);
		Recherche.setBackground(new Color(21, 27, 84));
		desktopPane.add(Recherche);
		
		JLabel CreerNouveauClient = new JLabel("Creer Nouveau Client");
		CreerNouveauClient.setBounds(208, 211, 204, 14);
		CreerNouveauClient.setFont(new Font("Georgia", Font.PLAIN, 18));
		desktopPane.add(CreerNouveauClient);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		Color col2=new Color(21, 27, 84);
		panel_1.setBackground(col2);
		panel_1.setBounds(208, 573, 912, 71);
		desktopPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(621, 263, 486, 266);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne=table.getSelectedRow();
				  int idEmp = Integer.parseInt((table.getModel().getValueAt(ligne,0)).toString());
				  String requete="select * from Client where idClient="+idEmp;
				  try {
				     con= Connexion.getConnection();
				     st = con.createStatement();
				     rs = st.executeQuery(requete);			
				     while(rs.next())
				     {
					   textField.setText(rs.getString("idClient"));
					   textField_1.setText(rs.getString("prenomClient"));
					   textField_2.setText(rs.getString("nomClient"));
					   textField_3.setText(rs.getString("adresseClient"));
					   textField_4.setText(rs.getString("phoneClient"));
					   			   
				       }
				        st.close();
					} catch (SQLException ee) {
					     ee.printStackTrace();
				    }

			}
		});
		scrollPane.setViewportView(table);
		
		JButton CompteClient = new JButton("CompteClient");
		CompteClient.setForeground(Color.WHITE);
		CompteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				
				Compte f1= new Compte();                               
				f1.setVisible(true);
				
				
			}
		});
		CompteClient.setHorizontalAlignment(SwingConstants.RIGHT);
		CompteClient.setBounds(1008, 143, 97, 32);
		CompteClient.setBackground(new Color(21, 27, 84));
		desktopPane.add(CompteClient);
	}
	public void clear()
	{
		  textField.setText("");
		  textField_1.setText("");
		  textField_2.setText("");
		  textField_3.setText("");
		  textField_4.setText("");

		  textField.requestFocus();
	}
	public void list()
	{
		String requete="select * from Client";
		try {
		   con= Connexion.getConnection();
		   st = con.createStatement();
		   rs = st.executeQuery(requete);
	 	   table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
	  	  e1.printStackTrace();
		}
	}
	public void suppressionCompte(int Vid )
	{
		String requete = "delete from Compte where idClient ="+ Vid;
		try {
			con= Connexion.getConnection();
		     st = con.createStatement();
		     st.executeUpdate(requete);
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
}
