package de.movingLine;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import de.movingLine.game.GameView;
import de.movingLine.game.MyGestureDetector;

public class MovingLine extends Activity {

	private GameView gameView;
	private GestureDetector gestureDetector;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gameView = (GameView) findViewById(R.id.gameView);

		gestureDetector = new GestureDetector(new MyGestureDetector(gameView));
		gameView.setOnTouchListener(new View.OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                if (gestureDetector.onTouchEvent(event)) {
	                    return true;
	                }
	                return false;
	            }
	        });	
	}


}