import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;

public class NorthPanel extends JPanel implements Observer {
	
	Model model;
	
	public NorthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
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
					ArrayList<File> files = new ArrayList<File>(Arrays.asList(chooser.getSelectedFiles()));
					model.setImageFiles(files);
				}
			}
		});
		this.add(openButton, BorderLayout.WEST);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
