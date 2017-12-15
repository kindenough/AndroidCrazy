
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.Color;
import android.os.Bundle;

import com.example.androidcrazy.R;
import com.example.androidcrazy.listener.MyUseGestureListener;

public class GestureLiberayUseActivity extends Activity {

    GestureOverlayView gestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_overlay_view);
        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureView);
        gestureOverlayView.setGestureColor(Color.GREEN);
        gestureOverlayView.setGestureStrokeWidth(4);
        gestureOverlayView
                .addOnGesturePerformedListener(new MyUseGestureListener(this));
    }
}
