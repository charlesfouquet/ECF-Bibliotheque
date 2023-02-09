package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6571947288755605768L;
	private JTextField textEmailConnexion;
	private JTextField textMdpConnexion;
	private JTextField textNomInscription;
	private JTextField textPrenomInscription;
	private JTextField textEmailInsription;
	private JTextField textMdpInscription;
	private JTextField textConfMdpInscription;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(null);
		setBounds(0, 0, 1000, 550);
		
		//#########################
		//### GAUCHE CONNEXION N###
		//#########################
		JPanel panelConnexion = new JPanel();
		panelConnexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelConnexion.setBounds(10, 10, 485, 530);
		add(panelConnexion);
		panelConnexion.setLayout(null);
		
		JLabel labelConnexion = new JLabel("Connexion");
		labelConnexion.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelConnexion.setBackground(Color.GRAY);
		labelConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		labelConnexion.setBounds(0, 0, 485, 50);
		panelConnexion.add(labelConnexion);
		
		JLabel labelMdpConnexion = new JLabel("Mot de passe :");
		labelMdpConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpConnexion.setBounds(50, 161, 150, 30);
		panelConnexion.add(labelMdpConnexion);
		
		textEmailConnexion = new JTextField();
		textEmailConnexion.setBounds(210, 100, 200, 30);
		panelConnexion.add(textEmailConnexion);
		textEmailConnexion.setColumns(10);
		
		JLabel labelEmailConnexion_1 = new JLabel("E-Mail :");
		labelEmailConnexion_1.setLabelFor(textEmailConnexion);
		labelEmailConnexion_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailConnexion_1.setBounds(50, 100, 150, 30);
		panelConnexion.add(labelEmailConnexion_1);
		
		textMdpConnexion = new JTextField();
		labelMdpConnexion.setLabelFor(textMdpConnexion);
		textMdpConnexion.setColumns(10);
		textMdpConnexion.setBounds(210, 161, 200, 30);
		panelConnexion.add(textMdpConnexion);
		
		// ### BTN CONNEXION
		JButton btnConnexion = new JButton("je me connecte");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConnexion.setBounds(260, 226, 150, 30);
		panelConnexion.add(btnConnexion);
		
		//##########################
		//### DROITE INSCRIPTION ###
		//##########################
		JPanel panelInscription = new JPanel();
		panelInscription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelInscription.setBounds(505, 10, 485, 530);
		add(panelInscription);
		panelInscription.setLayout(null);
		
		JLabel labelInscription = new JLabel("Inscription");
		labelInscription.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelInscription.setBackground(Color.GRAY);
		labelInscription.setHorizontalAlignment(SwingConstants.CENTER);
		labelInscription.setBounds(0, 0, 485, 50);
		panelInscription.add(labelInscription);
		
		JLabel labelNomInscription = new JLabel("Nom :");
		labelNomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomInscription.setBounds(50, 100, 150, 30);
		panelInscription.add(labelNomInscription);
		
		textNomInscription = new JTextField();
		textNomInscription.setColumns(10);
		textNomInscription.setBounds(222, 100, 200, 30);
		panelInscription.add(textNomInscription);
		
		JLabel labelPrenomInscription = new JLabel("Prenom :");
		labelPrenomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenomInscription.setBounds(50, 161, 150, 30);
		panelInscription.add(labelPrenomInscription);
		
		textPrenomInscription = new JTextField();
		textPrenomInscription.setColumns(10);
		textPrenomInscription.setBounds(222, 161, 200, 30);
		panelInscription.add(textPrenomInscription);
		
		JLabel labelEmailInscription = new JLabel("E-mail");
		labelEmailInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailInscription.setBounds(50, 222, 150, 30);
		panelInscription.add(labelEmailInscription);
		
		textEmailInsription = new JTextField();
		textEmailInsription.setColumns(10);
		textEmailInsription.setBounds(222, 222, 200, 30);
		panelInscription.add(textEmailInsription);
		
		JLabel labelMdpInscription = new JLabel("Mot de pase :");
		labelMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpInscription.setBounds(50, 284, 150, 30);
		panelInscription.add(labelMdpInscription);
		
		textMdpInscription = new JTextField();
		textMdpInscription.setColumns(10);
		textMdpInscription.setBounds(222, 284, 200, 30);
		panelInscription.add(textMdpInscription);
		
		JLabel labelConfMdpInscription = new JLabel("Confirmation (mdp) :");
		labelConfMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelConfMdpInscription.setBounds(50, 345, 150, 30);
		panelInscription.add(labelConfMdpInscription);
		
		textConfMdpInscription = new JTextField();
		textConfMdpInscription.setColumns(10);
		textConfMdpInscription.setBounds(222, 345, 200, 30);
		panelInscription.add(textConfMdpInscription);
		
		// ### BTN CONNEXION
		JButton btnInscription = new JButton("Je m'inscris");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnInscription.setBounds(272, 410, 150, 30);
		panelInscription.add(btnInscription);
		
	}
}
