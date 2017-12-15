
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.androidcrazy.R;
import com.example.androidcrazy.adapter.ExpandableListViewAdapter;

public class ExpandableListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_list_view);
        ExpandableListAdapter ela = new ExpandableListViewAdapter(this);
        ExpandableListView elv = (ExpandableListView) findViewById(R.id.expandableListView);
        elv.setAdapter(ela);
    }
}
