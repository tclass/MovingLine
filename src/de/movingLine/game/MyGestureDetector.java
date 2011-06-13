package de.movingLine.game;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

public class MyGestureDetector extends SimpleOnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 5;
    private MyGestureInteface receiver;
    
   
	
    public MyGestureDetector(MyGestureInteface receiver) {
		this.receiver = receiver;
	}
    
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		
		return super.onScroll(e1, e2, distanceX, distanceY);
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		// right to left swipe
		if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			receiver.setFlingDirection(MyGestureInteface.WEST);	
		}
	
		// left to right swipe
		else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			receiver.setFlingDirection(MyGestureInteface.EAST);		
		}
		
		// down to up swipe
		if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			receiver.setFlingDirection(MyGestureInteface.NORTH);		
		}
		
		// up to down swipe
		else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			receiver.setFlingDirection(MyGestureInteface.SOUTH);			
		}
		
		return super.onFling(e1, e2, velocityX, velocityY);
	}
	
	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return super.onSingleTapConfirmed(e);
	}
	
    // It is necessary to return true from onDown for the onFling event to register
    @Override
    public boolean onDown(MotionEvent e) {
        	return true;
    }
}
