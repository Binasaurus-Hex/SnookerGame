package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import gameObjects.GameObject;

public class MainMenuCreator {
	private Game game;
	public MainMenuCreator(Game game){
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
		
		buttons.add(new MenuButton(500,100,300,100,MenuID.PlayGame,game));
		buttons.add(new MenuButton(500,250,300,100,MenuID.Controls,game));
		buttons.add(new MenuButton(500,400,300,100,MenuID.Exit,game));
		
		return buttons;
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		return labels;
	}

}
