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
	
	public CopyOnWriteArrayList<GameObject> getMainMenuObjects(){
		CopyOnWriteArrayList<GameObject> menuObjects = new CopyOnWriteArrayList<GameObject>();
		
		menuObjects.addAll(getButtons());
		menuObjects.addAll(getLabels());
		
		return menuObjects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		
		MenuButton start = new MenuButton(500,100,300,100,MenuID.PlayGame,game);
		start.setVisible(true);
		start.setSelectable(true);
		buttons.add(start);
		
		MenuButton controls = new MenuButton(500,250,300,100,MenuID.Controls,game);
		controls.setSelectable(true);
		controls.setVisible(true);
		buttons.add(controls);
		
		MenuButton exit = new MenuButton(500,400,300,100,MenuID.Exit,game);
		exit.setSelectable(true);
		exit.setVisible(true);
		buttons.add(exit);
		
		return buttons;
	}
	
	private CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		return labels;
	}

}
