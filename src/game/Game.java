package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import gameObjects.GameObject;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int windowWidth = 1280;
	private int windowHeight = 720;
	private Window window;
	private Handler handler;
	private Handler mainMenuHandler;
	private Handler pauseMenuHandler;
	private String name = "SnookerGame";
	private Thread thread;
	private boolean running = false;
	private CueSystem cueSystem;
	public GameState currentState;
	
	public Game(){
		currentState = GameState.MainMenu;
		window = new Window(windowWidth,windowHeight,this,name);
		
		cueSystem = new CueSystem();
		
		CopyOnWriteArrayList<GameObject> objects = initObjects();
		handler = new Handler(objects);
		
		MenuButton start = new MenuButton(500,100,300,100,ID.UI_Object,MenuID.PlayGame,this);
		start.visible = true;
		start.setSelectable(true);
		CopyOnWriteArrayList<GameObject> mainMenuObjects = new CopyOnWriteArrayList<GameObject>();
		mainMenuObjects.add(start);
		mainMenuHandler = new Handler(mainMenuObjects);
		
		CopyOnWriteArrayList<GameObject> pauseMenuObjects = null;
		pauseMenuHandler = new Handler(pauseMenuObjects);
		
		MouseInput mouseInput = new MouseInput(this);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		
	}

	//loop of the entire game
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
	
	/*
	 * updates the game differenly depending on game state
	 */
	public void update(){
		switch(currentState){
		case Game:
			handler.update();
			//main game update
			break;
		case PauseMenu:
			pauseMenuHandler.update();
			//pause menu update
			break;
		case MainMenu:
			mainMenuHandler.update();
			//main menu update
			break;
		}
		
		
	}
	
	/*
	 * renders the game
	 */
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
			switch(currentState){
			case Game:
				handler.render(g);
				break;
			case PauseMenu:
				pauseMenuHandler.render(g);
				break;
			case MainMenu:
				mainMenuHandler.render(g);
				break;
			}
			
			buffer.show();
			g.dispose();
		}
	}
	
	public void pause(){
		currentState = GameState.PauseMenu;
	}
	
	public void play(){
		currentState = GameState.Game;
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

	public CueSystem getCueSystem() {
		return cueSystem;
	}
	
	public Handler getMainMenuHandler() {
		return mainMenuHandler;
	}

	public void setMainMenuHandler(Handler mainMenuHandler) {
		this.mainMenuHandler = mainMenuHandler;
	}

	public Handler getPauseMenuHandler() {
		return pauseMenuHandler;
	}

	public void setPauseMenuHandler(Handler pauseMenuHandler) {
		this.pauseMenuHandler = pauseMenuHandler;
	}

	public static void main(String[] a){
		Game game = new Game();
		game.start();
	}
	
	private CopyOnWriteArrayList<GameObject> initObjects(){
		ObjectCreator objectCreator = new ObjectCreator(this);
		CopyOnWriteArrayList<GameObject> objects = objectCreator.getObjectList();
		return objects;
	}

}
