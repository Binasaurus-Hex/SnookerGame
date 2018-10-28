package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import gameObjects.GameObject;

public class PauseMenuCreator {
	private Game game;
	
	public PauseMenuCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> menuObjects = new CopyOnWriteArrayList<GameObject>();
		menuObjects.addAll(getButtons());
		menuObjects.addAll(getLabels());
		
		return menuObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		
		buttons.add(new MenuButton(500,250,300,100,MenuID.Resume,game));
		buttons.add(new MenuButton(500,400,300,100,MenuID.Menu,game));
		
		return buttons;
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		
		
		return labels;
	}

}
