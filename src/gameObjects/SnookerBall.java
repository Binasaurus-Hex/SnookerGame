package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.LinkedList;

import Physics.Collision;
import game.Game;
import game.ID;

public class SnookerBall extends CircleObject {
	private Color color;
	private boolean colliding = false;
	private boolean followMouse = false;

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

	@Override
	public void update(LinkedList<GameObject> objects) {
		move();
		
	
		if(followMouse){
		
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX()-5;
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY()-15;
		
		double xDistance = (mouseX-x)/20;
		double yDistance = (mouseY-y)/20;
		
		vX = xDistance;
		vY = yDistance;
		return;
			
		}
		collisions(objects);
		if((x-radius<0)||(x+radius>game.getWindowWidth())){
			vX*=-1;
			colliding = true;
		}
		if((y-radius<0)||(y+radius>game.getWindowHeight())){
			vY*=-1;
			colliding = true;
		}
	}

	@Override
	public void render(Graphics g) {
		if(colliding){
			g.setColor(Color.blue);
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
	
	private void collisions(LinkedList<GameObject> objects){
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
					//collide(hole);
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
			case Table:
				break;
			default:
				break;
			}
		}
	}

}
