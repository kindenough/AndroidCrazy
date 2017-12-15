
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidcrazy.R;
import com.example.androidcrazy.service.FrameTopViewService;
import com.example.androidcrazy.view.LrcTextView;

public class FrameTopLrcTextViewActivity extends Activity implements OnClickListener {

    Button btnShow;
    Button btnClear;
    Button btnStart;
    Button btnStop;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_top_view);
        btnShow = (Button) findViewById(R.id.bt_show);
        btnClear = (Button) findViewById(R.id.bt_clear);
        btnStart = (Button) findViewById(R.id.bt_start);
        btnStop = (Button) findViewById(R.id.bt_stop);
        btnShow.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    public void onClick(View v) {

        // 初始化service属性
        FrameTopViewService.setContext(this);
        FrameTopViewService.setView(new LrcTextView(this));

        switch (v.getId()) {
            case R.id.bt_show:
                Intent show = new Intent();
                show.putExtra(FrameTopViewService.OPERATION,
                        FrameTopViewService.OPERATION_SHOW);
                show.setAction(FrameTopViewService.FILTER);// action与接收器相同
                sendBroadcast(show);
                break;

            case R.id.bt_clear:
                Intent hide = new Intent();
                hide.putExtra(FrameTopViewService.OPERATION,
                        FrameTopViewService.OPERATION_HIDE);
                hide.setAction(FrameTopViewService.FILTER);// action与接收器相同
                sendBroadcast(hide);
                break;

            case R.id.bt_start:
                Intent start = new Intent(this, FrameTopViewService.class);
                // 如果service已经启动了，这句话就什么也不做
                startService(start);
                break;

            case R.id.bt_stop:
                Intent stop = new Intent(this, FrameTopViewService.class);
                // 如果service已经停止了，这句话就什么也不做
                stopService(stop);
                break;
        }
    }
}
