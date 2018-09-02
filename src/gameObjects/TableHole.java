package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class TableHole extends CircleObject {
	private Color color;

	public TableHole(double x, double y, double radius,double mass, Color color, Game game) {
		super(x, y, radius,mass, ID.TableHole, game);
		this.color = color;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval((int)(x-radius),(int)(y-radius),(int)width,(int)height);
		
	}

}
