package game;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import Physics.MathsMethods;
import UI.UI_Object;
import gameObjects.GameObject;
import gameObjects.SnookerBall;

public class MouseInput extends MouseAdapter {
	private Game game;
	public MouseInput(Game game){
		this.game = game;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Point mouse = e.getPoint();
		switch(game.currentState){
		case Game:
			CueSystem cueSystem = game.getCueSystem();
			SnookerBall cueBall = cueSystem.getCueBall();
			if(cueBall == null)break;
			if(MathsMethods.distance(cueBall.getX(), cueBall.getY(), mouse.getX(), mouse.getY())<cueBall.getRadius()){
				cueBall.grab();
				cueBall.setMouse(mouse);
			}
			
			break;
			
		case MainMenu:
			CopyOnWriteArrayList<GameObject> mainMenuObjects = game.getMainMenuHandler().getObjects();
			UI_Object ui = getMenuObject(mainMenuObjects,mouse);
			if(ui == null)break;
			if(ui.isSelectable()&& ui.isHover()){
				ui.setSelected(true);
			}
			else{
				ui.setSelected(false);
			}
			break;
		case PauseMenu:
			//pause menu code
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		Point mouse = e.getPoint();
		switch(game.currentState){
		case Game:
			CueSystem cueSystem = game.getCueSystem();
			SnookerBall cueBall = cueSystem.getCueBall();
			if(cueBall == null)break;
			if(MathsMethods.distance(cueBall.getX(), cueBall.getY(), mouse.getX(), mouse.getY())<cueBall.getRadius()){
				cueBall.setHover(true);
			}
			else{
				cueBall.setHover(false);
			}
			break;
		case MainMenu:
			CopyOnWriteArrayList<GameObject> mainMenuObjects = game.getMainMenuHandler().getObjects();
			UI_Object ui = getMenuObject(mainMenuObjects,mouse);
			if(ui == null)break;
			if(ui.isSelectable()){
				ui.setHover(true);
			}
			else{
				ui.setHover(false);
			}
			break;
		case PauseMenu:
			//pause menu code
			break;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point mouse = e.getPoint();
		switch(game.currentState){
		case Game:
			CueSystem cueSystem = game.getCueSystem();
			SnookerBall cueBall = cueSystem.getCueBall();
			if(cueBall == null)break;
			if(cueBall.isSelected()){
				if(game.controlMode == ControlMode.Cue){
					cueSystem.hitCueBall(mouse);
				}
				else if(game.controlMode == ControlMode.Mouse){
					cueBall.setMouse(mouse);
				}
			}
			
		case MainMenu:
			break;
		case PauseMenu:
			break;
		default:
			break;
		}
	}
		
	private UI_Object getMenuObject(CopyOnWriteArrayList<GameObject> menuObjects, Point point){
		for(GameObject obj : menuObjects){
			if(obj.getId() == ID.UI_Object){
				UI_Object ui = (UI_Object)obj;
				if(ui.isColliding(point)){
					return ui;
				}
				else{
					ui.setHover(false);
				}
			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(game.currentState){
		case Game:
			CueSystem cueSystem = game.getCueSystem();
			SnookerBall cueBall = cueSystem.getCueBall();
			if(cueBall == null)break;
			if(cueBall.isSelected()){
				cueBall.setCollidable(true);
				cueBall.release();
			}
		case MainMenu:
			break;
		case PauseMenu:
			break;
		default:
			break;
		}
	}

}
