package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.SnookerBall;
import gameObjects.Table;
import gameObjects.TableCorner;
import gameObjects.TableHole;
import gameObjects.TableWall;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int windowWidth = 1280;
	private int windowHeight = 720;
	private Window window;
	private Handler handler;
	private String name = "SnookerGame";
	private Thread thread;
	private boolean running = false;
	private boolean paused = false;
	
	public Game(){
		window = new Window(windowWidth,windowHeight,this,name);
		LinkedList<GameObject> objects = initObjects();
		handler = new Handler(objects);
		
		
	}


	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double updatesPerSecond = 60;
		double nanoSecondsPerUpdate = 100000000 / updatesPerSecond;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta +=(now-lastTime) / nanoSecondsPerUpdate;
			lastTime = now;
			while(delta >=1){
				update();
				delta--;
			}
			if(running) render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this);
		thread.run();	
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		if(!paused){
			
			handler.update();
		}
		
		
	}
	
	public void render(){
		if(this.getBufferStrategy()==null){
			this.createBufferStrategy(3);
		}
		else{
			BufferStrategy buffer = this.getBufferStrategy();
			Graphics g = buffer.getDrawGraphics();
			
			//sets a black background
			g.setColor(Color.black);
			g.fillRect(0, 0, windowWidth, windowHeight);
			handler.render(g);
			buffer.show();
			g.dispose();
		}
	}
	
	public void pause(){
		paused = true;
	}
	
	
	
	public int getWindowWidth() {
		return windowWidth;
	}


	public int getWindowHeight() {
		return windowHeight;
	}


	public Handler getHandler() {
		return handler;
	}


	public static void main(String[] a){
		Game game = new Game();
		game.start();
	}
	
	private LinkedList<GameObject> initObjects(){
		LinkedList<GameObject> objects = new LinkedList<GameObject>();
		
		//adding the background
		Table table = new Table(0,0,windowWidth,windowHeight,this);
		table.visible = true;
		objects.add(table);
		
		//width = 560, height = 38
		//making the outer walls
		TableWall wall_1 = new TableWall(338,703,560,38,1,Color.green,this);
		objects.add(wall_1);
		
		TableWall wall_2 = new TableWall(941,703,560,38,1,Color.green,this);
		objects.add(wall_2);
		
		TableWall wall_3 = new TableWall(1260,361,38,605,1,Color.green,this);
		objects.add(wall_3);
		
		TableWall wall_4 = new TableWall(941,19,560,38,1,Color.green,this);
		objects.add(wall_4);
		
		TableWall wall_5 = new TableWall(338,19,560,38,1,Color.green,this);
		objects.add(wall_5);
		
		TableWall wall_6 = new TableWall(19,361,38,605,1,Color.green,this);
		objects.add(wall_6);
		
		//making the inner walls
		
		TableWall inner_1 = new TableWall(333,675,483,19,1,Color.white,this);
		objects.add(inner_1);
		
		TableWall inner_2 = new TableWall(946,675,483,19,1,Color.white,this);
		objects.add(inner_2);
		
		TableWall inner_3 = new TableWall(1233,362,19,525,1,Color.white,this);
		objects.add(inner_3);
		
		TableWall inner_4 = new TableWall(946,48,483,19,1,Color.white,this);
		objects.add(inner_4);
		
		TableWall inner_5 = new TableWall(333,48,483,19,1,Color.white,this);
		objects.add(inner_5);
		
		TableWall inner_6 = new TableWall(48,362,19,525,1,Color.white,this);
		objects.add(inner_6);
		
		//making the corners
		
		TableCorner corner_1 = new TableCorner(91,684,-28,-19,this);
		objects.add(corner_1);
		
		TableCorner corner_2 = new TableCorner(574,684,28,-19,this);
		objects.add(corner_2);
		
		TableCorner corner_3 = new TableCorner(704,684,-28,-19,this);
		objects.add(corner_3);
		
		TableCorner corner_4 = new TableCorner(1187,684,28,-19,this);
		objects.add(corner_4);
		
		TableCorner corner_5 = new TableCorner(1242,624,-19,28,this);
		objects.add(corner_5);
		
		TableCorner corner_6 = new TableCorner(1242,99,-19,-28,this);
		objects.add(corner_6);
		
		TableCorner corner_7 = new TableCorner(1187,38,28,19,this);
		objects.add(corner_7);
		
		TableCorner corner_8 = new TableCorner(704,38,-28,19,this);
		objects.add(corner_8);
		
		TableCorner corner_9 = new TableCorner(574,38,28,19,this);
		objects.add(corner_9);
		
		TableCorner corner_10 = new TableCorner(91,38,-28,19,this);
		objects.add(corner_10);
		
		TableCorner corner_11 = new TableCorner(38,99,19,-28,this);
		objects.add(corner_11);
		
		TableCorner corner_12 = new TableCorner(38,624,19,28,this);
		objects.add(corner_12);
		
		
		
		//tablehole radius = 22
		//making the table holes
		TableHole hole_1 = new TableHole(37,684,22,1,Color.blue,this);
		objects.add(hole_1);
		
		TableHole hole_2 = new TableHole(640,683,22,1,Color.blue,this);
		objects.add(hole_2);
		
		TableHole hole_3 = new TableHole(1242,684,22,1,Color.blue,this);
		objects.add(hole_3);
		
		TableHole hole_4 = new TableHole(1242,36,22,1,Color.blue,this);
		objects.add(hole_4);
		
		TableHole hole_5 = new TableHole(639,37,22,1,Color.blue,this);
		objects.add(hole_5);
		
		TableHole hole_6 = new TableHole(37,36,22,1,Color.blue,this);
		objects.add(hole_6);
		
		
		
		SnookerBall ball_1 = new SnookerBall(200,200,15,1,Color.red,this);
		ball_1.setvX(0.1);
		ball_1.setvY(0.1);
		ball_1.visible = true;
		objects.add(ball_1);
		
		SnookerBall ball_2 = new SnookerBall(100,200,15,1,Color.white,this);
		ball_2.setFollowMouse(true);
		ball_2.visible = true;
		objects.add(ball_2);
		
		SnookerBall ball_3 = new SnookerBall(400,300,15,1,Color.green,this);
		ball_3.setvX(0.1);
		ball_3.setvY(0.1);
		ball_3.visible = true;
		
		objects.add(ball_3);
		
		SnookerBall ball_4 = new SnookerBall(700,400,15,1,Color.black,this);
		ball_4.setvX(0.1);
		ball_4.setvY(0.1);
		ball_4.visible = true;
		
		objects.add(ball_4);
		
		
		return objects;
		
		
		
		
	}

}
