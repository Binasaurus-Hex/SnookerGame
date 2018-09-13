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
	
	public CopyOnWriteArrayList<GameObject> getPauseMenuObjects(){
		CopyOnWriteArrayList<GameObject> menuObjects = new CopyOnWriteArrayList<GameObject>();
		menuObjects.addAll(getButtons());
		menuObjects.addAll(getLabels());
		
		return menuObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		MenuButton resume = new MenuButton(500,250,300,100,MenuID.Resume,game);
		resume.setSelectable(true);
		resume.setVisible(true);
		MenuButton menu = new MenuButton(500,400,300,100,MenuID.Menu,game);
		menu.setSelectable(true);
		menu.setVisible(true);
		
		buttons.add(resume);
		buttons.add(menu);
		
		return buttons;
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		
		
		return labels;
	}

}
