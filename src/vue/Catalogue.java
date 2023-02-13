package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controler.LivreDAO;
import model.Livre;

public class Catalogue extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826242832898993370L;
	private JTable resultsTable;

	/**
	 * Create the panel.
	 */
	public Catalogue(String searchString) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel catalogTitle = new JLabel("Livres disponibles à l'emprunt");
		catalogTitle.setBounds(0, 20, 1000, 50);
		catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
		catalogTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(catalogTitle);
		
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(50, 80, 900, 450);
		add(resultsPane);
		
		resultsTable = new JTable();
		
		if ((searchString != null) && (searchString.length() > 0)) {
			catalogTitle.setText("Résultats de votre recherche : " + searchString);
			catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 25));
			resultsTable.setModel(listeDispo(searchString));
		} else {
			resultsTable.setModel(listeDispo());
		}
			resultsTable.setRowHeight(75);
			resultsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
			resultsTable.getColumnModel().getColumn(1).setPreferredWidth(400);
			resultsTable.getColumnModel().getColumn(2).setPreferredWidth(0);
			resultsTable.getColumnModel().getColumn(3).setPreferredWidth(0);
			resultsTable.getColumnModel().getColumn(4).setPreferredWidth(0);
			resultsPane.setViewportView(resultsTable);
	}
	
	public DefaultTableModel listeDispo() {
		String col [] =  {" ", "Titre", "Auteur", "Pages", "ISBN"};
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -2767358890848023063L;

			@Override
		    public Class<?> getColumnClass(int column) {
		        switch (column) {
		            case 0: return ImageIcon.class;
		            default: return String.class;
		        }
		    }
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		LivreDAO livreDao = new LivreDAO();
		
		for (Livre livre : livreDao.readDispo()) {
			tableau.addRow(new Object[] {
					new ImageIcon(new ImageIcon("src/images/" + livre.getCouverture()).getImage().getScaledInstance(53, 75, Image.SCALE_SMOOTH)),
					livre.getTitre(),
					livre.getAuteur().getNom() + ", " + livre.getAuteur().getPrenom(),
					livre.getNbPages(),
					livre.getISBN()
			});
		}
		return tableau;
	}
	
	public DefaultTableModel listeDispo(String searchString) {
		String col [] =  {" ", "Titre", "Auteur", "Pages", "ISBN"};
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -2767358890848023063L;

			@Override
		    public Class<?> getColumnClass(int column) {
		        switch (column) {
		            case 0: return ImageIcon.class;
		            default: return String.class;
		        }
		    }
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		LivreDAO livreDao = new LivreDAO();
		
		for (Livre livre : livreDao.readDispo(searchString)) {
			tableau.addRow(new Object[] {
					new ImageIcon(new ImageIcon("src/images/" + livre.getCouverture()).getImage().getScaledInstance(53, 75, Image.SCALE_SMOOTH)),
					livre.getTitre(),
					livre.getAuteur().getNom() + ", " + livre.getAuteur().getPrenom(),
					livre.getNbPages(),
					livre.getISBN()
			});
		}
		return tableau;
	}
}
