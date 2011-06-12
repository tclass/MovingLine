package de.movingLine;

import android.app.Activity;
import android.os.Bundle;
import de.movingLine.game.GameView;

public class MovingLine extends Activity {
	
	
	GameView gameView;


	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        gameView = (GameView) findViewById(R.id.gameView);

        
        
    }
    




}