package UI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import gameObjects.GameObject;

public class UI_Object extends GameObject{

	public UI_Object(double x, double y, double width, double height, ID id, Game game) {
		super(x, y, width, height, id, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isColliding(Point point){
		Rectangle rect = new Rectangle();
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
		if(rect.contains(point)){
			return true;
		}
		else{
			return false;
		}
	}
	
	

}
