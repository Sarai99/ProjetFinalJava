package metier;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class PageAccueil extends JFrame {

	  private JPanel contentPane;
	 JLabel lblImage;
	 private JLabel lblImage_1;
	 static PageAccueil frame;
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
					frame = new PageAccueil();
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
	public PageAccueil() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sarai DIEUDONNE\\Downloads\\CreditCardsStack.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1360, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(208, 11, 912, 71);
		panel.setBackground(Color.WHITE);
		Color col=new Color(21, 27, 84);
		contentPane.setLayout(null);
		panel.setBackground(col);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUniqBanque = new JLabel("Uniq-BANQUE");
		lblUniqBanque.setBounds(10, 24, 117, 23);
		panel.add(lblUniqBanque);
		lblUniqBanque.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniqBanque.setFont(new Font("MingLiU-ExtB", Font.BOLD, 18));
		lblUniqBanque.setForeground(Color.WHITE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(1215, 24, 97, 21);
		menuBar.setToolTipText("Menu");
		panel.add(menuBar);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(790, 26, 97, 21);
		panel.add(menuBar_1);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar_1.add(mnMenu);
		
		JMenuItem mntmAproposdenous = new JMenuItem("A propos de nous");
		mnMenu.add(mntmAproposdenous);
		
		JMenuItem mntmProduitEtService = new JMenuItem("Produits et Services");
		mnMenu.add(mntmProduitEtService);
		
		JMenuItem mntmFormulaire = new JMenuItem("Formulaire");
		mnMenu.add(mntmFormulaire);
		
		JMenuItem mntmContactezNous = new JMenuItem("Contactezo-nous");
		mnMenu.add(mntmContactezNous);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			});
		mnMenu.add(mntmExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(208, 93, 149, 468);
		Color col1=new Color(240, 240, 243);
		contentPane.setLayout(null);
		panel_1.setBackground(new Color(76, 108, 141));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEmploye = 
		new JButton("Employe");
		btnEmploye.setForeground(Color.WHITE);
		btnEmploye.setBackground(new Color(21, 27, 84));
		btnEmploye.setBounds(10, 72, 119, 38);
		
		panel_1.add(btnEmploye);
		
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client f1= new Client();
				f1.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btnClient.setForeground(Color.WHITE);
		btnClient.setBackground(new Color(21, 27, 84));
		btnClient.setBounds(10, 127, 119, 38);
		panel_1.add(btnClient);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(208, 135, 575, 392);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(0, 71, 154));
		panel_2.setLayout(null);
		
		 lblImage_1 = new JLabel(new ImageIcon("C:\\Users\\Sarai DIEUDONNE\\Downloads\\logo.jpg"));
		 lblImage_1.setBounds(-88, -153, 908, 702);
		 panel_2.add(lblImage_1);
		 panel_2.setVisible(true);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(410, 123, 383, 415);
		panel_5.setBackground(new Color(2, 1,7));
		contentPane.add(panel_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(475, 93, 645, 468);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
	     lblImage = new JLabel(new ImageIcon("C:\\Users\\Sarai DIEUDONNE\\Downloads\\Bank.png"));
	     lblImage.setBounds(-263, -234, 908, 702);
	     panel_3.add(lblImage);
		panel_3.setVisible(true);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		Color col2=new Color(21, 27, 84);
		panel_4.setBackground(col2);
		panel_4.setBounds(208, 572, 912, 71);
		contentPane.add(panel_4);
		
		
		
		btnEmploye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Employe f1= new Employe();                               
				f1.setVisible(true); 
				
				frame.setVisible(false);
				
			}
		});
		
	}
}
