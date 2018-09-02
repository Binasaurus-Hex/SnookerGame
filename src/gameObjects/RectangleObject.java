package gameObjects;

import Physics.Collision;
import game.Game;
import game.ID;

public abstract class RectangleObject extends GameObject{
	protected double vX,vY;
	protected double mass;

	public RectangleObject(double x, double y, double width, double height,double mass, ID id,Game game) {
		super(x, y, width, height, id,game);
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
	
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public boolean isColliding(CircleObject circle){
		return Collision.isColliding(circle,this);
	}
	
	public boolean isColliding(RectangleObject rect){
		return false;
		//TODO
	}

}
