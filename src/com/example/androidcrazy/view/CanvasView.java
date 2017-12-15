package com.example.androidcrazy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class CanvasView extends View {
	
	
	public CanvasView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);// 画笔的粗细
		paint.setAntiAlias(true);// 去锯齿
		// 绘制圆形
		canvas.drawCircle(40, 40, 30, paint);
		// 绘制正方形
		canvas.drawRect(10, 80, 70, 140, paint);
		// 绘制矩形
		canvas.drawRect(10, 150, 70, 190, paint);
		
		RectF re1 = new RectF(10, 200, 70, 230);
		// RectF的坐标是圆角矩形原来的那个矩形，用下面这句话画出它来就看出来了
//		canvas.drawRect(10, 200, 70, 230, paint);
		// 绘制圆角矩形，15,15是圆角的半径
		canvas.drawRoundRect(re1, 15, 15, paint);
		// 绘制椭圆
		RectF re11 = new RectF(10, 240, 70, 270);
		canvas.drawOval(re11, paint);
		
		// 定义一个Path封闭一个三角形
		Path path1 = new Path();
		path1.moveTo(10, 340);
		path1.lineTo(70, 340);
		path1.lineTo(40, 290);
		path1.close();
		// 根据path进行绘制，绘制出三角形
		canvas.drawPath(path1, paint);
		
/**======================== 设置填充风格后绘制====================================*/
		Paint paint2 = new Paint();
		paint2.setStrokeWidth(1);
		paint2.setAntiAlias(true);
		paint2.setStyle(Paint.Style.FILL);
		paint2.setColor(Color.RED);
		// 绘制圆形
		canvas.drawCircle(120, 40, 30, paint2);
		// 绘制正方形
		canvas.drawRect(90, 80, 150, 140, paint2);
		// 绘制矩形
		canvas.drawRect(90, 150, 150, 190, paint2);
		// 绘制圆角矩形
		RectF re2 = new RectF(90, 200, 150, 230);
		canvas.drawRoundRect(re2, 15, 15, paint2);
		// 绘制椭圆
		RectF re21 = new RectF(90, 240, 150, 270);
		canvas.drawOval(re21, paint2);
		// 定义一个Path封闭一个三角形
		Path path2 = new Path();
		path2.moveTo(90, 340);
		path2.lineTo(150, 340);
		path2.lineTo(120, 290);
		path2.close();
		// 根据path进行绘制，绘制出三角形
		canvas.drawPath(path2, paint2);
		
/**======================== 设置渐变后绘制====================================*/
		Shader mShader = new LinearGradient(0, 0, 40, 60
				, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}
				, null
				, Shader.TileMode.REPEAT);
		Paint paint3 = new Paint();
		paint3.setShader(mShader);// 设置渐变
		paint3.setShadowLayer(45, 10, 10, Color.GRAY);// 设置阴影
		paint3.setStrokeWidth(1);
		paint3.setAntiAlias(true);
		paint3.setStyle(Paint.Style.FILL);
		paint3.setColor(Color.RED);
		// 绘制圆形
		canvas.drawCircle(200, 40, 30, paint3);
		// 绘制正方形
		canvas.drawRect(170, 80, 230, 140, paint3);
		// 绘制矩形
		canvas.drawRect(170, 150, 230, 190, paint3);
		// 绘制圆角矩形
		RectF re3 = new RectF(170, 200, 230, 230);
		canvas.drawRoundRect(re3, 15, 15, paint3);
		// 绘制椭圆
		RectF re31 = new RectF(170, 240, 230, 270);
		canvas.drawOval(re31, paint3);
		// 定义一个Path封闭一个三角形
		Path path3 = new Path();
		path3.moveTo(170, 340);
		path3.lineTo(230, 340);
		path3.lineTo(200, 290);
		path3.close();
		// 根据path进行绘制，绘制出三角形
		canvas.drawPath(path3, paint3);
		
/**======================== 设置字符大小后绘制====================================*/	
		Paint paint4 = new Paint();
		paint4.setStrokeWidth(1);
		paint4.setAntiAlias(true);
		paint4.setStyle(Paint.Style.FILL);
		paint4.setColor(Color.RED);
		paint4.setTextSize(24);
		paint4.setShader(null);
		canvas.drawText("圆形", 240, 50, paint4);
		canvas.drawText("正方形", 240, 120, paint4);
		canvas.drawText("长方形", 240, 175, paint4);
		canvas.drawText("圆角矩形", 240, 220, paint4);
		canvas.drawText("椭圆形", 240, 260, paint4);
		canvas.drawText("三角形", 240, 325, paint4);
		
	}
	
}
