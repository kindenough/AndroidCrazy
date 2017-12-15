
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.androidcrazy.R;

/**
 * 功能描述：android线程必须遵守的两个原则
 * 1、不要阻塞UI线程。
 * 2、不要在UI线程之外访问Andoid的UI组件包。
 * 
 * 解决上面第二个原则的方法：
 * 1、uiHandler.post(runnable); 在非ui线程中用ui线程中的handler去post一个可能操作view的runnable
 * 2、使用异步任务AsyncTask 
 * 
 * @author njzhufeifei
 * @date 2013-2-16 下午3:00:20
 */
public class Thread_UIThreadActivity extends Activity {

    TextView[] views = new TextView[7];

    Handler uiHandler = new Handler();

    final int[] names = new int[] {
            R.id.text1, R.id.text2, R.id.text3,
            R.id.text4, R.id.text5, R.id.text6, R.id.text7

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);
        for (int i = 0; i < 7; i++) {
            views[i] = (TextView) findViewById(names[i]);
            views[i].setBackgroundColor(Color.RED);
        }

        // 非UI线程
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                /*
//                 * 这里直接操作view理论上会报错，但是要是在没有上面sleep的情况下也许不会，
//                 * 因为这个线程改变全局变量的速度很快在UI线程在渲染前就完成了，UI线程渲染就不会报错了
//                 */
//                for (int i = 0; i < 7; i++) {
//                    views[i].setBackgroundColor(Color.BLUE);
//                }

                // 给系统发送一条消息 ，非ui线程中用ui线程中的handler去post一个可能操作view的runnable
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 7; i++) {
                            // 这是UI线程中的一个handler的回调方法，所以是允许操作view的
                            views[i].setBackgroundColor(Color.BLUE);
                        }
                    }
                });

            }
        }).start();
    }
}
