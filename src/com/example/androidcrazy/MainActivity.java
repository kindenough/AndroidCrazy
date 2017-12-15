
package com.example.androidcrazy;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidcrazy.activity.AIDLServiceActivity;
import com.example.androidcrazy.activity.A_1_activity;
import com.example.androidcrazy.activity.AnimationDrawableViewActivity;
import com.example.androidcrazy.activity.AttributeDefinedActivity;
import com.example.androidcrazy.activity.AutoCompleteTextViewActivity;
import com.example.androidcrazy.activity.BitmapActivity;
import com.example.androidcrazy.activity.BroadcastActivity;
import com.example.androidcrazy.activity.CanvasActivity;
import com.example.androidcrazy.activity.CanvasDrawBoardActivity;
import com.example.androidcrazy.activity.ClipDrawableViewActivity;
import com.example.androidcrazy.activity.ContentResolverActivity;
import com.example.androidcrazy.activity.DateTimePickerViewActivity;
import com.example.androidcrazy.activity.DateTimeViewActivity;
import com.example.androidcrazy.activity.DownloadActivity;
import com.example.androidcrazy.activity.DrawViewActivity;
import com.example.androidcrazy.activity.ExpandableListViewActivity;
import com.example.androidcrazy.activity.FragmentViewActivity;
import com.example.androidcrazy.activity.FrameLayoutActivity;
import com.example.androidcrazy.activity.FrameTopLrcTextViewActivity;
import com.example.androidcrazy.activity.Gallery3DActivity;
import com.example.androidcrazy.activity.GestureDetectorActivity;
import com.example.androidcrazy.activity.GestureLiberayActivity;
import com.example.androidcrazy.activity.GestureLiberayUseActivity;
import com.example.androidcrazy.activity.GridViewImageSwitcherActivity;
import com.example.androidcrazy.activity.HorizontalScrollViewActivity;
import com.example.androidcrazy.activity.ImgDoubleCachesActivity;
import com.example.androidcrazy.activity.IntentActionCategoryActivity;
import com.example.androidcrazy.activity.LinearLayoutActivity;
import com.example.androidcrazy.activity.ListViewActivity;
import com.example.androidcrazy.activity.MenuViewActivity;
import com.example.androidcrazy.activity.NotificationActivity;
import com.example.androidcrazy.activity.PaomadengActivity;
import com.example.androidcrazy.activity.PinnedHeaderExpandableListViewActivity;
import com.example.androidcrazy.activity.ProgressBarViewActivity;
import com.example.androidcrazy.activity.PullUpLoadingListViewActivity;
import com.example.androidcrazy.activity.RatingBarViewActivity;
import com.example.androidcrazy.activity.RelativeLayoutActivity;
import com.example.androidcrazy.activity.SQLiteDatabaseActivity;
import com.example.androidcrazy.activity.ScrollViewActivity;
import com.example.androidcrazy.activity.SeekBarViewActivity;
import com.example.androidcrazy.activity.ServiceActivity;
import com.example.androidcrazy.activity.ServiceWithActivity;
import com.example.androidcrazy.activity.SharedPreferenceActivity;
import com.example.androidcrazy.activity.SmsManagerActivity;
import com.example.androidcrazy.activity.SpinnerViewActivity;
import com.example.androidcrazy.activity.SystemActionCategoryActivity;
import com.example.androidcrazy.activity.TableLayoutActivity;
import com.example.androidcrazy.activity.TextToSpeechActivity;
import com.example.androidcrazy.activity.Thread_UIThreadActivity;
import com.example.androidcrazy.activity.ToogleButtonActivity;
import com.example.androidcrazy.activity.WebViewActivity;
import com.example.androidcrazy.adapter.MainListViewAdapter;

@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {

    Map<String, Class<? extends Activity>> btnClasses = new HashMap<String, Class<? extends Activity>>();

    MainListViewAdapter mainListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        // 初始化所有activity的button
        initBtns();

        if (mainListViewAdapter == null) {
            mainListViewAdapter = new MainListViewAdapter(this, btnClasses); // 创建适配器
        }
        ListView btnListView = (ListView) findViewById(R.id.listView);
        btnListView.setAdapter(mainListViewAdapter);
    }

    private void initBtns() {

        /** LinearLayout Button、RadioGroup、 CheckBox */
        btnClasses.put("LinearLayout线性布局", LinearLayoutActivity.class);

        /** TableLayout */
        btnClasses.put("TableLayout表格布局", TableLayoutActivity.class);

        /** FrameLayout 霓虹灯效果 */
        btnClasses.put("FrameLayout霓虹灯效果", FrameLayoutActivity.class);

        /** RelativeLayout 梅花效果 */
        btnClasses.put("RelativeLayout梅花效果", RelativeLayoutActivity.class);

        /** 自定义view跟着触点走的小球 */
        btnClasses.put("自定义view跟着触点走的小球", DrawViewActivity.class);

        /** ListView 列表视图 */
        btnClasses.put("ListView ArrayAdapter列表视图", ListViewActivity.class);

        /** WebView web视图 */
        btnClasses.put("WebView web视图", WebViewActivity.class);

        /** ToggleButton 动态布局效果 */
        btnClasses.put("ToogleButton动态布局效果", ToogleButtonActivity.class);

        /** AnalogClock 、 DigitalClock and Chronometer 时钟和数字日期 */
        btnClasses.put("AnalogClock、DigitalClock、 Chronometer", DateTimeViewActivity.class);

        /** AutoCompleteTextView 根据输入自动补充可能的全部 */
        btnClasses.put("AutoCompleteTextView根据输入自动补充可能的全部", AutoCompleteTextViewActivity.class);

        /** Spinner View 选择框（弹出框形式选择） */
        btnClasses.put("SpinnerView弹出框形式选择列表", SpinnerViewActivity.class);

        /** DatePicker TimePicker View 日期时间选择器 */
        btnClasses.put("DatePicker TimePicker日期时间选择器 ", DateTimePickerViewActivity.class);

        /** ProgressBar View 普通进度条、显示在标题栏上的进度条 */
        btnClasses.put("ProgressBar普通进度条、显示在标题栏上的进度条", ProgressBarViewActivity.class);

        /** RatingBar View 评级 */
        btnClasses.put("RatingBar根据得分展示星级效果", RatingBarViewActivity.class);

        /** SeekBar 拖动条，音量调节效果 */
        btnClasses.put("SeekBar拖动条，音量调节效果", SeekBarViewActivity.class);

        /** ScrollView 、HorizontalScrollView 垂直和水平滚动条 */
        btnClasses.put("ScrollView之Horizontal", HorizontalScrollViewActivity.class);

        /** ScrollView 、HorizontalScrollView 垂直和水平滚动条 */
        btnClasses.put("ScrollView之Vertical", ScrollViewActivity.class);

        /** ExpandableListView 分组可展开收缩的ListView */
        btnClasses.put("ExpandableListView分组可展开收缩的ListView", ExpandableListViewActivity.class);

        /** Notification 状态栏通知 */
        btnClasses.put("Notification状态栏通知", NotificationActivity.class);

        /** GridView、ImageSwitcher */
        btnClasses.put("GridView、ImageSwitcher", GridViewImageSwitcherActivity.class);

        /** SmsManager 消息管理器，发短信（这里是模拟器只能给其它模拟器发短信） */
        btnClasses.put("SmsManager消息管理器，发短信", SmsManagerActivity.class);

        /** Intent Action、Category属性 测试 */
        btnClasses.put("Intent Action、Category", IntentActionCategoryActivity.class);

        /** 系统 Action、Category属性 */
        btnClasses.put("System  Action、Category", SystemActionCategoryActivity.class);

        /** ClipDrawable 徐徐展开的风景 */
        btnClasses.put("ClipDrawable徐徐展开的风景", ClipDrawableViewActivity.class);

        /** AnimationDrawable 会动的图片 */
        btnClasses.put("AnimationDrawable会动的图片", AnimationDrawableViewActivity.class);

        /** Menu、SubMenu、ContextMenu xml配置menu */
        btnClasses.put("Menu、SubMenu、ContextMenu", MenuViewActivity.class);

        /** Attribute 自定义view的duration属性 控制图片的透明度 */
        btnClasses.put("自定义Attribute", AttributeDefinedActivity.class);

        /** Bitmap、BitmapFactory 图形与图像处理 */
        btnClasses.put("Bitmap、BitmapFactory图形与图像处理", BitmapActivity.class);

        /** Canvas 绘制自定义图形 */
        btnClasses.put("Canvas绘制自定义图形 ", CanvasActivity.class);

        /** Canvas 采用双缓存实现画图板 */
        btnClasses.put("Canvas实现画板功能", CanvasDrawBoardActivity.class);

        /** SharedPreference 简单的key-value数据存取 */
        btnClasses.put("SharedPreference简单的key-value数据存取", SharedPreferenceActivity.class);

        /** SQLiteDatabase 安卓客户端的嵌入式数据库 */
        btnClasses.put("SQLiteDatabase嵌入式数据库", SQLiteDatabaseActivity.class);

        /** GestureDetector + ViewFlipper实现翻页效果 */
        btnClasses.put("GestureDetector + ViewFlipper实现翻页效果", GestureDetectorActivity.class);

        /** GestureLiberay 自定义手势 */
        btnClasses.put("GestureDetector自定义手势", GestureLiberayActivity.class);

        /** GestureLiberay 通过自定义的手势实现用户操作 */
        btnClasses.put("GestureDetector自定义手势操作", GestureLiberayUseActivity.class);

        /** TextToSpeech 语音朗读 */
        btnClasses.put("TextToSpeech语音朗读", TextToSpeechActivity.class);

        /** ContentProvider、ContentResolver 应用之间共享数据 */
        btnClasses.put("ContentProvider、ContentResolver应用之间共享数据 ", ContentResolverActivity.class);

        /** Service 相当于没有界面的activity */
        btnClasses.put("Service的两中启动方式", ServiceActivity.class);

        /** Activity与Service运行中通信 */
        btnClasses.put("Service与Activity运行过程中通信", ServiceWithActivity.class);

        /** Service 相当于没有界面的activity */
        btnClasses.put("Service", ServiceActivity.class);

        /** AIDL Service android中的跨进程调用 客户端,服务端见AidlService */
        btnClasses.put("AIDL Service安卓中的跨进程调用", AIDLServiceActivity.class);

        /** BroadcastReceiver 接收广播消息 */
        btnClasses.put("BroadcastReceiver收广播消息 ", BroadcastActivity.class);

        /** 非UI线程中不能操作UI线程中的View测试 */
        btnClasses.put("非UI线程中不能操作UI线程中的View测试", Thread_UIThreadActivity.class);

        /** ImageSwitcher animation gesture实现可以滑动的跑马灯 */
        btnClasses.put("跑马灯（ImageSwitcher animation gesture组合）", PaomadengActivity.class);

        /** 下载状态栏显示下载进度 */
        btnClasses.put("下载状态栏显示下载进度", DownloadActivity.class);

        /** Gallery3d效果 */
        btnClasses.put("Gallery3D效果", Gallery3DActivity.class);

        /** ListView 上拉加载更多效果 */
        btnClasses.put("ListView 上拉加载更多效果", PullUpLoadingListViewActivity.class);

        /** 异步加载图片的二级缓存技术 */
        btnClasses.put("异步加载图片的二级缓存技术", ImgDoubleCachesActivity.class);

        /** QQ的好友列表展示效果 */
        btnClasses.put("QQ的好友列表展示效果", PinnedHeaderExpandableListViewActivity.class);

        /** Fragment + ViewPager实现tab滑动切换 */
        btnClasses.put("Fragment + ViewPager实现tab滑动切换", FragmentViewActivity.class);

        /** 能够显示在桌面前面的的歌词效果 */
        btnClasses.put("显示在桌面前面的歌词效果", FrameTopLrcTextViewActivity.class);

        /** activity切换特效 */
        btnClasses.put("Activity切换特效 ", A_1_activity.class);
    }
}
