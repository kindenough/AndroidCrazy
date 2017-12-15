
package com.example.androidcrazy.view;

import com.example.androidcrazy.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 功能描述： 自定义image view 直接在xml中用的
 * 
 * @author 360BUY
 * @created 2013-1-23 下午1:57:32
 */
public class MyImageView extends ImageView {

    private int duration = 0;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray tda = context.obtainStyledAttributes(attrs, R.styleable.myImageView);
        this.duration = tda.getInt(R.styleable.myImageView_duration, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setAlpha(this.duration);
    }

}
