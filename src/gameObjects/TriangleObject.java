package gameObjects;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.LinkedList;

import game.Game;
import game.ID;

public abstract class TriangleObject extends GameObject {
	protected Polygon polygon = new Polygon();
	protected double[] closestPoint;
	

	public TriangleObject(double x, double y, double width, double height, ID id, Game game) {
		super(x, y, width, height, id, game);
		
		this.polygon.addPoint((int)x,(int)y);
		this.polygon.addPoint((int)(x+width),(int)y);
		this.polygon.addPoint((int)x,(int)(y+height));
		
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public double[] getClosestPoint() {
		return closestPoint;
	}

	public void setClosestPoint(double[] closestPoint) {
		this.closestPoint = closestPoint;
	}
	
	
	
}
