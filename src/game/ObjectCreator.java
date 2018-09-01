package game;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.GameObject;
import gameObjects.SnookerBall;
import gameObjects.Table;
import gameObjects.TableCorner;
import gameObjects.TableHole;
import gameObjects.TableWall;

public class ObjectCreator {
	private Game game;
	public ObjectCreator(Game game){
		this.game = game;
	}
	
	public CopyOnWriteArrayList<GameObject> getObjectList(){
		CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<GameObject>();
		
		CopyOnWriteArrayList<GameObject> tableObjects = getTableObjects();
		CopyOnWriteArrayList<GameObject> ballObjects = getBalls();
		
		gameObjects.addAll(tableObjects);
		gameObjects.addAll(ballObjects);
		
		return gameObjects;
		
	}
	
	private CopyOnWriteArrayList<GameObject> getTableObjects(){
		CopyOnWriteArrayList<GameObject> tableObjects = new CopyOnWriteArrayList<GameObject>();
		//adding the background
		Table table = new Table(0,0,game.getWindowWidth(),game.getWindowHeight(),game);
		table.visible = true;
		tableObjects.add(table);
		
		CopyOnWriteArrayList<GameObject> tableOuterSides = getTableOuter();
		CopyOnWriteArrayList<GameObject> tableInnerSides = getTableInner();
		CopyOnWriteArrayList<GameObject> tableCorners = getTableCorners();
		CopyOnWriteArrayList<GameObject> tableHoles = getTableHoles();
		
		tableObjects.addAll(tableOuterSides);
		tableObjects.addAll(tableInnerSides);
		tableObjects.addAll(tableCorners);
		tableObjects.addAll(tableHoles);
		
		return tableObjects;
	}
	
	/*
	 * gets a list of all the table outer wall objects
	 */
	private CopyOnWriteArrayList<GameObject> getTableOuter(){
		
		CopyOnWriteArrayList<GameObject> tableOuters = new CopyOnWriteArrayList<GameObject>();
		
		//width = 560, height = 38
		//making the outer walls
		TableWall wall_1 = new TableWall(338,703,560,38,1,Color.green,game);
		tableOuters.add(wall_1);
				
		TableWall wall_2 = new TableWall(941,703,560,38,1,Color.green,game);
		tableOuters.add(wall_2);
				
		TableWall wall_3 = new TableWall(1260,361,38,605,1,Color.green,game);
		tableOuters.add(wall_3);
				
		TableWall wall_4 = new TableWall(941,19,560,38,1,Color.green,game);
		tableOuters.add(wall_4);
				
		TableWall wall_5 = new TableWall(338,19,560,38,1,Color.green,game);
		tableOuters.add(wall_5);
				
		TableWall wall_6 = new TableWall(19,361,38,605,1,Color.green,game);
		tableOuters.add(wall_6);
		
		return tableOuters;
	}
	
	/*
	 * gets a list of all the table inner wall objects
	 */
	private CopyOnWriteArrayList<GameObject> getTableInner(){
		
		CopyOnWriteArrayList<GameObject> tableInners = new CopyOnWriteArrayList<GameObject>();
		
		TableWall inner_1 = new TableWall(333,675,483,19,1,Color.white,game);
		tableInners.add(inner_1);
		
		TableWall inner_2 = new TableWall(946,675,483,19,1,Color.white,game);
		tableInners.add(inner_2);
		
		TableWall inner_3 = new TableWall(1233,362,19,525,1,Color.white,game);
		tableInners.add(inner_3);
		
		TableWall inner_4 = new TableWall(946,48,483,19,1,Color.white,game);
		tableInners.add(inner_4);
		
		TableWall inner_5 = new TableWall(333,48,483,19,1,Color.white,game);
		tableInners.add(inner_5);
		
		TableWall inner_6 = new TableWall(48,362,19,525,1,Color.white,game);
		tableInners.add(inner_6);
		
		return tableInners;
	}
	
	/*
	 * gets a list of all the table corner objects
	 */
	private CopyOnWriteArrayList<GameObject> getTableCorners(){
		
		CopyOnWriteArrayList<GameObject> tableCorners = new CopyOnWriteArrayList<GameObject>();
		
		//making the corners
		double cornerHeight = 18;
		double cornerWidth = 28;
				
		TableCorner corner_1 = new TableCorner(91,684,-cornerWidth,-cornerHeight,game);
		tableCorners.add(corner_1);
				
		TableCorner corner_2 = new TableCorner(574,684,cornerWidth,-cornerHeight,game);
		tableCorners.add(corner_2);
				
		TableCorner corner_3 = new TableCorner(704,684,-cornerWidth,-cornerHeight,game);
		tableCorners.add(corner_3);
				
		TableCorner corner_4 = new TableCorner(1187,684,cornerWidth,-cornerHeight,game);
		tableCorners.add(corner_4);

		TableCorner corner_5 = new TableCorner(1242,624,-cornerHeight,cornerWidth,game);
		tableCorners.add(corner_5);

		TableCorner corner_6 = new TableCorner(1242,99,-cornerHeight,-cornerWidth,game);
		tableCorners.add(corner_6);

		TableCorner corner_7 = new TableCorner(1187,38,cornerWidth,cornerHeight,game);
		tableCorners.add(corner_7);

		TableCorner corner_8 = new TableCorner(704,38,-cornerWidth,cornerHeight,game);
		tableCorners.add(corner_8);

		TableCorner corner_9 = new TableCorner(574,38,cornerWidth,cornerHeight,game);
		tableCorners.add(corner_9);

		TableCorner corner_10 = new TableCorner(91,38,-cornerWidth,cornerHeight,game);
		tableCorners.add(corner_10);

		TableCorner corner_11 = new TableCorner(38,99,cornerHeight,-cornerWidth,game);
		tableCorners.add(corner_11);

		TableCorner corner_12 = new TableCorner(38,624,cornerHeight,cornerWidth,game);
		tableCorners.add(corner_12);
		
		return tableCorners;
	}
	
	/*
	 * gets a list of all the table corner objects
	 */
	private CopyOnWriteArrayList<GameObject> getTableHoles(){
		
		CopyOnWriteArrayList<GameObject> tableHoles = new CopyOnWriteArrayList<GameObject>();
		
		//making the table holes
		double tableholeRadius = 22;

		TableHole hole_1 = new TableHole(37,684,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_1);

		TableHole hole_2 = new TableHole(640,683,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_2);

		TableHole hole_3 = new TableHole(1242,684,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_3);

		TableHole hole_4 = new TableHole(1242,36,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_4);

		TableHole hole_5 = new TableHole(639,37,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_5);

		TableHole hole_6 = new TableHole(37,36,tableholeRadius,1,Color.blue,game);
		tableHoles.add(hole_6);
		
		return tableHoles;
	}
	
	/*
	 * gets a list of all the ball Objects
	 */
	private CopyOnWriteArrayList<GameObject> getBalls(){
		
		CopyOnWriteArrayList<GameObject> balls = new CopyOnWriteArrayList<GameObject>();
		
		//making the balls
		SnookerBall cueBall = new SnookerBall(100,200,15,1,Color.white,game);
		cueBall.setFollowMouse(true);
		cueBall.visible = true;
		cueBall.setCollidable(false);
		balls.add(cueBall);
		
		SnookerBall ball_1 = new SnookerBall(200,200,15,1,Color.red,game);
		ball_1.setvX(0.000001);
		ball_1.setvY(0.000001);
		ball_1.visible = true;
		balls.add(ball_1);
		
		SnookerBall ball_3 = new SnookerBall(400,300,15,1,Color.green,game);
		ball_3.setvX(0.000001);
		ball_3.setvY(0.000001);
		ball_3.visible = true;
		
		balls.add(ball_3);
		
		SnookerBall ball_4 = new SnookerBall(700,400,15,1,Color.black,game);
		ball_4.setvX(0.000001);
		ball_4.setvY(0.000001);
		ball_4.visible = true;
		
		balls.add(ball_4);
		
		return balls;
	}
}
