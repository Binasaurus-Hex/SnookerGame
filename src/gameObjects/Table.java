package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import game.Game;
import game.ID;
import game.ImageLoader;

public class Table extends GameObject {
	private BufferedImage image;
	private BufferedImage sticker;

	public Table(double x, double y, double width, double height, Game game) {
		super(x, y, width, height, ID.Table, game);
		ImageLoader imageLoad = new ImageLoader();
		image = imageLoad.loadImage("/snookertable.png");
		sticker = imageLoad.loadImage("/toromaruPic.jpg");
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		Font font1 = new Font("Impact",Font.PLAIN,20);
		g.setFont(font1);
		g.setColor(Color.red);
		String fps = "FPS: "+String.valueOf(game.getFps());
		g.drawImage(image,(int)(x),(int)(y),(int)(width),(int)(height), null);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 0.6);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(ac);
		g2d.drawImage(sticker,500,100,null);
		AlphaComposite standard = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
		g2d.setComposite(standard);
		g.drawString(fps, 100, 30);

	}


}
