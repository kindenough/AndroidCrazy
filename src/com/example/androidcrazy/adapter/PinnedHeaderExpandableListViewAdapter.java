
package com.example.androidcrazy.adapter;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidcrazy.R;

public class PinnedHeaderExpandableListViewAdapter implements PinnedHeaderExpandableListAdapter {

    private Context context;

    /** 缓存所有group 的views */
    @SuppressLint("UseSparseArrays")
    public HashMap<Integer, View> groupViews = new HashMap<Integer, View>();

    private String[] itemHeaders = new String[] {
            "aaaaaaaa", "AAAAAAAA", "bbbbbbbb", "BBBBBBBB", "CCCCCCCC", "DDDDDD", "EEEEEEEE"
    };

    private String[][] items = new String[][] {
            {
            // 没有子项
            },
            {
                    "test-A-1", "test-A-2", "test-A-3", "test-A-4", "test-A-5", "test-A-6",
                    "test-A-7", "test-A-8", "test-A-9"
            },
            {
            // 没有子项
            },
            {
                    "test-B-1", "test-B-2", "test-B-3", "test-B-4", "test-B-5", "test-B-6",
                    "test-B-7", "test-B-8", "test-B-9"
            },
            {
                    "test-C-1", "test-C-2", "test-C-3", "test-C-4", "test-C-5", "test-C-6",
                    "test-C-7", "test-C-8", "test-C-9"
            },
            {
                    "test-D-1", "test-D-2", "test-D-3", "test-D-4", "test-D-5", "test-D-6",
                    "test-D-7", "test-D-8", "test-D-9"
            },
            {
                    "test-E-1", "test-E-2", "test-E-3", "test-E-4", "test-E-5", "test-E-6",
                    "test-E-7", "test-E-8", "test-E-9", "test-E-4", "test-E-5", "test-E-6",
                    "test-E-7", "test-E-8", "test-E-9", "test-E-4", "test-E-5", "test-E-6",
                    "test-E-7", "test-E-8", "test-E-9", "test-E-4", "test-E-5", "test-E-6",
                    "test-E-7", "test-E-8", "test-E-9", "test-E-4", "test-E-5", "test-E-6",
            }
    };

    public PinnedHeaderExpandableListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getGroupView(int groupPosition) {
        return groupViews.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items[groupPosition].length;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = (LinearLayout) View.inflate(context,
                    R.layout.expandable_list_item_view, null);
        }

        TextView itemTextView = (TextView) convertView.findViewById(R.id.item_txt);
        itemTextView.setText(getChild(groupPosition, childPosition).toString());
        return convertView;

    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemHeaders[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return itemHeaders.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {

        /** 自己将的group的每个view对象都缓存下来 */
        if (groupViews.get(groupPosition) == null) {
            convertView = (LinearLayout) View.inflate(context,
                    R.layout.expandable_list_group_view, null);
            groupViews.put(groupPosition, convertView);
        }
        convertView = groupViews.get(groupPosition);

        TextView parentTextView = (TextView) convertView.findViewById(R.id.group_txt);
        parentTextView.setText(getGroup(groupPosition).toString());
        ImageView parentImageViw = (ImageView) convertView.findViewById(R.id.group_img);
        // 判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            parentImageViw.setBackgroundResource(R.drawable.icon_open);
        } else {
            parentImageViw.setBackgroundResource(R.drawable.icon_closed);
        }

        Log.d("getGroupView", groupPosition + "--" + convertView.toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        // TODO Auto-generated method stub
    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        // TODO Auto-generated method stub
        return 0;
    }

}
