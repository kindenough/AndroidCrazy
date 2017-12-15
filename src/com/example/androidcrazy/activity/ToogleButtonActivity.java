
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.example.androidcrazy.R;

public class ToogleButtonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_button_view);
        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);
        final LinearLayout ll = (LinearLayout) findViewById(R.id.layoutForToogle);
        tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {

                if (isChecked) {
                    // 垂直布局
                    ll.setOrientation(1);
                } else {
                    // 水平布局
                    ll.setOrientation(0);
                }
            }
        });

    }

}
