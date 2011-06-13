package de.movingLine.game;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import de.movingLine.R;

public class GameView extends View implements MyGestureInteface {

	// the Sleep time for the Thread
	public static int FRAMES = 2000;

	// The Snake or the Snake Handler
	private Snake snake = new Snake();
	private boolean gameOver = false;

	private String nextDirection;
	
	// RefreshHandler acting like a Thread
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	// Objects to Paint on the Canvas
	private Bitmap bitmap_body = BitmapFactory.decodeResource(getResources(),R.drawable.green);
	private Bitmap bitmap_head = BitmapFactory.decodeResource(getResources(),R.drawable.yellow);
	private Bitmap bitmap_gameOver = BitmapFactory.decodeResource(getResources(),R.drawable.gameover);
	private Paint p = new Paint();

	public GameView(Context context) {
		super(context);
		init();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * init the Snake first the head and then i can add the next Coordinates with a direction
	 */
	private void init() {
		snake.addBody(new Coordinate(120, 120));
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addX(Snake.XEAST);
		snake.addX(Snake.XEAST);
		snake.addX(Snake.YSOUTH);
		snake.addX(Snake.YSOUTH);
		
		nextDirection = MyGestureInteface.NORTH;
		update();
	}

	/**
	 * This Method draws the snake, the first bitmap is
	 * the head so its an other bitmap (yellow pic)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		if(gameOver){
			canvas.drawBitmap(bitmap_gameOver, this.getWidth()/2-100, this.getHeight()/2-50, p);
			//TODO search for a method to stop the RefreshHandler
		}
		else{	
			ArrayList<Coordinate> body = snake.getBody();
			for (int i = 0; i < snake.size(); i++) {
				if (i == 0) {
					canvas.drawBitmap(bitmap_head, body.get(i).getX(), body.get(i).getY(), p);
				} else {
					canvas.drawBitmap(bitmap_body, body.get(i).getX(), body.get(i).getY(), p);
				}

		}
		}
		super.onDraw(canvas);
	}

	/**
	 * This is were you can control the snake
	 * TODO: Maybe the equals is not so good, thinking of another solution
	 */
	private void update() {
			if (nextDirection.equals("NORTH")) {
				snake.moveNorth();
			}
			if (nextDirection.equals("SOUTH")) {
				snake.moveSouth();
			}
			if (nextDirection.equals("EAST")) {
				snake.moveEast();
			}
			if (nextDirection.equals("WEST")) {
				snake.moveWest();
			}
			System.out.println("X:"+ snake.getBody().get(0).getX());
			System.out.println("Y:"+ snake.getBody().get(0).getY());
			System.out.println("LEFT: "+ this.getLeft());
			System.out.println("RIGHT: " +this.getRight());
			System.out.println("UP: " + this.getTop());
			System.out.println("DOWN: "+this.getBottom());
			
			collisionDetection();
		mRedrawHandler.sleep(FRAMES);
	}
	
	private void collisionDetection() {
		
		//check if the snake head is crashing into a wall
		if(snake.getBody().get(0).getX() > this.getLeft() || snake.getBody().get(0).getX() > this.getRight()-30){
			System.out.println("left or right crash");
//			gameOver = true;
		}
		if(snake.getBody().get(0).getY() < 690 || snake.getBody().get(0).getY() > this.getTop()){
			System.out.println("up or down crash");
//			gameOver = true;
		}
	}

	@Override
	public void setFlingDirection(String direction) {
		nextDirection = direction;
	}
	
	/**
	 * This is a private Class, it's an RefreshHandler
	 * u can use it like a Thread to draw the bitmaps over and over again
	 */
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			GameView.this.update();
			GameView.this.invalidate();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
}
