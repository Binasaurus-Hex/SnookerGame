package UI;

public enum MenuID {
	PlayGame("Play"),
	Settings("Settings"),
	Controls("Controls"),
	Exit("Exit Game");
	
	private String string;
	
	private MenuID(String string){
		this.string = string;
	}
	
	public String getValue(){
		return string;
	}

}
