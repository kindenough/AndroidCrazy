
package com.example.androidcrazy.adapter;

import android.view.View;
import android.widget.ExpandableListAdapter;

public interface PinnedHeaderExpandableListAdapter extends ExpandableListAdapter {

    /**
     * 根据groupPosition 获取GroupView
     * 
     * @param groupPosition 第几个group
     * @return
     */
    public View getGroupView(int groupPosition);
}
