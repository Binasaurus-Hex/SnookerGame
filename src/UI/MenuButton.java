package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import gameObjects.GameObject;

public class MenuButton extends UI_Object {
	Font font = new Font("Impact",Font.PLAIN,30);

	public MenuButton(double x, double y, double width, double height,MenuID menuID, Game game) {
		super(x, y, width, height, menuID, game);
		text = new TextBox(x+halfWidth,y+halfHeight,font,game);
		text.addText(name);
		
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		text.update();
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
		text.render(g);
	}

}
