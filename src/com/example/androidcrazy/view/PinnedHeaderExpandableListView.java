
package com.example.androidcrazy.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidcrazy.adapter.PinnedHeaderExpandableListAdapter;

/**
 * 功能描述：组标题会停留在top处的ExpandableListView， QQ的好友列表展示效果
 * 
 * @author njzhufeifei
 * @date 2013-2-7 下午12:42:16
 */
public class PinnedHeaderExpandableListView {

    // top处显示的header内容
    private LinearLayout topHeader;
    /** topHeader可见且填充完内容后的高度 */
    int topHeaderHeight;
    private TextView topHeaderTxt;
    private ImageView topHeaderImg;

    /** 所有group在所有item(包括所有group和child)中的potision */
    int[] groupPositionInAllItem;

    /** 记录group是否打开 */
    boolean[] groupOpen;

    /** 当前topHeader中应该是第几个group */
    int currentGroupPosition;

    /** group 总数 */
    int groupCount;

    ExpandableListView elv;

    /** 自定义的adapter */
    PinnedHeaderExpandableListAdapter ela;

    /**
     * @param elv ExpandableListView
     * @param ela PinnedHeaderExpandableListAdapter 扩展的ExpandableListAdapter接口
     * @param topHeader 浮在top处header
     * @param topHeaderTxt 浮在top处的group的文本
     * @param topHeaderImg 浮在top处的group的logo图片
     * @param openIconId 打开情况下的logo图片 id
     */
    public PinnedHeaderExpandableListView(ExpandableListView elv,
            PinnedHeaderExpandableListAdapter ela, LinearLayout topHeader, TextView topHeaderTxt,
            ImageView topHeaderImg, int openIconId) {

        this.elv = elv;
        this.ela = ela;
        this.topHeader = topHeader;
        this.topHeaderTxt = topHeaderTxt;
        this.topHeaderImg = topHeaderImg;
        this.topHeaderImg.setBackgroundResource(openIconId);
        // 去除所有group左侧默认的图标
        this.elv.setGroupIndicator(null);
        this.elv.setAdapter(ela);

        this.groupCount = this.ela.getGroupCount();
        this.groupPositionInAllItem = new int[this.groupCount];
        this.groupOpen = new boolean[this.groupCount];

        // 计算group在所有item中的potision
        calculateGroupPosition();
        // 初始化ExpandableListView的监听器
        initExpandableListViewListeners();

    }

    private void initExpandableListViewListeners() {
        topHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                elv.collapseGroup(currentGroupPosition);
                elv.setSelectedGroup(currentGroupPosition);
            }
        });

        /** 监听父节点打开的事件 */
        elv.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                groupOpen[groupPosition] = true;
                calculateGroupPosition();// 重新计算group position
            }
        });
        /** 监听父节点关闭的事件 */
        elv.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                groupOpen[groupPosition] = false;
                calculateGroupPosition();// 重新计算group position
            }
        });

        /** 通过setOnScrollListener来监听列表上下滑动时item显示和消失的事件 */
        elv.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {

                currentGroupPosition = calculateCurrentGroupPosition(firstVisibleItem);

                /** 滑动经过未打开的group的时候topHeader隐藏，一旦遇到打开的则显现 */
                if (groupOpen[currentGroupPosition]) { // 当前group是打开的

                    topHeaderTxt.setText(ela.getGroup(currentGroupPosition).toString());
                    // 计算topHeader正常的时候（text和img都放进去的且VISIBLE时候）的高度
                    topHeaderHeight = topHeaderHeight == 0 ? topHeader.getHeight()
                            : topHeaderHeight;
                    topHeader.setVisibility(FrameLayout.VISIBLE);
                } else {
                    topHeader.setVisibility(FrameLayout.INVISIBLE);
                }

                /** 当前topHeader被下一个group推上去的特效 */
                View nextGroup = ela.getGroupView(currentGroupPosition + 1);
                // 当前group不是最后一个group,且topHeader是可见的
                if (nextGroup != null && topHeader.getVisibility() == FrameLayout.VISIBLE) {

                    if (nextGroup.getTop() >= 0 && nextGroup.getTop() <= topHeaderHeight) {
                        topHeader.layout(0, nextGroup.getTop() - topHeaderHeight,
                                topHeader.getWidth(), nextGroup.getTop());
                    } else if (nextGroup.getTop() > topHeaderHeight) {
                        /**
                         * 解决快速向下滑动导致nextGroup.getTop() > topHeaderHeight，但上
                         * 面还没来得及layout() header的时候可能会出先header只出现一部分的问题
                         */
                        topHeader.layout(0, 0, topHeader.getWidth(), topHeaderHeight);
                    }
                } else if (nextGroup == null) { // 当前group是最后一个group
                    topHeader.layout(0, 0, topHeader.getWidth(), topHeaderHeight);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

        });
    }

    /**
     * 计算group在所有item中的potision
     */
    private void calculateGroupPosition() {

        int itemPostitons = 0;
        groupPositionInAllItem[0] = 0;

        for (int i = 1; i < groupCount; i++) {
            // 打开的才计算childCount
            if (groupOpen[i - 1]) {
                itemPostitons = itemPostitons + ela.getChildrenCount(i - 1) + 1;
                groupPositionInAllItem[i] = itemPostitons;
            } else {
                itemPostitons = itemPostitons + 1;
                groupPositionInAllItem[i] = itemPostitons;
            }
        }
    }

    /**
     * 当前第一个可见item所属第几个group
     * 
     * @param firstVisibleItem 当前第一个可见的item position
     * @return
     */
    private int calculateCurrentGroupPosition(int firstVisibleItem) {

        firstVisibleItem++; // 第一个可见的被header挡住了，算是第二个可见的为第一个
        int result = 0;
        for (int i = 0; i < groupCount; i++) {
            if (i + 1 < groupCount) {
                if (firstVisibleItem >= groupPositionInAllItem[i]
                        && firstVisibleItem <= groupPositionInAllItem[i + 1]) {
                    result = i;
                    break;
                }
            } else {
                if (firstVisibleItem >= groupPositionInAllItem[i]) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

}
