
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidcrazy.R;

public class AnimationDrawableViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 多张图片实现gif图片效果 */
        setContentView(R.layout.animation_drawable_view);
        ImageView iv = (ImageView) findViewById(R.id.img_animation);
        final AnimationDrawable ad = (AnimationDrawable) iv.getDrawable();
        Button start = (Button) findViewById(R.id.anim_start);
        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ad.start();
            }
        });
        Button stop = (Button) findViewById(R.id.anim_stop);
        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ad.stop();
            }
        });
        /* 山水图片缩放和透明度渐变 */
        Button btn = (Button) findViewById(R.id.anim_shanshui_start);
        final ImageView shanshui = (ImageView) findViewById(R.id.anim_shanshui);
        final Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.my_animation);
        // 设置动画结束后保留结束状态
        // anim.setFillAfter(true);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                shanshui.startAnimation(anim);
            }
        });
    }
}
