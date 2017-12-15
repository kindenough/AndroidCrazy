
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidcrazy.R;

public class SystemActionCategoryActivity extends Activity {

    final int PICK_CONTACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sys_action_category_view);
        // 获取电话簿中号码信息
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // ACTION_GET_CONTENT让用户返回数据并选择所选数据
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // 打开联系人列表页面
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent, PICK_CONTACT);
                /* 这里的返回值是通过重写onActivityResult方法接收的 */
            }
        });
        // 返回home键
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                // 返回页面首页
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
        // 浏览指定网页
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String data = "http://www.baidu.com";
                Uri uri = Uri.parse(data);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        // 编辑指定联系人
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String data = "content://com.android.contacts/contacts/1";
                Uri uri = Uri.parse(data);
                // 为Intent设置Action属性（动作为：编辑）
                intent.setAction(Intent.ACTION_EDIT);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    // 查询联系人信息
                    Cursor cursor = managedQuery(contactData, null, null, null, null);
                    if (cursor.moveToFirst()) { // 如果查询到指定的联系人
                        // 查询联系人姓名
                        String name = cursor
                                .getString(cursor
                                        .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                        // 查询电话列表信息
                        String phoneNumber = "此联系人暂未输入电话号码";
                        String contactId = cursor.getString(cursor
                                .getColumnIndex(ContactsContract.Contacts._ID));
                        Cursor phones = getContentResolver()
                                .query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                                                + contactId,
                                        null,
                                        null);
                        if (phones.moveToFirst()) { // 如果查询到电话号码
                            phoneNumber = phones
                                    .getString(phones
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }

                        phones.close();
                        EditText show = (EditText) findViewById(R.id.show);
                        show.setText(name);
                        EditText phList = (EditText) findViewById(R.id.phList);
                        phList.setText(phoneNumber);
                    }
                    cursor.close();
                }
                break;
        }
    }
}
