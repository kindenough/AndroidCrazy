
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidcrazy.R;
import com.example.androidcrazy.adapter.PinnedHeaderExpandableListAdapter;
import com.example.androidcrazy.adapter.PinnedHeaderExpandableListViewAdapter;
import com.example.androidcrazy.view.PinnedHeaderExpandableListView;

/**
 * 功能描述：扩展ExpandableListView 实现的QQ列表类似的效果，
 * 
 * @author njzhufeifei
 * @date 2013-2-7 下午2:36:52
 * @toSee {@link PinnedHeaderExpandableListAdapter} 扩展的ExpandableListAdapter接口
 * @toSee {@link PinnedHeaderExpandableListView} 扩展的ExpandableListView视图
 */
public class PinnedHeaderExpandableListViewActivity extends Activity {

    PinnedHeaderExpandableListView phelv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_pinned_header_list_view);

        ExpandableListView elv = (ExpandableListView) findViewById(R.id.expandableListView);
        PinnedHeaderExpandableListAdapter ela = new PinnedHeaderExpandableListViewAdapter(this);
        LinearLayout topHeader = (LinearLayout) findViewById(R.id.top_group);
        TextView topHeaderTxt = (TextView) topHeader.findViewById(R.id.group_txt);
        ImageView topHeaderImg = (ImageView) topHeader.findViewById(R.id.group_img);

        phelv = new PinnedHeaderExpandableListView(elv, ela, topHeader, topHeaderTxt,
                topHeaderImg, R.drawable.icon_open);

    }
}
