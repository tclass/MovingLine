package de.movingLine.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Snake {

	public final static int snakeBodyPart = 30;
	
	// These are the variables to set the next image 30 pixels left,right,up or down
	public final static int YNORTH = -30;
	public final static int YSOUTH = 30;
	public final static int XEAST = +30;
	public final static int XWEST = -30;

	private List<Coordinate> snake = new ArrayList<Coordinate>();

	public List<Coordinate> getSnake() {
		return snake;
	}

	public void addSnake(Coordinate c) {
		snake.add(c);
	}

	public void addY(int position) {
		Coordinate lastCoordinate = snake.get(snake.size() - 1);

		Coordinate c = new Coordinate(lastCoordinate.getX(), lastCoordinate.getY() + position);
		snake.add(c);
	}

	public void addX(int position) {
		Coordinate lastCoordinate = snake.get(snake.size() - 1);

		Coordinate c = new Coordinate(lastCoordinate.getX() + position,lastCoordinate.getY());
		snake.add(c);
	}
	
	public void addTile() {
		Coordinate lastTile = snake.get(snake.size()-1);
		Coordinate newTile = new Coordinate(lastTile.getX(), lastTile.getY());
		snake.add(newTile);
	}

	//Sets always the last tile that it is before the first
	public void moveNorth() {

		List<Coordinate> newSnake = new ArrayList<Coordinate>();

		Coordinate lastTile = snake.get(snake.size() - 1);
		Coordinate firstTile = snake.get(0);

		lastTile.setY(firstTile.getY() + Snake.YNORTH);
		lastTile.setX(firstTile.getX());

		newSnake.add(lastTile);
		snake.remove(snake.size() - 1);
		newSnake.addAll(snake);
		snake = newSnake;

	}

	public void moveSouth() {

		List<Coordinate> newSnake = new ArrayList<Coordinate>();

		Coordinate lastTile = snake.get(snake.size() - 1);
		Coordinate firstTile = snake.get(0);

		lastTile.setY(firstTile.getY() + Snake.YSOUTH);
		lastTile.setX(firstTile.getX());

		newSnake.add(lastTile);
		snake.remove(snake.size() - 1);
		newSnake.addAll(snake);
		snake = newSnake;
	}

	public void moveEast() {
		List<Coordinate> newSnake = new ArrayList<Coordinate>();

		Coordinate lastTile = snake.get(snake.size() - 1);
		Coordinate firstTile = snake.get(0);

		lastTile.setY(firstTile.getY());
		lastTile.setX(firstTile.getX() + Snake.XEAST);

		newSnake.add(lastTile);
		snake.remove(snake.size() - 1);
		newSnake.addAll(snake);
		snake = newSnake;
	}

	public void moveWest() {
		
		List<Coordinate> newSnake = new ArrayList<Coordinate>();

		Coordinate lastTile = snake.get(snake.size() - 1);
		Coordinate firstTile = snake.get(0);

		lastTile.setY(firstTile.getY());
		lastTile.setX(firstTile.getX() + Snake.XWEST);

		newSnake.add(lastTile);
		snake.remove(snake.size() - 1);
		newSnake.addAll(snake);
		snake = newSnake;
	}
}
