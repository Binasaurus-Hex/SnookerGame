package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.GameState;
import game.ID;
import gameObjects.GameObject;

public class MenuButton extends UI_Object {
	private String name;

	public MenuButton(double x, double y, double width, double height, ID id,MenuID menuID, Game game) {
		super(x, y, width, height, id, menuID, game);
		this.name = menuID.getValue();
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		if(isSelected()&& menuID == MenuID.PlayGame){
			game.currentState = GameState.Game;
		}
		
	}

	@Override
	public void render(Graphics g) {
		System.out.println("rendered");
		g.setColor(Color.red);
		g.fillRect((int)(x),(int)(y), (int)width, (int)height);
		g.setColor(Color.white);
		Font font = new Font("Impact",Font.PLAIN,35);
		g.setFont(font);
	
		g.drawString(name, (int)(x+halfWidth-40), (int)(y+halfHeight+10));
		
	}

}
