import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<ImageObject> imageFiles;
	private boolean isGrid = true;
	private int stars = 0;
	private Canvas canvas;
	private PhotoRanking frame;
	
	public Model(PhotoRanking frame) {
		this.frame = frame;
	}
	
	public ArrayList<ImageObject> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(ArrayList<ImageObject> imageFiles) {
		this.imageFiles = imageFiles;
		setChanged();
		notifyObservers("imageFiles");
	}
	
	public boolean isGrid() {
		return isGrid;
	}

	public void setGrid(boolean isGrid) {
		this.isGrid = isGrid;
		setChanged();
		notifyObservers("grid");
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
		setChanged();
		notifyObservers("stars");
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public PhotoRanking getFrame() {
		return frame;
	}

	public void setFrame(PhotoRanking frame) {
		this.frame = frame;
	}

	public void resized(int width, int height) {
		canvas.resizePanels(width, height);
	}
}
