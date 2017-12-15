package com.example.androidcrazy.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListViewAdapter implements ExpandableListAdapter {

	private Context context;
	
	private String[] armTypes = new String[]{
			"神族兵种", "虫族兵种", "人族兵种"
	};
	
	private String[][] arms = new String[][]{
			{"狂战士", "龙骑士", "黑暗圣堂", "电兵"},
			{"小狗", "刺蛇", "飞龙", "自爆飞机"},
			{"机枪兵", "护士MM", "幽灵"}
	};
	
	public ExpandableListViewAdapter(Context context){
		this.context = context;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return arms[groupPosition][childPosition];
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return arms[groupPosition].length;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView textView = getTextView();
		textView.setText(getChild(groupPosition, childPosition).toString());
		return textView;
	}
	
	@Override
	public Object getGroup(int groupPosition) {
		return armTypes[groupPosition];
	}
	
	@Override
	public int getGroupCount() {
		return armTypes.length;
	}
	
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(0);
		TextView tv = getTextView();
		tv.setText(getGroup(groupPosition).toString());
		ll.addView(tv);
		return ll;
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

	private TextView getTextView(){
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER | Gravity.LEFT);
		textView.setPadding(36, 0, 0, 0);
		textView.setTextSize(20);
		return textView;
	}
}
