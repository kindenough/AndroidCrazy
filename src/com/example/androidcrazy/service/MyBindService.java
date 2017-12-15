
package com.example.androidcrazy.service;

import com.example.androidcrazy.util.CommonUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * 功能描述：bind数据的service
 * 
 * @author zhuff
 * @created 2013-1-21 下午1:49:22
 * @date 2013-1-21 下午1:49:22
 */
public class MyBindService extends Service {

    private int count;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    /**
     * 功能描述：用来返回给service调用这的IBinder对象
     * 
     * @author zhuff
     * @created 2013-1-21 下午2:38:36
     */
    public class MyBinder extends Binder {

        public int getCount() {
            return count;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        CommonUtils.traceLog(CommonUtils.INFO, "MyBindService onBind...");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        CommonUtils.traceLog(CommonUtils.INFO, "MyBindService onUnbind...");
        return true; // 会调onDestory犯法
    }

    @Override
    public void onCreate() {
        CommonUtils.traceLog(CommonUtils.INFO, "MyBindService onCreate...");
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        CommonUtils.traceLog(CommonUtils.INFO, "MyBindService onDestroy...");
        super.onDestroy();
        this.quit = true;
    }
}
