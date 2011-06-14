package de.movingLine.game.controller;

public interface MyGestureInteface {

    public static final String SOUTH = "SOUTH";
    public static final String NORTH = "NORTH";
    public static final String WEST = "WEST";
    public static final String EAST = "EAST";
	
	public void setFlingDirection(String direction);
}
