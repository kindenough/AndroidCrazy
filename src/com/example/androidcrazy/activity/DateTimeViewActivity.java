
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

import com.example.androidcrazy.R;

public class DateTimeViewActivity extends Activity {

    Chronometer ch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analog_digital_chronometer);
        ch = (Chronometer) findViewById(R.id.chronometer);
        ch.setOnChronometerTickListener(new OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                // 从开始计时到现在超过20s
                if (SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000) {
                    ch.stop();
                }
            }
        });
        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ch.setBase(SystemClock.elapsedRealtime());
                ch.start();
            }
        });
    }
}
