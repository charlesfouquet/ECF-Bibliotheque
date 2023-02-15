package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controler.CommentaireDAO;
import controler.LivreDAO;
import controler.UserDAO;
import model.Livre;
import utilities.DateTime;

public class FicheLivre extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3209269363955639051L;
	LivreDAO livreDAO = new LivreDAO();
	CommentaireDAO commentaireDAO = new CommentaireDAO();

	/**
	 * Create the panel.
	 */
	public FicheLivre(Livre livre) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel authorLabel = new JLabel("");
		authorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				authorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				authorLabel.setForeground(new Color(199, 152, 50));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] authorParts = authorLabel.getText().substring(12).split(" ");
				String auteurParNom = "";
				for (int i = authorParts.length - 1; i >= 0; i--) {
					auteurParNom += authorParts[i];
					if (i != 0) {
						auteurParNom += ", ";
					}
				}
				System.out.println(auteurParNom);
				removeAll();
				add(new Tri(new String[]{"Auteurs", auteurParNom}));
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				authorLabel.setForeground(new Color(120, 91, 31));
			}
		});
		authorLabel.setVerticalAlignment(SwingConstants.TOP);
		authorLabel.setBounds(282, 120, 378, 30);
		authorLabel.setFont(new Font("Noto Serif", Font.PLAIN, 15));
		authorLabel.setForeground(new Color(120, 91, 31));
		add(authorLabel);
		authorLabel.setText("Écrit par : " + livre.getAuteur().getPrenom() + " " + livre.getAuteur().getNom());
		
		JLabel bookCover = new JLabel(new ImageIcon(new ImageIcon("src/resources/images/bookcovers/" + livre.getCouverture()).getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH)));
		bookCover.setBounds(20, 50, 250, 350);
		add(bookCover);
		
		JScrollPane commentsScrollPane = new JScrollPane();
		commentsScrollPane.setBounds(680, 50, 300, 350);
		commentsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(commentsScrollPane);
		
		JEditorPane commentsPane = new JEditorPane();
		commentsPane.setEditable(false);
		commentsScrollPane.setViewportView(commentsPane);
		
		JLabel backToSeries = new JLabel("");
		backToSeries.setBounds(20, 20, 660, 30);
		backToSeries.setFont(new Font("Noto Serif", Font.ITALIC, 15));
		add(backToSeries);
		if (Integer.parseInt(livreDAO.getSeries(livre.getISBN()).get(0)) > 0) {
			backToSeries.setText("Tome " + livreDAO.getSeries(livre.getISBN()).get(0) + " de la série " + livreDAO.getSeries(livre.getISBN()).get(1));
			backToSeries.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					backToSeries.setCursor(new Cursor(Cursor.HAND_CURSOR));
					backToSeries.setForeground(new Color(199, 152, 50));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					removeAll();
					add(new Tri(new String[]{"Series", backToSeries.getText().split("de la série ")[1]}));
					repaint();
					revalidate();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					backToSeries.setForeground(new Color(0, 0, 0));
				}
			});
		}
		
		JLabel commentsLabel = new JLabel("Commentaires");
		commentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		commentsLabel.setBounds(680, 20, 300, 30);
		commentsLabel.setFont(new Font("Noto Serif", Font.BOLD, 16));
		add(commentsLabel);
		
		JScrollPane resumeScrollPane = new JScrollPane();
		resumeScrollPane.setBounds(280, 150, 400, 250);
		resumeScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(resumeScrollPane);
		
		JTextPane resumePane = new JTextPane();
		resumePane.setBackground(new Color(240, 227, 198));
		resumePane.setEditable(false);
		resumePane.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		resumeScrollPane.setViewportView(resumePane);
		resumePane.setText("Résumé :\n" + livre.getResume());
		
		JPanel bookTitlePane = new JPanel();
		bookTitlePane.setBackground(new Color(0, 0, 0, 0));
		bookTitlePane.setBounds(280, 50, 380, 70);
		add(bookTitlePane);
		bookTitlePane.setLayout(new BorderLayout(0, 0));
		
		JTextPane bookTitle = new JTextPane();
		bookTitle.setBackground(new Color(240, 227, 198));
		bookTitle.setEditable(false);
		bookTitle.setFont(new Font("Noto Serif", Font.BOLD, 25));
		bookTitle.setForeground(new Color(71, 54, 18));
		bookTitlePane.add(bookTitle, BorderLayout.SOUTH);		
		bookTitle.setText(livre.getTitre());
		
		JLabel stockInfo = new JLabel("");
		stockInfo.setHorizontalAlignment(SwingConstants.CENTER);
		stockInfo.setBounds(20, 400, 250, 50);
		stockInfo.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		add(stockInfo);
		stockInfo.setText(livreDAO.getStock(livre.getISBN()).get(0) + "/" + livreDAO.getStock(livre.getISBN()).get(1) + " exemplaires disponibles");
		
		JButton btnNewButton = new JButton("Emprunter");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(199, 152, 50));
		btnNewButton.setFont(new Font("Noto Serif", Font.BOLD, 15));
		btnNewButton.setBounds(20, 450, 250, 40);
		add(btnNewButton);
		
		JLabel labelDate = new JLabel("");
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		labelDate.setForeground(new Color(0, 0, 0, 125));
		labelDate.setBounds(280, 422, 380, 21);
		add(labelDate);
		labelDate.setText("Date de publication : " + DateTime.sdfDate.format(livre.getDatePubli()));
		
		JLabel labelPages = new JLabel("");
		labelPages.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPages.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		labelPages.setForeground(new Color(0, 0, 0, 125));
		labelPages.setBounds(280, 443, 380, 21);
		add(labelPages);
		labelPages.setText("Nombre de pages : " + livre.getNbPages());
		
		JLabel labelISBN = new JLabel("");
		labelISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		labelISBN.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		labelISBN.setForeground(new Color(0, 0, 0, 125));
		labelISBN.setBounds(280, 464, 380, 21);
		add(labelISBN);
		labelISBN.setText("ISBN : " + livre.getISBN());
		
		JLabel labelEditeur = new JLabel("");
		labelEditeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelEditeur.setCursor(new Cursor(Cursor.HAND_CURSOR));
				labelEditeur.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				add(new Catalogue(labelEditeur.getText().substring(10)));
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelEditeur.setForeground(new Color(0, 0, 0, 125));
			}
		});
		labelEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEditeur.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		labelEditeur.setForeground(new Color(0, 0, 0, 125));
		labelEditeur.setBounds(280, 485, 380, 21);
		add(labelEditeur);
		labelEditeur.setText("Editeur : " + livre.getEditeur().getNomsocial());
		
		JLabel labelGenres = new JLabel("");
		labelGenres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelGenres.setCursor(new Cursor(Cursor.HAND_CURSOR));
				labelGenres.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String genres = "";
				String[] genresParts = labelGenres.getText().substring(9).split(", ");
				for (String theme : genresParts) {
					genres += theme + " ";
				}
				removeAll();
				add(new Catalogue(genres));
				repaint();
				revalidate();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				labelGenres.setForeground(new Color(0, 0, 0, 125));
			}
		});
		labelGenres.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGenres.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		labelGenres.setForeground(new Color(0, 0, 0, 125));
		labelGenres.setBounds(280, 506, 380, 21);
		add(labelGenres);
		labelGenres.setText("Genres : " + livreDAO.getGenres(livre.getISBN()));
		
		JPanel ajoutCom = new JPanel();
		ajoutCom.setBounds(680, 422, 300, 105);
		ajoutCom.setBackground(new Color(248, 243, 231));
		add(ajoutCom);
		ajoutCom.setLayout(null);
		
		JTextArea textAjoutCom = new JTextArea();
		textAjoutCom.setBounds(10, 30, 280, 40);
		ajoutCom.add(textAjoutCom);
		
		JButton ajoutComBtn = new JButton("Envoyer");
		ajoutComBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ajoutComBtn.isEnabled()) {
					if (textAjoutCom.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Le commentaire est vide.\nVeuillez écrire un commentaire avant de l'envoyer.", "Commentaire vide", JOptionPane.WARNING_MESSAGE);											
					} else if (!textAjoutCom.getText().equals("")) {
						
						JOptionPane.showMessageDialog(null, "Merci beaucoup pour votre commentaire !", "Commentaire envoyé", JOptionPane.INFORMATION_MESSAGE);											
					}
				}
			}
		});
		ajoutComBtn.setBounds(10, 75, 280, 20);
		ajoutComBtn.setBackground(new Color(255, 255, 255));
		ajoutComBtn.setForeground(new Color(199, 152, 50));
		ajoutComBtn.setFont(new Font("Noto Serif", Font.BOLD, 13));
		ajoutCom.add(ajoutComBtn);
		
		JLabel labelAjoutCom = new JLabel("Ajouter un commentaire");
		labelAjoutCom.setHorizontalAlignment(SwingConstants.CENTER);
		labelAjoutCom.setFont(new Font("Noto Serif", Font.BOLD, 13));
		labelAjoutCom.setBounds(0, 0, 300, 30);
		ajoutCom.add(labelAjoutCom);
		
		if (livreDAO.getStock(livre.getISBN()).get(0) == 0) {
			stockInfo.setText("Ce livre est temporairement indisponible");
			stockInfo.setForeground(Color.RED);
			btnNewButton.setText("Emprunt impossible");
			btnNewButton.setEnabled(false);
		} else if (UserDAO.currentUser == null) {
			stockInfo.setText(livreDAO.getStock(livre.getISBN()).get(0) + "/" + livreDAO.getStock(livre.getISBN()).get(1) + " exemplaires disponibles");
			btnNewButton.setText("Connexion requise");
			ajoutComBtn.setText("Connexion requise");
			btnNewButton.setEnabled(false);
			ajoutComBtn.setEnabled(false);
			textAjoutCom.setEnabled(false);
		}
				
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				resumeScrollPane.getViewport().setViewPosition(new Point(1, 1));				
			}
		});
	}
}
