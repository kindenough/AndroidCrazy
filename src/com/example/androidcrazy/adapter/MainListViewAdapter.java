
package com.example.androidcrazy.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.androidcrazy.R;

@SuppressWarnings("unchecked")
public class MainListViewAdapter extends BaseAdapter {

    Map<String, Class<? extends Activity>> btnClasses = null;

    // Button信息集合
    private List<Map<String, Object>> listItems;

    private Context context;

    // 视图容器
    private LayoutInflater listContainer;

    @SuppressWarnings("rawtypes")
    public MainListViewAdapter(Context context, Map<String, Class<? extends Activity>> btnClasses) {
        this.btnClasses = btnClasses;
        this.listItems = new ArrayList<Map<String, Object>>();
        this.context = context;
        this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文

        //根据textString按字母排序
        List keyList = new ArrayList(btnClasses.keySet());
        Collections.sort(keyList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (((String) o1).compareTo((String) o2));
            }
        });

        Iterator<String> it = keyList.iterator();
        Map<String, Object> map = null;
        while (it.hasNext()) {
            map = new HashMap<String, Object>();
            String text = (String) it.next();
            map.put("text", text);
            map.put("clazz", btnClasses.get(text));
            listItems.add(map);
        }
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // 获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.main_button_list, null);
        }

        Button btnList = (Button) convertView.findViewById(R.id.btn_list);
        btnList.setText((String) listItems.get(position).get("text"));
        Class<? extends Activity> clazz = (Class<? extends Activity>) listItems.get(position).get(
                "clazz");
        btnList.setOnClickListener(new BtnOnclickListener(clazz));
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class BtnOnclickListener implements OnClickListener {

        Class<? extends Activity> clazz = null;

        BtnOnclickListener(Class<? extends Activity> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(context, this.clazz);
            context.startActivity(intent);
        }
    }

}
