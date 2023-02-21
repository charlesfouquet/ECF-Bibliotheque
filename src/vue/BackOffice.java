package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controler.BackOfficeDAO;

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
	private JTextField prenom;
	private JTextField ISBNCm;
	private JTextField titreCm;
	private JTextField resumeCm;
	private JTextField couvCm;
	private JTable tableExemplaire;

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
		labelBackOffice.setFont(new Font("Noto serif", Font.PLAIN, 18));
		labelBackOffice.setForeground(new Color(255, 255, 255));
		panelTitre.add(labelBackOffice);
		
		/*######################*/
		/* ### PANEL ETAPE 1 ### */
		/*######################*/
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 41, 485, 50);
		add(panel1);
		panel1.setLayout(null);
		
		JLabel titreLabelZone1 = new JLabel("<html><b style=\"color:red\">1 -</b> Création ou modification ?</html>");
		titreLabelZone1.setFont(new Font("Noto serif", Font.PLAIN, 16));
		titreLabelZone1.setBounds(10, 15, 215, 20);
		panel1.add(titreLabelZone1);

		JComboBox comboBox1 = new JComboBox();
		comboBox1.setBounds(224, 11, 251, 28);
		panel1.add(comboBox1);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Je créer un nouveau livre !", "ISBN N° 1", "ISBN N° 2", "ISBN N° 3", "ISBN N° 4"}));
		
		/*######################*/
		/* ### PANEL ETAPE 2 ### */
		/*######################*/
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(250, 243, 230));
		panel2.setBounds(10, 100, 485, 223);
		add(panel2);
		panel2.setLayout(null);
		
		JLabel titreLabelZone2 = new JLabel("<html><b style=\"color:red\">2 -</b> Création des éléments :</html>");
		titreLabelZone2.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		titreLabelZone2.setHorizontalAlignment(SwingConstants.LEFT);
		titreLabelZone2.setBounds(10, 11, 200, 30);
		panel2.add(titreLabelZone2);
		
		JLabel auteurs= new JLabel("<html><b>Auteur :</b><br> <i>nom, prénom</i></html>");
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
				//TODO INSERT INTO avec méthode générique
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
		
		nomSerie = new JTextField();
		nomSerie.setBounds(103, 132, 285, 30);
		panel2.add(nomSerie);
		nomSerie.setColumns(10);

		JButton btnSerie = new JButton("Ajouter");
		btnSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO INSERT INTO avec méthode générique
			}
		});
		btnSerie.setBackground(new Color(90, 205, 25));
		btnSerie.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnSerie.setBounds(398, 136, 77, 23);
		panel2.add(btnSerie);
		
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
				//TODO INSERT INTO avec méthode générique
			}
		});
		btnEditeur.setBackground(new Color(90, 205, 25));
		btnEditeur.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnEditeur.setBounds(398, 176, 77, 23);
		panel2.add(btnEditeur);
		
		/*######################*/
		/* ### PANEL ETAPE 3 ### */
		/*######################*/
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(250, 243, 230));
		panel3.setBounds(10, 334, 485, 206);
		add(panel3);
		panel3.setLayout(null);
		
		JLabel titreabelZone3 = new JLabel("<html><b style=\"color:red\">3 -</b> Gestion des exemplaires :</html>");
		titreabelZone3.setHorizontalAlignment(SwingConstants.LEFT);
		titreabelZone3.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		titreabelZone3.setBounds(10, 12, 224, 30);
		panel3.add(titreabelZone3);
		
		JComboBox comboBoxExemplaire = new JComboBox();
		comboBoxExemplaire.setModel(new DefaultComboBoxModel(new String[] {"Sélectionner un ISBN"}));
		comboBoxExemplaire.setBounds(10, 52, 395, 30);
		panel3.add(comboBoxExemplaire);

		JButton btnPlus1 = new JButton("+ 1");
		btnPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO ajout d'un exemplaire selon ISBN dans le comboBox
			}
		});
		btnPlus1.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnPlus1.setBackground(new Color(90, 205, 25));
		btnPlus1.setBounds(415, 52, 60, 30);
		panel3.add(btnPlus1);
		
		
		JScrollPane scrollPaneExemplaire = new JScrollPane();
		scrollPaneExemplaire.setBounds(10, 93, 465, 102);
		panel3.add(scrollPaneExemplaire);
		
		tableExemplaire = new JTable();
		tableExemplaire.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"id exemplaire", "ISBN", "Titre"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6218395721389455182L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableExemplaire.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableExemplaire.getColumnModel().getColumn(1).setPreferredWidth(60);
		scrollPaneExemplaire.setViewportView(tableExemplaire);
		
		/*######################*/
		/* ### PANEL ETAPE 4 ### */
		/*######################*/
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(250, 243, 230));
		panel4.setLayout(null);
		panel4.setBounds(505, 41, 485, 499);
		add(panel4);
		
		JLabel titreLabelZone4 = new JLabel("<html><b style=\"color:red\">4 -</b> Création ou Modification d'un livre :");
		titreLabelZone4.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		titreLabelZone4.setHorizontalAlignment(SwingConstants.LEFT);
		titreLabelZone4.setBounds(10, 11, 275, 30);
		panel4.add(titreLabelZone4);
		
		JLabel ISBNLivre = new JLabel("ISBN : ");
		ISBNLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		ISBNLivre.setBounds(20, 52, 115, 30);
		panel4.add(ISBNLivre);
		
		ISBNCm = new JTextField();
		ISBNCm.setColumns(10);
		ISBNCm.setBounds(145, 52, 319, 30);
		panel4.add(ISBNCm);
		
		JLabel titreLivre = new JLabel("Titre :");
		titreLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		titreLivre.setBounds(20, 93, 115, 30);
		panel4.add(titreLivre);
		
		titreCm = new JTextField();
		titreCm.setColumns(10);
		titreCm.setBounds(145, 93, 319, 30);
		panel4.add(titreCm);
		
		JLabel resumeLivre = new JLabel("Résumé :");
		resumeLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		resumeLivre.setBounds(20, 134, 115, 30);
		panel4.add(resumeLivre);

		resumeCm = new JTextField();
		resumeCm.setColumns(10);
		resumeCm.setBounds(145, 134, 319, 30);
		panel4.add(resumeCm);
		
		JLabel datePubliLivre = new JLabel("Datede publication : ");
		datePubliLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		datePubliLivre.setBounds(20, 175, 115, 30);
		panel4.add(datePubliLivre);

		JSpinner dateCm = new JSpinner();
		dateCm.setModel(new SpinnerDateModel(new Date(-2208992400000L), new Date(-2208992400000L), new Date(4133890800000L), Calendar.DAY_OF_WEEK_IN_MONTH));
		dateCm.setBounds(145, 175, 319, 30);
		panel4.add(dateCm);
		
		JLabel nbrPagesLivre = new JLabel("Nbr de pages :");
		nbrPagesLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		nbrPagesLivre.setBounds(20, 216, 115, 30);
		panel4.add(nbrPagesLivre);
		
		JSpinner nbPageCm = new JSpinner();
		nbPageCm.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		nbPageCm.setBounds(145, 216, 319, 26);
		panel4.add(nbPageCm);
		
		JLabel couvLivre = new JLabel("Couverture :");
		couvLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		couvLivre.setBounds(20, 257, 115, 30);
		panel4.add(couvLivre);

		couvCm = new JTextField();
		couvCm.setColumns(10);
		couvCm.setBounds(145, 257, 319, 30);
		panel4.add(couvCm);
		
		JLabel auteurLivre = new JLabel("Auteur :");
		auteurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		auteurLivre.setBounds(20, 298, 59, 30);
		panel4.add(auteurLivre);

		JComboBox auteurCm = new JComboBox();
		auteurCm.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un auteur", "auteur 1", "auteur 2"}));
		auteurCm.setBounds(89, 298, 375, 30);
		panel4.add(auteurCm);
		
		JLabel genreLivre = new JLabel("Genre :");
		genreLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		genreLivre.setBounds(20, 339, 59, 30);
		panel4.add(genreLivre);
		
		JComboBox genreCm = new JComboBox();
		genreCm.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un thème", "thème 1", "thème 2"}));
		genreCm.setBounds(89, 339, 375, 30);
		panel4.add(genreCm);
		
		JLabel serieLivre = new JLabel("Serie :");
		serieLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		serieLivre.setBounds(20, 380, 59, 30);
		panel4.add(serieLivre);

		JComboBox serieCm = new JComboBox();
		serieCm.setModel(new DefaultComboBoxModel(new String[] {"Choisissez une serie", "null (Livre ne faisant as partie d'une série)", "serie 1", "serie 2"}));
		serieCm.setBounds(89, 380, 214, 30);
		panel4.add(serieCm);
		
		JLabel labelVolumeCm = new JLabel("+ son Volume N° :");
		labelVolumeCm.setHorizontalAlignment(SwingConstants.RIGHT);
		labelVolumeCm.setBounds(313, 380, 87, 30);
		panel4.add(labelVolumeCm);
		
		JSpinner volNumCm = new JSpinner();
		volNumCm.setBounds(410, 380, 54, 30);
		panel4.add(volNumCm);
		
		JLabel editeurLivre = new JLabel("Editeur :");
		editeurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		editeurLivre.setBounds(20, 421, 59, 30);
		panel4.add(editeurLivre);
		
		JComboBox editeurCm = new JComboBox();
		editeurCm.setModel(new DefaultComboBoxModel(new String[] {"Choisissez un éditeur", "editeur 1", "editeur 2"}));
		editeurCm.setBounds(89, 421, 375, 30);
		panel4.add(editeurCm);
		
		JButton btnValideLIvre = new JButton("Je valide mon livre !");
		btnValideLIvre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnValideLIvre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		btnValideLIvre.setBackground(new Color(90, 205, 25));
		btnValideLIvre.setBounds(314, 462, 150, 30);
		panel4.add(btnValideLIvre);
		
	}
}
