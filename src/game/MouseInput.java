package game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;
import gameObjects.SnookerBall;

public class MouseInput extends MouseAdapter {
	private Game game;
	public MouseInput(Game game){
		this.game = game;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		switch(game.currentState){
		case Game:
			CueSystem cueSystem = game.getCueSystem();
			double mouseX = e.getX();
			double mouseY = e.getY();
			CopyOnWriteArrayList<GameObject> objects = game.getHandler().getObjects();
			SnookerBall cueBall = cueSystem.getCueBall(objects);
			break;
		case MainMenu:
			//main menu code
			break;
		case PauseMenu:
			//pause menu code
			break;
		}
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
	}

}
