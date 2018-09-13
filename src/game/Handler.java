package game;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;

public class Handler {
	private CopyOnWriteArrayList<GameObject> objects;
	
	public Handler(CopyOnWriteArrayList<GameObject> objects){
		this.objects = objects;
	}
	
	public void update(){
		for(GameObject obj:objects){
			obj.update(objects);
		}
	}
	
	public void render(Graphics g){
		for(GameObject obj:objects){
			if(obj.isVisible())
			obj.render(g);
		}
	}
	
	public void add(GameObject obj){
		objects.add(obj);
	}
	
	public void remove(GameObject obj){
		objects.remove(obj);
	}

	public CopyOnWriteArrayList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(CopyOnWriteArrayList<GameObject> objects) {
		this.objects = objects;
	}
	
}
