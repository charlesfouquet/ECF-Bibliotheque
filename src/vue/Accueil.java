package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798845607393016064L;
	private JTextField searchField;

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
		toCatalog.setFont(new Font("Noto Serif", Font.PLAIN, 15));
		toCatalog.setBounds(350, 470, 300, 40);
		body.add(toCatalog);
		
		JLabel newBook1 = new JLabel(new ImageIcon(new ImageIcon("src/images/bookcover1.jpg").getImage().getScaledInstance(175, 245, Image.SCALE_SMOOTH)));
		newBook1.setBounds(60, 120, 175, 245);
		body.add(newBook1);
		
		JLabel newBook2 = new JLabel(new ImageIcon(new ImageIcon("src/images/bookcover2.jpg").getImage().getScaledInstance(175, 245, Image.SCALE_SMOOTH)));
		newBook2.setBounds(295, 120, 175, 245);
		body.add(newBook2);
		
		JLabel newBook3 = new JLabel(new ImageIcon(new ImageIcon("src/images/bookcover3.jpg").getImage().getScaledInstance(175, 245, Image.SCALE_SMOOTH)));
		newBook3.setBounds(530, 120, 175, 245);
		body.add(newBook3);
		
		JLabel newBook4 = new JLabel(new ImageIcon(new ImageIcon("src/images/bookcover4.jpg").getImage().getScaledInstance(175, 245, Image.SCALE_SMOOTH)));
		newBook4.setBounds(765, 120, 175, 245);
		body.add(newBook4);
		
		JLabel newBookTitle1 = new JLabel("Harry Potter à l'école des Sorciers");
		newBookTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		newBookTitle1.setVerticalAlignment(SwingConstants.CENTER);
		newBookTitle1.setBounds(60, 375, 175, 20);
		body.add(newBookTitle1);
		
		JLabel newBookTitle2 = new JLabel("Harry Potter et la Chambre des Secrets");
		newBookTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		newBookTitle2.setVerticalAlignment(SwingConstants.CENTER);
		newBookTitle2.setBounds(295, 375, 175, 20);
		body.add(newBookTitle2);
		
		JLabel newBookTitle3 = new JLabel("Harry Potter et le Prisonnier d'Azkaban");
		newBookTitle3.setHorizontalAlignment(SwingConstants.CENTER);
		newBookTitle3.setVerticalAlignment(SwingConstants.CENTER);
		newBookTitle3.setBounds(530, 375, 175, 20);
		body.add(newBookTitle3);
		
		JLabel newBookTitle4 = new JLabel("Harry Potter et la Coupe de Feu");
		newBookTitle4.setHorizontalAlignment(SwingConstants.CENTER);
		newBookTitle4.setVerticalAlignment(SwingConstants.CENTER);
		newBookTitle4.setBounds(765, 375, 175, 20);
		body.add(newBookTitle4);
		
		newBookTitle1.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		newBookTitle2.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		newBookTitle3.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		newBookTitle4.setFont(new Font("Noto Serif", Font.PLAIN, 13));
		
		JLabel newBookAuthor1 = new JLabel("J.K Rowling");
		newBookAuthor1.setHorizontalAlignment(SwingConstants.CENTER);
		newBookAuthor1.setVerticalAlignment(SwingConstants.CENTER);
		newBookAuthor1.setBounds(60, 405, 175, 20);
		body.add(newBookAuthor1);
		
		JLabel newBookAuthor2 = new JLabel("J.K Rowling");
		newBookAuthor2.setHorizontalAlignment(SwingConstants.CENTER);
		newBookAuthor2.setVerticalAlignment(SwingConstants.CENTER);
		newBookAuthor2.setBounds(295, 405, 175, 20);
		body.add(newBookAuthor2);
		
		JLabel newBookAuthor3 = new JLabel("J.K Rowling");
		newBookAuthor3.setHorizontalAlignment(SwingConstants.CENTER);
		newBookAuthor3.setVerticalAlignment(SwingConstants.CENTER);
		newBookAuthor3.setBounds(530, 405, 175, 20);
		body.add(newBookAuthor3);
		
		JLabel newBookAuthor4 = new JLabel("J.K Rowling");
		newBookAuthor4.setHorizontalAlignment(SwingConstants.CENTER);
		newBookAuthor4.setVerticalAlignment(SwingConstants.CENTER);
		newBookAuthor4.setBounds(765, 405, 175, 20);
		body.add(newBookAuthor4);
		
		newBookAuthor1.setFont(new Font("Noto Serif", Font.PLAIN, 11));
		newBookAuthor2.setFont(new Font("Noto Serif", Font.PLAIN, 11));
		newBookAuthor3.setFont(new Font("Noto Serif", Font.PLAIN, 11));
		newBookAuthor4.setFont(new Font("Noto Serif", Font.PLAIN, 11));
		
		JPanel header = new JPanel();
		header.setBackground(new Color(248, 243, 231));
		header.setBounds(0, 0, 1000, 50);
		add(header);
		header.setLayout(null);
		
		JLabel logo = new JLabel(new ImageIcon("src/images/home_30x30.png"));
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
		
		JLabel userIcon = new JLabel(new ImageIcon("src/images/user.png"));
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
		
		userAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				body.removeAll();
				body.add(new Login());
				body.repaint();
				body.revalidate();
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
		
		menuAuteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(menuAuteurs.getText()));
				body.repaint();
				body.revalidate();
			}
		});
		
		menuGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(menuGenres.getText()));
				body.repaint();
				body.revalidate();
			}
		});
		
		menuSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				body.removeAll();
				body.add(new Tri(menuSeries.getText()));
				body.repaint();
				body.revalidate();
			}
		});

	}
}
