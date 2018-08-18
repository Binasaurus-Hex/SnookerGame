package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	public static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
