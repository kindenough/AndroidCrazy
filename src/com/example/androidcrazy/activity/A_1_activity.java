
package com.example.androidcrazy.activity;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidcrazy.R;

/**
 * 功能描述：activity切换带动画效果
 * overridePendingTransition需要用户手机设置动画为：显示动画
 *
 * @author njzhufeifei
 * @date 2013-2-19 下午1:03:04
 */
public class A_1_activity extends Activity {

    Spinner spinner;
    Button btn;

    int enterAnim = 0;
    int exitAnim = 0;

    HashMap<String, int[]> anims = new HashMap<String, int[]>();

    ListView dropDownListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_view);

        initDatas();

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                String name = tv.getText().toString();
                int[] anim = anims.get(name);
                enterAnim = anim[0];
                exitAnim = anim[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btn = (Button) findViewById(R.id.a_2);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(A_1_activity.this, A_2_activity.class);
                startActivity(intent);
                if (enterAnim != 0 && exitAnim != 0) {
                    overridePendingTransition(enterAnim, exitAnim);
                }
            }
        });

    }

    /**
     * 初始化数据除了需要向map中加入外还需要在array.xml中添加
     */
    private void initDatas() {
        anims.put("左进右出", new int[] {
                R.anim.left_in, R.anim.right_out
        });
        anims.put("右进左出", new int[] {
                R.anim.right_in, R.anim.left_out
        });
        anims.put("淡入淡出", new int[] {
                android.R.anim.fade_in, android.R.anim.fade_out
        });
        anims.put("zoom入zoom出", new int[] {
                R.anim.zoom_in, R.anim.zoom_out
        });

    }
}
