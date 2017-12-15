
package com.example.androidcrazy.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.androidcrazy.service.ICat.Stub;

/**
 * 功能描述： 跨进程的Service服务端实现 Aidl服务端开发过程:
 * 1、开发ICat.aidl接口；2、AidlService类；3、添加service到Manifest.xml
 * 
 * @author njzhufeifei
 * @created 2013-1-21 下午5:27:40
 */
public class AidlService extends Service {

    private CatBinder catBinder;

    private String color;

    private double weight;

    Timer timer = new Timer();

    String[] colors = new String[] {
            "红色",
            "黄色",
            "蓝色"
    };

    double[] weights = new double[] {
            2.3,
            1.5,
            3.1
    };

    public class CatBinder extends Stub {
        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        /**
         * 返回catBinder对象 在绑定本地Service的情况下，该catBinder对象会直接
         * 传给客户端的ServiceConnection对象的onServiceConnected方法，在绑定远程Service的情况下，
         * 只将catBinder对象的代理传给客户端的ServiceConnection对象的onServiceConnected方法
         */
        return catBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catBinder = new CatBinder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 3);
                color = colors[rand];
                weight = weights[rand];
            }
        }, 0, 800);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
