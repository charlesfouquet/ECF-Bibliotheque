package vue;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		} else {
			
		}
		
		resultsTable.setModel(new DefaultTableModel(
				new Object[][] {
					{new ImageIcon(new ImageIcon("src/images/bookcover1.jpg").getImage().getScaledInstance(53, 75, Image.SCALE_SMOOTH)), "Test", "Test", "Test", "Test"},
				},
				new String[] {
					" ", "Titre", "Auteur", "Pages", "ISBN"
				}
			) {
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
			});
			resultsTable.setRowHeight(75);
			resultsTable.getColumnModel().getColumn(0).setPreferredWidth(53);
			resultsPane.setViewportView(resultsTable);
		
	}
}
