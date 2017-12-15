
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.contentProvider.MyContentObserver;
import com.example.androidcrazy.contentProvider.WordsConstants;
import com.example.androidcrazy.util.CommonUtils;

public class ContentResolverActivity extends Activity {

    ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* ContentProvider 见DictContentProvider */
        setContentView(R.layout.provider_resolver_view);
        contentResolver = getContentResolver();
        Button query = (Button) findViewById(R.id.query);
        Button insert = (Button) findViewById(R.id.insert);
        query.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                contentResolver.query(WordsConstants.Word.TABLE_CONTENT_URI,
                        null,
                        "word like ? or detail like ?",
                        new String[] {
                                "%word%", "%detail%"
                        },
                        null);
                Toast.makeText(ContentResolverActivity.this, "查询成功", 3000).show();
            }
        });
        insert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cvs = new ContentValues();
                cvs.put(WordsConstants.Word.WORD, "myword");
                cvs.put(WordsConstants.Word.DETAIL, "mydetail");
                contentResolver.insert(WordsConstants.Word.TABLE_CONTENT_URI, cvs);
                Toast.makeText(ContentResolverActivity.this, "插入成功", 3000).show();
            }
        });
        /* ContentObserver 监听ContentProvider的数据改变 */
        // 这里可能没有效果只是做了个代码示例。原因是MyContentProvider（图简单）没有改变数据库的内容
        MyContentObserver observer = new MyContentObserver(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                CommonUtils.traceLog(CommonUtils.INFO, "===========MyContentObserver==========");
            }
        });
        contentResolver.registerContentObserver(WordsConstants.Word.CONTENT_URI,
                true, observer);
    }
}
