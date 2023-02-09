package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2875626128795039613L;
	private JPanel contentPane;
	protected static Main frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
					frame.getContentPane().setPreferredSize(new Dimension(1000, 600));
					frame.pack();
					frame.getContentPane().removeAll();
					frame.getContentPane().add(new Accueil());
					frame.getContentPane().repaint();
					frame.getContentPane().revalidate();
					
					frame.setLocationRelativeTo(null);
					
					frame.setTitle("Bibliothèque");
					
					List<Image> icons = new ArrayList<Image>();
					icons.add(new ImageIcon("src/images/home_16x16.png").getImage());
					icons.add(new ImageIcon("src/images/home_32x32.png").getImage());
					icons.add(new ImageIcon("src/images/home_64x64.png").getImage());
					icons.add(new ImageIcon("src/images/home_128x128.png").getImage());
					frame.setIconImages(icons);
					
					InputStream fontFile = null;
					Font noto = null;
					try {
						fontFile = new BufferedInputStream(Files.newInputStream(Paths.get("src/font/NotoSerif-Regular.ttf")));
						try {
							noto = Font.createFont(Font.TRUETYPE_FONT, fontFile);
							GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
							genv.registerFont(noto);
						} catch (FontFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 0, 0)));
		panel.setBackground(new Color(255, 128, 128));
		panel.setBounds(0, 0, 600, 400);
		contentPane.add(panel);
		
	}

}
