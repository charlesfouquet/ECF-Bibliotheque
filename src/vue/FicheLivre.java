package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controler.LivreDAO;
import model.Livre;
import javax.swing.JButton;

public class FicheLivre extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3209269363955639051L;
	LivreDAO livreDAO = new LivreDAO();

	/**
	 * Create the panel.
	 */
	public FicheLivre(Livre livre) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel authorLabel = new JLabel("New label");
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
		commentsScrollPane.setBounds(680, 50, 300, 480);
		commentsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(commentsScrollPane);
		
		JEditorPane commentsPane = new JEditorPane();
		commentsPane.setEditable(false);
		commentsScrollPane.setViewportView(commentsPane);
		
		JLabel backToSeries = new JLabel("New label");
		backToSeries.setBounds(20, 20, 660, 30);
		backToSeries.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		add(backToSeries);
		
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
		
		JLabel stockInfo = new JLabel("New label");
		stockInfo.setHorizontalAlignment(SwingConstants.CENTER);
		stockInfo.setBounds(20, 400, 250, 50);
		stockInfo.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		add(stockInfo);
		
		JButton btnNewButton = new JButton("Emprunter");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(199, 152, 50));
		btnNewButton.setFont(new Font("Noto Serif", Font.BOLD, 15));
		btnNewButton.setBounds(20, 450, 250, 40);
		add(btnNewButton);
		
		if (livreDAO.getStock(livre.getISBN()).get(0) == 0) {
			stockInfo.setText("Ce livre est temporairement indisponible");
			stockInfo.setForeground(Color.RED);
			btnNewButton.setText("Emprunt impossible");
			btnNewButton.setEnabled(false);
		} else {
			stockInfo.setText(livreDAO.getStock(livre.getISBN()).get(0) + "/" + livreDAO.getStock(livre.getISBN()).get(1) + " exemplaires disponibles");
			btnNewButton.setText("Emprunter");
			btnNewButton.setEnabled(true);
		}
				
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				resumeScrollPane.getViewport().setViewPosition(new Point(1, 1));				
			}
		});
	}
}
