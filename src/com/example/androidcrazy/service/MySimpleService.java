
package com.example.androidcrazy.service;

import com.example.androidcrazy.util.CommonUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 功能描述：简单的不bind数据的service
 * 
 *    一个服务不是一个独立的进程，也不是一个线程。
 *    可能有人会问，如果Service不是独立的一个进程的话，为什么当Activity退出时，Service仍然可以进行运行呢？
 * 其实是这样的，sdk上说了activity和service默认是运行在应用进程的主线程中，四大组件默认都是和activity运行在同一个主线程中的，
 * 那就是说activity通过startservice方法启动一个服务后，被启动的服务和activity都是在同一个线程中的。所以当我主动销毁了这个activity，
 * 但是他所在的线程还是存在的，只不过是这个activity他所占用的资源被释放掉了，这个activity所在的主线程只有当android内存不足才会被系统杀死掉，
 * 否则一般的情况下这个activity所在的应用程序的线程始终存在，也就是这个activity所启动的服务也会一直运行下去。
 *    还有一点需要注意的是，如果Service要处理一些比较耗时的工作时，因为Service和Activity默认情况都在同一个主线程中的缘故，
 * 所以要操作这些耗时的工作一般是在Service里另起一个新线程来处理。这样可以避免主线程的阻塞，影响用户体验性。
 * 
 * @author zhuff
 * @created 2013-1-21 下午1:49:22
 * @date 2013-1-21 下午1:49:22
 */
public class MySimpleService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        CommonUtils.traceLog(CommonUtils.INFO, "MyService onCreate...");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CommonUtils.traceLog(CommonUtils.INFO, "MyService onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        CommonUtils.traceLog(CommonUtils.INFO, "MyService onDestroy...");
        super.onDestroy();
    }

}
