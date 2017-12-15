
package com.example.androidcrazy.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * 功能描述：自定义的有3d效果的gallery
 * 
 * @author
 * @date
 */
public class FlowGallery extends Gallery {

    /** 相机类 */
    private Camera mCamera = new Camera();

    /** 最大转动角度 */
    private int mMaxRotationAngle = 60;

    /** 最大缩放值 */
    private int mMaxZoom = -100;

    /** gallery X轴中心位置 */
    private int mCoveflowCenter;

    public FlowGallery(Context context) {
        super(context);
        //支持转换 ,执行getChildStaticTransformation方法
        this.setStaticTransformationsEnabled(true);
    }

    public FlowGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setStaticTransformationsEnabled(true);
    }

    public FlowGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setStaticTransformationsEnabled(true);
    }

    /**
     * 控制gallery中每个图片的旋转(重写的gallery中方法)
     */
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation tsfm) {
        //取得当前子view中心x轴值
        int childCenter = getCenterOfView(child);
        int childWidth = child.getWidth();
        //旋转角度
        int rotationAngle = 0;
        //重置转换状态
        tsfm.clear();
        //设置转换类型
        tsfm.setTransformationType(Transformation.TYPE_MATRIX);
        //如果图片位于中心位置不需要进行旋转
        if (childCenter == mCoveflowCenter) {
            transformImageBitmap((ImageView) child, tsfm, 0);
        } else {
            //根据图片在gallery中的位置来计算图片的旋转角度
            rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * 45);
            transformImageBitmap((ImageView) child, tsfm, rotationAngle);
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 转换图片
     * 
     * @param child
     * @param tsfm
     * @param rotationAngle
     */
    private void transformImageBitmap(ImageView child, Transformation tsfm,
            int rotationAngle) {

        //对效果进行保存
        mCamera.save();

        //返回旋转角度的绝对值
        int rotation = Math.abs(rotationAngle);

        //控制旋转的最大角度，否则旋转多了就看不到了
        if (rotation > mMaxRotationAngle) {
            rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle : mMaxRotationAngle;
        }
        // 根据角度动态缩放，这样效果会连续
        float zoomAmount = (float) (mMaxZoom + (rotation * 2));
        // 在Z轴上正向移动camera的视角，实际效果为放大图片； 如果在Y轴上移动，则图片上下移动；X轴上对应图片左右移动。
        mCamera.translate(0.0f, 0.0f, zoomAmount);

        // 在Y轴上旋转，对应图片竖向向里翻转。
        mCamera.rotateY(rotationAngle);

        int imageWidth = child.getWidth();
        int imageHeight = child.getHeight();
        Matrix imageMatrix = tsfm.getMatrix();

        mCamera.getMatrix(imageMatrix); // 这句话只能在下面两句话前面
        // 设置以图片中间旋转和缩放
        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
        mCamera.restore();
    }

    /**
     * 图片中心
     * 
     * @param view
     * @return
     */
    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    /**
     * gallery控件中心
     * 
     * @return
     */
    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                + getPaddingLeft();
    }

}
