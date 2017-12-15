
package com.example.androidcrazy.activity;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.example.androidcrazy.R;

public class DateTimePickerViewActivity extends Activity {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_timepicker_view);
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        // 获取当前的年月日小时分
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        dp.init(year, month, day, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int month,
                    int day) {
                DateTimePickerViewActivity.this.year = year;
                DateTimePickerViewActivity.this.month = month;
                DateTimePickerViewActivity.this.day = day;
                showDate(year, month, day, hour, minute);
            }

        });
        tp.setOnTimeChangedListener(new OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                DateTimePickerViewActivity.this.hour = hourOfDay;
                DateTimePickerViewActivity.this.minute = minute;
                showDate(year, month, day, hour, minute);
            }

        });
    }

    private void showDate(int year, int month, int day, int hour, int minute) {
        EditText tv = (EditText) findViewById(R.id.textView);
        tv.setText("您选择的时间为：" + year + "年" + month + "月" + day + "日" + hour
                + "时" + minute + "秒");
    }
}
