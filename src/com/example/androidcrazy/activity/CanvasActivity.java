
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidcrazy.R;
import com.example.androidcrazy.view.CanvasView;
import com.example.androidcrazy.view.SpecialPathView;

public class CanvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_main_view);
        Button common = (Button) findViewById(R.id.common);
        common.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 普通的几何图形 */
                CanvasView cv = new CanvasView(CanvasActivity.this);
                setContentView(cv);
            }
        });
        Button specialPath = (Button) findViewById(R.id.specialPath);
        specialPath.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 不同路径的图形效果 */
                SpecialPathView spv = new SpecialPathView(CanvasActivity.this);
                setContentView(spv);
            }
        });
    }
}
