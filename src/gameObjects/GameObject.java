package gameObjects;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public abstract class GameObject {
	//setup by the constructor
	protected double x,y,width,height;
	protected ID id;
	
	//created for collision detection purposes
	protected double top,bottom,left,right;
	protected double halfWidth,halfHeight;
	public boolean visible = false;
	
	protected Game game;
	
	public GameObject(double x,double y,double width,double height,ID id,Game game){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		halfWidth = width/2;
		halfHeight = height/2;
		
		this.top = y-halfHeight;
		this.bottom = y+halfHeight;
		this.left = x-halfWidth;
		this.right = x+halfWidth;
		
		this.game = game;
	}
	
	
	
	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public ID getId() {
		return id;
	}



	public void setId(ID id) {
		this.id = id;
	}



	public double getTop() {
		return top;
	}
	

	public double getBottom() {
		return bottom;
	}


	public double getLeft() {
		return left;
	}


	public double getRight() {
		return right;
	}



	public double getHalfWidth() {
		return halfWidth;
	}



	public double getHalfHeight() {
		return halfHeight;
	}



	//updates the object(all logic code goes here)
	public abstract void update(CopyOnWriteArrayList<GameObject> objects);
	//renders the object onto the screen
	public abstract void render(Graphics g);

}
