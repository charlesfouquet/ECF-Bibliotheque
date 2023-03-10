package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import controler.UserDAO;
import model.User;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

public class Login extends JPanel {
	private static final long serialVersionUID = -6571947288755605768L;
	private JTextField textEmailC;
	private JPasswordField textMdpC;
	private JTextField textNomI;
	private JTextField textPrenomI;
	private JTextField textEmailI;
	private JPasswordField textMdpI;
	private JPasswordField textConfMdpI;

	//instance pour utilisation méthode
	UserDAO userDao = new UserDAO();
	
	
	public Login() {
		setBackground(new Color(240, 227, 198));
		setLayout(null);
		setBounds(0, 0, 1000, 550);
		
		//#########################
		//### CONNEXION N###
		//#########################
		JPanel panelConnexion = new JPanel();
		panelConnexion.setBackground(new Color(240, 227, 198));
		panelConnexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelConnexion.setBounds(10, 10, 485, 530);
		add(panelConnexion);
		panelConnexion.setLayout(null);
		
		JLabel labelConnexion = new JLabel("Connexion");
		labelConnexion.setFont(new Font("Noto Serif", Font.BOLD, 16));
		labelConnexion.setBackground(Color.GRAY);
		labelConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		labelConnexion.setBounds(0, 0, 485, 50);
		panelConnexion.add(labelConnexion);
		
		JLabel labelEmailConnexion = new JLabel("E-Mail * :");
		labelEmailConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailConnexion.setLabelFor(textEmailC);
		labelEmailConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailConnexion.setBounds(58, 100, 130, 30);
		panelConnexion.add(labelEmailConnexion);
		
		textEmailC = new JTextField();
		textEmailC.setBackground(new Color(250, 243, 230));
		textEmailC.setBounds(200, 100, 200, 30);
		panelConnexion.add(textEmailC);
		textEmailC.setColumns(10);
		
		JLabel labelMdpConnexion = new JLabel("Mot de passe * :");
		labelMdpConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpConnexion.setBounds(58, 160, 130, 30);
		panelConnexion.add(labelMdpConnexion);
		
		textMdpC = new JPasswordField();
		textMdpC.setBackground(new Color(250, 243, 230));
		labelMdpConnexion.setLabelFor(textMdpC);
		textMdpC.setColumns(10);
		textMdpC.setBounds(200, 160, 200, 30);
		panelConnexion.add(textMdpC);
		
		JLabel labelContrainteC = new JLabel("* champs obligatoires !");
		labelContrainteC.setHorizontalAlignment(SwingConstants.RIGHT);
		labelContrainteC.setBounds(200, 200, 200, 14);
		panelConnexion.add(labelContrainteC);
		
		// ### BTN CONNEXION
		JButton btnConnexion = new JButton("Je me connecte");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verif regex mail
				if (userDao.emailValidator(textEmailC.getText())) {
					//capture des champs text dans la methode UserDAO connexion
					userDao.connexion(textEmailC.getText(), String.valueOf(textMdpC.getPassword()));
					//message de connexion et redirection
					if(UserDAO.currentUser != null) {
						JOptionPane.showMessageDialog(null,"Vous êtes connecté !","CONNEXION", JOptionPane.INFORMATION_MESSAGE);
						Main.frame.getContentPane().removeAll();
						Main.frame.getContentPane().add(new Accueil());
						Main.frame.getContentPane().repaint();
						Main.frame.getContentPane().revalidate();
					}else {
						JOptionPane.showMessageDialog(null,"Vérifiez vos informations !\nECHEC de connexion !","CONNEXION", JOptionPane.ERROR_MESSAGE);
					}
				}else {
				JOptionPane.showMessageDialog(null,  "Le format de votre e-mail n'est pas correct !\nMerci de le modifier.", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnConnexion.setBackground(new Color(255, 255, 255));
		btnConnexion.setForeground(new Color(199, 152, 50));
		btnConnexion.setBounds(250, 230, 150, 30);
		panelConnexion.add(btnConnexion);
		
		//##########################
		//### INSCRIPTION ###
		//##########################
		JPanel panelInscription = new JPanel();
		panelInscription.setBackground(new Color(240, 227, 198));
		panelInscription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelInscription.setBounds(505, 10, 485, 530);
		add(panelInscription);
		panelInscription.setLayout(null);
		
		JLabel labelInscription = new JLabel("Inscription");
		labelInscription.setFont(new Font("Noto Serif", Font.BOLD, 16));
		labelInscription.setBackground(Color.GRAY);
		labelInscription.setHorizontalAlignment(SwingConstants.CENTER);
		labelInscription.setBounds(0, 0, 485, 50);
		panelInscription.add(labelInscription);
		
		JLabel labelNomInscription = new JLabel("Nom * :");
		labelNomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomInscription.setBounds(50, 100, 150, 30);
		panelInscription.add(labelNomInscription);
		
		textNomI = new JTextField();
		textNomI.setBackground(new Color(250, 243, 230));
		labelNomInscription.setLabelFor(textNomI);
		textNomI.setColumns(10);
		textNomI.setBounds(222, 100, 200, 30);
		panelInscription.add(textNomI);
		
		JLabel labelPrenomInscription = new JLabel("Prenom * :");
		labelPrenomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelPrenomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenomInscription.setBounds(50, 161, 150, 30);
		panelInscription.add(labelPrenomInscription);
		
		textPrenomI = new JTextField();
		textPrenomI.setBackground(new Color(250, 243, 230));
		labelPrenomInscription.setLabelFor(textPrenomI);
		textPrenomI.setColumns(10);
		textPrenomI.setBounds(222, 161, 200, 30);
		panelInscription.add(textPrenomI);
		
		JLabel labelEmailInscription = new JLabel("E-mail * :");
		labelEmailInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailInscription.setBounds(50, 220, 150, 30);
		panelInscription.add(labelEmailInscription);
		
		textEmailI = new JTextField();
		textEmailI.setBackground(new Color(250, 243, 230));
		labelEmailInscription.setLabelFor(textEmailI);
		textEmailI.setColumns(10);
		textEmailI.setBounds(222, 222, 200, 30);
		panelInscription.add(textEmailI);
		
		JLabel labelContrainte = new JLabel("* champs obligatoires !");
		labelContrainte.setHorizontalAlignment(SwingConstants.RIGHT);
		labelContrainte.setBounds(222, 386, 200, 14);
		panelInscription.add(labelContrainte);
		
		/* ### EMAIL ICONE INFO */
		JLabel labelIconeEmail = new JLabel(new ImageIcon(new ImageIcon("src/resources/images/logos/info.png").getImage()));
		labelIconeEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Format e-mail obligatoire :\nExemple @ domaine . fr","CONTRAINTE", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		JLabel labelMdpInscription = new JLabel("Mot de passe * :");
		labelMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpInscription.setBounds(50, 284, 150, 30);
		panelInscription.add(labelMdpInscription);
		
		textMdpI = new JPasswordField();
		textMdpI.setBackground(new Color(250, 243, 230));
		labelMdpInscription.setLabelFor(textMdpI);
		textMdpI.setColumns(10);
		textMdpI.setBounds(222, 284, 200, 30);
		panelInscription.add(textMdpI);
		
		labelIconeEmail.setBounds(432, 222, 31, 31);
		panelInscription.add(labelIconeEmail);
		
		/* ### PASSWORD ICONE INFO */
		JLabel labelIconeMdp = new JLabel(new ImageIcon(new ImageIcon("src/resources/images/logos/info.png").getImage()));
		labelIconeMdp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Format mot de passe oligatoire :\n8 caractères minimum\ndont :\n1 majuscule\n1 minuscule\n1 chiffre\n1 caractère spécial","CONTRAINTE", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		labelIconeMdp.setBounds(432, 284, 31, 31);
		panelInscription.add(labelIconeMdp);
		
		JLabel labelConfMdpInscription = new JLabel("Confirmation (mdp) * :");
		labelConfMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelConfMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelConfMdpInscription.setBounds(25, 345, 175, 30);
		panelInscription.add(labelConfMdpInscription);
		
		textConfMdpI = new JPasswordField();
		textConfMdpI.setBackground(new Color(250, 243, 230));
		labelConfMdpInscription.setLabelFor(textConfMdpI);
		textConfMdpI.setColumns(10);
		textConfMdpI.setBounds(222, 345, 200, 30);
		panelInscription.add(textConfMdpI);
		
		/* ### BTN CONNEXION ### */
		JButton btnInscription = new JButton("Je m'inscris");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//instance d'utilisateur
				User userI = new User(textNomI.getText(), textPrenomI.getText(), textEmailI.getText(), String.valueOf(textMdpI.getPassword()));
				//vérif si aucun champs vide
				if(textNomI.getText().isEmpty() || textPrenomI.getText().isEmpty() || textEmailI.getText().isEmpty() || String.valueOf(textMdpI.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Merci de remplir les champs vides.","INSCRIPTION", JOptionPane.WARNING_MESSAGE);
				}else {
					//verif regex mail
					if (userDao.emailValidator(textEmailI.getText())) {
						//verif si mail existe déjà bdd
						if (userDao.isExist(textEmailI.getText())) {
							JOptionPane.showMessageDialog(null, "Cet e-mail existe déjà !\nVeuillez en choisir un nouveau.","INSCRIPTION", JOptionPane.WARNING_MESSAGE);
						} else {
							//verif regex mdp
							if (userDao.passValidator(String.valueOf(textMdpI.getPassword()))) {
								//verif mdp confirmation
								if (String.valueOf(textMdpI.getPassword()).equals(String.valueOf(textConfMdpI.getPassword()))) {
									//si tous OK insert dans la bdd
									if (userDao.create(userI)) {
										//reprise des infos pour connexion direct en page d'accueil
										userDao.connexion(textEmailI.getText(), String.valueOf(textMdpI.getPassword()));
										JOptionPane.showMessageDialog(null, "Votre compte a bien été créé.","INSCRIPTION", JOptionPane.PLAIN_MESSAGE);
										//redirection
										Main.frame.getContentPane().removeAll();
										Main.frame.getContentPane().add(new Accueil());
										Main.frame.getContentPane().repaint();
										Main.frame.getContentPane().revalidate();
									} else {
										JOptionPane.showMessageDialog(null, "Votre compte n'a pas pu être créé !\nVerifiez vos informations.","INSCRIPTION", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "ATTENTION, vos mots de passe ne sont pas identiques !", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Le format de votre mot de passe n'est pas correct !\nMerci de le modifier.", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null,  "Le format de votre e-mail n'est pas correct !\nMerci de le modifier.", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnInscription.setBackground(new Color(255, 255, 255));
		btnInscription.setForeground(new Color(199, 152, 50));
		btnInscription.setBounds(272, 425, 150, 30);
		panelInscription.add(btnInscription);
	}
}
