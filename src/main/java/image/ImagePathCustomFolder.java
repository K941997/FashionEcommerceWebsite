package image;

import java.io.File;
import java.io.FileReader;

public class ImagePathCustomFolder {
	
		File imagePathCustomFolder = new File("D:\\1_K941997_Backend_Java\\1_Apps_JavaJSPServlet\\ProjectEShopOnWeb\\src\\main\\webapp\\images");

		//Constructor:
		public ImagePathCustomFolder() {
			super();
		}

		public ImagePathCustomFolder(File imagePathCustomFolder) {
			super();
			this.imagePathCustomFolder = imagePathCustomFolder;
		}

		//Getter Setter:
		public File getImagePathCustomFolder() {
			return imagePathCustomFolder;
		}

		public void setImagePathCustomFolder(File imagePathCustomFolder) {
			this.imagePathCustomFolder = imagePathCustomFolder;
		}
}
