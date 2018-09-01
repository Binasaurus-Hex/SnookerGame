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
