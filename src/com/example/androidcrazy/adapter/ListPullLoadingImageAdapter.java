
package com.example.androidcrazy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ListPullLoadingImageAdapter extends BaseAdapter {

    private List<ImageView> listItems = new ArrayList<ImageView>();

    public ListPullLoadingImageAdapter(Context context, List<ImageView> listItems) {
        this.listItems = listItems;
    }

    @SuppressWarnings("unused")
    private Resources getResources() {
        return null;
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return listItems.get(position);
    }

}
