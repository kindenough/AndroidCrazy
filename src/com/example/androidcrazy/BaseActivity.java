
package com.example.androidcrazy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class BaseActivity extends Activity {

    private Thread mUiThread;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mUiThread = Thread.currentThread();

        handler = new Handler();

        super.onCreate(savedInstanceState);

    }

    public Handler getHandler() {
        return handler;
    }

    /**
     * 统一 post 接口
     */
    public void post(final Runnable action) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (BaseActivity.this.isFinishing()) {
                    return;
                }
                action.run();
            }
        });
    }

    /**
     * 统一 post 接口
     */
    public void post(final Runnable action, int delayMillis) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (BaseActivity.this.isFinishing()) {
                    return;
                }
                action.run();
            }
        }, delayMillis);
    }

    /**
     * 如果正在UI线程则马上执行，否则就加入到任务队列
     */
    public void attemptRunOnUiThread(final Runnable action) {
        if (Thread.currentThread() != getUiThread()) {// 判断UI线程
            post(action);
        } else {
            action.run();
        }
    }

    private Thread getUiThread() {
        return mUiThread;
    }

}
