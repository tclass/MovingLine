package de.movingLine.game;

public class Coordinate {

	private int x;
	private int y;
	
	@SuppressWarnings("unused")
	private Coordinate() {	
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
