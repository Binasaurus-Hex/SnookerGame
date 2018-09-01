package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import game.Image;

public class Table extends GameObject {
	private BufferedImage image;

	public Table(double x, double y, double width, double height, Game game) {
		super(x, y, width, height, ID.Table, game);
		this.image = Image.loadImage("res\\snookertable.png");
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image,(int)(x),(int)(y),(int)(width),(int)(height), null);

	}

}
