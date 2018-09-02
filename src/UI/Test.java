package UI;

import game.Game;
import game.ID;

public class Test {
	public static void main(String[] args) {
		Game game = new Game();
		MenuButton menuButton = new MenuButton(0,0,100,100,ID.UI_Object,MenuID.PlayGame,game);
	}

}
