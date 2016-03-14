import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Canvas extends JPanel implements Observer {
	
	private Model model;
	private JScrollPane scrollPane;
	private ArrayList<JPanel> panelArray;
	
	private int iconWidth = 180;
	private int iconHeight = 150;
	private int panelHeight = 250;
	
	public Canvas(Model model) {
		this.model = model;
		model.setCanvas(this);
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public void loadImages() {
		this.removeAll();
		ArrayList<ImageObject> imageObjects = model.getImageFiles();
		if (imageObjects == null) {
			return;
		}
		for (int i = 0; i < imageObjects.size(); i++) {
			ImageObject imageObject = imageObjects.get(i);
			if (imageObject.getRating() < model.getStars()) {
				continue;
			}
			try {
				JPanel panel = new JPanel();
				BufferedImage image = ImageIO.read(imageObject.getFile());
				Image scaledImage = image.getScaledInstance(iconWidth, iconHeight, 0);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension((this.getWidth()/3) - 5, panelHeight));
				JButton imageButton = new JButton(new ImageIcon(scaledImage));
				imageButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JFrame imageWindow = new JFrame(imageObject.getFile().getName());
						imageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						imageWindow.setVisible(true);
						JPanel imagePanel = new JPanel();
						imagePanel.add(new JLabel(new ImageIcon(imageObject.getFile().getAbsolutePath())));
						imageWindow.setSize(new Dimension(image.getWidth(), image.getHeight()));
						imageWindow.setMaximumSize(new Dimension(800, 600));
						imageWindow.add(imagePanel);
					}
				});
				panel.add(imageButton);
				imageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				JLabel nameLabel = new JLabel(imageObject.getFile().getName());
				nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(nameLabel);
				JLabel dateLabel = new JLabel(new Date(imageObject.getFile().lastModified()).toString());
				dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(dateLabel);
				ArrayList<JButton> starButtonArray = new ArrayList<JButton>();
				JPanel starPanel = new JPanel();
				JButton star1 = new JButton();
				JButton star2 = new JButton();
				JButton star3 = new JButton();
				JButton star4 = new JButton();
				JButton star5 = new JButton();
				starPanel.add(star1);
				starPanel.add(star2);
				starPanel.add(star3);
				starPanel.add(star4);
				starPanel.add(star5);
				starButtonArray.add(star1);
				starButtonArray.add(star2);
				starButtonArray.add(star3);
				starButtonArray.add(star4);
				starButtonArray.add(star5);
				star1.setPreferredSize(new Dimension(25, 25));
				star1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (imageObject.getRating() == 1) {
							imageObject.setRating(0);
						}
						else {
							imageObject.setRating(1);
						}
						model.setImageFiles(model.getImageFiles());
					}
				});
				star2.setPreferredSize(new Dimension(25, 25));
				star2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (imageObject.getRating() == 2) {
							imageObject.setRating(0);
						}
						else {
							imageObject.setRating(2);
						}
						model.setImageFiles(model.getImageFiles());
					}
				});
				star3.setPreferredSize(new Dimension(25, 25));
				star3.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (imageObject.getRating() == 3) {
							imageObject.setRating(0);
						}
						else {
							imageObject.setRating(3);
						}
						model.setImageFiles(model.getImageFiles());
					}
				});
				star4.setPreferredSize(new Dimension(25, 25));
				star4.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (imageObject.getRating() == 4) {
							imageObject.setRating(0);
						}
						else {
							imageObject.setRating(4);
						}
						model.setImageFiles(model.getImageFiles());
					}
				});
				star5.setPreferredSize(new Dimension(25, 25));
				star5.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (imageObject.getRating() == 5) {
							imageObject.setRating(0);
						}
						else {
							imageObject.setRating(5);
						}
						model.setImageFiles(model.getImageFiles());
					}
				});
				starPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(starPanel);
				try {
					Image emptyStar = ImageIO.read(new File("src/images/star-empty.png"));
					Image fullStar = ImageIO.read(new File("src/images/star-full.png"));
					int stars = imageObject.getRating();
					for (int j = 0; j < starButtonArray.size(); j++) {
						if (stars >= j+1) {
							starButtonArray.get(j).setIcon(new ImageIcon(fullStar));
						}
						else {
							starButtonArray.get(j).setIcon(new ImageIcon(emptyStar));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.add(panel);
				if (panelArray == null) {
					panelArray = new ArrayList<JPanel>();
				}
				panelArray.add(panel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scrollPane.revalidate();
		validate();
		repaint();
	}
	
	public void resizePanels(int width, int height) {
		width -= 30;
		if (panelArray != null) {
			int cols;
			if (width >= iconWidth * 3 + 150) {
				cols = 3;
			}
			else if (width >= iconWidth * 2 + 100) {
				cols = 2;
			}
			else {
				cols = 1;
			}
			int rows = (int)Math.ceil((double)panelArray.size() / (double)cols);
			if (panelArray != null) {
				for (int i = 0; i < panelArray.size(); i++) {
					JPanel panel = panelArray.get(i);
					panel.setPreferredSize(new Dimension(width/cols - 5, panelHeight));
				}
			}
			this.setPreferredSize(new Dimension(width, (rows * panelHeight) + (rows * 5)));
			scrollPane.revalidate();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "imageFiles" || arg == "stars") {
			loadImages();
		}
	}
}
