package com.example.androidcrazy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author 360BUY
 * 为什么要使用双缓冲技术?拿Android 游戏开发来说，界面贞每次都是全部重画的，也就说画了新的，旧的就没了，所以需要使用双缓冲技术保存之前的内容。
 */
public class DrawBoardView extends View {
	
	float preX;
	float preY;
	
	private Path path;
	Bitmap cacheBitmap = null;
	Canvas cacheCanvas = null;
	public Paint paint = null;
	
	public DrawBoardView(Context context, AttributeSet set) {
		super(context, set);
		path = new Path();
		cacheBitmap = Bitmap.createBitmap(480, 480, Config.ARGB_8888);
		cacheCanvas = new Canvas();
		cacheCanvas.setBitmap(cacheBitmap);
		
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint bmpPaint = new Paint();
		// 双缓存技术：这里再将cacheCanvas上的图案画到canvas中,这样才不会覆盖之前canvas上的图案
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
		// 这个保证每次event都会画图案，不至于到每次ACTION_UP的时候才把画的一次性展示出来，太不友好
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				path.moveTo(x, y);
				preX = x;
				preY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				path.quadTo(preX, preY, x, y);
				preX = x;
				preY = y;
				break;
			case MotionEvent.ACTION_UP:
				// 双缓存技术：这里不直接将图案画到canvas中，而是画到cacheCanvas中
				cacheCanvas.drawPath(path, paint);
				path.reset();
				break;
		}
		invalidate();
		return true;
	}
	
}
