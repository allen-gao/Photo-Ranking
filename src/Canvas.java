import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Canvas extends JPanel implements Observer {
	
	private Model model;
	private JScrollPane scrollPane;
	
	public Canvas(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public void loadImages() {
		ArrayList<File> files = model.getImageFiles();
		for (int i = 0; i < files.size(); i++) {
			Image image;
			try {
				image = ImageIO.read(files.get(i));
				JButton imageButton = new JButton(new ImageIcon(image));
				this.add(imageButton);
				validate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "imageFiles") {
			loadImages();
		}
	}
}
