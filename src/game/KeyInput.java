package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameObjects.SnookerBall;

public class KeyInput extends KeyAdapter {
	private Game game;
	public KeyInput(Game game){
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key){
		case 'f':
			SnookerBall cueBall = game.getCueSystem().getCueBall(game.getHandler().getObjects());
			cueBall.setFollowMouse(false);
			cueBall.setSelected(false);
			cueBall.setSelectable(true);
		}
	}
	
	

}
