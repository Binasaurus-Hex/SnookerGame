package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;
import game.ControlMode;
import game.Game;
import game.ID;
import game.Sound;

public class SnookerBall extends CircleObject {
	private Color color;
	private boolean colliding = false;
	private boolean hover = false;
	private boolean collidable = true;
	private boolean selectable = true;
	private boolean selected = false;
	private boolean moveable = true;
	private Point mouse = new Point();
	Sound snookerSound = new Sound("/ballHit.wav");

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
		if(moveable){
			move();
		}
		if(selected && game.controlMode == ControlMode.Mouse){
			moveToPoint(mouse);
		}
		
		if(collidable){
			collisions(objects);
		}
		
	}
	
	private void moveToPoint(Point mouse){
		double mouseX = mouse.getX();
		double mouseY = mouse.getY();
		
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
		}
		g.setColor(color);
		g.fillOval((int)(x-radius),(int)(y-radius),(int)radius*2,(int)radius*2);
		if(selected && game.controlMode == ControlMode.Cue){
			g.drawLine((int)x, (int)y, (int)(x+(vX*100)), (int)(y+(vY*100)));
		}
	}
	
	private void move(){
		double deceleration = 0.0017;
		vX*=1-deceleration;
		vY*=1-deceleration;
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
							snookerSound.play();
						}
					}
				}
				break;
			case TableHole:
				TableHole hole = (TableHole)obj;
				if(isColliding(hole)){
					game.score++;
					System.out.println(game.score);
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
	
	public void grab(){
		switch(game.controlMode){
		case Mouse:
			selectable = false;
			selected = true;
			hover = true;
			break;
		case Cue:
			moveable = false;
			selectable = false;
			selected = true;
			hover = true;
			break;
		}
	}
	
	public void release(){
		switch(game.controlMode){
		case Mouse:
			selectable = true;
			selected = false;
			hover = false;
			break;
		case Cue:
			moveable = true;
			selectable = true;
			selected = false;
			hover = false;
			break;
		}
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setMouse(Point mouse) {
		this.mouse = mouse;
	}

}
