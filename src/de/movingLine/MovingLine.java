package de.movingLine;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import de.movingLine.game.GameView;
import de.movingLine.game.controller.MyGestureDetector;

public class MovingLine extends Activity implements OnCreateContextMenuListener {

	private GameView gameView;
	private GestureDetector gestureDetector;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Creating the GameView 
		gameView = (GameView) findViewById(R.id.gameView);

		registerForContextMenu(gameView);
		
		
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		
		gameView.setPause(true);
		
		if(v.getId() == R.id.gameView){
		
		String[] menuItems = this.getResources().getStringArray(R.array.contextMenu);
		String headTitle = this.getResources().getString(R.string.app_name)+" "+this.getResources().getString(R.string.version);
		
	    menu.setHeaderTitle(headTitle);
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
		}
	  
	}
	
	@Override
	public void onContextMenuClosed(Menu menu) {
		gameView.setPause(false);
		super.onContextMenuClosed(menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		gameView.setPause(false);
		return super.onContextItemSelected(item);
	}


}