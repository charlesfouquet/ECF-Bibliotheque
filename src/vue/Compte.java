package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controler.BackOfficeDAO;
import controler.EmpruntDAO;
import controler.UserDAO;
import model.Emprunt;
import model.User;
import utilities.DateTime;

public class Compte extends JPanel {
	private static final long serialVersionUID = 6556442715550137983L;
	
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textEmail;
	private JPasswordField textAncienMdp;
	private JPasswordField textNvMdp;
	private JPasswordField textConfMdp;
	private JTextField textAdr;
	private JTextField textCp;
	private JTextField textVille;
	private JTextField textTel;
	private JTable table;
	private boolean histoState = false;

	UserDAO userDao = new UserDAO();
	EmpruntDAO empruntDAO = new EmpruntDAO();
	
	public Compte() {
		setBackground(new Color(240, 227, 198));
		setLayout(null);
		setBounds(0, 0, 1000, 550);
		
		//###################
		// ### BACKOFFICE ###
		//###################
		JPanel panelBackOffice = new JPanel();
		panelBackOffice.setBounds(900, 0, 100, 30);
		panelBackOffice.setBackground(new Color(233, 215, 170));
		panelBackOffice.setLayout(null);
		
		if (UserDAO.currentUser.getId_role() != 1) {
			add(panelBackOffice);
		}
		
		JButton btnBackOffice = new JButton("Back-Office");
		btnBackOffice.setForeground(new Color(255, 255, 255));
		btnBackOffice.setBackground(new Color(128, 64, 0));
		btnBackOffice.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		btnBackOffice.setBounds(0, 0, 100, 30);
		panelBackOffice.add(btnBackOffice);
		btnBackOffice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackOfficeDAO.ISBNOnLoad = null;
				removeAll();
				add(new BackOffice());
				repaint();
				revalidate();;
			}
		});
		
		//###################
		// ### INFORMATIONS GENERALES ###
		//###################
		JPanel panelInfoGen = new JPanel();
		panelInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		panelInfoGen.setBackground(new Color(240, 227, 198));
		panelInfoGen.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(199, 152, 50)));
		panelInfoGen.setLayout(null);
		panelInfoGen.setBounds(10, 10, 490, 225);
		add(panelInfoGen);
	
		JLabel titreInfoGen = new JLabel(" Informations g??n??rales");
		titreInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		titreInfoGen.setIcon(new ImageIcon("src/resources/images/logos/user.png"));
		titreInfoGen.setHorizontalAlignment(SwingConstants.LEFT);
		titreInfoGen.setBounds(0, 0, 480, 35);
		panelInfoGen.add(titreInfoGen);
		
		JLabel labelNomInscription = new JLabel("Nom :");
		labelNomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomInscription.setBounds(78, 46, 75, 30);
		panelInfoGen.add(labelNomInscription);
		
		textNom = new JTextField();
		textNom.setBackground(new Color(250, 243, 230));
		labelNomInscription.setLabelFor(textNom);
		textNom.setColumns(10);
		textNom.setBounds(175, 46, 200, 30);
		panelInfoGen.add(textNom);
		
		JLabel labelPrenomInscription = new JLabel("Prenom :");
		labelPrenomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelPrenomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenomInscription.setBounds(78, 87, 75, 30);
		panelInfoGen.add(labelPrenomInscription);
		
		textPrenom = new JTextField();
		textPrenom.setBackground(new Color(250, 243, 230));
		labelPrenomInscription.setLabelFor(textPrenom);
		textPrenom.setColumns(10);
		textPrenom.setBounds(175, 87, 200, 30);
		panelInfoGen.add(textPrenom);
		
		JLabel labelEmailInscription = new JLabel("E-mail :");
		labelEmailInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailInscription.setBounds(78, 128, 75, 30);
		panelInfoGen.add(labelEmailInscription);
		
		textEmail = new JTextField();
		textEmail.setBackground(new Color(250, 243, 230));
		labelEmailInscription.setLabelFor(textEmail);
		textEmail.setColumns(10);
		textEmail.setBounds(175, 128, 200, 30);
		panelInfoGen.add(textEmail);
			
		// ### BTN VALIDATION INFORMATIONS GENERALES ###
		JButton btnInfoGen = new JButton("Validation");
		btnInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnInfoGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!textNom.getText().isEmpty()) && (!textPrenom.getText().isEmpty()) && (!textEmail.getText().isEmpty())) {
					if (userDao.emailValidator(textEmail.getText())) {
						if (userDao.updateInfo(new User(UserDAO.currentUser.getId(), textNom.getText(), textPrenom.getText(), textEmail.getText(), null, null, 0, null, null, 0))) {
							JOptionPane.showMessageDialog(null,"Mise ?? jour de vos informations personnelles effectu??e !","Mise ?? jour du compte", JOptionPane.INFORMATION_MESSAGE);
							UserDAO.currentUser.setNom(textNom.getText());
							UserDAO.currentUser.setPrenom(textPrenom.getText());
							UserDAO.currentUser.setEmail(textEmail.getText());
							Accueil.updateConnectionStatus(textPrenom.getText(), textNom.getText());
							removeAll();
							add(new Compte());
							repaint();
							revalidate();
						} else {
							JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la mise ?? jour de vos informations personnelles.\nVeuillez r??essayer ult??rieurement.","Mise ?? jour du compte", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,  "Le format de votre e-mail n'est pas correct !\nMerci de le modifier.", "Mise ?? jour du compte", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"Certaines informations sont manquantes.\nVeuillez les renseigner, s'il vous pla??t.","Mise ?? jour du compte", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnInfoGen.setBounds(230, 166, 145, 30);
		btnInfoGen.setBackground(new Color(255, 255, 255));
		btnInfoGen.setForeground(new Color(199, 152, 50));
		btnInfoGen.setFont(new Font("Noto Serif", Font.BOLD, 14));
		panelInfoGen.add(btnInfoGen);
		
		if (UserDAO.currentUser != null) {
			textNom.setText(UserDAO.currentUser.getNom());
			textPrenom.setText(UserDAO.currentUser.getPrenom());
			textEmail.setText(UserDAO.currentUser.getEmail());
		}
		
		//###################
		// ### ADRESSE DE DOMICILIATION ###
		//###################
		JPanel panelAdresseDom = new JPanel();
		panelAdresseDom.setBackground(new Color(240, 227, 198));
		panelAdresseDom.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 152, 50)));
		panelAdresseDom.setLayout(null);
		panelAdresseDom.setBounds(500, 10, 490, 265);
		add(panelAdresseDom);
		
		JLabel lblAdresseDeDomiciliation = new JLabel(" Adresse de domiciliation");
		lblAdresseDeDomiciliation.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		lblAdresseDeDomiciliation.setIcon(new ImageIcon("src/resources/images/logos/house.png"));
		lblAdresseDeDomiciliation.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresseDeDomiciliation.setBounds(10, 0, 480, 35);
		panelAdresseDom.add(lblAdresseDeDomiciliation);
		
		JLabel labelAdr = new JLabel("Adresse :");
		labelAdr.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelAdr.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAdr.setBounds(87, 46, 75, 30);
		panelAdresseDom.add(labelAdr);
		
		textAdr = new JTextField();
		textAdr.setBackground(new Color(250, 243, 230));
		textAdr.setColumns(10);
		textAdr.setBounds(184, 46, 200, 30);
		panelAdresseDom.add(textAdr);
		
		JLabel labelCp = new JLabel("Code Postal :");
		labelCp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelCp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCp.setBounds(62, 87, 100, 30);
		panelAdresseDom.add(labelCp);
		
		textCp = new JTextField();
		textCp.setBackground(new Color(250, 243, 230));
		textCp.setColumns(10);
		textCp.setBounds(184, 87, 200, 30);
		panelAdresseDom.add(textCp);
		
		JLabel labelVille = new JLabel("Ville :");
		labelVille.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelVille.setHorizontalAlignment(SwingConstants.RIGHT);
		labelVille.setBounds(87, 128, 75, 30);
		panelAdresseDom.add(labelVille);
		
		textVille = new JTextField();
		textVille.setBackground(new Color(250, 243, 230));
		textVille.setColumns(10);
		textVille.setBounds(184, 128, 200, 30);
		panelAdresseDom.add(textVille);
		
		JLabel labelTel = new JLabel("T??l??phone :");
		labelTel.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelTel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTel.setBounds(62, 169, 100, 30);
		panelAdresseDom.add(labelTel);
		
		textTel = new JTextField();
		textTel.setBackground(new Color(250, 243, 230));
		textTel.setColumns(10);
		textTel.setBounds(184, 169, 200, 30);
		panelAdresseDom.add(textTel);
		
		// ### BTN VALIDATION ADRESSE DE DOMICILIATION ###
		JButton btnAdrDom = new JButton("Validation");
		btnAdrDom.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnAdrDom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int postCode;
				if (textCp.getText().isEmpty()) {
					postCode = 0;
				} else {
					postCode = Integer.parseInt(textCp.getText());
				}
				if (userDao.updateAddress(new User(UserDAO.currentUser.getId(), null, null, null, null, textAdr.getText(), postCode, textVille.getText(), textTel.getText(), 0))) {
 					JOptionPane.showMessageDialog(null,"Mise ?? jour de votre adresse effectu??e !","Mise ?? jour de l'adresse", JOptionPane.INFORMATION_MESSAGE);
					if (textAdr.getText().isEmpty()) {
						UserDAO.currentUser.setAdresse(null);		
					} else {
						UserDAO.currentUser.setAdresse(textAdr.getText());	
					}

					if (!(postCode > 0)) {
						UserDAO.currentUser.setCp(0);
					} else {
						UserDAO.currentUser.setCp(postCode);	
					}
					
					if (textVille.getText().isEmpty()) {
						UserDAO.currentUser.setVille(null);	
					} else {			
						UserDAO.currentUser.setVille(textVille.getText());
					}
					
					if (textTel.getText().isEmpty()) {
						UserDAO.currentUser.setTel(null);			
					} else {			
						UserDAO.currentUser.setTel(textTel.getText());
					}
					removeAll();
					add(new Compte());
					repaint();
					revalidate();
				} else {
					JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la mise ?? jour de votre adresse.\nVeuillez r??essayer ult??rieurement.","Mise ?? jour de l'adresse", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdrDom.setBackground(new Color(255, 255, 255));
		btnAdrDom.setForeground(new Color(199, 152, 50));
		btnAdrDom.setFont(new Font("Noto Serif", Font.BOLD, 14));
		btnAdrDom.setBounds(254, 210, 130, 30);
		panelAdresseDom.add(btnAdrDom);
		
		if (UserDAO.currentUser != null) {
			if (UserDAO.currentUser.getAdresse() != null) {
				textAdr.setText(UserDAO.currentUser.getAdresse());
			}
			if (UserDAO.currentUser.getCp() > 0) {
				textCp.setText(String.valueOf(UserDAO.currentUser.getCp()));
			}
			if (UserDAO.currentUser.getVille() != null) {
				textVille.setText(UserDAO.currentUser.getVille());
			}
			if (UserDAO.currentUser.getTel() != null) {
				textTel.setText(UserDAO.currentUser.getTel());
			}
		}
		
		//###################
		// ### MODIFICATION MOT DE PASSE ###
		//###################
		JPanel PanelMdp = new JPanel();
		PanelMdp.setBackground(new Color(240, 227, 198));
		PanelMdp.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(192, 152, 50)));
		PanelMdp.setLayout(null);
		PanelMdp.setBounds(10, 235, 490, 220);
		add(PanelMdp);
		
		JLabel lblModific = new JLabel(" Modification mot de passe");
		panelInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		lblModific.setIcon(new ImageIcon("src/resources/images/logos/lock.png"));
		lblModific.setHorizontalAlignment(SwingConstants.LEFT);
		lblModific.setFont(new Font("Noto Serif", Font.BOLD, 16));
		lblModific.setBounds(0, 10, 480, 35);
		PanelMdp.add(lblModific);
		
		JLabel lblAncienMdp = new JLabel("Ancien :");
		lblAncienMdp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		lblAncienMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAncienMdp.setBounds(80, 51, 75, 30);
		PanelMdp.add(lblAncienMdp);
		
		textAncienMdp = new JPasswordField();
		textAncienMdp.setBackground(new Color(250, 243, 230));
		lblAncienMdp.setLabelFor(textAncienMdp);
		textAncienMdp.setColumns(10);
		textAncienMdp.setBounds(177, 51, 200, 30);
		PanelMdp.add(textAncienMdp);
		
		JLabel labelNvMdp = new JLabel("Nouveau :");
		labelNvMdp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNvMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNvMdp.setBounds(80, 92, 75, 30);
		PanelMdp.add(labelNvMdp);
		
		textNvMdp = new JPasswordField();
		textNvMdp.setBackground(new Color(250, 243, 230));
		labelNvMdp.setLabelFor(textNvMdp);
		textNvMdp.setColumns(10);
		textNvMdp.setBounds(177, 92, 200, 30);
		PanelMdp.add(textNvMdp);
		
		JLabel labelConfirmMdp = new JLabel("Confirmation :");
		labelConfirmMdp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelConfirmMdp.setHorizontalAlignment(SwingConstants.RIGHT);
		labelConfirmMdp.setBounds(55, 133, 100, 30);
		PanelMdp.add(labelConfirmMdp);
		
		textConfMdp = new JPasswordField();
		textConfMdp.setBackground(new Color(250, 243, 230));
		labelConfirmMdp.setLabelFor(textConfMdp);
		textConfMdp.setColumns(10);
		textConfMdp.setBounds(177, 133, 200, 30);
		PanelMdp.add(textConfMdp);
		
		// ### BTN VALIDATION MODIFICATION MOT DE PASSE ###
		JButton btnModifMdp = new JButton("Validation");
		btnModifMdp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnModifMdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!String.valueOf(textAncienMdp.getPassword()).isEmpty()) && (!String.valueOf(textNvMdp.getPassword()).isEmpty()) && (!String.valueOf(textConfMdp.getPassword()).isEmpty())) {
					if (String.valueOf(textNvMdp.getPassword()).equals(String.valueOf(textConfMdp.getPassword()))) {
						if (userDao.passValidator(String.valueOf(textNvMdp.getPassword()))) {
							if (userDao.updatePassword(UserDAO.currentUser.getId(), new User(textEmail.getText(), String.valueOf(textAncienMdp.getPassword())), new User(textEmail.getText(), String.valueOf(textNvMdp.getPassword())))) {
								JOptionPane.showMessageDialog(null,"Mise ?? jour de votre mot de passe effectu??e !","Mise ?? jour du mot de passe", JOptionPane.INFORMATION_MESSAGE);
								removeAll();
								add(new Compte());
								repaint();
								revalidate();
							} else {
								JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la mise ?? jour de votre mot de passe.\nVeuillez r??essayer ult??rieurement.","Mise ?? jour du mot de passe", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,  "Le format de votre nouveau mot de passe n'est pas correct !\nMerci de le modifier.", "Mise ?? jour du mot de passe", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,"ATTENTION, vos mots de passe ne sont pas identiques !","Mise ?? jour du mot de passe", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"Certaines informations sont manquantes.\nVeuillez les renseigner, s'il vous pla??t.","Mise ?? jour du mot de passe", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnModifMdp.setBounds(232, 174, 145, 30);
		btnModifMdp.setBackground(new Color(255, 255, 255));
		btnModifMdp.setForeground(new Color(199, 152, 50));
		btnModifMdp.setFont(new Font("Noto Serif", Font.BOLD, 14));
		PanelMdp.add(btnModifMdp);
		
		//###################
		// ### LISTE DES EMPRUNTS ###
		//###################
		JPanel panelEmprunt = new JPanel();
		panelEmprunt.setBackground(new Color(240, 227, 198));
		panelEmprunt.setLayout(null);
		panelEmprunt.setBounds(500, 274, 490, 265);
		add(panelEmprunt);
		
		JLabel lblListeDesEmprunts = new JLabel(" Liste des emprunts");
		lblListeDesEmprunts.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		lblListeDesEmprunts.setIcon(new ImageIcon("src/resources/images/logos/list.png"));
		lblListeDesEmprunts.setHorizontalAlignment(SwingConstants.LEFT);
		lblListeDesEmprunts.setFont(new Font("Noto Serif", Font.BOLD, 16));
		lblListeDesEmprunts.setBounds(10, 10, 240, 35);
		panelEmprunt.add(lblListeDesEmprunts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 66, 480, 188);
		panelEmprunt.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (!histoState) {
					Object[] options = {"Oui", "Non"};
					int livreRendu = JOptionPane.showOptionDialog(null, "Souhaitez-vous rendre \"" + (String) table.getValueAt(row, 1) + "\" (Livre #" + (String) table.getValueAt(row, 0) + ") ?", "Retourner un livre", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (livreRendu == 0) {
						if (empruntDAO.rendreLivre(UserDAO.currentUser, Integer.parseInt((String) table.getValueAt(row, 0)))) {
							JOptionPane.showConfirmDialog(null, "Merci d'avoir rendu \"" + (String) table.getValueAt(row, 1) + "\"", "Livre retourn??", JOptionPane.WARNING_MESSAGE);							
						} else {
							JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez r??essayer ult??rieurement", "Retourner un livre", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				table.setModel(listeEmprunts(UserDAO.currentUser, histoState));
				reformatTable(table);
			}
		});
		table.setBackground(new Color(250, 243, 230));
		table.setModel(listeEmprunts(UserDAO.currentUser, histoState));
		reformatTable(table);
		scrollPane.setViewportView(table);
		
		JButton histoBtn = new JButton("Afficher l'historique des commandes");
		histoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				histoState = !histoState;
				if (histoState) {
					histoBtn.setText("Afficher les emprunts en cours");
				} else {
					histoBtn.setText("Afficher l'historique des commandes");					
				}
				table.setModel(listeEmprunts(UserDAO.currentUser, histoState));
				reformatTable(table);
			}
		});
		histoBtn.setBackground(new Color(255, 255, 255));
		histoBtn.setForeground(new Color(199, 152, 50));
		histoBtn.setFont(new Font("Noto Serif", Font.BOLD, 11));
		histoBtn.setBounds(240, 15, 250, 25);
		panelEmprunt.add(histoBtn);
		
		//###################
		// ### SUPPRESSION DU COMPTE ###
		//###################
		JPanel panelDesactivez = new JPanel();
		panelDesactivez.setBackground(new Color(240, 227, 198));
		panelDesactivez.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(199, 152, 50)));
		panelDesactivez.setBounds(10, 455, 490, 85);
		add(panelDesactivez);
		panelDesactivez.setLayout(null);
		
		JLabel labelSuppCompte = new JLabel("Desactivez mon compte :");
		labelSuppCompte.setIcon(new ImageIcon("src/resources/images/logos/userDesactivate.png"));
		labelSuppCompte.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelSuppCompte.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSuppCompte.setBounds(0, 10, 210, 45);
		panelDesactivez.add(labelSuppCompte);
		
		JButton btnDelete = new JButton("D??sactivation");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Dialog", Font.ITALIC, 14));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBackground(new Color(240, 145, 145));
		btnDelete.addMouseListener(new MouseAdapter() {
			//hover des couleur du bouton
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(new Color(255, 0, 0));
				btnDelete.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(new Color(240, 145, 145));
				btnDelete.setForeground(new Color(255, 255, 255));
			}
			
			// ### BTN VALIDATION SUPPRESSION DU COMPTE ###
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbrDeLivre = empruntDAO.qtLivreARendre(UserDAO.currentUser);
				if (nbrDeLivre == 0) {
					Object[] options = {"Oui", "Non"};
					int supprimeCompte = JOptionPane.showOptionDialog(null, "Souhaitez-vous r??ellement d??sactiver votre compte ?", "DESACTIVATION du compte !", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/resources/images/logos/userDesactivate.png"), options, options[0]);
					if (supprimeCompte == 0) {
						JPasswordField jpass = new JPasswordField();
						int reponse = JOptionPane.showConfirmDialog(null, jpass, "Saisissez votre mot de passe !", JOptionPane.OK_CANCEL_OPTION );
						System.out.println(reponse);
						if(reponse == 0) {
							if(userDao.deactivate(UserDAO.currentUser, String.valueOf(jpass.getPassword() ))) {
								JOptionPane.showMessageDialog(null, "Votre compte a ??t?? d??sactiv?? !", "DESACTIVATION du compte !", JOptionPane.INFORMATION_MESSAGE);
								UserDAO.currentUser = null;
								Main.frame.getContentPane().removeAll();
								Main.frame.getContentPane().add(new Accueil());
								Main.frame.getContentPane().repaint();
								Main.frame.getContentPane().revalidate();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Votre mot de passe est incorrect !", "DESACTIVATION du compte !", JOptionPane.ERROR_MESSAGE);
						}
					} 
				}else {
					JOptionPane.showConfirmDialog(null, "Vous ne pouvez pas d??sactiver votre compte.\n Il vous reste : "+nbrDeLivre+" livres ?? rendre", "DESACTIVATION du compte", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnDelete.setBounds(233, 20, 145, 30);
		panelDesactivez.add(btnDelete);
	}
	
	//###################
	// ### LISTE DES EMPRUNTS ###
	//###################
	public void reformatTable(JTable tableInput) {
		tableInput.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableInput.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableInput.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	public DefaultTableModel listeEmprunts(User user, Boolean histoState) {
		String col [] = new String[4];
		col[0] = "Ref.";
		col[1] = "Titre";
		col[2] = "Emprunt?? le";
		if (histoState) {
			col[3] = "Rendu le";					
		} else {
			col[3] = "A rendre le";											
		}
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -2767358890848023063L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		ArrayList<Emprunt> emprunts = empruntDAO.read(user, histoState);
		
		for (Emprunt emp : emprunts) {
			String finalDateRetour = "";
			Date dateSortie = emp.getDateSortie();
			LocalDate dateRetour = dateSortie.toLocalDate().plusMonths(1);
			LocalDate today = LocalDate.now();
			
			if (histoState == false) {				
				Period difference = Period.between(dateRetour, today);
				
				Date dateRetourD = Date.valueOf(dateRetour);
				if (difference.getDays() > 0) {
					finalDateRetour = "<html><body style=\"color:red;\">???<span style=\"font-weight:bold;\">" + DateTime.sdfShortDate.format(dateRetourD) + "</span></body></html>";					
				} else {
					finalDateRetour = DateTime.sdfShortDate.format(dateRetourD);
				}
			} else {
				finalDateRetour = DateTime.sdfShortDate.format(emp.getDateRetour());
			}
			
			tableau.addRow(new Object[] {
					String.format("%05d", emp.getExemplaire()),
					emp.getTitre(),
					DateTime.sdfShortDate.format(dateSortie),
					finalDateRetour
			});
			
		}
		return tableau;
	}
}
