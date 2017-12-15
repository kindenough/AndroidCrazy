
package com.example.androidcrazy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.androidcrazy.R;
import com.example.androidcrazy.adapter.ListPullLoadingImageAdapter;
import com.example.androidcrazy.view.PullLoadListView;
import com.example.androidcrazy.view.PullLoadListView.OnPullLoadListener;

public class PullUpLoadingListViewActivity extends Activity {

    /** listview每次加载的条数 */
    private static final int LIST_PAGE_COUNT = 6;

    /** 数据总数 */
    private int itemCounts; // 调接口获取

    ListPullLoadingImageAdapter listAdapter = null;

    /** 自定义上拉加载的listVIew */
    private PullLoadListView mPullLoadView;

    /** 伪造的数据 */
    List<Integer> mImageIds = new ArrayList<Integer>();

    /** listview加载的数据集 */
    private List<ImageView> listItems = new ArrayList<ImageView>();

    /** 消息加载结果信息接收器 */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PullLoadListView.WHAT_DID_LOAD_DATA: {
                    // 通知Adapter改变数据
                    listAdapter.notifyDataSetChanged();
                    // 告诉它数据加载完毕;
                    mPullLoadView.notifyDidLoad();
                    break;
                }
                case PullLoadListView.WHAT_DID_MORE: {
                    listAdapter.notifyDataSetChanged();
                    // 告诉它获取更多完毕
                    mPullLoadView.notifyDidMore();
                    break;
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pull_loading_view);
        initDatas();

        showList();
    }

    private void showList() {
        if (listAdapter == null) {
            refreshHotGameList();
            listAdapter = new ListPullLoadingImageAdapter(this, listItems); // 创建适配器
        }
        mPullLoadView = (PullLoadListView) findViewById(R.id.game_list);
        mPullLoadView.setOnPullLoadListener(new OnPullLoadListener() {
            @Override
            public void onMore() {
                // 新起个线程去加载数据这样页面不会卡，否则会卡在这个地方
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {// 这里可以异步加载数据
                            refreshHotGameList();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg = new Message();
                        if (listItems.size() >= itemCounts) {
                            msg.what = PullLoadListView.WHAT_DID_MORE;
                        } else {
                            msg.what = PullLoadListView.WHAT_DID_LOAD_DATA;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
        ListView listView = mPullLoadView.getListView();
        listView.setAdapter(listAdapter);
        mPullLoadView.enableAutoFetchMore(true);
    }

    private void refreshHotGameList() {
        int loadCount = LIST_PAGE_COUNT;
        if (itemCounts < LIST_PAGE_COUNT + listItems.size()) {
            loadCount = itemCounts - listItems.size();
        }

        int start = listItems.size();
        int end = start + loadCount;
        for (int i = start; i < end; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(mImageIds.get(i));
            listItems.add(iv);
        }
    }

    /**
     * 造游戏列表数据
     */
    private void initDatas() {
        mImageIds.add(R.drawable.u22_normal_1);
        mImageIds.add(R.drawable.u22_normal_2);
        mImageIds.add(R.drawable.u22_normal_3);
        mImageIds.add(R.drawable.u22_normal_4);
        mImageIds.add(R.drawable.u22_normal_1);
        mImageIds.add(R.drawable.u22_normal_2);
        mImageIds.add(R.drawable.u22_normal_3);
        mImageIds.add(R.drawable.u22_normal_4);
        mImageIds.add(R.drawable.u22_normal_1);
        mImageIds.add(R.drawable.u22_normal_2);
        mImageIds.add(R.drawable.u22_normal_3);
        mImageIds.add(R.drawable.u22_normal_4);
        mImageIds.add(R.drawable.u22_normal_1);
        itemCounts = mImageIds.size();
    }

}
