package game;

import java.awt.Graphics;
import java.util.LinkedList;

import gameObjects.GameObject;

public class Handler {
	private LinkedList<GameObject> objects;
	
	public Handler(LinkedList<GameObject> objects){
		this.objects = objects;
	}
	
	public void update(){
		for(GameObject obj:objects){
			obj.update(objects);
		}
	}
	
	public void render(Graphics g){
		for(GameObject obj:objects){
			if(obj.visible == true)
			obj.render(g);
		}
		
	}
	
	public void add(GameObject obj){
		objects.add(obj);
	}
	
	public void remove(GameObject obj){
		objects.remove(obj);
	}

}
