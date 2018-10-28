package game;

import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class WinMenuCreator {
	private Game game;
	public WinMenuCreator(Game game) {
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjects(){
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		objects.addAll(getButtons());
		objects.addAll(getLabels());
		return objects;
	}
	
	private CopyOnWriteArrayList<GameObject> getButtons() {
		 CopyOnWriteArrayList<GameObject> buttons = new CopyOnWriteArrayList<GameObject>();
		 
		 buttons.add(new MenuButton(500,450,300,100,MenuID.PlayAgain,game));
		 
		 return buttons;
	}
	
	private  CopyOnWriteArrayList<GameObject> getLabels() {
		CopyOnWriteArrayList<GameObject> labels = new CopyOnWriteArrayList<GameObject>();
		
		MenuLabel win = new MenuLabel(400,300,500,100,MenuID.Label,game);
		win.setFontSize(40);
		win.addLine("You Win");
		labels.add(win);
		
		return labels;
		
	}

}
