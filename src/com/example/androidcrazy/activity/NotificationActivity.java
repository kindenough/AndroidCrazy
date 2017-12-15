
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidcrazy.R;
import com.example.androidcrazy.SecondPageActivity;

public class NotificationActivity extends Activity {

    static final int NOTIFICATION_ID = 0x1123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        Button btn = (Button) findViewById(R.id.btn_notification);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 创建一个启动其它activity的Intent
                Intent intent = new Intent(NotificationActivity.this,
                        SecondPageActivity.class);
                // 创建一个非立马发生的intent
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this,
                        0, intent, 0);
                // 创建一个Notification
                Notification notify = new Notification();
                notify.icon = R.drawable.ic_launcher;
                // 状态栏显示的通知简语
                notify.tickerText = "通知";
                // 通知时间
                notify.when = System.currentTimeMillis();
                notify.defaults = Notification.DEFAULT_ALL;
                notify.setLatestEventInfo(NotificationActivity.this,
                        // 展开状态栏 内容的标题
                        "第二个页面",
                        // 展开状态栏 内容
                        "点击查看", pi);
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(NOTIFICATION_ID, notify);
            }
        });
        Button del = (Button) findViewById(R.id.btn_del);
        del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.cancel(NOTIFICATION_ID);
            }
        });
    }
}
