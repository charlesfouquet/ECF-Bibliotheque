package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controler.BackOfficeDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BackOffice extends JPanel {
	BackOfficeDAO backOffice = new BackOfficeDAO();

	/**
	 * 
	 */
	private static final long serialVersionUID = 8865893874374732445L;
	private JTextField nom;
	private JTextField theme;
	private JTextField nomSerie;
	private JTextField nomSocial;
	private JTextField ISBN_livre;
	private JTextField prenom;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public BackOffice() {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		/*######################*/
		/* ### PANEL TITRE ### */
		/*######################*/
		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(new Color(211, 162, 95));
		panelTitre.setBounds(0, 0, 1000, 30);
		add(panelTitre);
		
		JLabel labelBackOffice = new JLabel("BACK OFFICE");
		labelBackOffice.setFont(new Font("Dialog", Font.PLAIN, 18));
		labelBackOffice.setForeground(new Color(255, 255, 255));
		panelTitre.add(labelBackOffice);
		
		/*######################*/
		/* ### PANEL ETAPE 1 ### */
		/*######################*/
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 41, 485, 50);
		add(panel1);
		panel1.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(224, 11, 251, 28);
		panel1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Je créer un nouveau livre !", "ISBN N° 1", "ISBN N° 2", "ISBN N° 3", "ISBN N° 4"}));
		
		JLabel labelZone1 = new JLabel("1- Création ou modification ?");
		labelZone1.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelZone1.setBounds(10, 15, 215, 20);
		panel1.add(labelZone1);
		
		/*######################*/
		/* ### PANEL ETAPE 2 ### */
		/*######################*/
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(250, 243, 230));
		panel2.setBounds(10, 100, 485, 440);
		add(panel2);
		panel2.setLayout(null);
		
		JLabel labelZone2 = new JLabel("2 - Création des éléments :");
		labelZone2.setFont(new Font("Dialog", Font.PLAIN, 16));
		labelZone2.setHorizontalAlignment(SwingConstants.LEFT);
		labelZone2.setBounds(10, 11, 454, 30);
		panel2.add(labelZone2);
		
		JLabel auteurs= new JLabel("<html><b>Auteur :</b><br> <i>nom, prénom, bio</i></html>");
		auteurs.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		auteurs.setBounds(10, 52, 95, 30);
		panel2.add(auteurs);
		
		nom = new JTextField();
		nom.setBounds(103, 52, 140, 30);
		panel2.add(nom);
		
		prenom = new JTextField();
		prenom.setBounds(248, 52, 140, 30);
		panel2.add(prenom);
			
		JButton btnAuteur = new JButton("Ajouter");
		btnAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String [] values = {nom.getText(), prenom.getText()};
//				for (String string : values) {
//					System.out.println(string);
//				}
//				if(!nom.getText().isEmpty() && !prenom.getText().isEmpty()) {
//					if(backOffice.create("auteurs", "nom, prenom", values, "nom NOT IN (SELECT nom From auteur.nom) AND prenom NOT IN (SELECT prenom FROM auteur.prenom)")) {
//						JOptionPane.showMessageDialog(null,"Auteur correctement créer", "AUTEUR", JOptionPane.INFORMATION_MESSAGE);
//					}else {
//						JOptionPane.showMessageDialog(null,"Erreur : Auteur non créé", "AUTEUR", JOptionPane.ERROR_MESSAGE);
//					}
//				}else {
//					JOptionPane.showMessageDialog(null,"Merci de remplir les champs auteur","AUTEUR", JOptionPane.WARNING_MESSAGE);
//				}
			}
		});
		btnAuteur.setBackground(new Color(90, 205, 25));
		btnAuteur.setBounds(398, 56, 77, 23);
		panel2.add(btnAuteur);
		btnAuteur.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		
		JLabel genres = new JLabel("<html><b>Genre :</b><br> <i>thème</i></html>");
		genres.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		genres.setBounds(10, 92, 83, 30);
		panel2.add(genres);
		
		theme = new JTextField();
		theme.setBounds(103, 92, 285, 30);
		panel2.add(theme);
		theme.setColumns(10);
					
		JButton btnGenre = new JButton("Ajouter");
		btnGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGenre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnGenre.setBackground(new Color(90, 205, 25));
		btnGenre.setBounds(398, 96, 77, 23);
		panel2.add(btnGenre);
		btnGenre.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		
		JLabel series = new JLabel("<html><b>Serie :</b><br> <i>nomSerie</i></html>");
		series.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		series.setBounds(10, 132, 83, 30);
		panel2.add(series);
		
		JButton btnSerie = new JButton("Ajouter");
		btnSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSerie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnSerie.setBackground(new Color(90, 205, 25));
		btnSerie.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnSerie.setBounds(398, 136, 77, 23);
		panel2.add(btnSerie);
		
		nomSerie = new JTextField();
		nomSerie.setBounds(103, 132, 285, 30);
		panel2.add(nomSerie);
		nomSerie.setColumns(10);
							
		JLabel editeurs = new JLabel("<html><b>Editeur :</b><br> <i>nomSocial</i></html>");
		editeurs.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		editeurs.setBounds(10, 172, 83, 30);
		panel2.add(editeurs);
		
		nomSocial = new JTextField();
		nomSocial.setBounds(103, 172, 285, 30);
		panel2.add(nomSocial);
		nomSocial.setColumns(10);
									
		JButton btnEditeur = new JButton("Ajouter");
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditeur.setBackground(new Color(90, 205, 25));
		btnEditeur.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnEditeur.setBounds(398, 176, 77, 23);
		panel2.add(btnEditeur);
		
		JLabel exemplaires = new JLabel("<html><b>Exemplaire :</b><br> <i>ISBN_livre</i></html>");
		exemplaires.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		exemplaires.setBounds(10, 212, 83, 30);
		panel2.add(exemplaires);
		
		ISBN_livre = new JTextField();
		ISBN_livre.setBounds(103, 212, 285, 30);
		panel2.add(ISBN_livre);
		ISBN_livre.setColumns(10);
		
		JButton btnExemplaire = new JButton("Ajouter");
		btnExemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExemplaire.setBackground(new Color(90, 205, 25));
		btnExemplaire.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnExemplaire.setBounds(398, 216, 77, 23);
		panel2.add(btnExemplaire);
		
		/*######################*/
		/* ### PANEL ETAPE 3 ### */
		/*######################*/
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(250, 243, 230));
		panel3.setLayout(null);
		panel3.setBounds(505, 41, 485, 499);
		add(panel3);
		
		JLabel labelZone3 = new JLabel("3 - Création ou Modification d'un livre :");
		labelZone3.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		labelZone3.setHorizontalAlignment(SwingConstants.LEFT);
		labelZone3.setBounds(10, 11, 275, 30);
		panel3.add(labelZone3);
		
		JLabel ISBNLivre = new JLabel("ISBN : ");
		ISBNLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		ISBNLivre.setBounds(20, 52, 115, 30);
		panel3.add(ISBNLivre);
		
		JLabel titreLivre = new JLabel("Titre :");
		titreLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		titreLivre.setBounds(20, 93, 115, 30);
		panel3.add(titreLivre);
		
		JLabel resumeLivre = new JLabel("Résumé :");
		resumeLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		resumeLivre.setBounds(20, 134, 115, 30);
		panel3.add(resumeLivre);
		
		JLabel datePubliLivre = new JLabel("Datede publication : ");
		datePubliLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		datePubliLivre.setBounds(20, 175, 115, 30);
		panel3.add(datePubliLivre);
		
		JLabel nbrPagesLivre = new JLabel("Nbr de pages :");
		nbrPagesLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		nbrPagesLivre.setBounds(20, 216, 115, 30);
		panel3.add(nbrPagesLivre);
		
		JLabel couvLivre = new JLabel("Couverture :");
		couvLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		couvLivre.setBounds(20, 257, 115, 30);
		panel3.add(couvLivre);
		
		JLabel auteurLivre = new JLabel("Auteur :");
		auteurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		auteurLivre.setBounds(20, 298, 115, 30);
		panel3.add(auteurLivre);
		
		JLabel genreLivre = new JLabel("Genre :");
		genreLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		genreLivre.setBounds(20, 339, 115, 30);
		panel3.add(genreLivre);
		
		JLabel serieLivre = new JLabel("Serie :");
		serieLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		serieLivre.setBounds(20, 380, 115, 30);
		panel3.add(serieLivre);
		
		JLabel editeurLivre = new JLabel("Editeur :");
		editeurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		editeurLivre.setBounds(20, 421, 115, 30);
		panel3.add(editeurLivre);
		
		JLabel exempLivre = new JLabel("Exemplaire :");
		exempLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		exempLivre.setBounds(20, 462, 115, 30);
		panel3.add(exempLivre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(145, 52, 319, 30);
		panel3.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 93, 319, 30);
		panel3.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 134, 319, 30);
		panel3.add(textField_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(145, 257, 319, 30);
		panel3.add(textField_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un auteur", "auteur 1", "auteur 2"}));
		comboBox_1.setBounds(145, 298, 319, 30);
		panel3.add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un thème", "thème 1", "thème 2"}));
		comboBox_1_1.setBounds(145, 339, 319, 30);
		panel3.add(comboBox_1_1);
		
		JComboBox comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"Choisissez une serie", "null (Livre ne faisant as partie d'une série)", "serie 1", "serie 2\t"}));
		comboBox_1_1_1.setBounds(145, 380, 319, 30);
		panel3.add(comboBox_1_1_1);
		
		JComboBox comboBox_1_1_1_1 = new JComboBox();
		comboBox_1_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un éditeur", "editeur 1", "editeur 2"}));
		comboBox_1_1_1_1.setBounds(145, 421, 319, 30);
		panel3.add(comboBox_1_1_1_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setBounds(145, 462, 319, 26);
		panel3.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(-2208992400000L), new Date(-2208992400000L), new Date(4133890800000L), Calendar.DAY_OF_WEEK_IN_MONTH));
		spinner_1.setBounds(145, 175, 319, 30);
		panel3.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner_2.setBounds(145, 216, 319, 26);
		panel3.add(spinner_2);
		
		JButton btnValideLIvre = new JButton("Je valide mon livre !");
		btnValideLIvre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnValideLIvre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		btnValideLIvre.setBackground(new Color(90, 205, 25));
		btnValideLIvre.setBounds(314, 11, 150, 30);
		panel3.add(btnValideLIvre);
		
	}
}
