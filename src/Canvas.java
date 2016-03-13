import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Canvas extends JPanel implements Observer {
	
	private Model model;
	private JScrollPane scrollPane;
	
	public Canvas(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public void loadImages() {
		this.removeAll();
		ArrayList<File> files = model.getImageFiles();
		System.out.println(files.size());
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			BufferedImage image;
			try {
				JPanel panel = new JPanel();
				image = ImageIO.read(file);
				Image scaledImage = image.getScaledInstance(this.getWidth() / 3, 150, 0);
				JButton imageButton = new JButton(new ImageIcon(scaledImage));
				imageButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JFrame imageWindow = new JFrame(file.getName());
						imageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						imageWindow.setVisible(true);
						JPanel imagePanel = new JPanel();
						imagePanel.add(new JLabel(new ImageIcon(file.getAbsolutePath())));
						imageWindow.setSize(new Dimension(image.getWidth(), image.getHeight()));
						imageWindow.setMaximumSize(new Dimension(800, 600));
						imageWindow.add(imagePanel);
					}
				});
				panel.add(imageButton);
				JLabel nameLabel = new JLabel(file.getName());
				panel.add(nameLabel);
				JLabel dateLabel = new JLabel(new Date(file.lastModified()).toString());
				panel.add(dateLabel);
				panel.setPreferredSize(new Dimension(this.getWidth() / 3, 300));
				this.add(panel);
				validate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void resizeImages() {
		
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
