package metier;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Employe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Employe frame = new Employe();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTable table;
	private JTextField textField_5;
	

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
	public Employe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1360, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(0, 0, 1344, 749);
		contentPane.add(desktopPane);
		
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
				Employe f1= new Employe();                               
				f1.setVisible(true);
				
				frame.setVisible(false);
			}
		});
		btnEmploye.setBounds(616, 26, 89, 23);
		panel.add(btnEmploye);
		
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
		
		JLabel Employe = new JLabel("Employe");
		Employe.setHorizontalAlignment(SwingConstants.CENTER);
		Employe.setFont(new Font("Georgia", Font.PLAIN, 18));
		Employe.setBounds(208,139, 73, 25);
		desktopPane.add(Employe);
		
		JButton ListeEmploye = new JButton("ListeEmploye");
		ListeEmploye.setForeground(Color.WHITE);
		ListeEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		ListeEmploye.setBounds(577, 210, 92, 32);
		ListeEmploye.setBackground(new Color(21, 27, 84));
		desktopPane.add(ListeEmploye);
		
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
				  int vIdEmploye;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				 int opt = jp1.showConfirmDialog(null, "Voulez vous vraiment Modifier??","Attention", JOptionPane.OK_CANCEL_OPTION);
				 if(textField.getText().equals(""))
					{
						jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
					}
				 else{
				 if(opt==0){
				  vIdEmploye= Integer.parseInt(textField.getText());				
				  String requete="Update Employe set prenomEmploye ='"+ textField_1.getText()+ "',nomEmploye ='" + textField_2.getText() +"',adresseEmploye ='" + textField_3.getText()+"',phoneEmploye ='" + textField_4.getText()  +"',salaireEmploye ='" + textField_5.getText() + "'  where idEmploye = '" + vIdEmploye + "'";
								  				  				  
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
				int vIdEmploye,opt;
				JOptionPane jp1 = new JOptionPane();
				JOptionPane jp2 = new JOptionPane();
							  
				opt= jp1.showConfirmDialog(null,"Voulez vous vraiment supprimer??", "Employe Banque-Uniq IUO !",JOptionPane.OK_CANCEL_OPTION);
		
				Connection cn = Connexion.getConnection();
				if(textField.getText().equals(""))
				{
					jp1.showMessageDialog(null, "Veillez selectionner une ligne","Attention",JOptionPane.WARNING_MESSAGE);
				}
				else{
				if(opt==0){
					vIdEmploye=Integer.parseInt(textField.getText());
					String requete ="delete from Employe where idEmploye =" +vIdEmploye ;
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
		button_8.setBounds(1008, 210, 97, 32);
		button_8.setBackground(new Color(21, 27, 84));
		desktopPane.add(button_8);
		
	    textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(301, 244, 97, 20);
		desktopPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(301, 280, 97, 20);
		desktopPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(301, 319, 97, 20);
		desktopPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(301, 372, 97, 20);
		desktopPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(301, 420, 97, 20);
		textField_4.setColumns(10);
		desktopPane.add(textField_4);

		JLabel idEmploye = new JLabel("IdEmploye");
		idEmploye.setFont(new Font("Calibri", Font.PLAIN, 15));
		idEmploye.setBounds(208, 247, 65, 14);
		desktopPane.add(idEmploye);
		
		JLabel prenomEmploye = new JLabel("Prenom");
		prenomEmploye.setFont(new Font("Calibri", Font.PLAIN, 15));
		prenomEmploye.setBounds(208, 283, 65, 14);
		desktopPane.add(prenomEmploye);
		
		JLabel adresseEmploye = new JLabel("Adresse");
		adresseEmploye.setFont(new Font("Calibri", Font.PLAIN, 15));
		adresseEmploye.setBounds(208, 375, 73, 14);
		desktopPane.add(adresseEmploye);
		
		JLabel nomEmploye = new JLabel("Nom");
		nomEmploye.setFont(new Font("Calibri", Font.PLAIN, 15));
		nomEmploye.setBounds(208, 322, 92, 14);
		desktopPane.add(nomEmploye);
		
		JButton AjouterClient = new JButton("Ajouter");
		AjouterClient.setForeground(Color.WHITE);
		AjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jp1 =new JOptionPane();
				JOptionPane jp2 =new JOptionPane();
				if(textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals("") || textField_5.getText().equals("")){
				jp2.showMessageDialog(null,"Veuillez ajouter les informations!!");
				}
				else{
				  String requete="Insert into Employe (idEmploye, prenomEmploye, nomEmploye, adresseEmploye,phoneEmploye, salaireEmploye)  values (null,'";
				  requete = requete + textField_1.getText()+ "','" + textField_2.getText()+"','" + textField_3.getText()+"','";
				  requete = requete + textField_4.getText()+"',"+textField_5.getText()+ ")";
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
		
		JLabel phoneEmploye = new JLabel("Phone");
		phoneEmploye.setFont(new Font("Calibri", Font.PLAIN, 15));
		phoneEmploye.setBounds(208, 423, 46, 14);
		desktopPane.add(phoneEmploye);
		
		
		
		JButton Recherche = new JButton("Recherche");
		Recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  int vId;
				  String opt=null;
				  JOptionPane jp1 = new JOptionPane();
				  JOptionPane jp2 = new JOptionPane();
				  
				  opt = jp1.showInputDialog(null, "Veuillez indiquer l'ID !", "Employe Banque-UNIQ !",JOptionPane.QUESTION_MESSAGE);
					 if(opt!=null)
					 {
						vId= Integer.parseInt(opt);
						con = Connexion.getConnection();
						String requete ="select * from Employe where idEmploye =" +vId;
						try {
							 Statement st = con.createStatement();
							 ResultSet  rrs = st.executeQuery(requete);
							 if (rrs.next()){
								textField.setText(rrs.getInt(1)+"");
								textField_1.setText(rrs.getString(2)); 
								textField_2.setText(rrs.getString(3));
								textField_3.setText(rrs.getString(4));		
								textField_4.setText(rrs.getString(5));
								textField_5.setText(rrs.getString(6));
								
										
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
		
		JLabel CreerNouveauEmploye = new JLabel("Creer Nouveau Employe");
		CreerNouveauEmploye.setBounds(208, 192, 204, 41);
		CreerNouveauEmploye.setFont(new Font("Georgia", Font.PLAIN, 18));
		desktopPane.add(CreerNouveauEmploye);
		
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
				  String requete="select * from Employe where idEmploye="+idEmp;
				  try {
				     con= Connexion.getConnection();
				     st = con.createStatement();
				     rs = st.executeQuery(requete);			
				     while(rs.next())
				     {
					   textField.setText(rs.getString("idEmploye"));
					   textField_1.setText(rs.getString("prenomEmploye"));
					   textField_2.setText(rs.getString("nomEmploye"));
					   textField_3.setText(rs.getString("adresseEmploye"));
					   textField_4.setText(rs.getString("phoneEmploye"));
					   textField_5.setText(rs.getString("salaireEmploye"));
					   			   
				       }
				        st.close();
					} catch (SQLException ee) {
					     ee.printStackTrace();
				    }

			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblSalaire.setBounds(208, 467, 46, 14);
		desktopPane.add(lblSalaire);
		
		textField_5 = new JTextField();
		textField_5.setBounds(301, 464, 97, 20);
		desktopPane.add(textField_5);
		textField_5.setColumns(10);
	}
	
	public void clear()
	{
		  textField.setText("");
		  textField_1.setText("");
		  textField_2.setText("");
		  textField_3.setText("");
		  textField_4.setText("");
		  textField_5.setText("");

		  textField.requestFocus();
	}
	
	public void list()
	{
		String requete="select * from Employe";
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
