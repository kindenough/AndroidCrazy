
package com.example.androidcrazy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Handler;
import android.widget.TextView;

/**
 * 功能描述：可以显示在桌面上的TextView
 *
 * @author njzhufeifei
 * @date 2013-2-17 下午4:29:18
 */
@SuppressLint("DrawAllocation")
public class LrcTextView extends TextView {

    /** 歌词颜色渐变特效参数 */
    private float float1 = 0.0f;
    private float float2 = 0.01f;

    /** TextView显示的内容 */
    public String text = "世上只有妈妈好，有妈的孩子像块宝";
    public float textSize = 25;

    private Handler uiHandler = new Handler();
    /** 更新界面线程 */
    @SuppressWarnings("unused")
    private Runnable update = new Runnable() {
        public void run() {
            LrcTextView.this.postInvalidate();
            uiHandler.postDelayed(update, 100);
        }
    };

    public LrcTextView(Context context) {
        super(context);
        this.setBackgroundColor(Color.argb(10, 150, 150, 150));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 文字总长度
        float len = textSize * text.length();
        // 文字距离view左边
        float left = (this.getWidth() - len) / 2;

        float1 += 0.001f;
        float2 += 0.001f;

        if (float2 > 1.0) {
            float1 = 0.0f;
            float2 = 0.01f;
        }
        this.setText("");

        Shader shader = new LinearGradient(left, 0, left + len, 0,
                new int[] {
                        Color.YELLOW, Color.RED
                },
                new float[] {
                        float1, float2
                },
                TileMode.CLAMP);
        Paint p = new Paint();
        p.setTextSize(textSize);
        p.setShader(shader);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(text, left, textSize, p);
    }

}
