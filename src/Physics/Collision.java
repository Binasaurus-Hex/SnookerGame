package Physics;

import java.awt.Polygon;
import java.awt.geom.PathIterator;

import gameObjects.CircleObject;
import gameObjects.RectangleObject;
import gameObjects.SnookerBall;
import gameObjects.TriangleObject;

public class Collision {
	
	
	
	public static boolean isColliding(CircleObject c1,CircleObject c2){
		return MathsMethods.intersectCircle(c1.getX(), c1.getY(), c1.getRadius(),c2.getX(), c2.getY(), c2.getRadius());
	}
	
	public static boolean isColliding(CircleObject c,RectangleObject r){
		//gets the closest point on the rectangle to the circle
		double closestX = MathsMethods.clamp(c.getX(), r.getLeft(),r.getRight());
		double closestY = MathsMethods.clamp(c.getY(), r.getTop(), r.getBottom());
		
		//distance between the closest point on the rectangle and the centre of the circle
		double distance = MathsMethods.distance(closestX, closestY, c.getX(), c.getY());
		if(distance<c.getRadius()){
			
			return true;
		}
		else{
			return false;
			
		}
	}
	
	public static boolean isColliding(CircleObject c,TriangleObject t){
		Polygon p = t.getPolygon();
		
		/*
		 * iterating through each of the line segments,
		 * vertexA is the starting vertex, vertexB is the next one.
		 */
		for(int i = 0;i<p.npoints;i++){
			double[] vertexA = {p.xpoints[i],p.ypoints[i]};
			double[] vertexB = new double[2];
			if(i+1==p.npoints){
				vertexB[0] = p.xpoints[0];
				vertexB[1] = p.ypoints[1];
			}
			else{
				vertexB[0] = p.xpoints[i+1];
				vertexB[1] = p.ypoints[i+1];
			}
			//working out the closest x and y point to the circle
			double gradient = (vertexA[1]-vertexB[1])/(vertexA[0]-vertexB[0]);
			double interceptA = vertexA[1]-(gradient*vertexA[0]);
			double normal = -1/gradient;
			double interceptB = c.getY()-(normal*c.getX());
			
			double intersectX = (interceptB-interceptA)/(gradient-normal);
			double intersectY = (gradient*intersectX) + interceptA;
			
			double closestX = MathsMethods.clamp(intersectX, Math.min(vertexA[0],vertexB[0]), Math.max(vertexA[0],vertexB[0]));
			double closestY = MathsMethods.clamp(intersectY, Math.min(vertexA[1],vertexB[1]), Math.max(vertexA[1],vertexB[1]));
			double distance = MathsMethods.distance(closestX, closestY, c.getX(), c.getY());
			
			if(distance<c.getRadius()){
				double[] closestPoint = {closestX,closestY};
				t.setClosestPoint(closestPoint);
				return true;
				
			}
		}
		return false;
		
		
	}
	
	
	public static void collide(CircleObject c1,CircleObject c2){
		//calculating the velocities of the two planets
		double[] resultA = PhysicsMethods.collidedVel(c1, c2);
		double[] resultB = PhysicsMethods.collidedVel(c2, c1);
		
		//moving the circle objects apart so that they are no longer colliding 
		resolveCollision(c1,c2);
		//setting the velocities of the two circle objects
		c1.setvX(resultA[0]);
		c1.setvY(resultA[1]);
		c2.setvX(resultB[0]);
		c2.setvY(resultB[1]);
	}
	
	public static void collide(CircleObject c,RectangleObject r){
		//gets the closest point on the rectangle to the circle
		double closestX = MathsMethods.clamp(c.getX(), r.getLeft(),r.getRight());
		double closestY = MathsMethods.clamp(c.getY(), r.getTop(), r.getBottom());
		
		
		
		if(closestX==r.getLeft()||closestX==r.getRight()){
			c.setvX(c.getvX()*-1);
		}
		
		if(closestY==r.getTop()||closestY==r.getBottom()){
			c.setvY(c.getvY()*-1);
		}
	}
	
	public static void collide(CircleObject c,TriangleObject t){
		double circleSpeed = MathsMethods.length(c.getvX(), c.getvY());
		double distanceX = t.getClosestPoint()[0]-c.getX();
		double distanceY = t.getClosestPoint()[1]-c.getY();
		double surfaceAngle = PhysicsMethods.vectorAngle(t.getClosestPoint()[0], t.getClosestPoint()[1]);
		double phi = PhysicsMethods.vectorAngle(distanceX, distanceY);
		double normalComp1 = circleSpeed*Math.cos(surfaceAngle)*-1;
		double normalComp2 = circleSpeed*Math.sin(surfaceAngle);
		double compX1 = normalComp1*Math.cos(phi);
		double compY1 = normalComp1*Math.sin(phi);
		double compX2 = normalComp2*Math.cos(phi);
		double compY2 = normalComp2*Math.sin(phi);
		
		c.setvX(c.getvX()+compX1+compX2);
		c.setvY(c.getvY()+compY1+compY2);
		
		
		
		
		
	}
	
	private static void resolveCollision(CircleObject c1,CircleObject c2){
		double distance = (c1.getRadius()+c2.getRadius())-MathsMethods.distance(c1.getX(), c1.getY(), c2.getX(), c2.getY());
		double phi = PhysicsMethods.vectorAngle(c2.getX()-c1.getX(), c2.getY()-c1.getY());
		double xDistance = distance*Math.cos(phi)*-1;
		double yDistance = distance*Math.sin(phi)*-1;
		c1.setX(c1.getX()+xDistance);
		c1.setY(c1.getY()+yDistance);
		
	}
	
	private static void resolveCollision(){
		//TODO resolve collision between rectangle and circle
	}
	
	

}
