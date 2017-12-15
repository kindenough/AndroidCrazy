
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.service.ActivityWithService;

/**
 * 功能描述：通过receiver与service通信的activity
 * 
 * @author njzhufeifei
 * @date 2013-2-18 下午3:44:14
 */
public class ServiceWithActivity extends Activity {

    Button startService;
    Button stopService;
    Button putDataToService;

    ActivityReceiver receiver = new ActivityReceiver();

    private int data = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_view);
        startService = (Button) findViewById(R.id.startService);
        stopService = (Button) findViewById(R.id.stopService);
        putDataToService = (Button) findViewById(R.id.putDataToService);

        // 注册receiver，同时制定filter只接收action为service_broadcast的broadcast
        IntentFilter filter = new IntentFilter("service_broadcast");
        registerReceiver(receiver, filter);

        startService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(ServiceWithActivity.this,
                        ActivityWithService.class);
                startService(serviceIntent);
            }
        });

        stopService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(ServiceWithActivity.this,
                        ActivityWithService.class);
                stopService(serviceIntent);
            }
        });

        putDataToService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("activityData", "activity_data = " + data++);
                intent.setAction("activity_broadcast");// action与接收器相同
                sendBroadcast(intent);
            }
        });

    }

    public class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String serviceData = bundle.getString("serviceData");
            // 处理接收到的内容
            Toast.makeText(context, "activity收到的service的数据为：" + serviceData, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
