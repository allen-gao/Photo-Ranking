import java.io.File;

public class ImageObject {
	private File file;
	private int rating;
	
	public ImageObject(File file, int rating) {
		this.file = file;
		this.rating = rating;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
