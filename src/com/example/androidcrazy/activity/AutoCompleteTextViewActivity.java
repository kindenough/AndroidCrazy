
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.androidcrazy.R;

public class AutoCompleteTextViewActivity extends Activity {

    String[] books = new String[] {
            "疯狂java讲义", "疯狂android讲义", "疯狂xml讲义",
            "aacaaa", "abces", "avdcfre"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocompletetext_view);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                R.layout.simple_dropdown_layout, books);
        AutoCompleteTextView act = (AutoCompleteTextView) findViewById(R.id.auto);
        act.setAdapter(aa);
    }
}
