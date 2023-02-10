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
		
		JLabel labelMdpConnexion = new JLabel("Mot de passe :");
		labelMdpConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpConnexion.setBounds(91, 122, 100, 30);
		panelConnexion.add(labelMdpConnexion);
		
		textEmailConnexion = new JTextField();
		textEmailConnexion.setBackground(new Color(250, 243, 230));
		textEmailConnexion.setBounds(201, 61, 200, 30);
		panelConnexion.add(textEmailConnexion);
		textEmailConnexion.setColumns(10);
		
		JLabel labelEmailConnexion = new JLabel("E-Mail :");
		labelEmailConnexion.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailConnexion.setLabelFor(textEmailConnexion);
		labelEmailConnexion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailConnexion.setBounds(91, 61, 100, 30);
		panelConnexion.add(labelEmailConnexion);
		
		textMdpConnexion = new JTextField();
		textMdpConnexion.setBackground(new Color(250, 243, 230));
		labelMdpConnexion.setLabelFor(textMdpConnexion);
		textMdpConnexion.setColumns(10);
		textMdpConnexion.setBounds(201, 122, 200, 30);
		panelConnexion.add(textMdpConnexion);
		
		// ### BTN CONNEXION
		JButton btnConnexion = new JButton("je me connecte");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConnexion.setBounds(251, 187, 150, 30);
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
		
		JLabel labelNomInscription = new JLabel("Nom :");
		labelNomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomInscription.setBounds(50, 100, 150, 30);
		panelInscription.add(labelNomInscription);
		
		textNomInscription = new JTextField();
		textNomInscription.setBackground(new Color(250, 243, 230));
		labelNomInscription.setLabelFor(textNomInscription);
		textNomInscription.setColumns(10);
		textNomInscription.setBounds(222, 100, 200, 30);
		panelInscription.add(textNomInscription);
		
		JLabel labelPrenomInscription = new JLabel("Prenom :");
		labelPrenomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelPrenomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenomInscription.setBounds(50, 161, 150, 30);
		panelInscription.add(labelPrenomInscription);
		
		textPrenomInscription = new JTextField();
		textPrenomInscription.setBackground(new Color(250, 243, 230));
		labelPrenomInscription.setLabelFor(textPrenomInscription);
		textPrenomInscription.setColumns(10);
		textPrenomInscription.setBounds(222, 161, 200, 30);
		panelInscription.add(textPrenomInscription);
		
		JLabel labelEmailInscription = new JLabel("E-mail");
		labelEmailInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailInscription.setBounds(50, 222, 150, 30);
		panelInscription.add(labelEmailInscription);
		
		textEmailInsription = new JTextField();
		textEmailInsription.setBackground(new Color(250, 243, 230));
		labelEmailInscription.setLabelFor(textEmailInsription);
		textEmailInsription.setColumns(10);
		textEmailInsription.setBounds(222, 222, 200, 30);
		panelInscription.add(textEmailInsription);
		
		JLabel labelMdpInscription = new JLabel("Mot de pase :");
		labelMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMdpInscription.setBounds(50, 284, 150, 30);
		panelInscription.add(labelMdpInscription);
		
		textMdpInscription = new JTextField();
		textMdpInscription.setBackground(new Color(250, 243, 230));
		labelMdpInscription.setLabelFor(textMdpInscription);
		textMdpInscription.setColumns(10);
		textMdpInscription.setBounds(222, 284, 200, 30);
		panelInscription.add(textMdpInscription);
		
		JLabel labelConfMdpInscription = new JLabel("Confirmation (mdp) :");
		labelConfMdpInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelConfMdpInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelConfMdpInscription.setBounds(50, 345, 150, 30);
		panelInscription.add(labelConfMdpInscription);
		
		textConfMdpInscription = new JTextField();
		textConfMdpInscription.setBackground(new Color(250, 243, 230));
		labelConfMdpInscription.setLabelFor(textConfMdpInscription);
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
