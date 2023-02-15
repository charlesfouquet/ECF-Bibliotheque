package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controler.LivreDAO;
import controler.UserDAO;
import model.Livre;

public class Accueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798845607393016064L;
	private JTextField searchField;
	LivreDAO livreDAO = new LivreDAO();

	/**
	 * Create the panel.
	 */
	public Accueil() {
		setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		Font mf = new Font("Noto Serif", Font.BOLD, 15);
		UIManager.put("Menu.font", mf);
		Font mif = new Font("Noto Serif", Font.PLAIN, 12);
		UIManager.put("MenuItem.font", mif);
		
		JPanel accountMenu = new JPanel();
		accountMenu.setBounds(850, 50, 150, 40);
		accountMenu.setLayout(null);
		add(accountMenu);
		accountMenu.setVisible(false);
		
		JButton toAccountButton = new JButton("Mon compte");
		toAccountButton.setBounds(0, 0, 150, 20);
		toAccountButton.setBackground(new Color(255, 255, 255));
		toAccountButton.setForeground(new Color(199, 152, 50));
		toAccountButton.setFont(new Font("Noto Serif", Font.BOLD, 11));
		accountMenu.add(toAccountButton);
		
		JButton logoutButton = new JButton("Déconnexion");
		logoutButton.setBounds(0, 20, 150, 20);
		logoutButton.setBackground(new Color(255, 255, 255));
		logoutButton.setForeground(new Color(199, 152, 50));
		logoutButton.setFont(new Font("Noto Serif", Font.BOLD, 11));
		accountMenu.add(logoutButton);
		
		JPanel menu = new JPanel();
		menu.setBounds(320, 0, 250, 300);
		menu.setBackground(new Color(0, 0, 0, 0));
		add(menu);
		menu.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(243, 232, 207));
		menuBar.setBounds(0, 10, 100, 30);
//		menuBar.setLayout(new GridBagLayout());
		menu.add(menuBar);
		
		JMenu menuBtn = new JMenu("Menu");
		menuBtn.setBackground(new Color(255, 204, 51));
		menuBtn.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuBtn);

		menuBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		JMenuItem menuCatalogue = new JMenuItem("Catalogue complet");
		menuBtn.add(menuCatalogue);
		
		JMenuItem menuAuteurs = new JMenuItem("Auteurs");
		menuBtn.add(menuAuteurs);
		
		JMenuItem menuGenres = new JMenuItem("Genres");
		menuBtn.add(menuGenres);
		
		JMenuItem menuSeries = new JMenuItem("Series");
		menuBtn.add(menuSeries);
		
		JPanel body = new JPanel();
		body.setBackground(new Color(240, 227, 198));
		body.setBounds(0, 50, 1000, 550);
		add(body);
		body.setLayout(null);
		
		JLabel newBooks = new JLabel("Derniers ajouts");
		newBooks.setBounds(0, 50, 1000, 50);
		newBooks.setFont(new Font("Noto Serif", Font.BOLD, 35));
		newBooks.setHorizontalAlignment(SwingConstants.CENTER);
		body.add(newBooks);
		
		JButton toCatalog = new JButton("ACCES AU CATALOGUE COMPLET");
		toCatalog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				body.removeAll();
				body.add(new Catalogue(null));
				body.repaint();
				body.revalidate();
			}
		});
		toCatalog.setBackground(new Color(255, 255, 255));
		toCatalog.setForeground(new Color(199, 152, 50));
		toCatalog.setFont(new Font("Noto Serif", Font.BOLD, 15));
		toCatalog.setBounds(350, 470, 300, 40);
		body.add(toCatalog);
		
		ArrayList<Livre> listeNewBooks = livreDAO.newestBooks();
		
		int newBookXPos = 60;
		for (int i = 0; i < listeNewBooks.size(); i++) {
			JLabel newBook = new JLabel(new ImageIcon(new ImageIcon("src/resources/images/bookcovers/" + listeNewBooks.get(i).getCouverture()).getImage().getScaledInstance(175, 245, Image.SCALE_SMOOTH)));
			newBook.setBounds(newBookXPos, 120, 175, 245);
			body.add(newBook);
			newBook.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectedID = Integer.parseInt(new String(newBook.getName().substring(7))) - 1;
					body.removeAll();
					body.add(new FicheLivre(livreDAO.findByISBN(listeNewBooks.get(selectedID).getISBN())));
					body.repaint();
					body.revalidate();
				}
			});
			newBook.setName("newBook"+(i+1));
			
			JLabel newBookTitle = new JLabel(listeNewBooks.get(i).getTitre());
			newBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
			newBookTitle.setVerticalAlignment(SwingConstants.CENTER);
			newBookTitle.setBounds(newBookXPos, 375, 175, 20);
			newBookTitle.setFont(new Font("Noto Serif", Font.PLAIN, 13));
			body.add(newBookTitle);
			newBookTitle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectedID = Integer.parseInt(new String(newBookTitle.getName().substring(12))) - 1;
					body.removeAll();
					body.add(new Catalogue(listeNewBooks.get(selectedID).getISBN()));
					body.repaint();
					body.revalidate();
				}
			});
			newBookTitle.setName("newBookTitle"+(i+1));
			
			JLabel newBookAuthor = new JLabel(listeNewBooks.get(i).getAuteur().getPrenom() + " " + listeNewBooks.get(i).getAuteur().getNom());
			newBookAuthor.setHorizontalAlignment(SwingConstants.CENTER);
			newBookAuthor.setVerticalAlignment(SwingConstants.CENTER);
			newBookAuthor.setBounds(newBookXPos, 405, 175, 20);
			newBookAuthor.setFont(new Font("Noto Serif", Font.PLAIN, 11));
			body.add(newBookAuthor);
			newBookAuthor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectedID = Integer.parseInt(new String(newBookAuthor.getName().substring(13))) - 1;
					body.removeAll();
					body.add(new Catalogue(listeNewBooks.get(selectedID).getAuteur().getPrenom() + " " + listeNewBooks.get(selectedID).getAuteur().getNom()));
					body.repaint();
					body.revalidate();
				}
			});
			newBookAuthor.setName("newBookAuthor"+(i+1));
			
			newBookXPos += 235;
		}
		
		JPanel header = new JPanel();
		header.setBackground(new Color(248, 243, 231));
		header.setBounds(0, 0, 1000, 50);
		add(header);
		header.setLayout(null);
		
		JLabel logo = new JLabel(new ImageIcon("src/resources/images/logos/home_30x30.png"));
		logo.setBounds(10, 10, 32, 32);
		header.add(logo);
		
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.frame.getContentPane().removeAll();
				Main.frame.getContentPane().add(new Accueil());
				Main.frame.getContentPane().repaint();
				Main.frame.getContentPane().revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel homeName = new JLabel("Bibliothèque");
		homeName.setVerticalAlignment(JLabel.CENTER);
		homeName.setFont(new Font("Noto Serif", Font.PLAIN, 25));
		homeName.setBounds(50, 0, 175, 50);
		header.add(homeName);
		
		homeName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.frame.getContentPane().removeAll();
				Main.frame.getContentPane().add(new Accueil());
				Main.frame.getContentPane().repaint();
				Main.frame.getContentPane().revalidate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				homeName.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					body.removeAll();
					body.add(new Catalogue(searchField.getText()));
					body.repaint();
					body.revalidate();
	            }
			}
		});
		searchField.setBounds(435, 15, 300, 20);
		header.add(searchField);
		searchField.setColumns(10);
		
		JButton searchBtn = new JButton("Rechercher");
		searchBtn.setBackground(new Color(243, 232, 207));
		searchBtn.setFont(new Font("Noto Serif", Font.BOLD, 11));
		searchBtn.setBounds(735, 15, 100, 19);
		header.add(searchBtn);
		
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				body.removeAll();
				body.add(new Catalogue(searchField.getText()));
				body.repaint();
				body.revalidate();
			}
		});
		
		JPanel userAccount = new JPanel();
		userAccount.setBounds(850, 0, 150, 50);
		userAccount.setBackground(new Color(255, 255, 255));
		header.add(userAccount);
		userAccount.setLayout(null);
		
		JLabel userIcon = new JLabel(new ImageIcon("src/resources/images/logos/user.png"));
		userIcon.setBounds(10, 10, 32, 32);
		userAccount.add(userIcon);
		
		JLabel connectionStatus1 = new JLabel("Connexion");
		connectionStatus1.setHorizontalAlignment(SwingConstants.CENTER);
		connectionStatus1.setFont(new Font("Noto Serif", Font.BOLD, 13));
		connectionStatus1.setForeground(new Color(199, 152, 50));
		connectionStatus1.setBounds(52, 10, 90, 15);
		userAccount.add(connectionStatus1);
		
		JLabel connectionStatus2 = new JLabel("Inscription");
		connectionStatus2.setHorizontalAlignment(SwingConstants.CENTER);
		connectionStatus2.setFont(new Font("Noto Serif", Font.BOLD, 13));
		connectionStatus2.setForeground(new Color(199, 152, 50));
		connectionStatus2.setBounds(52, 25, 90, 15);
		userAccount.add(connectionStatus2);
		
		if (UserDAO.currentUser != null) {
			connectionStatus1.setText(UserDAO.currentUser.getPrenom());
			connectionStatus2.setText(UserDAO.currentUser.getNom());
		}
		
		userAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (UserDAO.currentUser != null) {
					accountMenu.setVisible(true);
				} else {
					body.removeAll();
					body.add(new Login());
					body.repaint();
					body.revalidate();					
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				userAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
				connectionStatus1.setForeground(new Color(155, 103, 25));
				connectionStatus2.setForeground(new Color(155, 103, 25));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				connectionStatus1.setForeground(new Color(199, 152, 50));
				connectionStatus2.setForeground(new Color(199, 152, 50));
			}
		});
		
		toAccountButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accountMenu.setVisible(false);
				body.removeAll();
				body.add(new Compte());
				body.repaint();
				body.revalidate();	
			}
		});
		
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				accountMenu.setVisible(false);
				Object[] options = {"Oui", "Non"};
				int choix = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment vous déconnecter ?", "Déconnexion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (choix == 0) {
					UserDAO.currentUser = null;
					Main.frame.getContentPane().removeAll();
					Main.frame.getContentPane().add(new Accueil());
					Main.frame.getContentPane().repaint();
					Main.frame.getContentPane().revalidate();
				}
			}
		});
		
		menuAuteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(new String[]{menuAuteurs.getText()}));
				body.repaint();
				body.revalidate();
			}
		});
		
		menuGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(new String[]{menuGenres.getText()}));
				body.repaint();
				body.revalidate();
			}
		});
		
		menuSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(new String[]{menuSeries.getText()}));
				body.repaint();
				body.revalidate();
			}
		});
		
		menuCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Catalogue(null));
				body.repaint();
				body.revalidate();
			}
		});
	}
}
