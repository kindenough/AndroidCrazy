
package com.example.androidcrazy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidcrazy.R;
import com.example.androidcrazy.view.ScrollOverListView.OnScrollOverListener;

/**
 * 功能描述：真正实现上拉加载的是这个控件, ScrollOverListView只是提供触摸的事件等,
 * 
 * @author njzhufeifei
 * @date 2013-2-2 上午9:53:21
 */
@SuppressLint("HandlerLeak")
public class PullLoadListView extends LinearLayout {

    /** Handler what 数据加载完毕，没有数据可以下次加载了 */
    public static final int WHAT_DID_LOAD_DATA = 1;

    /** Handler what 已经获取完更多，还有数据可以下次加载 */
    public static final int WHAT_DID_MORE = -1;

    private Context context;

    /** listView底部的layout */
    private View mFooterView;
    /** listView底部的文字 */
    private TextView mFooterTextView;
    /** listView底部的loading view */
    private View mFooterLoadingView;

    private ScrollOverListView mListView;

    /** 底部拉动拉动list事件listener */
    private OnPullLoadListener mOnPullLoadListener;

    /** 是否获取更多中 */
    private boolean mIsFetchMoreing;

    /** 是否允许自动获取更多 */
    private boolean mEnableAutoFetchMore;

    /** 加载消息接受器 */
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_DID_LOAD_DATA: {
                    mIsFetchMoreing = false;
                    mFooterTextView.setText("上拉加载更多...");
                    mFooterLoadingView.setVisibility(View.GONE);
                    return;
                }
                case WHAT_DID_MORE: {
                    // 数据加载完毕,设置mIsFetchMoreing=true确保不会再触发加载事件
                    mIsFetchMoreing = true;
                    mFooterTextView.setText("数据加载完毕！");
                    mFooterLoadingView.setVisibility(View.GONE);
                    return;
                }
            }
        }
    };

    public PullLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initFooterViewAndListView();
    }

    public PullLoadListView(Context context) {
        super(context);
        this.context = context;
        initFooterViewAndListView();
    }

    /**
     * 初始化界面
     */
    private void initFooterViewAndListView() {
        setOrientation(LinearLayout.VERTICAL);
        /**
         * 自定义脚部文件
         */
        mFooterView = LayoutInflater.from(context).inflate(R.layout.list_pull_footer, null);
        mFooterTextView = (TextView) mFooterView.findViewById(R.id.pulldown_footer_text);
        mFooterLoadingView = mFooterView.findViewById(R.id.pulldown_footer_loading);
        // 非自动加载的时候点击更多文字加载
        mFooterTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsFetchMoreing) {
                    mIsFetchMoreing = true;
                    mFooterLoadingView.setVisibility(View.VISIBLE);
                    mOnPullLoadListener.onMore();
                }
            }
        });

        /*
         * ScrollOverListView 同样是考虑到都是使用，所以放在这里。同时因为，需要它的监听事件
         */
        mListView = new ScrollOverListView(context);
        mListView.setOnScrollOverListener(new OnScrollOverListener() {

            // 是否上拉
            @Override
            public boolean onListViewBottomAndPullUp(int delta) {
                if (!mEnableAutoFetchMore || mIsFetchMoreing) {
                    return false;
                } else {
                    mIsFetchMoreing = true;
                    mFooterTextView.setText("加载更多中...");
                    mFooterLoadingView.setVisibility(View.VISIBLE);
                    mOnPullLoadListener.onMore();
                    return true;
                }
            }

            @Override
            public boolean onMotionDown(MotionEvent ev) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onMotionMove(MotionEvent ev, int delta) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onMotionUp(MotionEvent ev) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        // mListView.setCacheColorHint(0);
        initFooterView();
        // 将listView加入到自定义的View中
        this.addView(mListView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

    }

    /**
     * 初始化footerview
     */
    private void initFooterView() {
        if (mListView.getFooterViewsCount() == 0) {
            mFooterTextView.setText("上拉加载更多...");
            mFooterLoadingView.setVisibility(View.GONE);
            mListView.addFooterView(mFooterView);
            mListView.setAdapter(mListView.getAdapter());
        }
    }

    /**
     * 通知加载完了数据，要放在Adapter.notifyDataSetChanged后面,
     * 当你加载完数据的时候，调用这个notifyDidLoad() 才会隐藏头部，并初始化数据等
     */
    public void notifyDidLoad() {
        this.mUIHandler.sendEmptyMessage(WHAT_DID_LOAD_DATA);
    }

    /**
     * 通知已经获取完更多了，要放在Adapter.notifyDataSetChanged后面
     * 当你执行完更多任务之后，调用这个notyfyDidMore() 才会隐藏加载圈等操作
     */
    public void notifyDidMore() {
        this.mUIHandler.sendEmptyMessage(WHAT_DID_MORE);
    }

    /**
     * 设置监听器
     * 
     * @param listener
     */
    public void setOnPullLoadListener(OnPullLoadListener listener) {
        this.mOnPullLoadListener = listener;
    }

    /**
     * 获取内嵌的listview
     * 
     * @return ScrollOverListView
     */
    public ListView getListView() {
        return this.mListView;
    }

    /**
     * 是否开启自动获取更多，自动获取更多，将会隐藏footer，并在到达底部的时候自动刷新
     */
    public void enableAutoFetchMore(boolean enable) {
        this.mEnableAutoFetchMore = enable;
    }

    /**
     * 功能描述：事件接口监听器
     * 
     * @author njzhufeifei
     * @date 2013-2-2 上午10:44:32
     */
    public interface OnPullLoadListener {
        void onMore();
    }
}
