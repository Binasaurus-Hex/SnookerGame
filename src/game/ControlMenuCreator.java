package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class ControlMenuCreator {
	private Game game;
	
	public ControlMenuCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		objects.addAll(getButtons());
		objects.addAll(getLabels());
		return objects;
	}
	
	public CopyOnWriteArrayList<GameObject> getButtons(){
		CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		
		buttons.add(new MenuButton(500,400,300,100,MenuID.Menu,game));
		
		return buttons;
	}
	
	public CopyOnWriteArrayList<GameObject> getLabels(){
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		
		MenuLabel controlsTitle = new MenuLabel(450,100,400,100,MenuID.Label,game);
		controlsTitle.addLine("Controls");
		labels.add(controlsTitle);
		
		MenuLabel controls = new MenuLabel(450,200,400,100,MenuID.Label,game);
		controls.setFontSize(20);
		controls.addText("M = switch between 'snooker' and 'grab' modes \n"
				+ "B = resets all balls to start positions \n"
				+ "ESC = main menu \n"
				+ "P = pause menu\n"
				+ "O = music on/off");
		
		labels.add(controls);
		return labels;
	}

}
