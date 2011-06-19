package de.movingLine.game;

import java.util.List;

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
import de.movingLine.game.controller.CollisionDetector;
import de.movingLine.game.controller.MyGestureInteface;
import de.movingLine.game.entities.Bonus;
import de.movingLine.game.entities.Coordinate;
import de.movingLine.game.entities.Snake;
import de.movingLine.game.holder.BonusHolder;
/**
 * 
 * @author Royalclass
 * This is the MainGameView and the MainGameHandler
 */
public class GameView extends View implements MyGestureInteface {

	// Sleep time for the Thread
	public static int FRAMES = 400;

	// Snake Handler
	private Snake snake = new Snake();
	
	private BonusHolder bonusHolder = new BonusHolder();
	private CollisionDetector collisonDetector = null;
	
	private boolean gameOver = false;
	private boolean pause = false;

	private String nextDirection;
	private String lastDirection;
	
	// RefreshHandler acting like a Thread
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	// Objects to Paint on the Canvas
	private Bitmap bitmap_body = BitmapFactory.decodeResource(getResources(),R.drawable.green);
	private Bitmap bitmap_head = BitmapFactory.decodeResource(getResources(),R.drawable.yellow);
	private Bitmap bitmap_boni = BitmapFactory.decodeResource(getResources(),R.drawable.apple);
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
		snake.addSnake(new Coordinate(240,180));
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addY(Snake.YSOUTH);
		snake.addX(Snake.XEAST);
		snake.addX(Snake.XEAST);
		snake.addX(Snake.YSOUTH);
		snake.addX(Snake.YSOUTH);
		
		nextDirection = MyGestureInteface.NORTH;
		lastDirection = MyGestureInteface.NORTH;
		update();
	}

	/**
	 * This Method draws the snake, the first bitmap is
	 * the head so its an other bitmap (yellow pic)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		if (gameOver) {
			canvas.drawBitmap(bitmap_gameOver, this.getWidth() / 2 - 100, this.getHeight() / 2 - 50, p);
			mRedrawHandler.removeMessages(0);
		} 
		else {
			List<Coordinate> body = snake.getSnake();
			for (int i = 0; i < body.size(); i++) {
				if (i == 0) {
					canvas.drawBitmap(bitmap_head, body.get(i).getX(), body.get(i).getY(), p);
				} 
				else {
					canvas.drawBitmap(bitmap_body, body.get(i).getX(), body.get(i).getY(), p);
				}

			}
			
			bonusHolder.checkTimeToCatch();
			List<Bonus> boni = bonusHolder.getBoni();
			
			for (int i = 0; i < boni.size(); i++) {
				canvas.drawBitmap(bitmap_boni, boni.get(i).getX(), boni.get(i).getY(), p);
			}
			
		}
		super.onDraw(canvas);
	}

	/**
	 * This is were you can control the snake
	 */
	private void update() {
		
			if (nextDirection.equals(MyGestureInteface.NORTH)) {
				snake.moveNorth();
			}
			else if (nextDirection.equals(MyGestureInteface.SOUTH)) {
				snake.moveSouth();
			}
			else if (nextDirection.equals(MyGestureInteface.EAST)) {
				snake.moveEast();
			}
			else if (nextDirection.equals(MyGestureInteface.WEST)) {
				snake.moveWest();
			}
		
		bonusHolder.generateBoni();
		
		// TODO: i don't know why, but this.getMeasuredWidht and Height is 0
		// on the first call
		if(collisonDetector == null && this.getMeasuredHeight() != 0 && this.getMeasuredWidth() != 0)
			collisonDetector = new CollisionDetector(this.getMeasuredWidth(), this.getMeasuredHeight(), snake, bonusHolder);
			
		if(collisonDetector != null)
		gameOver = collisonDetector.detect();
		
		
		mRedrawHandler.sleep(FRAMES);
	}

	@Override
	public void setFlingDirection(String direction) {
		lastDirection = nextDirection;
		nextDirection = direction;
	}
	
	public void setPause(boolean pause) {
		
		this.pause = pause;
		
		//if the game should run then fire an event to the RefreshHandler
		if(!pause){
			mRedrawHandler.sleep(FRAMES);
		}
		
	}
	
	public boolean getPause() {
		return pause;
	}
	
	/**
	 * This is a private Class, it's an RefreshHandler
	 * u can use it like a Thread to let draw the bitmaps over and over again
	 */
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			GameView.this.update();
			GameView.this.invalidate();
		}

		public void sleep(long delayMillis) {
			if(!getPause()) {

				this.removeMessages(0);
				sendMessageDelayed(obtainMessage(0), delayMillis);
			}
		}
	}
}
