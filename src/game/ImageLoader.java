package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public ImageLoader(){
		
	}
	
	public BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
