package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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
	LivreDAO livreDAO = new LivreDAO();
	private boolean lineState = true;
	private String selectedItem = null;

	/**
	 * Create the panel.
	 */
	public Tri(String categoryString) {
		setBackground(new Color(240, 227, 198));
		setBounds(0, 0, 1000, 550);
		setLayout(null);
		
		JLabel catalogTitle = new JLabel("Extrait du catalogue complet");
		catalogTitle.setBounds(200, 20, 800, 50);
		catalogTitle.setFont(new Font("Noto Serif", Font.BOLD, 35));
		catalogTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(catalogTitle);
		
		JScrollPane resultsPane = new JScrollPane();
		resultsPane.setBounds(250, 80, 700, 420);
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
		sortTitle.setFont(new Font("Noto Serif", Font.BOLD, 20));
		sortTitle.setBounds(0, 120, 200, 30);
		add(sortTitle);
		
		sortTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = sortTable.getSelectedRow();
				selectedItem = (String) sortTable.getModel().getValueAt(row, 0);
				resultsTable.setModel(listeViaTri(categoryString, selectedItem, false));
				reformatTable(resultsTable);
				resultsPane.setViewportView(resultsTable);
			}
		});
		
		resultsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = resultsTable.getSelectedRow();
				String ISBN = (String) resultsTable.getModel().getValueAt(id, 4);
				lineState = false;

				removeAll();
				add(new FicheLivre(livreDAO.findByISBN(ISBN)));
				repaint();
				revalidate();
			}
		});
		
		JCheckBox onlyAvailable = new JCheckBox("Afficher uniquement les livres disponibles");
		onlyAvailable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					catalogTitle.setText("Livres disponibles à l'emprunt");
					resultsTable.setModel(listeViaTri(categoryString, selectedItem, true));
					reformatTable(resultsTable);
		        } else {
					catalogTitle.setText("Extrait du catalogue complet");
		        	resultsTable.setModel(listeViaTri(categoryString, selectedItem, false));
		    		reformatTable(resultsTable);
		        };
			}
		});
		onlyAvailable.setBounds(465, 515, 270, 20);
		onlyAvailable.setFont(new Font("Noto Serif", Font.PLAIN, 12));
		add(onlyAvailable);
	}
	
	public void reformatTable(JTable tableInput) {
		tableInput.setRowHeight(75);
		tableInput.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableInput.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(3).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(4).setPreferredWidth(0);
		tableInput.getColumnModel().getColumn(5).setPreferredWidth(0);
	}
	
	public void paint(Graphics g) {
	    super.paint(g); 
	    Graphics2D g2d = (Graphics2D) g;
	    Line2D line = new Line2D.Float(200, 20, 200, 530);
	    if (lineState) {
	    	g2d.setColor(new Color(199, 152, 50));
	    	g2d.draw(line);			
		}
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
		
		for (String string : livreDAO.getListeTri(categoryString)) {
			tableau.addRow(new String[] {string.toString()});
		}
		return tableau;
	}
	
	public DefaultTableModel listeViaTri(String triString, String subTriString, Boolean dispoState) {
		String col [] =  {" ", "Titre", "Auteur", "Pages", "ISBN", "Stock"};
		
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
		
		ArrayList<Livre> source = livreDAO.readViaTri(triString, subTriString, dispoState);
		
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
					new ImageIcon(new ImageIcon("src/resources/images/bookcovers/" + livre.getCouverture()).getImage().getScaledInstance(53, 75, Image.SCALE_SMOOTH)),
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
