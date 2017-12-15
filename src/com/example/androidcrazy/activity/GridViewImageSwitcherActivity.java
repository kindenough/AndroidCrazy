
package com.example.androidcrazy.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.androidcrazy.R;

public class GridViewImageSwitcherActivity extends Activity {

    final int[] imageIds = new int[] {
            R.drawable.u30_normal,
            R.drawable.u54_normal, R.drawable.u56_normal,
            R.drawable.u59_normal, R.drawable.u61_normal,
            R.drawable.u63_normal, R.drawable.u65_normal,
            R.drawable.u147_normal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        final ImageSwitcher switcher = (ImageSwitcher) findViewById(R.id.switcher);
        switcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        switcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageSwitcher.LayoutParams lp = new ImageSwitcher.LayoutParams(
                        150, 150);
                ImageView imageView = new ImageView(GridViewImageSwitcherActivity.this);
                imageView.setBackgroundColor(0xff0000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(lp);
                return imageView;
            }
        });
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.grid_adapter_view, new String[] {
                        "image"
                },
                new int[] {
                        R.id.image1
                });
        GridView grid = (GridView) findViewById(R.id.gridView);
        grid.setAdapter(simpleAdapter);
        grid.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                switcher.setImageResource(imageIds[position % imageIds.length]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                switcher.setImageResource(imageIds[position % imageIds.length]);
            }
        });

    }
}
