
package com.example.androidcrazy.fragment;

import com.example.androidcrazy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class Tab3Fragment extends Fragment {

    private Activity context;

    // fragment选项卡按钮
    Button btnTab1 = null;
    Button btnTab2 = null;
    Button btnTab3 = null;

    public Tab3Fragment(Activity context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }
}
