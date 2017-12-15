
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.androidcrazy.view.DrawView;

public class DrawViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DrawView dw = new DrawView(this);
        dw.setMinimumWidth(300);
        dw.setMinimumHeight(500);
        dw.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                dw.currentX = event.getX();
                dw.currentY = event.getY();
                dw.invalidate();
                return true;
            }
        });
        setContentView(dw);
    }
}
