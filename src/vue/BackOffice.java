package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.SqlDateModel;

import controler.BackOfficeDAO;
import controler.LivreDAO;
import model.Auteur;
import model.Editeur;
import model.Livre;

public class BackOffice extends JPanel {
	BackOfficeDAO backOfficeDAO = new BackOfficeDAO();
	LivreDAO livreDAO = new LivreDAO();

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
	private JFrame frame = new JFrame();
	private JComboBox<String> comboBoxEditeurCm = new JComboBox<String>();
	private JComboBox<String> comboBoxSerieCm = new JComboBox<String>();
	private JComboBox<String> comboBoxGenreCm = new JComboBox<String>();
	private JComboBox<String> comboBoxAuteurCm = new JComboBox<String>();
	private JComboBox<String> comboBoxPrincipale = new JComboBox<String>();
	private JSpinner nbPageCm = new JSpinner();
	private JTextField dateCm;
	private JButton datePanelBtn = new JButton("Choisir");
	private JButton bookCoverBtn = new JButton("Choisir");
	private JButton bookCoverDelete = new JButton("Retirer");
	private JSpinner volNumCm = new JSpinner();
	private JButton btnPlus1 = new JButton("Ajouter un exemplaire");
	private Livre selectedBook = null;
	private 	String bookCoverFilePath = "";
	private String bookCoversDestination = "src/resources/images/bookcovers/";

	/**
	 * Create the panel.
	 */
	public BackOffice() {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		/*######################*/
		/* ### CODE DU JDATEPICKER ### */
		/*######################*/
		
		frame.setSize(300, 250);
		SqlDateModel model = new SqlDateModel();
		model.setDate(2000, 1, 1);
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		frame.getContentPane().add(datePanel);
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		      e.getWindow().dispose();
		      java.sql.Date selectedDate = (java.sql.Date) datePanel.getModel().getValue();
		      dateCm.setText(String.valueOf(selectedDate));
		    }
		});
		
		
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
		panel1.setBounds(10, 41, 485, 78);
		add(panel1);
		panel1.setLayout(null);
		
		JLabel titreLabelZone1 = new JLabel("<html><b style=\"color:red\">1 -</b> Création ou modification ?</html>");
		titreLabelZone1.setFont(new Font("Noto serif", Font.PLAIN, 16));
		titreLabelZone1.setBounds(10, 11, 465, 24);
		panel1.add(titreLabelZone1);
		comboBoxPrincipale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPrincipale.getSelectedItem().toString() != "Créer un nouveau livre") {
					loadSelectedBook();
				} else {
					removeAll();
					add(new BackOffice());
					repaint();
					revalidate();
				}
			}
		});
		comboBoxPrincipale.setBounds(10, 39, 465, 28);
		panel1.add(comboBoxPrincipale);
		
		/*######################*/
		/* ### PANEL ETAPE 2 ### */
		/*######################*/
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(250, 243, 230));
		panel2.setBounds(10, 130, 485, 193);
		add(panel2);
		panel2.setLayout(null);
		
		JLabel titreLabelZone2 = new JLabel("<html><b style=\"color:red\">2 -</b> Création des éléments :</html>");
		titreLabelZone2.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		titreLabelZone2.setHorizontalAlignment(SwingConstants.LEFT);
		titreLabelZone2.setBounds(10, 11, 465, 30);
		panel2.add(titreLabelZone2);
		
		JLabel auteurs= new JLabel("<html><b>Auteur :</b><br> <i>nom, prénom</i></html>");
		auteurs.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		auteurs.setBounds(10, 48, 95, 30);
		panel2.add(auteurs);
		
		nom = new JTextField();
		nom.setBounds(103, 48, 140, 30);
		panel2.add(nom);
		
		prenom = new JTextField();
		prenom.setBounds(248, 48, 140, 30);
		panel2.add(prenom);
			
		JButton btnAuteur = new JButton("Ajouter");
		btnAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nom.getText().isEmpty() && !prenom.getText().isEmpty()) {
					if(backOfficeDAO.smallAddToDB("Auteur", nom.getText(), prenom.getText())) {
						JOptionPane.showMessageDialog(null,"Auteur correctement créé", "AUTEUR", JOptionPane.INFORMATION_MESSAGE);
						BackOfficeDAO.ISBNOnLoad = null;
						removeAll();
						add(new BackOffice());
						repaint();
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null,"Erreur : L'auteur existe probablement déjà", "AUTEUR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Merci de remplir les champs auteur","AUTEUR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAuteur.setBackground(new Color(90, 205, 25));
		btnAuteur.setBounds(398, 52, 77, 23);
		panel2.add(btnAuteur);
		btnAuteur.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		
		JLabel genres = new JLabel("<html><b>Genre :</b><br> <i>thème</i></html>");
		genres.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		genres.setBounds(10, 83, 83, 30);
		panel2.add(genres);
		
		theme = new JTextField();
		theme.setBounds(103, 83, 285, 30);
		panel2.add(theme);
		theme.setColumns(10);
		
		JButton btnGenre = new JButton("Ajouter");
		btnGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!theme.getText().isEmpty()) {
					if(backOfficeDAO.smallAddToDB("Genre", theme.getText(), null)) {
						JOptionPane.showMessageDialog(null,"Genre correctement créé", "GENRE", JOptionPane.INFORMATION_MESSAGE);
						BackOfficeDAO.ISBNOnLoad = null;
						removeAll();
						add(new BackOffice());
						repaint();
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null,"Erreur : Le genre existe probablement déjà", "GENRE", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Merci de remplir le champ genre","GENRE", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnGenre.setBackground(new Color(90, 205, 25));
		btnGenre.setBounds(398, 87, 77, 23);
		panel2.add(btnGenre);
		btnGenre.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		
		JLabel series = new JLabel("<html><b>Serie :</b><br> <i>nomSerie</i></html>");
		series.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		series.setBounds(10, 118, 83, 30);
		panel2.add(series);
		
		nomSerie = new JTextField();
		nomSerie.setBounds(103, 118, 285, 30);
		panel2.add(nomSerie);
		nomSerie.setColumns(10);

		JButton btnSerie = new JButton("Ajouter");
		btnSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nomSerie.getText().isEmpty()) {
					if(backOfficeDAO.smallAddToDB("Serie", nomSerie.getText(), null)) {
						JOptionPane.showMessageDialog(null,"Série correctement créée", "SERIE", JOptionPane.INFORMATION_MESSAGE);
						BackOfficeDAO.ISBNOnLoad = null;
						removeAll();
						add(new BackOffice());
						repaint();
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null,"Erreur : La série existe probablement déjà", "SERIE", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Merci de remplir le champ série","SERIE", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSerie.setBackground(new Color(90, 205, 25));
		btnSerie.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnSerie.setBounds(398, 122, 77, 23);
		panel2.add(btnSerie);
		
		JLabel editeurs = new JLabel("<html><b>Editeur :</b><br> <i>nomSocial</i></html>");
		editeurs.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		editeurs.setBounds(10, 153, 83, 30);
		panel2.add(editeurs);
		
		nomSocial = new JTextField();
		nomSocial.setBounds(103, 153, 285, 30);
		panel2.add(nomSocial);
		nomSocial.setColumns(10);
									
		JButton btnEditeur = new JButton("Ajouter");
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nomSocial.getText().isEmpty()) {
					if(backOfficeDAO.smallAddToDB("Editeur", nomSocial.getText(), null)) {
						JOptionPane.showMessageDialog(null,"Editeur correctement créé", "EDITEUR", JOptionPane.INFORMATION_MESSAGE);
						BackOfficeDAO.ISBNOnLoad = null;
						removeAll();
						add(new BackOffice());
						repaint();
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null,"Erreur : L'éditeur existe probablement déjà", "EDITEUR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Merci de remplir le champ éditeur","EDITEUR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEditeur.setBackground(new Color(90, 205, 25));
		btnEditeur.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnEditeur.setBounds(398, 157, 77, 23);
		panel2.add(btnEditeur);
		
		/*######################*/
		/* ### PANEL ETAPE 3 ### */
		/*######################*/
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(250, 243, 230));
		panel3.setBounds(10, 334, 485, 206);
		add(panel3);
		panel3.setLayout(null);
		
		JLabel titreabelZone3 = new JLabel("<html><b style=\"color:red\">3 -</b> Gestion des exemplaires :<br><i style=\"font-size:x-small;\">Pour supprimer selectionner le dans la liste ci-dessous</i></html>");
		titreabelZone3.setHorizontalAlignment(SwingConstants.LEFT);
		titreabelZone3.setFont(new Font("Noto Serif", Font.PLAIN, 16));
		titreabelZone3.setBounds(10, 12, 224, 48);
		panel3.add(titreabelZone3);

		btnPlus1.setEnabled(false);
		btnPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO ajout d'un exemplaire selon ISBN dans le comboBox
				if (btnPlus1.isEnabled()) {
					if (backOfficeDAO.smallAddToDB("Exemplaire", selectedBook.getISBN(), null)) {
						JOptionPane.showMessageDialog(null,"Exemplaire ajouté au stock", "AJOUT D'EXEMPLAIRE", JOptionPane.INFORMATION_MESSAGE);
						tableExemplaire.setModel(listeExemplaires(selectedBook.getISBN()));
						reformatTable(tableExemplaire);
					} else {
						JOptionPane.showMessageDialog(null,"Une erreur est survenue.\nVeuillez réessayer","AJOUT D'EXEMPLAIRE", JOptionPane.WARNING_MESSAGE);
					};
				}
			}
		});
		btnPlus1.setFont(new Font("Noto Serif", Font.PLAIN, 10));
		btnPlus1.setBackground(new Color(90, 205, 25));
		btnPlus1.setBounds(296, 15, 179, 30);
		panel3.add(btnPlus1);
		
		
		JScrollPane scrollPaneExemplaire = new JScrollPane();
		scrollPaneExemplaire.setBounds(10, 71, 465, 124);
		panel3.add(scrollPaneExemplaire);
		
		tableExemplaire = new JTable();
		scrollPaneExemplaire.setViewportView(tableExemplaire);
		
		tableExemplaire.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableExemplaire.getSelectedRow();
				Object[] options = {"Oui", "Non"};
				int exemplaireSupprime = JOptionPane.showOptionDialog(null, "Souhaitez-vous supprimer l'exemplaire " + (String) tableExemplaire.getValueAt(row, 0) + " ?", "Supprimer un exemplaire", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (exemplaireSupprime == 0) {
					if (backOfficeDAO.deleteExemplaire((String) tableExemplaire.getValueAt(row, 0), (String) tableExemplaire.getValueAt(row, 1))) {
						JOptionPane.showConfirmDialog(null, "L'exemplaire " + (String) tableExemplaire.getValueAt(row, 0) + " a bien été supprimé !", "Exemplaire supprimé", JOptionPane.WARNING_MESSAGE);							
					} else {
						JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Supprimer un exemplaire", JOptionPane.ERROR_MESSAGE);
					}
				}
				tableExemplaire.setModel(listeExemplaires(selectedBook.getISBN()));
				reformatTable(tableExemplaire);
			}
		});
		
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
		titreLabelZone4.setBounds(10, 11, 465, 30);
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
		
		JLabel datePubliLivre = new JLabel("Date de sortie : ");
		datePubliLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		datePubliLivre.setBounds(20, 175, 115, 30);
		panel4.add(datePubliLivre);
		
		JLabel nbrPagesLivre = new JLabel("Nbr de pages :");
		nbrPagesLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		nbrPagesLivre.setBounds(20, 216, 115, 30);
		panel4.add(nbrPagesLivre);
		
		nbPageCm.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		((JSpinner.DefaultEditor)nbPageCm.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
		nbPageCm.setBounds(145, 216, 319, 26);
		panel4.add(nbPageCm);
		
		JLabel couvLivre = new JLabel("Couverture :");
		couvLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		couvLivre.setBounds(20, 257, 115, 30);
		panel4.add(couvLivre);

		couvCm = new JTextField();
		couvCm.setEditable(false);
		couvCm.setColumns(10);
		couvCm.setBounds(145, 257, 225, 30);
		panel4.add(couvCm);
		
		JLabel auteurLivre = new JLabel("Auteur :");
		auteurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		auteurLivre.setBounds(20, 298, 59, 30);
		panel4.add(auteurLivre);

		comboBoxAuteurCm.setBounds(89, 298, 375, 30);
		panel4.add(comboBoxAuteurCm);
		
		JLabel genreLivre = new JLabel("Genre :");
		genreLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		genreLivre.setBounds(20, 339, 59, 30);
		panel4.add(genreLivre);
		
		comboBoxGenreCm.setBounds(89, 339, 375, 30);
		panel4.add(comboBoxGenreCm);
		
		JLabel serieLivre = new JLabel("Serie :");
		serieLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		serieLivre.setBounds(20, 380, 59, 30);
		panel4.add(serieLivre);

		comboBoxSerieCm.setBounds(89, 380, 266, 30);
		panel4.add(comboBoxSerieCm);
		
		JLabel labelVolumeCm = new JLabel("Tome :");
		labelVolumeCm.setHorizontalAlignment(SwingConstants.RIGHT);
		labelVolumeCm.setBounds(313, 380, 87, 30);
		panel4.add(labelVolumeCm);
		
		volNumCm.setBounds(410, 380, 54, 30);
		panel4.add(volNumCm);
		
		JLabel editeurLivre = new JLabel("Editeur :");
		editeurLivre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		editeurLivre.setBounds(20, 421, 59, 30);
		panel4.add(editeurLivre);
		
		comboBoxEditeurCm.setBounds(89, 421, 375, 30);
		panel4.add(comboBoxEditeurCm);
		
		bookCoverBtn.setBounds(380, 243, 84, 30);
		bookCoverBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(file);
				File fileSelected = file.getSelectedFile();
				bookCoverFilePath = fileSelected.getAbsolutePath();
				couvCm.setText(bookCoverFilePath);
				File folder = new File(bookCoversDestination);
				if (!folder.exists()) {
					folder.mkdirs();
				}
			}
		});
		bookCoverBtn.setBackground(new Color(255, 255, 255));
		bookCoverBtn.setForeground(new Color(199, 152, 50));
		bookCoverBtn.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		panel4.add(bookCoverBtn);
		
		JButton btnValideLIvre = new JButton("Je valide mon livre !");
		btnValideLIvre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!ISBNCm.getText().isEmpty()) && (!titreCm.getText().isEmpty()) && (!resumeCm.getText().isEmpty()) && (!dateCm.getText().isEmpty()) && ((int) nbPageCm.getValue() >= 1) && (comboBoxAuteurCm.getSelectedItem().toString() != "Choisir un auteur") && (comboBoxGenreCm.getSelectedItem().toString() != "Choisir un thème") && (comboBoxEditeurCm.getSelectedItem().toString() != "Choisir un éditeur")) {
					if (comboBoxPrincipale.getSelectedItem().toString().equals("Créer un nouveau livre")) {
						try {
							Livre bookToCreate = new Livre(ISBNCm.getText(), "", titreCm.getText(), new Auteur(null, null), resumeCm.getText(), null, Integer.parseInt(nbPageCm.getValue().toString()), new Editeur(null));
							if (comboBoxSerieCm.getSelectedItem().toString() != "Choisir une série") {
								if (backOfficeDAO.bigAddToDB(bookToCreate, dateCm.getText(), Integer.parseInt(backOfficeDAO.getItemID(comboBoxAuteurCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxGenreCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxSerieCm.getSelectedItem().toString())), Integer.parseInt(volNumCm.getValue().toString()), Integer.parseInt(backOfficeDAO.getItemID(comboBoxEditeurCm.getSelectedItem().toString())))) {
									JOptionPane.showConfirmDialog(null, "Le livre \"" + bookToCreate.getTitre() + "\" a bien été créé !", "Nouveau livre ajouté", JOptionPane.WARNING_MESSAGE);
									if (!couvCm.getText().equals(livreDAO.findByISBN(bookToCreate.getISBN()).getCouverture())) {
										if (!getOrUpdateBookcover(bookToCreate)) {
											JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'import de la couverture.\nVeuillez réessayer ultérieurement", "Ajout de livre / Couverture", JOptionPane.ERROR_MESSAGE);								
										}
									}
									BackOfficeDAO.ISBNOnLoad = bookToCreate.getISBN();
									removeAll();
									add(new BackOffice());
									repaint();
									revalidate();
								} else {
									JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Ajout de livre", JOptionPane.ERROR_MESSAGE);
								}							
							} else {
								if (backOfficeDAO.bigAddToDB(bookToCreate, dateCm.getText(), Integer.parseInt(backOfficeDAO.getItemID(comboBoxAuteurCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxGenreCm.getSelectedItem().toString())), 0, 0, Integer.parseInt(backOfficeDAO.getItemID(comboBoxEditeurCm.getSelectedItem().toString())))) {
									JOptionPane.showConfirmDialog(null, "Le livre \"" + bookToCreate.getTitre() + "\" a bien été créé !", "Nouveau livre ajouté", JOptionPane.WARNING_MESSAGE);
									if (!couvCm.getText().equals(livreDAO.findByISBN(bookToCreate.getISBN()).getCouverture())) {
										if (!getOrUpdateBookcover(bookToCreate)) {
											JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'import de la couverture.\nVeuillez réessayer ultérieurement", "Ajout de livre / Couverture", JOptionPane.ERROR_MESSAGE);								
										}
									}
									BackOfficeDAO.ISBNOnLoad = bookToCreate.getISBN();
									removeAll();
									add(new BackOffice());
									repaint();
									revalidate();
								} else {
									JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Ajout de livre", JOptionPane.ERROR_MESSAGE);
								}	
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							Livre bookToUpdate = new Livre(ISBNCm.getText(), "", titreCm.getText(), new Auteur(null, null), resumeCm.getText(), null, Integer.parseInt(nbPageCm.getValue().toString()), new Editeur(null));
							getOrUpdateBookcover(bookToUpdate);
							if (comboBoxSerieCm.getSelectedItem().toString() != "Choisir une série") {
								if (backOfficeDAO.bigUpdateToDB(bookToUpdate, dateCm.getText(), Integer.parseInt(backOfficeDAO.getItemID(comboBoxAuteurCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxGenreCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxSerieCm.getSelectedItem().toString())), Integer.parseInt(volNumCm.getValue().toString()), Integer.parseInt(backOfficeDAO.getItemID(comboBoxEditeurCm.getSelectedItem().toString())))) {
									JOptionPane.showConfirmDialog(null, "Le livre \"" + bookToUpdate.getTitre() + "\" a bien été mis à jour !", "Livre mis à jour", JOptionPane.WARNING_MESSAGE);
									if (!couvCm.getText().equals(livreDAO.findByISBN(bookToUpdate.getISBN()).getCouverture())) {
										if (!getOrUpdateBookcover(bookToUpdate)) {
											JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'import de la couverture.\nVeuillez réessayer ultérieurement", "Ajout de livre / Couverture", JOptionPane.ERROR_MESSAGE);								
										}
									}
									BackOfficeDAO.ISBNOnLoad = bookToUpdate.getISBN();
									removeAll();
									add(new BackOffice());
									repaint();
									revalidate();
								} else {
									JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Mise à jour de livre", JOptionPane.ERROR_MESSAGE);
								}								
							} else {
								if (backOfficeDAO.bigUpdateToDB(bookToUpdate, dateCm.getText(), Integer.parseInt(backOfficeDAO.getItemID(comboBoxAuteurCm.getSelectedItem().toString())), Integer.parseInt(backOfficeDAO.getItemID(comboBoxGenreCm.getSelectedItem().toString())), 0, 0, Integer.parseInt(backOfficeDAO.getItemID(comboBoxEditeurCm.getSelectedItem().toString())))) {
									JOptionPane.showConfirmDialog(null, "Le livre \"" + bookToUpdate.getTitre() + "\" a bien été mis à jour !", "Livre mis à jour", JOptionPane.WARNING_MESSAGE);
									if (!couvCm.getText().equals(livreDAO.findByISBN(bookToUpdate.getISBN()).getCouverture())) {
										if (!getOrUpdateBookcover(bookToUpdate)) {
											JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'import de la couverture.\nVeuillez réessayer ultérieurement", "Ajout de livre / Couverture", JOptionPane.ERROR_MESSAGE);								
										}
									}
									BackOfficeDAO.ISBNOnLoad = bookToUpdate.getISBN();
									removeAll();
									add(new BackOffice());
									repaint();
									revalidate();
								} else {
									JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Mise à jour de livre", JOptionPane.ERROR_MESSAGE);
								}			
							}
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Certains champs sont vides, veuillez réessayer", "Ajout ou mise à jour de livre", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnValideLIvre.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		btnValideLIvre.setBackground(new Color(90, 205, 25));
		btnValideLIvre.setBounds(314, 462, 150, 30);
		panel4.add(btnValideLIvre);
		
		bookCoverDelete.setBackground(new Color(255, 255, 255));
		bookCoverDelete.setForeground(new Color(255, 0, 0));
		bookCoverDelete.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		bookCoverDelete.setBounds(380, 270, 84, 30);
		bookCoverDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!couvCm.getText().isEmpty()) {
					File fileToDelete = new File(bookCoversDestination + couvCm.getText());
					Object[] options = {"Oui", "Non"};
					int imageDel = JOptionPane.showOptionDialog(null, "Souhaitez-vous supprimer la couverture \"" + fileToDelete.getPath() + "\") ?", "Supprimer une couverture", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (imageDel == 0) {
						if (fileToDelete.delete()) {
							backOfficeDAO.deleteBookcoverDB(backOfficeDAO.getISBN(comboBoxPrincipale.getSelectedItem().toString()));
							selectedBook.setCouverture(null);
							couvCm.setText("");
							JOptionPane.showConfirmDialog(null, "La couverture a bien été supprimée", "Couverture supprimée", JOptionPane.WARNING_MESSAGE);							
						} else {
							JOptionPane.showMessageDialog(null, "Une erreur est survenue, veuillez réessayer ultérieurement", "Supprimer une couverture", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		panel4.add(bookCoverDelete);
		
		dateCm = new JTextField();
		dateCm.setEditable(false);
		dateCm.setBounds(145, 175, 225, 30);
		panel4.add(dateCm);
		dateCm.setColumns(10);
		
		datePanelBtn.setBounds(380, 175, 84, 30);
		panel4.add(datePanelBtn);
		datePanelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(true);
			}
		});
		datePanelBtn.setBackground(new Color(255, 255, 255));
		datePanelBtn.setForeground(new Color(199, 152, 50));
		datePanelBtn.setFont(new Font("Noto Serif", Font.PLAIN, 13));		
		
		populateComboBoxes();
		
		if (BackOfficeDAO.ISBNOnLoad != null) {
			comboBoxPrincipale.setSelectedIndex(getBookComboItem(BackOfficeDAO.ISBNOnLoad));
			loadSelectedBook();
		}
		
	}
	
	public void loadSelectedBook() {
		selectedBook = livreDAO.findByISBN(backOfficeDAO.getISBN(comboBoxPrincipale.getSelectedItem().toString()));
		ISBNCm.setText(selectedBook.getISBN());
		ISBNCm.setEditable(false);
		titreCm.setText(selectedBook.getTitre());
		resumeCm.setText(selectedBook.getResume());
		resumeCm.setCaretPosition(0);
		dateCm.setText(selectedBook.getDatePubli().toString());
		if (selectedBook.getCouverture() != null) {
			couvCm.setText(selectedBook.getCouverture());			
		} else {
			couvCm.setText("");
		}
		comboBoxAuteurCm.setSelectedIndex(getComboItem(selectedBook.getISBN(), "Auteur"));
		comboBoxGenreCm.setSelectedIndex(getComboItem(selectedBook.getISBN(), "Genre"));
		comboBoxSerieCm.setSelectedIndex(getComboItem(selectedBook.getISBN(), "Serie"));
		volNumCm.setValue(backOfficeDAO.getPositionInSeries(selectedBook.getISBN()));
		comboBoxEditeurCm.setSelectedIndex(getComboItem(selectedBook.getISBN(), "Editeur"));
		nbPageCm.setValue(selectedBook.getNbPages());
		tableExemplaire.setModel(listeExemplaires(selectedBook.getISBN()));
		reformatTable(tableExemplaire);
		btnPlus1.setEnabled(true);
	}
	
	public void reformatTable(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
	}
	
	public void populateComboBoxes() {
	    for (int i = 0; i < 5; i++) {
	        setComboModel(i);
	    }
	}
	
	public void setComboModel(int i) {
	    ArrayList<String> liste = backOfficeDAO.getList(i);
	    switch (i) {
	        case 0: {
	            liste.add(0, "Créer un nouveau livre");
	            comboBoxPrincipale.setModel(new DefaultComboBoxModel(liste.toArray()));
	            break;
	        }
	        case 1: {
	            liste.add(0, "Choisir un auteur");
	            comboBoxAuteurCm.setModel(new DefaultComboBoxModel(liste.toArray()));
	            break;
	        }
	        case 2: {
	            liste.add(0, "Choisir un thème");
	            comboBoxGenreCm.setModel(new DefaultComboBoxModel(liste.toArray()));
	            break;
	        }
	        case 3: {
	            liste.add(0, "Choisir une série");
	            comboBoxSerieCm.setModel(new DefaultComboBoxModel(liste.toArray()));
	            break;
	        }
	        case 4: {
	            liste.add(0, "Choisir un éditeur");
	            comboBoxEditeurCm.setModel(new DefaultComboBoxModel(liste.toArray()));
	            break;
	        }
	    };
	}
	
	public int getBookComboItem(String ISBN) {		
		for (int i = 0; i < comboBoxPrincipale.getItemCount(); i++) {
			if (comboBoxPrincipale.getItemAt(i).toString().contains(ISBN)) 
			{ 
				return i;
			}
		}
		return 0;
	}
	
	public int getComboItem(String ISBN, String type) {
		
		JComboBox<String> selectedBox = new JComboBox<String>();
		int id = backOfficeDAO.readRelation(ISBN, type);
		
		switch (type) {
			case "Auteur": {
				selectedBox = comboBoxAuteurCm;
				break;
			}
			case "Genre": {
				selectedBox = comboBoxGenreCm;
				break;
			}
			case "Serie": {			
				selectedBox = comboBoxSerieCm;
				break;
			}
			case "Editeur": {				
				selectedBox = comboBoxEditeurCm;
				break;
			}
		}
		
		for (int i = 0; i < selectedBox.getItemCount(); i++) {
			if (selectedBox.getItemAt(i).toString().contains("#"+String.valueOf(id)+")")) 
			{ 
				return i;
			}
		}
		return 0;
	}
	
	public DefaultTableModel listeExemplaires(String ISBN) {
		String col [] =  {"Exemp n°", "ISBN", "Titre"};
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -6015414029794944436L;
			
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		ArrayList<String[]> listeExemplaires = backOfficeDAO.getExemplaires(ISBN);
		
		for (String[] exemplaire : listeExemplaires) {
			tableau.addRow(new Object[] {
					exemplaire[0],
					exemplaire[1],
					exemplaire[2],
			});
		}
		return tableau;
	}
	
	public boolean getOrUpdateBookcover(Livre livre) {
		File copyStart = new File(bookCoverFilePath);
		File copyEnd = new File(bookCoversDestination + livre.getISBN()+"."+bookCoverFilePath.substring(bookCoverFilePath.lastIndexOf(".")+1));
		try {
			Files.copy(copyStart.toPath(), copyEnd.toPath(), StandardCopyOption.REPLACE_EXISTING);
			if (!backOfficeDAO.setOrUpdateBookcover(livre.getISBN(), livre.getISBN()+"."+bookCoverFilePath.substring(bookCoverFilePath.lastIndexOf(".")+1))) {
				return false;
			}
			return true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
}
