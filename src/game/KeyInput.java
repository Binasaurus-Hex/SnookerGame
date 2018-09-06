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
		int keyCode = e.getKeyCode();
		String key = KeyEvent.getKeyText(keyCode);
		SnookerBall cueBall = game.getCueSystem().getCueBall();
		switch(key){
		case "F":
			cueBall.release();
			break;
		case "Escape":
			game.menu();
			break;
		case "B":
			game.reset();
			break;
		case "M":
			if(cueBall.isSelected())break;
			if(game.controlMode == ControlMode.Cue){
				game.controlMode = ControlMode.Mouse;
			}
			else{
				game.controlMode = ControlMode.Cue;
			}
		
		}
		
	}
	
	

}
