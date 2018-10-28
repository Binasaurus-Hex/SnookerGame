package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

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
	private Handler controlMenuHandler;
	private Handler winMenuHandler;
	private String name = "SnookerGame";
	private Thread thread;
	private boolean running = false;
	private CueSystem cueSystem;
	public GameState currentState;
	public ControlMode controlMode;
	private int fps;
	public Sound gameMusic;
	public Sound menuMusic;
	public Sound winSound;
	public int score = 0;
	public boolean musicOn = true;
	
	public Game(){
		//initializing the main game music
		gameMusic = new Sound("/nick game loop.wav");
		menuMusic = new Sound("/Music.wav");
		winSound = new Sound("/tuturu.wav");
		
		//initialising the default state of the game 
		controlMode = ControlMode.Cue;
		currentState = GameState.MainMenu;
		
		//initialising the window
		window = new Window(windowWidth,windowHeight,this,name);
		
		//initialising the cue system
		cueSystem = new CueSystem(this);
		
		/*
		 * initialising all the game objects and
		 * adding all the objects to their respective handlers
		 */
		
		//handler for objects in the snooker 'world'
		handler = new Handler(initObjects());
		
		//handler for main menu objects
		MainMenuCreator mainMenu = new MainMenuCreator(this);
		mainMenuHandler = new Handler(mainMenu.getObjects());
		
		//handler for pause menu objects
		PauseMenuCreator pauseMenu = new PauseMenuCreator(this);
		pauseMenuHandler = new Handler(pauseMenu.getObjects());
		
		//handler for the control menu objects
		ControlMenuCreator controlMenu = new ControlMenuCreator(this);
		controlMenuHandler = new Handler(controlMenu.getObjects());
		
		WinMenuCreator winMenu = new WinMenuCreator(this);
		winMenuHandler = new Handler(winMenu.getObjects());
		
		
		/*
		 * initialising input for the game 
		 */
		MouseInput mouseInput = new MouseInput(this);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		
		KeyInput keyInput = new KeyInput(this);
		this.addKeyListener(keyInput);
		
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
	
	public Handler getControlMenuHandler() {
		return controlMenuHandler;
	}

	public void setControlMenuHandler(Handler controlMenuHandler) {
		this.controlMenuHandler = controlMenuHandler;
	}
	
	public Handler getWinMenuHandler() {
		return winMenuHandler;
	}

	public void setWinMenuHandler(Handler winMenuHandler) {
		this.winMenuHandler = winMenuHandler;
	}
	
	
	public int getFps() {
		return fps;
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
				//System.out.println("FPS: "+ frames);
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
	private void update(){
		switch(currentState){
		case Game:
			//main game update
			if(score == 16)win();
			menuMusic.stop();
			handler.update();
			if(musicOn) {
				if(!gameMusic.isPlaying())gameMusic.loop();
			}
			else gameMusic.stop();
			break;
		case PauseMenu:
			gameMusic.pause();
			pauseMenuHandler.update();
			break;
		case MainMenu:
			if(!menuMusic.isPlaying())//menuMusic.loop();
			gameMusic.stop();
			mainMenuHandler.update();
			//main menu update
			break;
		case Controls:
			controlMenuHandler.update();
			break;
		case Settings:
			break;
		case Win:
			winMenuHandler.update();
		default:
			break;
		}
		
		
	}
	
	/*
	 * renders the game
	 */
	private void render(){
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
				handler.render(g);
				Color fade = new Color(0, 0, 0, 150);
				g.setColor(fade);
				g.fillRect(0, 0, windowWidth, windowHeight);
				pauseMenuHandler.render(g);
				break;
			case MainMenu:
				mainMenuHandler.render(g);
				break;
			case Controls:
				controlMenuHandler.render(g);
				break;
			case Settings:
				break;
			case Win:
				handler.render(g);
				Color fadewin = new Color(0, 255, 0, 150);
				g.setColor(fadewin);
				g.fillRect(0, 0, windowWidth, windowHeight);
				winMenuHandler.render(g);
				
			default :
				break;
			}
			
			buffer.show();
			g.dispose();
		}
	}
	
	//control for game states
	public void pause(){
		currentState = GameState.PauseMenu;
	}
	
	public void play(){
		currentState = GameState.Game;
	}
	
	public void menu(){
		currentState = GameState.MainMenu;
	}
	
	public void settings(){
		currentState = GameState.Settings;
	}
	
	public void controls(){
		currentState = GameState.Controls;
	}
	
	public void win() {
		winSound.play();
		score = 0;
		currentState = GameState.Win;
	}
	
	public void lose() {
		score = 0;
		currentState = GameState.Lose;
	}
	
	private CopyOnWriteArrayList<GameObject> initObjects(){
		ObjectCreator objectCreator = new ObjectCreator(this);
		CopyOnWriteArrayList<GameObject> objects = objectCreator.getObjects();
		return objects;
	}
	
	public void reset(){
		score = 0;
		handler.setObjects(initObjects());
	}
	
	public static void main(String[] a){
		Game game = new Game();
		game.start();
	}

}
