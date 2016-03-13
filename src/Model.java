import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<File> imageFiles;

	public ArrayList<File> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(ArrayList<File> imageFiles) {
		this.imageFiles = imageFiles;
		setChanged();
		notifyObservers("imageFiles");
	}
}
