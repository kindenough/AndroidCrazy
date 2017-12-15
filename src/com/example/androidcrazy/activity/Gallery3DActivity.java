
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.adapter.Gallery3DImageAdapter;
import com.example.androidcrazy.view.FlowGallery;

public class Gallery3DActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gallery_3d_view);

        Integer[] images = {
                R.drawable.image01,
                R.drawable.image02,
                R.drawable.image03,
                R.drawable.image04,
                R.drawable.image05
        };

        Gallery3DImageAdapter adapter = new Gallery3DImageAdapter(this, images);
        adapter.createReflectedImages();//创建倒影效果
        FlowGallery galleryFlow = (FlowGallery) this.findViewById(R.id.gallery);
        galleryFlow.setFadingEdgeLength(0);
        galleryFlow.setSpacing(-100); //图片之间的间距
        galleryFlow.setAdapter(adapter);

        galleryFlow.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position),
                        Toast.LENGTH_SHORT).show();
            }

        });
        galleryFlow.setSelection(4);
    }

}
