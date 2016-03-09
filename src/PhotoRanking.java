import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PhotoRanking extends JFrame {
	
	private static Model model;
	private static GamePanel gamePanel;
	
	public PhotoRanking() {
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PhotoRanking frame = new PhotoRanking(); // main
				model = new Model(); // model
				gamePanel = new GamePanel(); // view + controller
				model.setGamePanel(gamePanel);
				frame.setContentPane(gamePanel);
				frame.setMinimumSize(new Dimension(600, 600));
				
				frame.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						// resizer
					}
				});
			}
		}
	}
}
