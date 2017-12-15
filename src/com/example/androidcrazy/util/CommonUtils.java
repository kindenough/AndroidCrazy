
package com.example.androidcrazy.util;

import android.annotation.SuppressLint;
import android.util.Log;

public class CommonUtils {

    public static final String TAG = "Application";
    public static final String VERBOSE = "verbose";
    public static final String DEBUG = "debug";
    public static final String INFO = "info";
    public static final String WARN = "warn";
    public static final String ERROR = "error";

    /**
     * 功能描述：日志打印
     * 
     * @param level 日志级别（verbose、debug、info、warn、error），default info
     * @param msg 打印信息
     */
    @SuppressLint("DefaultLocale")
    public static void traceLog(String level, String msg) {
        level = level.toLowerCase();
        if (INFO.equals(level)) {
            Log.i(TAG, msg);
        } else if (DEBUG.equals(level)) {
            Log.d(TAG, msg);
        } else if (VERBOSE.equals(level)) {
            Log.v(TAG, msg);
        } else if (WARN.equals(level)) {
            Log.w(TAG, msg);
        } else if (ERROR.equals(level)) {
            Log.e(TAG, msg);
        } else {
            Log.d(TAG, msg);
        }
    }
}
