package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import game.Game;
import game.ID;

public class TableWall extends RectangleObject {
	private Color color;

	public TableWall(double x, double y, double width, double height,double mass,Color color, Game game) {
		super(x, y, width, height,mass,ID.TableWall, game);
		this.color = color;
	}
	

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void update(LinkedList<GameObject> objects) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)(x-halfWidth), (int)(y-halfHeight), (int)(width), (int)(height));
		
	}

}
