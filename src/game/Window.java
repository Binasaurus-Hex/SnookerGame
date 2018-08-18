package game;

import java.awt.Insets;

import javax.swing.JFrame;

public class Window {

	/*
	 * sets up the window for the game
	 */
	Window(int windowWidth, int windowHeight, Game game,String name){
		JFrame frame = new JFrame(name);
		Insets insets = frame.getInsets();
		frame.setSize(windowWidth+5, windowHeight+29);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
	}

}
