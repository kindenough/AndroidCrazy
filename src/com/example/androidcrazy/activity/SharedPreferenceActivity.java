
package com.example.androidcrazy.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcrazy.PreferenceTestActivity;
import com.example.androidcrazy.R;

public class SharedPreferenceActivity extends Activity {

    SharedPreferences sharedPreferences;

    SharedPreferences sharedPreferencesXml;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences_view);
        sharedPreferences = getSharedPreferences("androidCrazy", MODE_PRIVATE);
        sharedPreferencesXml = getSharedPreferences(
                "com.example.androidcrazy_preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Button read = (Button) findViewById(R.id.btn_read);
        Button write = (Button) findViewById(R.id.btn_write);
        read.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String time = sharedPreferences.getString("time", null);
                int randNum = sharedPreferences.getInt("random", 0);

                String result = time == null ? "您暂时没有写入数据！" : "写入时间为：" + time
                        + "\n上次生成的随机数：" + randNum;
                Toast.makeText(SharedPreferenceActivity.this, result, 500).show();

                String ringValue = sharedPreferencesXml.getString("ring_key",
                        "");
                String nameValue = sharedPreferencesXml.getString("name_key",
                        "");
                String genderValue = sharedPreferencesXml.getString(
                        "gender_key", "0");
                boolean autoSaveValue = sharedPreferencesXml.getBoolean(
                        "autoSave_key", true);
                String setPreference = "ring=" + ringValue + "; name="
                        + nameValue + "; gender=" + genderValue + "; autoSave="
                        + autoSaveValue;
                Toast.makeText(SharedPreferenceActivity.this, setPreference, 500).show();
            }
        });
        write.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy年MM月dd日 hh:mm:ss");
                editor.putString("time", sdf.format(new Date()));
                editor.putInt("random", (int) (Math.random() * 100));
                editor.commit();
                /* PreferenceActivity 适合选项设置的activity */
                Intent intent = new Intent();
                intent.setClass(SharedPreferenceActivity.this, PreferenceTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
