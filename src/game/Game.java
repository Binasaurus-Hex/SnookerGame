package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
		LinkedList<GameObject> objects = new LinkedList<GameObject>();
		
		Table table = new Table(0,0,100,100,this);
		objects.add(table);
		
		SnookerBall ball = new SnookerBall(500,200,50,0.17,Color.white,this);
		ball.setvX(1);
		ball.setvY(1);
		ball.setFollowMouse(true);
		objects.add(ball);
		
		
//		TableHole hole = new TableHole(600,300,50,1,Color.red,this);
//		objects.add(hole);
		
		
		
		SnookerBall ball2 = new SnookerBall(600,300,50,0.15,Color.red,this);
		ball2.setvX(1);
		ball2.setvY(1);
		objects.add(ball2);
		
		TableCorner corner = new TableCorner(1300,600,-400,-400,this);
		objects.add(corner);
		
		SnookerBall ball3 = new SnookerBall(400,400,50,0.15,Color.green,this);
		ball3.setvX(1);
		ball3.setvY(1);
		objects.add(ball3);
		
		TableWall wall = new TableWall(600,700,900,300,2,Color.green,this);
		objects.add(wall);
		handler = new Handler(objects);
		
		
	}


	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double updatesPerSecond = 60.0;
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

}
