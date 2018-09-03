package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.ID;
import gameObjects.GameObject;

public class MenuLabel extends UI_Object {

	public MenuLabel(double x, double y, double width, double height,MenuID menuID, Game game) {
		super(x, y, width, height, menuID, game);
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)(x),(int)(y), (int)width, (int)height);
		g.setColor(Color.white);
		Font font1 = new Font("Impact",Font.PLAIN,20);
		g.setFont(font1);
		g.drawString(name, (int)(x), (int)(y));
		Font font2 = new Font("Impact",Font.PLAIN,15);
		g.drawString("b = resets objects",(int) x,(int)y+20);
		g.drawString("m = toggles between grab and pull and release modes",(int) x,(int) y+40);
		g.drawString("escape = goes to menu",(int) x,(int) y+60);
		g.drawString("f = force releases ball if grabbing",(int) x,(int) y+80);

	}

}
