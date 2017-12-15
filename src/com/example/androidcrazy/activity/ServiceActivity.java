
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.service.MyBindService;
import com.example.androidcrazy.util.CommonUtils;

/**
 * 功能描述：service启动有两种方式startService()和bindService();
 * 前者启动后activity与service没有任何关系，后者两者捆绑共生死
 * 前者只能用stopService()结束，但是已经被bind的service stop也没用
 * 后者可以通过unbindService()方法结束，当一个service所有binder都被unbind了就结束了
 * 前者会调用service的onCreate()（只会在service启动的时候执行一次）方法和onStart()方法
 * 后者会调用service的onCreate()（只会在service启动的时候执行一次）方法和onBind()方法
 * 
 *
 * @author njzhufeifei
 * @date 2013-2-18 下午3:27:23
 */
public class ServiceActivity extends Activity {

    ServiceConnection serviceConnection;

    private MyBindService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 没有数据bind的service */
        setContentView(R.layout.service_view);
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        final Intent serviceIntent = new Intent();
        serviceIntent.setAction("first_service_action");
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
                Toast.makeText(ServiceActivity.this, "service start 成功", Toast.LENGTH_SHORT).show();
            }
        });
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
                Toast.makeText(ServiceActivity.this, "service stop 成功", Toast.LENGTH_SHORT).show();
            }
        });
        /* bind数据的service */
        Button bind = (Button) findViewById(R.id.bind);
        Button unbind = (Button) findViewById(R.id.unbind);
        Button status = (Button) findViewById(R.id.status);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (MyBindService.MyBinder) service;
                CommonUtils.traceLog(CommonUtils.INFO,
                        "MyBindServiceConnection onServiceConnected...");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                CommonUtils.traceLog(CommonUtils.INFO,
                        "MyBindServiceConnection onServiceDisconnected...");
            }
        };
        final Intent bindIntent = new Intent();
        bindIntent.setAction("bind_service_action");
        bind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里将service和serviceConnection绑定
                // 会将service的onBind()方法返回值传给serviceConnection的onServiceConnected()方法
                // 当activity结束的时候service也会结束，同事调用serviceConnection的onServiceDisconnected()方法
                bindService(bindIntent, serviceConnection, Service.BIND_AUTO_CREATE);
                Toast.makeText(ServiceActivity.this, "service bind 成功", Toast.LENGTH_SHORT).show();
            }
        });
        unbind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
                Toast.makeText(ServiceActivity.this, "service unbind 成功", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // 这个是在bind的情况下activity中获取service中的数据
        status.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceActivity.this, "service 的count值为： " + binder.getCount(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
