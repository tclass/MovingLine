package de.movingLine.game;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import de.movingLine.R;

public class Snake {

	
	public final static int YNORTH = -30;
	public final static int YSOUTH = 30;
	public final static int XEAST = +30;
	public final static int XWEST = -30;
	
	private ArrayList<Coordinate> body = new ArrayList<Coordinate>();
	
	public Snake() {
	}
	

	public ArrayList<Coordinate> getBody() {
		return body;
	}

	public void addBody(Coordinate c) {
		body.add(c);
	}
	
	public void addY(int position){
		Coordinate lastCoordinate = body.get(body.size()-1);
		
		Coordinate c = new Coordinate(lastCoordinate.getX(),lastCoordinate.getY()+position);
		body.add(c);
	}
	
	public void addX(int position){
		Coordinate lastCoordinate = body.get(body.size()-1);
		
		Coordinate c = new Coordinate(lastCoordinate.getX()+position,lastCoordinate.getY());
		body.add(c);
	}
	
	public int size() {
		return body.size();
	}
	
	public void moveNorth() {
		for (int i = body.size()-1; i > -1; i--) {
			Coordinate c = body.get(i);
			
			if(i == 0){
				c.setY(c.getY()+Snake.YNORTH);
			}
			
			if(i != 0){
				c.setX(body.get(i-1).getX());
				c.setY(body.get(i-1).getY());
			}
			
			body.set(i, c);
		}
	}

	public void moveSouth() {
		for (int i = body.size()-1; i > -1; i--) {
			Coordinate c = body.get(i);
			
			if(i == 0){
				c.setY(c.getY()+Snake.YSOUTH);
			}
			
			if(i != 0){
				c.setX(body.get(i-1).getX());
				c.setY(body.get(i-1).getY());
			}
			
			body.set(i, c);
		}
	}
	
	public void moveEast() {
		for (int i = body.size()-1; i > -1; i--) {
			Coordinate c = body.get(i);
			
			if(i == 0){
				c.setX(c.getX()+Snake.XEAST);
			}
			
			if(i != 0){
				c.setX(body.get(i-1).getX());
				c.setY(body.get(i-1).getY());
			}
			
			body.set(i, c);
		}
	}
	
	public void moveWest() {
		for (int i = body.size()-1; i > -1; i--) {
			Coordinate c = body.get(i);
			
			if(i == 0){
				c.setX(c.getX()+Snake.XWEST);
			}
			
			if(i != 0){
				c.setX(body.get(i-1).getX());
				c.setY(body.get(i-1).getY());
			}
			
			body.set(i, c);
		}
	}
	
	
	
}
