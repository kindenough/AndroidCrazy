
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidcrazy.R;
import com.example.androidcrazy.service.ICat;

/**
 * 功能描述： 跨进程的Service服务端实现 Aidl客户端开发过程: 
 * 1、开发aidl客户端第一步需要将服务端的ICat.aidl文件考到客户端
 * 2、创建ServiceConnection实例，以ServiceConnection对象为参数调用context的bindService()方法绑定远程Service
 * 3、在onDestroy方法中解绑AidlService
 * 
 * @author njzhufeifei
 * @date 2013-2-16 下午1:52:06
 */
public class AIDLServiceActivity extends Activity {

    private ICat iCat;

    private ServiceConnection aidlServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl_service_view);
        aidlServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 这里是客户端绑定远程Service和本地Serivce代码上的唯一区别
                iCat = ICat.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iCat = null;
            }
        };
        Intent aidlIntent = new Intent();
        aidlIntent.setAction("aidl_service_action");
        bindService(aidlIntent, aidlServiceConnection, Service.BIND_AUTO_CREATE);
        Button get = (Button) findViewById(R.id.getAidlService);
        final EditText set = (EditText) findViewById(R.id.aidlServiceText);
        get.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = "Color: " + iCat.getColor() + "; Weight: " + iCat.getWeight();
                    set.setText(result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        /** AIDL Service android中系统服务，TelephonyManager */
        Button getTHM = (Button) findViewById(R.id.getTHM);
        final EditText showTHM = (EditText) findViewById(R.id.aidlTHMText);
        getTHM.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager thm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                // 获取设备编号
                String deviceId = thm.getDeviceId();
                // 获取系统平台版本
                String softWareVersion = thm.getDeviceSoftwareVersion() != null ? thm
                        .getDeviceSoftwareVersion() : "未知";
                /* 还可以获取很多信息，参照疯狂android讲义394页 */
                /* 还可以监听电话状态的改变，参照疯狂android讲义395页 */
                String result = "deviceId: " + deviceId + "; softWareVersion: "
                        + softWareVersion;
                showTHM.setText(result);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (aidlServiceConnection != null) {
            this.unbindService(aidlServiceConnection);
        }
    }
}
