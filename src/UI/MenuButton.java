package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import gameObjects.GameObject;

public class MenuButton extends UI_Object {

	public MenuButton(double x, double y, double width, double height,MenuID menuID, Game game) {
		super(x, y, width, height, menuID, game);
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		if(selected){
			switch(menuID){
			case Controls:
				break;
			case Exit:
				break;
			case PlayGame:
				game.play();
				selected = false;
				break;
			case Settings:
				break;
			default:
				break;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(hover){
			g.setColor(Color.cyan);
			g.fillRect((int)(x-10), (int)(y-10),(int)(width+20),(int)(height+20));
		}
		g.setColor(Color.red);
		g.fillRect((int)(x),(int)(y), (int)width, (int)height);
		g.setColor(Color.white);
		Font font = new Font("Impact",Font.PLAIN,35);
		g.setFont(font);
	
		g.drawString(name, (int)(x+halfWidth-40), (int)(y+halfHeight+10));
		
	}

}
