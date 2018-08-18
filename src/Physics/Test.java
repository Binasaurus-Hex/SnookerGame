package Physics;

import java.awt.Color;

import game.Game;
import gameObjects.SnookerBall;
import gameObjects.TableCorner;

public class Test {

	public static void main(String[] args) {

		Game game = new Game();
		SnookerBall ball2 = new SnookerBall(600,300,50,0.15,Color.red,game);
		TableCorner corner = new TableCorner(0,0,100,100,game);
		Collision.isColliding(ball2, corner);
	}
	

}
