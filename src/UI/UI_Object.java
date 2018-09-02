package UI;

import java.awt.Point;
import java.awt.Rectangle;
import game.Game;
import game.ID;
import gameObjects.GameObject;

public abstract class UI_Object extends GameObject{
	protected boolean hover = false;
	protected boolean selected = false;
	protected boolean selectable = false;
	protected MenuID menuID;

	public UI_Object(double x, double y, double width, double height, ID id,MenuID menuID,Game game) {
		super(x, y, width, height, id, game);
		this.setMenuID(menuID);
	}

	public boolean isHover() {
		return hover;
	}


	public void setHover(boolean hover) {
		this.hover = hover;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public boolean isSelectable() {
		return selectable;
	}


	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	
	public boolean isColliding(Point point){
		Rectangle rect = new Rectangle();
		rect.setBounds((int)x, (int)y, (int)width, (int)height);
		if(rect.contains(point)){
			return true;
		}
		else{
			return false;
		}
	}

	public MenuID getMenuID() {
		return menuID;
	}

	public void setMenuID(MenuID menuID) {
		this.menuID = menuID;
	}
}
