
package com.example.androidcrazy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page_view);
        Button btn = (Button) findViewById(R.id.gotoPrePage);
        btn.setOnClickListener(new BtnListener());
    }

    class BtnListener implements OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SecondPageActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
