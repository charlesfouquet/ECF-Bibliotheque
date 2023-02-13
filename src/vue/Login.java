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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import controler.UserDAO;
import model.User;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6571947288755605768L;
	private JTextField textEmailC;
	private JTextField textMdpC;
	private JTextField textNomI;
	private JTextField textPrenomI;
	private JTextField textEmailI;
	private JTextField textMdpI;
	private JTextField textConfMdpI;

	//instance pour utilisation méthode
	UserDAO userDao = new UserDAO();
	
	/**
	 * Create the panel.
	 */
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
		
		JLabel labelMdpConnexion = new JLabel("Mot de passe * :");
		labelMdpConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpConnexion.setBounds(59, 122, 130, 30);
		panelConnexion.add(labelMdpConnexion);
		
		textEmailC = new JTextField();
		textEmailC.setBackground(new Color(250, 243, 230));
		textEmailC.setBounds(201, 61, 200, 30);
		panelConnexion.add(textEmailC);
		textEmailC.setColumns(10);
		
		JLabel labelEmailConnexion = new JLabel("E-Mail * :");
		labelEmailConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailConnexion.setLabelFor(textEmailC);
		labelEmailConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailConnexion.setBounds(59, 61, 130, 30);
		panelConnexion.add(labelEmailConnexion);
		
		textMdpC = new JTextField();
		textMdpC.setBackground(new Color(250, 243, 230));
		labelMdpConnexion.setLabelFor(textMdpC);
		textMdpC.setColumns(10);
		textMdpC.setBounds(201, 122, 200, 30);
		panelConnexion.add(textMdpC);
		
		// ### BTN CONNEXION
		JButton btnConnexion = new JButton("je me connecte");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConnexion.setBounds(251, 194, 150, 30);
		panelConnexion.add(btnConnexion);
		
		JLabel labelContrainteC = new JLabel("* champs obligatoire !");
		labelContrainteC.setHorizontalAlignment(SwingConstants.RIGHT);
		labelContrainteC.setBounds(201, 162, 200, 14);
		panelConnexion.add(labelContrainteC);
		
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
		
		JLabel labelMdpInscription = new JLabel("Mot de pase * :");
		labelMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpInscription.setBounds(50, 284, 150, 30);
		panelInscription.add(labelMdpInscription);
		
		textMdpI = new JTextField();
		textMdpI.setBackground(new Color(250, 243, 230));
		labelMdpInscription.setLabelFor(textMdpI);
		textMdpI.setColumns(10);
		textMdpI.setBounds(222, 284, 200, 30);
		panelInscription.add(textMdpI);
		
		JLabel labelConfMdpInscription = new JLabel("Confirmation (mdp) * :");
		labelConfMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelConfMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelConfMdpInscription.setBounds(50, 345, 150, 30);
		panelInscription.add(labelConfMdpInscription);
		
		textConfMdpI = new JTextField();
		textConfMdpI.setBackground(new Color(250, 243, 230));
		labelConfMdpInscription.setLabelFor(textConfMdpI);
		textConfMdpI.setColumns(10);
		textConfMdpI.setBounds(222, 345, 200, 30);
		panelInscription.add(textConfMdpI);
		
		// ### BTN CONNEXION
		JButton btnInscription = new JButton("Je m'inscris");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//instance d'utilisateur
				User user = new User(textNomI.getText(), textPrenomI.getText(), textEmailI.getText(), textMdpI.getText() );
				//verif regex
				if (userDao.emailValidator(textEmailI.getText())) {
					System.out.println(userDao.emailValidator(textEmailI.getText()));
					//verif si mail existe déjà avec méthodes créé dans UserDAO
					if (userDao.isExist(textEmailI.getText())) {
						JOptionPane.showMessageDialog(null, "Cet e-mail existe déjà !\n Veuillez en choisir un nouveaux.","INSCRIPTION", JOptionPane.WARNING_MESSAGE);
					} else {
						if (userDao.passValidator(textMdpI.getText())) {
							if (!textMdpI.getText().equals(textConfMdpI.getText())) {
								JOptionPane.showMessageDialog(null, "ATTENTION, vos mots de passe ne sont pas identique !", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
							} else {
								//sinon insert dans la bdd
								if (userDao.create(user)) {
									JOptionPane.showMessageDialog(null, "Votre compte a bien été créé.","INSCRIPTION", JOptionPane.PLAIN_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, "Votre compte n'a pas pu être créé !\n Verifier vos informations.","INSCRIPTION", JOptionPane.ERROR_MESSAGE);
								}
							}
						} else {
							System.out.println(textMdpI.getText());
							JOptionPane.showMessageDialog(null, "Le format de votre mot de passe n'est pas correct !\n Merci de le modifié.", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,  "Le format de votre e-mail n'est pas correct !\n Merci de le modifié.", "INSCRIPTION", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInscription.setBounds(272, 425, 150, 30);
		panelInscription.add(btnInscription);
		
		JLabel labelContrainte = new JLabel("* champs obligatoire !");
		labelContrainte.setHorizontalAlignment(SwingConstants.RIGHT);
		labelContrainte.setBounds(222, 386, 200, 14);
		panelInscription.add(labelContrainte);
		
		JLabel labelIconeEmail = new JLabel("");
		labelIconeEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Format mail obligatoire :\n exemple@domaine.fr","CONTRAINTE", JOptionPane.WARNING_MESSAGE);
			}
		});
		labelIconeEmail.setIcon(new ImageIcon(Login.class.getResource("/images/info.png")));
		labelIconeEmail.setBounds(432, 222, 31, 31);
		panelInscription.add(labelIconeEmail);
		
		JLabel labelIconeMdp = new JLabel("");
		labelIconeMdp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Format mot de passe oligatoire :\n 8 caractères minimum.\n dont :\n 1 majuscule.\n 1 minuscule.\n 1 chiffre.\n 1 caractère spécial.","CONTRAINTE", JOptionPane.WARNING_MESSAGE);
			}
		});
		labelIconeMdp.setIcon(new ImageIcon(Login.class.getResource("/images/info.png")));
		labelIconeMdp.setBounds(432, 284, 31, 31);
		panelInscription.add(labelIconeMdp);
	}
}
