package game;

import java.awt.Color;
import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;
import gameObjects.SnookerBall;

public class CueSystem {
	private Point lastMouse;
	private Point currentMouse;
	private boolean mousePressed;
	private boolean mouseReleased;
	
	
	public CueSystem(){
		
	}
	
	public Point getLastMouse() {
		return lastMouse;
	}



	public void setLastMouse(Point lastMouse) {
		this.lastMouse = lastMouse;
	}



	public Point getCurrentMouse() {
		return currentMouse;
	}



	public void setCurrentMouse(Point currentMouse) {
		this.currentMouse = currentMouse;
	}



	public boolean isMousePressed() {
		return mousePressed;
	}



	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}



	public boolean isMouseReleased() {
		return mouseReleased;
	}



	public void setMouseReleased(boolean mouseReleased) {
		this.mouseReleased = mouseReleased;
	}



	public SnookerBall getCueBall(CopyOnWriteArrayList<GameObject> objects){
		for(GameObject obj: objects){
			if(obj.getId()==ID.SnookerBall){
				SnookerBall ball = (SnookerBall)obj;
				if(ball.getColor()==Color.white){
					return ball;
				}
			}
		}
		return null;
	}
	
	
	

}
