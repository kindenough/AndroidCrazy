
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidcrazy.R;
import com.example.androidcrazy.util.ActivityConstant;

public class IntentActionCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page_view);
        Button btn = (Button) findViewById(R.id.gotoSecondPage);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(ActivityConstant.PAGE_TWO_ACTION);
                // 如果这里不addCategory 则 Catrgory的默认值为“android.intent.category.DEFAULT”
                intent.addCategory(ActivityConstant.PAGE_TWO_CATEGORY);
                // intent.addCategory("sssss"); // 这个category没在activity中配置filter所以找不到
                startActivity(intent);
            }
        });
    }
}
