import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<ImageObject> imageFiles;
	private boolean isGrid = false;
	private int stars = 0;

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
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
		setChanged();
		notifyObservers("stars");
	}
	
	public void resized(int width, int height) {
		setChanged();
		notifyObservers("resized");
	}
}
