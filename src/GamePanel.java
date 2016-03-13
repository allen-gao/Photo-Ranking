import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GamePanel extends JPanel {
	
	private Model model;
	private NorthPanel northPanel;
	private Canvas canvas;
	
	public GamePanel(Model model) {
		this.model = model;
		northPanel = new NorthPanel(model);
		canvas = new Canvas(model);
		
		JScrollPane scrollPane = new JScrollPane(canvas);
		canvas.setScrollPane(scrollPane);
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void resized() {
	}
}
