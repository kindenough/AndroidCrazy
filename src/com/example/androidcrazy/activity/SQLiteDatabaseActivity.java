
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.util.CommonUtils;

public class SQLiteDatabaseActivity extends Activity {

    SQLiteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_db_view);
        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()
                .toString() + "/mydb.db3", null);
        Button btn_insert = (Button) findViewById(R.id.btn_insert);
        Button btn_query = (Button) findViewById(R.id.btn_query);
        Button btn_clear = (Button) findViewById(R.id.btn_clear);
        CommonUtils.traceLog(
                "info",
                "==============================================================================");
        btn_insert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.edit_title);
                EditText content = (EditText) findViewById(R.id.edit_content);
                String titleStr = title.getText().toString();
                String contentStr = content.getText().toString();
                try {
                    sqliteDatabase.execSQL(
                            "insert into news_table values(null, ?, ?)",
                            new String[] {
                                    titleStr, contentStr
                            });
                } catch (SQLiteException se) {
                    sqliteDatabase
                            .execSQL("create table news_table(_id integer primary key autoincrement,"
                                    + " news_title varchar(50),"
                                    + " news_content varchar(255))");
                    sqliteDatabase.execSQL(
                            "insert int news_table values(null, ?, ?)",
                            new String[] {
                                    titleStr, contentStr
                            });
                }
                Toast.makeText(SQLiteDatabaseActivity.this, "插入成功", 500).show();
            }
        });
        btn_query.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String result = "";
                try {
                    Cursor cursor = sqliteDatabase.rawQuery(
                            "select * from news_table", null);
                    if (cursor.moveToFirst()) {
                        do {
                            String title = cursor.getString(1);
                            String content = cursor.getString(2);
                            result = result + "title: " + title + "; content: "
                                    + content + "\n";
                        } while (cursor.moveToNext());
                    }
                } catch (SQLiteException se) {
                    se.printStackTrace();
                }
                Toast.makeText(SQLiteDatabaseActivity.this, result, 3000).show();
            }
        });
        btn_clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    sqliteDatabase.execSQL("delete from news_table");
                } catch (SQLiteException se) {
                    se.printStackTrace();
                }
                Toast.makeText(SQLiteDatabaseActivity.this, "清除成功", 500).show();
            }
        });
    }
}
