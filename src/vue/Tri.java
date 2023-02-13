package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controler.LivreDAO;
import model.Livre;

public class Tri extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826242832898993370L;
	private JTable resultsTable;
	private JTable sortTable;

	/**
	 * Create the panel.
	 */
	public Tri(String categoryString) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel catalogTitle = new JLabel("Livres disponibles à l'emprunt");
		catalogTitle.setBounds(200, 20, 800, 50);
		catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
		catalogTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(catalogTitle);
		
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(250, 80, 700, 450);
		add(resultsPane);
			
		JScrollPane sortPane = new JScrollPane();
		sortPane.setBounds(25, 150, 150, 310);
		add(sortPane);
			
		sortTable = new JTable();
		sortTable.setModel(listeTri(categoryString));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		sortTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		sortTable.getTableHeader().setDefaultRenderer(new SortTableHeaderRenderer());
		sortPane.setViewportView(sortTable);
		
		resultsTable = new JTable();
			
		JLabel sortTitle = new JLabel("Tri par");
		sortTitle.setHorizontalAlignment(SwingConstants.CENTER);
		sortTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		sortTitle.setBounds(0, 120, 200, 30);
		add(sortTitle);
		
		@SuppressWarnings("unused")
		String selectedItem = null;
		sortTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = sortTable.getSelectedRow();
				String selectedItem = (String) sortTable.getModel().getValueAt(row, 0);
				resultsTable.setModel(listeDispoViaTri(categoryString, selectedItem));
				resultsTable.setRowHeight(75);
				resultsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
				resultsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
				resultsTable.getColumnModel().getColumn(2).setPreferredWidth(0);
				resultsTable.getColumnModel().getColumn(3).setPreferredWidth(0);
				resultsTable.getColumnModel().getColumn(4).setPreferredWidth(0);
				resultsPane.setViewportView(resultsTable);
			}
		});
		
	}
	
	public void paint(Graphics g) {
	    super.paint(g); 
	    Graphics2D g2d = (Graphics2D) g;
	    Line2D line = new Line2D.Float(200, 20, 200, 530);
	    g2d.setColor(new Color(199, 152, 50));
	    g2d.draw(line);
	}
	
	public DefaultTableModel listeTri(String categoryString) {
		String col [] =  {categoryString};
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		LivreDAO livreDao = new LivreDAO();
		
		for (String string : livreDao.getListeTri(categoryString)) {
			tableau.addRow(new String[] {string.toString()});
		}
		return tableau;
	}
	
	public DefaultTableModel listeDispoViaTri(String triString, String subTriString) {
		String col [] =  {" ", "Titre", "Auteur", "Pages", "ISBN"};
		
		DefaultTableModel tableau = new DefaultTableModel(null, col)
		{
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -6015414029794944436L;
			
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
		
		for (Livre livre : livreDao.readDispoViaTri(triString, subTriString)) {
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
