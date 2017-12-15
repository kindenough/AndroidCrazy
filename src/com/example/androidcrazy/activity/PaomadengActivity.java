
package com.example.androidcrazy.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.androidcrazy.BaseActivity;
import com.example.androidcrazy.R;
import com.example.androidcrazy.view.CommodityPromotionView;

public class PaomadengActivity extends BaseActivity {

    private LinearLayout commLayout;
    private ImageSwitcher mSwitcher;
    private CommodityPromotionView mCpv;

    private LinearLayout commLayout2;
    private ImageSwitcher mSwitcher2;
    private CommodityPromotionView mCpv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paomaden_view);

        commLayout = (LinearLayout) this.findViewById(R.id.linearlayout_actives);
        mSwitcher = (ImageSwitcher) this.findViewById(R.id.imageSwitcher);
        mSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                final ImageView iv = new ImageView(PaomadengActivity.this);
                iv.setScaleType(ScaleType.FIT_XY);
                int height = getWindowManager().getDefaultDisplay().getHeight();
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
                        height * 15 / 100));
                return iv;
            }
        });
        // 启动跑马灯线程
        mCpv = new CommodityPromotionView(this, mSwitcher, commLayout, 0);

        // =========================================================================

        commLayout2 = (LinearLayout) this.findViewById(R.id.linearlayout_actives2);
        mSwitcher2 = (ImageSwitcher) this.findViewById(R.id.imageSwitcher2);
        mSwitcher2.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                final ImageView iv = new ImageView(PaomadengActivity.this);
                iv.setScaleType(ScaleType.FIT_XY);
                int height = getWindowManager().getDefaultDisplay().getHeight();
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
                        height * 15 / 100));
                return iv;
            }
        });
        // 启动跑马灯线程
        mCpv2 = new CommodityPromotionView(this, mSwitcher2, commLayout2, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCpv.start();
        mCpv2.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCpv.stop();
        mCpv2.stop();
    }

}
