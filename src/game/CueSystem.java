package game;

import java.awt.Color;
import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;
import gameObjects.SnookerBall;

public class CueSystem {
	private Game game;
	
	
	public CueSystem(Game game){
		this.game = game;
	}
	
	public SnookerBall getCueBall(){
		CopyOnWriteArrayList<GameObject> objects = game.getHandler().getObjects();
		for(GameObject obj: objects){
			if(obj.getId()==ID.SnookerBall){
				SnookerBall ball = (SnookerBall)obj;
				if((ball.getColor()==Color.white)){
					return ball;
				}
			}
		}
		return null;
	}
	
	public void hitCueBall(Point mouse){
		SnookerBall cueBall = getCueBall();
		double distanceX = cueBall.getX()-mouse.getX();
		double distanceY = cueBall.getY()-mouse.getY();
		
		double newX = distanceX/100;
		double newY = distanceY/100;
		
		cueBall.setvX(newX);
		cueBall.setvY(newY);
		
	}
}
