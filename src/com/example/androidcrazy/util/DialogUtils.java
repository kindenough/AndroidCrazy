
package com.example.androidcrazy.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogUtils {

    /** doNothing的Listener */
    public static final OnClickListener negativeListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            return;
        }
    };

    /**
     * 普通展示信息dialog
     * 
     * @param context
     * @param str
     */
    public static void showDialog(Context context, String str) {

        new AlertDialog.Builder(context)
                .setMessage(str)
                .show();
    }

    /**
     * 确定，取消dialog
     * 
     * @param context
     * @param title
     */
    public static void showCheckDialog(Context context, String title,
            OnClickListener positiveListener,
            OnClickListener negativeListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setPositiveButton("确定", positiveListener);
        builder.setNegativeButton("取消", negativeListener);
        builder.create().show();
    }
}
