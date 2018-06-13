package uno.unisal.com.uno;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListner implements View.OnTouchListener  {

    private GestureDetector gestureDetector;

    public OnSwipeTouchListner(Context context){
        gestureDetector = new GestureDetector(context, new GestureListner());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListner extends GestureDetector.SimpleOnGestureListener{

        private static final int SWIPE_THRESHHOLD = 50;
        private static final int SWIPE_VELOCITY_THRESHHOLD = 100;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float diffx = e2.getX()-e1.getX();
            float diffy = e2.getY()-e1.getY();

            if (Math.abs(diffy) > Math.abs(diffx)){
                if (Math.abs(diffy) >  SWIPE_THRESHHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHHOLD){
                    if (diffy > 0) {
                        onSwipeUp();
                    }
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public void onSwipeUp() {

    }
}
