
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidcrazy.R;
import com.example.androidcrazy.broadcast.SecondBroadcastReceiver;

public class BroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter("my_broadcast_receiver");
        SecondBroadcastReceiver mybr = new SecondBroadcastReceiver();
        // 注册BroadcastReceiver
        registerReceiver(mybr, filter);
        /* 发送broadcast广播 */
        setContentView(R.layout.broadcast_receiver_view);
        Button send = (Button) findViewById(R.id.sendBroadcast);
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("my_broadcast_receiver");
                intent.putExtra("msg", "activity发出的broadcast消息");
                //sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);
            }
        });
    }
}
