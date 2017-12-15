
package com.example.androidcrazy.activity;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.androidcrazy.R;
import com.example.androidcrazy.adapter.FragmentViewPagerAdapter;
import com.example.androidcrazy.fragment.Tab1Fragment;
import com.example.androidcrazy.fragment.Tab2Fragment;
import com.example.androidcrazy.fragment.Tab3Fragment;

/**
 * 功能描述：Fragment + ViewPager组合使用
 * 
 * fragment和ViewPager是安卓3.0之后出现的组建，低版本中通过引入android-support-v4.jar包也可以支持
 * fragment比tab更强大的地方就是它可以在页面的内部某个区域实现tab效果，而tab只能对全页实现tab切换
 *
 * @author njzhufeifei
 * @date 2013-2-16 下午5:05:41
 */
@SuppressLint("UseSparseArrays")
public class FragmentViewActivity extends FragmentActivity {

    RadioGroup tabGroup;
    ImageView iv;
    /** tab移动效果起始结束位置 */
    int fromLeft = 0;
    int toLeft;
    /** 当前选中tab（默认第二个） */
    int currentTabIndex = 1;

    HashMap<Integer, Integer> tabsIdIndex = new HashMap<Integer, Integer>();
    HashMap<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();

    private OnCheckedChangeListener onCheckedChangeListener = new RadioGroupOnCheckedChangeListener();
    private OnPageChangeListener onPageChangeListener = new FragmentOnPageChangeListener();

    ViewPager viewPager;

    /** viewPager页面是否需要切换 */
    boolean pageLock = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.fragment_view);
        super.onCreate(savedInstanceState);
        initTabIdIndex();
        initFragment();

        tabGroup = (RadioGroup) findViewById(R.id.lyTabTitle);
        tabGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setAlpha(50);

        viewPager = (ViewPager) findViewById(R.id.fragment_container);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments));
        // 初始化currnetTabId对应fragment为content_fragment的内容
        viewPager.setCurrentItem(currentTabIndex);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    private void initTabIdIndex() {
        tabsIdIndex.put(0, R.id.tab_1);
        tabsIdIndex.put(1, R.id.tab_2);
        tabsIdIndex.put(2, R.id.tab_3);
        tabsIdIndex.put(R.id.tab_1, 0);
        tabsIdIndex.put(R.id.tab_2, 1);
        tabsIdIndex.put(R.id.tab_3, 2);
    }

    private void initFragment() {
        fragments.put(0, new Tab1Fragment(this));
        fragments.put(1, new Tab2Fragment(this));
        fragments.put(2, new Tab3Fragment(this));
    }

    /**
     * 选项卡切换
     * 
     * @param toBeCheckedIndex
     */
    private void tabsChanges(int toBeCheckedIndex) {
        int tabWidth = iv.getWidth();// 每个菜单的宽度
        toLeft = fromLeft + tabWidth * (toBeCheckedIndex - currentTabIndex);
        Log.d("currentTabIndex1", currentTabIndex + "");
        currentTabIndex = toBeCheckedIndex;
        Log.d("currentTabIndex2", currentTabIndex + "");
        TranslateAnimation animation = new TranslateAnimation(fromLeft, toLeft, 0, 0);
        animation.setDuration(400);
        animation.setFillAfter(true);
        animation.setFillBefore(true);
        iv.startAnimation(animation);
        fromLeft = toLeft;
    }

    public class RadioGroupOnCheckedChangeListener implements OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d("onCheckedChanged", checkedId + "");
            int checkedIndex = tabsIdIndex.get(checkedId);
            // ViewPager滑动切换页面的时候需要手动改变group中当前选中radio，此时会触发该方法但不需要切换页面
            if (pageLock) {
                // 主页面切换
                viewPager.setCurrentItem(checkedIndex);
            }
        }
    }

    public class FragmentOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int index) {
            int checkId = tabsIdIndex.get(index);
            pageLock = false;
            tabGroup.check(checkId); // 改变radiogroup的选中radio
            pageLock = true;
            tabsChanges(index);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}
