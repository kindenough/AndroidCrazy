
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.androidcrazy.R;
import com.example.androidcrazy.listener.MyOnGestureListener;

public class GestureDetectorActivity extends Activity {

    GestureDetector gestureDetector;

    ViewFlipper viewFlipper;

    Animation[] animations = new Animation[4];

    int[] animIds = new int[] {
            R.anim.right_in, R.anim.left_out,
            R.anim.left_in, R.anim.right_out

    };

    final int[] imageIds = new int[] {
            R.drawable.u30_normal,
            R.drawable.u54_normal, R.drawable.u56_normal,
            R.drawable.u59_normal, R.drawable.u61_normal,
            R.drawable.u63_normal, R.drawable.u65_normal,
            R.drawable.u147_normal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_detector_view);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        for (int i = 0; i < 4; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imageIds[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            viewFlipper.addView(iv);
            animations[i] = AnimationUtils.loadAnimation(this, animIds[i]);
        }
        gestureDetector = new GestureDetector(new MyOnGestureListener(
                viewFlipper, animations));
        /* 需要重写onTouchEvent方法 return gestureDetector.onTouchEvent(event); */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
