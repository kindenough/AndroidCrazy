
package com.example.androidcrazy.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class FrameTopViewService extends Service {

    public static final String OPERATION = "operation";
    public static final String FILTER = "FrameTopViewServiceFilter";
    public static final int OPERATION_SHOW = 100;
    public static final int OPERATION_HIDE = 101;

    private static WindowManager wm;
    private static WindowManager.LayoutParams params;
    public static View view;
    public static Activity context;
    /** 工具栏高度 */
    private static int TOOL_BAR_HEIGHT = 0;

    private boolean isAdded = false;

    /** 以屏幕左上角为原点，设置view的初始位置 */
    public int viewStartX = 0;
    public int viewStartY = 300;
    /** view的高度 */
    public int viewHeight = 40;

    /** MotionEvent.ACTION_DOWN的时候触摸点相对此view左上角的坐标 */
    private float x = 0;
    private float y = 0;

    OperationReceiver receiver = new OperationReceiver();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OPERATION_SHOW:
                    if (!isAdded) {
                        wm.addView(view, params);
                        isAdded = true;
                    }
                    break;
                case OPERATION_HIDE:
                    if (isAdded) {
                        wm.removeView(view);
                        isAdded = false;
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();

        // 注册receiver，同时制定filter只接收action为service_broadcast的broadcast
        IntentFilter filter = new IntentFilter(FILTER);
        registerReceiver(receiver, filter);

        createFloatView();
        Toast.makeText(this, "音乐服务启动成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        mHandler.sendEmptyMessage(OPERATION_HIDE);
        super.onDestroy();
        Toast.makeText(this, "音乐服务停止成功！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建悬浮窗
     */
    private void createFloatView() {

        // 下面的type让view显示在最前面,如果设置为LayoutParams.TYPE_PHONE;那么优先级低一些,即拉下通知栏不可见
        // TYPE_SYSTEM_OVERLAY窗口显示的时候焦点在后面的Activity上，仍旧可以操作后面的Activity
        params.type = LayoutParams.TYPE_SYSTEM_ALERT | LayoutParams.TYPE_SYSTEM_OVERLAY;
        // 下面的flags属性的效果形同“锁定”, 悬浮窗不可触摸 | 不接受任何事件
        params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = LayoutParams.FILL_PARENT;
        params.height = viewHeight;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.alpha = 0.8f; // view的透明度
        params.x = viewStartX; // view的初始x位置
        params.y = viewStartY; // view的初始y位置

        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 触摸点相对于屏幕(整块显示屏)左上角坐标
                float rawX = event.getRawX();
                float rawY = event.getRawY() - TOOL_BAR_HEIGHT;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.x = (int) (rawX - x);
                        params.y = (int) (rawY - y);
                        wm.updateViewLayout(view, params);
                        break;
                    case MotionEvent.ACTION_UP:
                        params.x = (int) (rawX - x);
                        params.y = (int) (rawY - y);
                        wm.updateViewLayout(view, params);
                        break;
                }
                return true;
            }
        });
    }

    public class OperationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int operation = bundle.getInt(OPERATION);
            switch (operation) {
                case OPERATION_SHOW:
                    mHandler.sendEmptyMessage(OPERATION_SHOW);
                    break;
                case OPERATION_HIDE:
                    mHandler.sendEmptyMessage(OPERATION_HIDE);
                    break;
            }
        }
    }

    public static void setContext(Activity activity) {
        if (context == null) {
            context = activity;
            // 求工具栏的高度
            Rect frame = new Rect();
            context.getWindow().getDecorView()
                    .getWindowVisibleDisplayFrame(frame);
            TOOL_BAR_HEIGHT = frame.top;
        }
    }

    public static void setView(View v) {
        if (view == null) {
            view = v;
        }
    }
}
