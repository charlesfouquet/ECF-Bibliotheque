package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Compte extends JPanel {
	private static final long serialVersionUID = 6556442715550137983L;
	
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textEmail;
	private JTextField textAncienMdp;
	private JTextField textNvMdp;
	private JTextField textConfMdp;
	private JTextField textAdr;
	private JTextField textCp;
	private JTextField textVille;
	private JTextField textTel;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Compte() {
		setBackground(new Color(240, 227, 198));
		setLayout(null);
		setBounds(0, 0, 1000, 550);
		
		//###################
		// ### BACKOFFICE ###
		//###################
		JPanel panelBackOffice = new JPanel();
		panelBackOffice.setBounds(900, 0, 100, 30);
		panelBackOffice.setBackground(new Color(233, 215, 171));
		add(panelBackOffice);
		panelBackOffice.setLayout(null);
		
		JButton btnBackOffice = new JButton("Back-Office");
		btnBackOffice.setForeground(new Color(128, 64, 0));
		btnBackOffice.setBackground(new Color(128, 64, 0));
		btnBackOffice.setBounds(0, 0, 100, 30);
		panelBackOffice.add(btnBackOffice);
		
		
		//###################
		// ### INFORMATION MATION GENERALE ###
		//###################
		JPanel panelInfoGen = new JPanel();
		panelInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		panelInfoGen.setBackground(new Color(240, 227, 198));
		panelInfoGen.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(199, 152, 50)));
		panelInfoGen.setLayout(null);
		panelInfoGen.setBounds(10, 10, 490, 225);
		add(panelInfoGen);
	
		JLabel titreInfoGen = new JLabel(" Informations générales");
		titreInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		titreInfoGen.setIcon(new ImageIcon("src/resources/images/logos/user.png"));
		titreInfoGen.setFont(new Font("Noto Serif", Font.BOLD, 16));
		titreInfoGen.setHorizontalAlignment(SwingConstants.LEFT);
		titreInfoGen.setBounds(0, 0, 480, 35);
		panelInfoGen.add(titreInfoGen);
		
		JLabel labelNomInscription = new JLabel("Nom :");
		labelNomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelNomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomInscription.setBounds(79, 64, 75, 30);
		panelInfoGen.add(labelNomInscription);
		
		textNom = new JTextField();
		textNom.setBackground(new Color(250, 243, 230));
		labelNomInscription.setLabelFor(textNom);
		textNom.setColumns(10);
		textNom.setBounds(176, 64, 200, 30);
		panelInfoGen.add(textNom);
		
		JLabel labelPrenomInscription = new JLabel("Prenom :");
		labelPrenomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelPrenomInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelPrenomInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrenomInscription.setBounds(79, 105, 75, 30);
		panelInfoGen.add(labelPrenomInscription);
		
		textPrenom = new JTextField();
		textPrenom.setBackground(new Color(250, 243, 230));
		labelPrenomInscription.setLabelFor(textPrenom);
		textPrenom.setColumns(10);
		textPrenom.setBounds(176, 105, 200, 30);
		panelInfoGen.add(textPrenom);
		
		JLabel labelEmailInscription = new JLabel("E-mail :");
		labelEmailInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailInscription.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelEmailInscription.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmailInscription.setBounds(79, 146, 75, 30);
		panelInfoGen.add(labelEmailInscription);
		
		textEmail = new JTextField();
		textEmail.setBackground(new Color(250, 243, 230));
		labelEmailInscription.setLabelFor(textEmail);
		textEmail.setColumns(10);
		textEmail.setBounds(176, 146, 200, 30);
		panelInfoGen.add(textEmail);
		
		JButton btnInfoGen = new JButton("Validation");
		btnInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnInfoGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnInfoGen.setBounds(247, 187, 130, 30);
		panelInfoGen.add(btnInfoGen);
		
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
		lblAdresseDeDomiciliation.setFont(new Font("Noto Serif", Font.BOLD, 16));
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
		
		JLabel labelTel = new JLabel("Téléphone :");
		labelTel.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelTel.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTel.setBounds(62, 169, 100, 30);
		panelAdresseDom.add(labelTel);
		
		textTel = new JTextField();
		textTel.setBackground(new Color(250, 243, 230));
		textTel.setColumns(10);
		textTel.setBounds(184, 169, 200, 30);
		panelAdresseDom.add(textTel);
		
		JButton btnAdrDom = new JButton("Validation");
		btnAdrDom.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnAdrDom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdrDom.setBounds(254, 210, 130, 30);
		panelAdresseDom.add(btnAdrDom);
		
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
		
		textAncienMdp = new JTextField();
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
		
		textNvMdp = new JTextField();
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
		
		textConfMdp = new JTextField();
		textConfMdp.setBackground(new Color(250, 243, 230));
		labelConfirmMdp.setLabelFor(textConfMdp);
		textConfMdp.setColumns(10);
		textConfMdp.setBounds(177, 133, 200, 30);
		PanelMdp.add(textConfMdp);
		
		JButton btnModifMdp = new JButton("Validation");
		btnModifMdp.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		panelInfoGen.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnModifMdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnModifMdp.setBounds(247, 174, 130, 30);
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
		lblListeDesEmprunts.setBounds(10, 10, 480, 35);
		panelEmprunt.add(lblListeDesEmprunts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 66, 480, 188);
		panelEmprunt.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(250, 243, 230));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Référence", "Titre", "Emprunté le  :", "A rendre le :"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		//###################
		// ### SUPPRESSION DU COMPTE ###
		//###################
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 227, 198));
		panel.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(199, 152, 50)));
		panel.setBounds(10, 455, 490, 85);
		add(panel);
		panel.setLayout(null);
		
		JLabel labelSuppCompte = new JLabel("Je supprime mon compte :");
		labelSuppCompte.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelSuppCompte.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		labelSuppCompte.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSuppCompte.setBounds(10, 11, 257, 45);
		panel.add(labelSuppCompte);
		
		JButton btnDelete = new JButton("Suppression");
		btnDelete.setFont(new Font("Noto Serif", Font.PLAIN, 14));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(new Color(255, 0, 0));
				btnDelete.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(new Color(240, 145, 145));
				btnDelete.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDelete.setBackground(Color.GREEN);
				btnDelete.setForeground(new Color(0, 0, 0));
			}
		});
		btnDelete.setBounds(277, 20, 120, 30);
		panel.add(btnDelete);
		
		
		
		
		

	}
}
