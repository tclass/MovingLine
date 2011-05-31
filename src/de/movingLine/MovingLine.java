package de.movingLine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MovingLine extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnNewGame = (Button)findViewById(R.id.btnNewGame);
        Button btnHighscore = (Button)findViewById(R.id.btnHighscore);
        Button btnInformation = (Button)findViewById(R.id.btnInformation);
        
    }

}