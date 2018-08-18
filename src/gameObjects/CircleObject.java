package gameObjects;

import Physics.Collision;
import game.Game;
import game.ID;

public abstract class CircleObject extends GameObject {
	protected double vX,vY;
	protected double radius;
	protected double mass;

	public CircleObject(double x, double y, double radius,double mass, ID id,Game game) {
		super(x, y, radius*2,radius*2, id,game);
		this.radius = radius;
		this.mass = mass;
	}

	public double getvX() {
		return vX;
	}


	public void setvX(double vX) {
		this.vX = vX;
	}


	public double getvY() {
		return vY;
	}


	public void setvY(double vY) {
		this.vY = vY;
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public boolean isColliding(CircleObject circle){
		return Collision.isColliding(this,circle);
	}
	
	public boolean isColliding(RectangleObject rect){
		return Collision.isColliding(this,rect);
	}
	
	public boolean isColliding(TriangleObject triangle){
		return Collision.isColliding(this, triangle);
	}
	
	public void collide(CircleObject circle){
		Collision.collide(this, circle);
	}
	
	public void collide(RectangleObject rect){
		Collision.collide(this, rect);
	}
	
	public void collide(TriangleObject triangle){
		Collision.collide(this, triangle);
	}
	

}
