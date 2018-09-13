package UI;

import game.Activatable;
import game.GameState;

public enum MenuID {
	
	PlayGame("Play",(game)->game.play()),
	Resume("Resume",(game)->game.play()),
	Menu("Menu",(game)->game.menu()),
	Settings("Settings",(game)->game.currentState = GameState.Game),
	Controls("Controls",(game)->game.pause()),
	Exit("Exit Game",(game)->System.exit(0));
	
	private String name;
	private Activatable behaviour;
	
	private MenuID(String name,Activatable behaviour){
		this.name = name;
		this.behaviour = behaviour;
	}
	
	public String getName(){
		return name;
	}
	
	public Activatable getBehaviour(){
		return behaviour;
	}

}
