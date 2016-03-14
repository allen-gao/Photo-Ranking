import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanel extends JPanel implements Observer {
	
	private Model model;
	private ArrayList<JButton> starButtonArray;
	
	public NorthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton openButton = new JButton();
		try {
			Image openIcon = ImageIO.read(new File("src/images/folder-open.png"));
			openButton.setIcon(new ImageIcon(openIcon));
			openButton.setPreferredSize(new Dimension(25, 25));
		} catch (IOException e) {
			openButton.setText("Open");
		}
		openButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ArrayList<File> fileArray = new ArrayList<File>(Arrays.asList(chooser.getSelectedFiles()));
					ArrayList<ImageObject> imageObjects = new ArrayList<ImageObject>();
					for (int i = 0; i < fileArray.size(); i++) {
						ImageObject imageObject = new ImageObject(fileArray.get(i), 0);
						imageObjects.add(imageObject);
					}
					if (model.getImageFiles() != null) {
						model.getImageFiles().addAll(imageObjects);
						model.setImageFiles(model.getImageFiles()); // to trigger observer
					}
					else {
						model.setImageFiles(imageObjects);
					}
				}
			}
		});
		this.add(openButton);
		
		JLabel ratingLabel = new JLabel("Filter by:");
		this.add(ratingLabel);
		
		starButtonArray = new ArrayList<JButton>();
		JButton star1 = new JButton();
		JButton star2 = new JButton();
		JButton star3 = new JButton();
		JButton star4 = new JButton();
		JButton star5 = new JButton();
		starButtonArray.add(star1);
		starButtonArray.add(star2);
		starButtonArray.add(star3);
		starButtonArray.add(star4);
		starButtonArray.add(star5);
		try {
			Image emptyStar = ImageIO.read(new File("src/images/star-empty.png"));
			star1.setIcon(new ImageIcon(emptyStar));
			star2.setIcon(new ImageIcon(emptyStar));
			star3.setIcon(new ImageIcon(emptyStar));
			star4.setIcon(new ImageIcon(emptyStar));
			star5.setIcon(new ImageIcon(emptyStar));
		} catch (IOException e) {
			e.printStackTrace();
		}
		star1.setPreferredSize(new Dimension(25, 25));
		star1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (model.getStars() == 1) {
					model.setStars(0);
				}
				else {
					model.setStars(1);
				}
			}
		});
		this.add(star1);
		star2.setPreferredSize(new Dimension(25, 25));
		star2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (model.getStars() == 2) {
					model.setStars(0);
				}
				else {
					model.setStars(2);
				}
			}
		});
		this.add(star2);
		star3.setPreferredSize(new Dimension(25, 25));
		star3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (model.getStars() == 3) {
					model.setStars(0);
				}
				else {
					model.setStars(3);
				}
			}
		});
		this.add(star3);
		star4.setPreferredSize(new Dimension(25, 25));
		star4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (model.getStars() == 4) {
					model.setStars(0);
				}
				else {
					model.setStars(4);
				}
			}
		});
		this.add(star4);
		star5.setPreferredSize(new Dimension(25, 25));
		star5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (model.getStars() == 5) {
					model.setStars(0);
				}
				else {
					model.setStars(5);
				}
			}
		});
		this.add(star5);
		
		JButton gridButton = new JButton();
		try {
			Image gridIcon = ImageIO.read(new File("src/images/grid-layout.png"));
			gridIcon = gridIcon.getScaledInstance(25, 25, 0);
			gridButton.setIcon(new ImageIcon(gridIcon));
			gridButton.setPreferredSize(new Dimension(25, 25));
		} catch (IOException e) {
			gridButton.setText("Grid");
		}
		gridButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setGrid(true);
			}
		});
		this.add(gridButton);
		
		JButton listButton = new JButton();
		try {
			Image listIcon = ImageIO.read(new File("src/images/list-layout.png"));
			listIcon = listIcon.getScaledInstance(25, 25, 0);
			listButton.setIcon(new ImageIcon(listIcon));
			listButton.setPreferredSize(new Dimension(25, 25));
		} catch (IOException e) {
			listButton.setText("List");
		}
		listButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setGrid(false);
			}
		});
		this.add(listButton);
	}
	
	public void setStars() {
		try {
			Image emptyStar = ImageIO.read(new File("src/images/star-empty.png"));
			Image fullStar = ImageIO.read(new File("src/images/star-full.png"));
			int stars = model.getStars();
			for (int i = 0; i < starButtonArray.size(); i++) {
				if (stars >= i+1) {
					starButtonArray.get(i).setIcon(new ImageIcon(fullStar));
				}
				else {
					starButtonArray.get(i).setIcon(new ImageIcon(emptyStar));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "stars") {
			setStars();
		}
	}
}
