package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;

public class SnookerBall extends CircleObject {
	private Color color;
	private boolean colliding = false;
	private boolean followMouse = false;
	private boolean hover = false;
	private boolean collidable = true;

	public SnookerBall(double x, double y,double radius,double mass,Color color,Game game) {
		super(x, y, radius,mass,ID.SnookerBall,game);
		this.color = color;
		
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFollowMouse() {
		return followMouse;
	}

	public void setFollowMouse(boolean followMouse) {
		this.followMouse = followMouse;
	}
	
	public boolean isColliding() {
		return colliding;
	}

	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}
	
	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		move();
		if(followMouse){
			moveToMouse();
		}
		if(collidable){
			collisions(objects);
		}
		
	}
	
	private void moveToMouse(){
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX()-5;
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY()-15;
		
		double xDistance = (mouseX-x)/20;
		double yDistance = (mouseY-y)/20;
		
		vX = xDistance;
		vY = yDistance;
		return;
	}

	@Override
	public void render(Graphics g) {
		if(colliding){
			g.setColor(Color.blue);
		}
		if(hover){
			g.setColor(Color.cyan);
			double outerRingRadius = radius*1.2;
			g.fillOval((int)(x-outerRingRadius),(int)(y-outerRingRadius),(int)outerRingRadius*2,(int)outerRingRadius*2);
			
			g.setColor(color);
			g.fillOval((int)(x-radius),(int)(y-radius),(int)radius*2,(int)radius*2);
			
			return;
		}
		else{
			g.setColor(color);
		}
		
		g.fillOval((int)(x-radius),(int)(y-radius),(int)radius*2,(int)radius*2);
		//g.drawLine((int)x, (int)y, (int)(x+vX*100), (int)(y+vY*100));
		
	
	}
	
	private void move(){
		vX*=0.9993;
		vY*=0.9993;
		x+=vX;
		y+=vY;
	}
	
	private void collisions(CopyOnWriteArrayList<GameObject> objects){
		colliding = false;
		
		for(GameObject obj:objects){
			ID type = obj.getId();
			
			switch(type){
			case SnookerBall:
				SnookerBall ball = (SnookerBall)obj;
				if(obj!=this){
					if(isColliding(ball)){
						if(!colliding){
							colliding = true;
							collide(ball);
						}
					}
				}
				break;
			case TableHole:
				TableHole hole = (TableHole)obj;
				if(isColliding(hole)){
					colliding = true;
					objects.remove(this);
				}
				break;
			
			case TableWall:
				TableWall wall = (TableWall)obj;
				if(isColliding(wall)){
					colliding = true;
					collide(wall);
				}
				break;
			
			case TableCorner:
				TableCorner corner = (TableCorner)obj;
				if(isColliding(corner)){
					colliding = true;
					collide(corner);
					
				}
				break;
			case Table:
				break;
			default:
				break;
			}
		}
	}

}
