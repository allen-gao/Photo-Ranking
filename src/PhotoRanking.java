import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PhotoRanking extends JFrame {
	
	private static int width = 800;
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
				model = new Model(frame); // model
				gamePanel = new GamePanel(model); // view + controller
				frame.setContentPane(gamePanel);
				frame.setMinimumSize(new Dimension(minWidth, minHeight));
				frame.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						model.resized(frame.getWidth(), frame.getHeight());
					}
				});
				
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						model.save();
					}
				});
				
				File file = new File(System.getProperty("user.dir") + "/storage.bin");
				if (file.exists()) {
					try {
						FileInputStream fileIn = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						BinaryModel bin = (BinaryModel)in.readObject();
						model.setImageFiles(bin.imageFiles);
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
}
