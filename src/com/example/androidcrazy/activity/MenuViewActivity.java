
package com.example.androidcrazy.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.androidcrazy.R;

public class MenuViewActivity extends Activity {

    private EditText menuText = null;

    Map<Integer, Integer> menuColorMap = new HashMap<Integer, Integer>();

    Map<Integer, Integer> menuSizeMap = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_submenu_view);
        menuText = (EditText) findViewById(R.id.menuText);
        registerForContextMenu(menuText);
        /* 需要重写onCreateContextMenu 和 onContextItemSelected方法 */
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        /** xml定义context menu */
        MenuInflater inflator = new MenuInflater(this);
        // 装填xml到菜单并添加到menu中
        inflator.inflate(R.menu.my_context_menu, menu);
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle("请选择字体颜色");
        menuColorMap.put(R.id.context_menu_red, Color.RED);
        menuColorMap.put(R.id.context_menu_yellow, Color.YELLOW);
        menuColorMap.put(R.id.context_menu_blue, Color.BLUE);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Integer color = menuColorMap.get(item.getItemId());
        if (color != null) {
            menuText.setTextColor(color);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /** xml定义menu */
        MenuInflater inflator = new MenuInflater(this);
        // 装填xml到菜单并添加到menu中
        inflator.inflate(R.menu.my_menu, menu);
        menuSizeMap.put(R.id.font_10, 10);
        menuSizeMap.put(R.id.font_12, 12);
        menuSizeMap.put(R.id.font_14, 14);
        menuColorMap.put(R.id.color_red, Color.RED);
        menuColorMap.put(R.id.color_yellow, Color.YELLOW);
        menuColorMap.put(R.id.color_blue, Color.BLUE);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.normal_menu) {
            menuText.setTextSize(15);
            menuText.setTextColor(Color.BLACK);
        }
        Integer size = menuSizeMap.get(item.getItemId());
        Integer color = menuColorMap.get(item.getItemId());
        if (size != null) {
            menuText.setTextSize(size * 2);
        }
        if (color != null) {
            menuText.setTextColor(color);
        }
        return super.onOptionsItemSelected(item);
    }
}
