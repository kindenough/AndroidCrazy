
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcrazy.R;

public class SmsManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_view);
        EditText phoneNo = (EditText) findViewById(R.id.phoneNoText);
        final String phoneNoStr = phoneNo.getText().toString();
        EditText content = (EditText) findViewById(R.id.contentText);
        final String contentStr = content.getText().toString();
        Button snedButton = (Button) findViewById(R.id.sendButton);
        snedButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                PendingIntent pi = PendingIntent.getBroadcast(
                        SmsManagerActivity.this, 0, new Intent(), 0);
                smsManager.sendTextMessage(phoneNoStr, null, contentStr, pi,
                        null);
                Toast.makeText(SmsManagerActivity.this, "发送成功", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
