
package com.example.androidcrazy.util;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class FileUtils {

    /**
     * 打开APK程序代码
     * 
     * @param context
     * @param file
     */
    public static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
