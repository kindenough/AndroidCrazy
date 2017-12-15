
package com.example.androidcrazy.listener;

import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MyOnGestureListener implements OnGestureListener {
    // 移动速度
    final int FLIP_INSTANCE = 50;

    ViewFlipper viewFlipper;

    Animation[] animations = new Animation[4];

    public MyOnGestureListener(ViewFlipper viewFlipper, Animation[] animations) {
        this.viewFlipper = viewFlipper;
        this.animations = animations;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
            float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > FLIP_INSTANCE) {
            viewFlipper.setInAnimation(animations[0]);
            viewFlipper.setOutAnimation(animations[1]);
            viewFlipper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() > FLIP_INSTANCE) {
            viewFlipper.setInAnimation(animations[2]);
            viewFlipper.setOutAnimation(animations[3]);
            viewFlipper.showNext();
            return true;
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
            float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

}
