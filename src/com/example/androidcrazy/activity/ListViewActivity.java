
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidcrazy.R;

public class ListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ListView lv = (ListView) findViewById(R.id.listView);
        String[] strs = new String[] {
                "孙悟空", "猪八戒", "牛魔王"
        };
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, strs);
        lv.setAdapter(arrAdapter);
    }
}
