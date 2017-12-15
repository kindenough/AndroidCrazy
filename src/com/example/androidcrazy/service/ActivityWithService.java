
package com.example.androidcrazy.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 功能描述：通过receiver与activity通信的service
 * 
 * @author zhuff
 * @created 2013-1-21 下午1:49:22
 * @date 2013-1-21 下午1:49:22
 */
public class ActivityWithService extends Service {

    private boolean isStop;

    private int data = 1000;

    ServiceReceiver receiver = new ServiceReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册receiver，同时制定filter只接收action为activity_broadcast的broadcast
        IntentFilter filter = new IntentFilter("activity_broadcast");
        registerReceiver(receiver, filter);

        new Thread() {// 新建线程，每隔5秒发送一次广播，同时把i放进intent传出
            public void run() {
                while (!isStop) {
                    Intent intent = new Intent();
                    intent.putExtra("serviceData", "service_data = " + data++);
                    intent.setAction("service_broadcast");// action与接收器相同
                    sendBroadcast(intent);
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        isStop = true;
        super.onDestroy();
    }

    public class ServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String activityData = bundle.getString("activityData");
            // 处理接收到的内容
            Toast.makeText(context, "service收到的activity的数据为：" + activityData, Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
