package com.example.androidcrazy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.view.View;

public class SpecialPathView extends View {
	
	private float phase = 0f;
	// 特殊的Path路径效果列表
	private PathEffect[] effects = new PathEffect[7];

	private int[] colors = new int[]{Color.BLACK, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY, Color.MAGENTA};
	
	private Paint paint;
	
	private Path path;
	
	public SpecialPathView(Context context) {
		super(context);
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		path = new Path();
		path.moveTo(0, 0);
		for(int i = 1; i <= 10; i++){
			path.lineTo(i*30, (float)Math.random() * 30);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		//下面开始7中路径效果
		effects[0] = null;
		effects[1] = new CornerPathEffect(10);
		effects[2] = new DiscretePathEffect(3.0f, 5.0f);
		effects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);
		
		Path p = new Path();
		p.addRect(0, 0, 8, 8, Path.Direction.CCW);
		effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);
		effects[5] = new ComposePathEffect(effects[2], effects[4]);
		effects[6] = new SumPathEffect(effects[4], effects[3]);
		// 将画布移动到8，8处
		canvas.translate(8, 8);
		for(int i = 0; i<effects.length; i++){
			paint.setPathEffect(effects[i]);
			paint.setColor(colors[i]);
			canvas.drawPath(path, paint);
			canvas.translate(0, 60);
			
			Paint textPaint = new Paint();
			textPaint.setTextAlign(Paint.Align.RIGHT);
			textPaint.setTextSize(20);
			textPaint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath("疯狂android讲义", path, 0, -20, textPaint);
		}
		phase += 1;
		invalidate();//刷新
	}
	
}
