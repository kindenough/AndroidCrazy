
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.androidcrazy.R;

public class ProgressBarViewActivity extends Activity {

    private int[] data = new int[100];

    private int hasData = 0;

    private int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** 显示在标题上的进度条 */
        requestWindowFeature(Window.FEATURE_PROGRESS);// 设置窗口特征：启用显示进度的进度条
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);// 设置窗口特征：启用显示进度的进度条
        // 上面两句必须在setContentView()之前
        setContentView(R.layout.progress_bar_view);
        Button show = (Button) findViewById(R.id.show);
        Button hidden = (Button) findViewById(R.id.hidden);
        show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressBarIndeterminateVisibility(true);
                setProgressBarVisibility(true);
                setProgress(4500);
            }
        });
        hidden.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressBarIndeterminateVisibility(false);
                setProgressBarVisibility(false);
            }
        });

        /** 普通进度条 */
        final ProgressBar pb1 = (ProgressBar) findViewById(R.id.progressBar);
        final ProgressBar pb2 = (ProgressBar) findViewById(R.id.progressBar2);
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    pb1.setProgress(status);
                    pb2.setProgress(status);
                }
            }
        };
        new Thread() {
            public void run() {
                while (status < 100) {
                    status = doWork();
                    Message msg = new Message();
                    msg.what = 0x111;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }

    private int doWork() {
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasData;
    }

}
