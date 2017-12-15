
package com.example.androidcrazy.activity;

import com.example.androidcrazy.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;

public class RatingBarViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar_view);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_stars);
        ratingBar.setRating(3.5f);
    }

}
