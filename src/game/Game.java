package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;

import UI.MenuButton;
import UI.MenuID;
import UI.MenuLabel;
import gameObjects.GameObject;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int windowWidth = 1280;
	private int windowHeight = 720;
	@SuppressWarnings("unused")
	private Window window;
	private Handler handler;
	private Handler mainMenuHandler;
	private Handler pauseMenuHandler;
	private String name = "SnookerGame";
	private Thread thread;
	private boolean running = false;
	private CueSystem cueSystem;
	public GameState currentState;
	public ControlMode controlMode;
	private int fps;
	public Sound gameMusic;
	public Sound menuMusic;
	
	public int score = 0;
	
	public Game(){
		//initializing the main game music
		gameMusic = new Sound("/Music.wav");
		menuMusic = new Sound("/AliA.wav");
		
		//initialising the default state of the game 
		controlMode = ControlMode.Mouse;
		currentState = GameState.MainMenu;
		
		//initialising the window
		window = new Window(windowWidth,windowHeight,this,name);

		cueSystem = new CueSystem(this);
		
		CopyOnWriteArrayList<GameObject> objects = initObjects();
		handler = new Handler(objects);
		
		MenuButton start = new MenuButton(500,100,300,100,MenuID.PlayGame,this);
		start.visible = true;
		start.setSelectable(true);
		MenuLabel controls = new MenuLabel(500,250,300,100,MenuID.Controls,this);
		
		CopyOnWriteArrayList<GameObject> mainMenuObjects = new CopyOnWriteArrayList<GameObject>();
		mainMenuObjects.add(start);
		mainMenuObjects.add(controls);
		mainMenuHandler = new Handler(mainMenuObjects);
		
		CopyOnWriteArrayList<GameObject> pauseMenuObjects = null;
		pauseMenuHandler = new Handler(pauseMenuObjects);
		
		MouseInput mouseInput = new MouseInput(this);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		
		KeyInput keyInput = new KeyInput(this);
		this.addKeyListener(keyInput);
		
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
				fps = frames;
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
			if(!gameMusic.isPlaying())gameMusic.loop();
			menuMusic.stop();
			handler.update();
			//main game update
			break;
		case PauseMenu:
			gameMusic.stop();
			pauseMenuHandler.update();
			//pause menu update
			break;
		case MainMenu:
			gameMusic.stop();
			menuMusic.loop();
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
	
	public void menu(){
		currentState = GameState.MainMenu;
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
	

	public int getFps() {
		return fps;
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
	
	public void reset(){
		score = 0;
		handler.setObjects(initObjects());
	}

}
