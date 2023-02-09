package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JMenuItem menuGenres = new JMenuItem("Auteurs");
		menuBtn.add(menuGenres);
		
		JMenuItem menuAuteurs = new JMenuItem("Genres");
		menuBtn.add(menuAuteurs);
		
		JMenuItem menuSeries = new JMenuItem("Series");
		menuBtn.add(menuSeries);
		
		JPanel body = new JPanel();
		body.setBackground(new Color(240, 227, 198));
		body.setBounds(0, 50, 1000, 550);
		add(body);
		body.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(248, 243, 231));
		header.setBounds(0, 0, 1000, 50);
		add(header);
		header.setLayout(null);
		
		JLabel logo = new JLabel(new ImageIcon("src/images/home_30x30.png"));
		logo.setBounds(10, 10, 32, 32);
		header.add(logo);
		
		JLabel homeName = new JLabel("Bibliothèque");
		homeName.setVerticalAlignment(JLabel.CENTER);
		homeName.setFont(new Font("Noto Serif", Font.PLAIN, 25));
		homeName.setBounds(50, 0, 175, 50);
		header.add(homeName);
		
		searchField = new JTextField();
		searchField.setBounds(435, 15, 300, 20);
		header.add(searchField);
		searchField.setColumns(10);
		
		JButton searchBtn = new JButton("Rechercher");
		searchBtn.setBackground(new Color(243, 232, 207));
		searchBtn.setFont(new Font("Noto Serif", Font.BOLD, 11));
		searchBtn.setBounds(735, 15, 100, 19);
		header.add(searchBtn);
		
		JPanel userAccount = new JPanel();
		userAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				body.removeAll();
				body.add(new Login());
				body.repaint();
				body.revalidate();
			}
		});
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

	}
}
