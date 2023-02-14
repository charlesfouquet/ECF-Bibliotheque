package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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
	LivreDAO livreDAO = new LivreDAO();

	/**
	 * Create the panel.
	 */
	public Catalogue(String searchString) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel catalogTitle = new JLabel("Catalogue complet");
		catalogTitle.setBounds(0, 20, 1000, 50);
		catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
		catalogTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(catalogTitle);
		
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(50, 80, 900, 420);
		add(resultsPane);
		
		resultsTable = new JTable();
		
		if ((searchString != null) && (searchString.length() > 0)) {
			catalogTitle.setText("Résultats de votre recherche : " + searchString);
			catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 25));
		}
		resultsTable.setModel(liste(searchString, false));
		reformatTable(resultsTable);
		resultsPane.setViewportView(resultsTable);
		
		JCheckBox onlyAvailable = new JCheckBox("Afficher uniquement les livres disponibles");
		onlyAvailable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if ((searchString != null) && (searchString.length() > 0)) {
						catalogTitle.setText("Résultats disponibles de votre recherche : " + searchString);
						catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 25));
					} else {
						catalogTitle.setText("Livres disponibles à l'emprunt");
						catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
					}
					resultsTable.setModel(liste(searchString, true));
					reformatTable(resultsTable);
		        } else {
		        	if ((searchString != null) && (searchString.length() > 0)) {
						catalogTitle.setText("Résultats de votre recherche : " + searchString);
						catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 25));
					} else {
						catalogTitle.setText("Catalogue complet");
						catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
					}
		        	resultsTable.setModel(liste(searchString, false));
		    		reformatTable(resultsTable);
		        };
			}
		});
		onlyAvailable.setBounds(365, 515, 270, 20);
		onlyAvailable.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		add(onlyAvailable);
		
		resultsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = resultsTable.getSelectedRow();
				String ISBN = (String) resultsTable.getModel().getValueAt(id, 4);

				removeAll();
				add(new FicheLivre(livreDAO.findByISBN(ISBN)));
				repaint();
				revalidate();
			}
		});
	}
	
	public void reformatTable(JTable tableInput) {
		tableInput.setRowHeight(75);
		tableInput.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(1).setPreferredWidth(300);
		tableInput.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(3).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(4).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(5).setPreferredWidth(0);
	}
	
	public DefaultTableModel liste(String searchString, Boolean dispoState) {
		String col [] =  {" ", "Titre", "Auteur", "Pages", "ISBN", "Stock"};
		
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
		
		ArrayList<Livre> source;
		
		if (dispoState == true) {
			if (searchString == null) {
				source = livreDAO.readDispo();
			} else {
				source = livreDAO.readDispo(searchString);
			}
		} else {
			if (searchString == null) {
				source = livreDAO.read();
			} else {
				source = livreDAO.read(searchString);
			}
		}
		
		for (Livre livre : source) {
			String stockInfo = "";
			switch (livreDAO.getStock(livre.getISBN()).get(0)) {
			case 0:
				stockInfo = "<html><body style=\"color:red;\">✖<span style=\"font-weight:bold;\"> Indisponible</span></body></html>";
				break;
			case 1:
				stockInfo = "<html><body style=\"color:orange;\">⚠<span style=\"font-weight:bold;\"> 1 disponible</span></body></html>";				
				break;
			default:
				stockInfo = "<html><body style=\"color:green;\">✔<span style=\"font-weight:bold;\"> Disponible</span></body></html>";								
				break;
			}
			tableau.addRow(new Object[] {
					new ImageIcon(new ImageIcon("src/images/" + livre.getCouverture()).getImage().getScaledInstance(53, 75, Image.SCALE_SMOOTH)),
					livre.getTitre(),
					livre.getAuteur().getNom() + ", " + livre.getAuteur().getPrenom(),
					livre.getNbPages(),
					livre.getISBN(),
					stockInfo
			});
			
		}
		return tableau;
	}
}
