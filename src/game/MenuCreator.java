package game;

import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;

public class MenuCreator {
	private Game game;
	public MenuCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getMainMenuObjects(){
		Activateable behaviour = (game)-> game.controlMode = ControlMode.Cue;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		
	}

}
