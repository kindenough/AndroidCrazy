
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.Color;
import android.os.Bundle;

import com.example.androidcrazy.R;
import com.example.androidcrazy.listener.MyLoadGestureListener;

public class GestureLiberayActivity extends Activity {

    GestureOverlayView gestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 不需要重写onTouchEvent方法 */
        setContentView(R.layout.gesture_overlay_view);
        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
        gestureOverlayView.setGestureColor(Color.RED);
        gestureOverlayView.setGestureStrokeWidth(4);
        gestureOverlayView
                .addOnGesturePerformedListener(new MyLoadGestureListener(this));
    }
}
