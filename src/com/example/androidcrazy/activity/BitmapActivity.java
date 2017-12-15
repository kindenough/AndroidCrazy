
package com.example.androidcrazy.activity;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androidcrazy.R;

public class BitmapActivity extends Activity {

    private String[] files = null;

    AssetManager assets = null;

    int currentImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* AssetManager使用 */
        setContentView(R.layout.bitmap_bitmaofactory_view);
        final ImageView image = (ImageView) findViewById(R.id.img_bitmap);
        try {
            // /assets目录下的文件与/res/raw下的类似都会以原始文件的形式放到apk中，唯一区别是前者不会再R文件中创建索引
            // 读取assets目录下的文件需要用AssetManager
            assets = getAssets();
            // 获取/assets/目录下所有文件
            files = assets.list("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Button btn = (Button) findViewById(R.id.getNextImg);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (currentImg >= files.length) {
                    currentImg = 0;
                }
                while (!files[currentImg].endsWith(".png")) {
                    currentImg++;
                    if (currentImg >= files.length) {
                        currentImg = 0;
                    }
                }
                InputStream assetFileIS = null;
                try {
                    assetFileIS = assets.open(files[currentImg++]);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                BitmapDrawable bitd = (BitmapDrawable) image.getDrawable();
                if (bitd != null && !bitd.getBitmap().isRecycled()) {
                    bitd.getBitmap().recycle();
                }
                image.setImageBitmap(BitmapFactory.decodeStream(assetFileIS));
            }
        });
    }
}
