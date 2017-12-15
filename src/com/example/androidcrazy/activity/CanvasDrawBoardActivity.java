
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.androidcrazy.R;
import com.example.androidcrazy.view.DrawBoardView;

public class CanvasDrawBoardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_board_view);
        DrawBoardView dbv = (DrawBoardView) findViewById(R.id.drawBoard);
        // 这里就可以设置画笔的属性了
        dbv.paint.setColor(Color.BLUE);
    }
}
