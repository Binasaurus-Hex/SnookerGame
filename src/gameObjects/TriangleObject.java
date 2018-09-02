package gameObjects;

import java.awt.Polygon;
import game.Game;
import game.ID;

public abstract class TriangleObject extends GameObject {
	protected Polygon polygon = new Polygon();
	protected double normal;
	protected double[] closestPoint = new double[2];
	

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

	public double getNormal() {
		return normal;
	}

	public void setNormal(double normal) {
		this.normal = normal;
	}

	public double[] getClosestPoint() {
		return closestPoint;
	}

	public void setClosestPoint(double[] closestPoint) {
		this.closestPoint = closestPoint;
	}
	
	

	
	
	
	
}
