import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PhotoRanking extends JFrame {
	
	private static int width = 600;
	private static int height = 600;
	private static int minWidth = 400;
	private static int minHeight = 400;
	private static Model model;
	private static GamePanel gamePanel;
	
	public PhotoRanking() {
		super();
		this.setTitle("Photo Ranking");
		this.setSize(width, height);
		this.getContentPane().setLayout(new GridLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PhotoRanking frame = new PhotoRanking(); // main
				model = new Model(); // model
				gamePanel = new GamePanel(model); // view + controller
				frame.setContentPane(gamePanel);
				frame.setMinimumSize(new Dimension(minWidth, minHeight));
				frame.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						// resizer
					}
				});
			}
		});
	}
}
