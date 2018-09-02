package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class TableCorner extends TriangleObject {

	public TableCorner(double x, double y, double width, double height, Game game) {
		super(x, y, width, height, ID.TableCorner, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawPolygon(polygon);
	}

}
