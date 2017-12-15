
package com.example.androidcrazy.contentProvider;

import com.example.androidcrazy.util.CommonUtils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * 功能描述： ContentProvider主类 需要在Mainfest.xml中注册此provider
 * 
 * @author zhufeifei
 * @created 2013-1-21 上午10:59:56
 * @date 2013-1-21 上午10:59:56
 */
public class MyContentProvider extends ContentProvider {

    private static UriMatcher uriMacher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int WORDS = 1;

    public static final int WORD = 2;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMacher.match(uri)) {
            case WORDS:
                return "vnd.android.cursor.dir/com.example.androidcrazy.dict";
            case WORD:
                return "vnd.android.cursor.item/com.example.androidcrazy.dict";
            default:
                throw new IllegalArgumentException("位置Uril: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        CommonUtils.traceLog("info", "Uri:" + uri);
        CommonUtils.traceLog("info", "ContentValues:" + values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        CommonUtils.traceLog("info", "Uri:" + uri.toString());
        CommonUtils.traceLog("info", "Selection:" + selection);
        CommonUtils.traceLog("info", "SelectionArgs:" + selectionArgs);
        return 1001;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        CommonUtils.traceLog("info", "Uri:" + uri);
        CommonUtils.traceLog("info", "ContentValues:" + values);
        CommonUtils.traceLog("info", "Selection:" + selection);
        CommonUtils.traceLog("info", "SelectionArgs:" + selectionArgs);
        return 1002;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        CommonUtils.traceLog("info", "Uri:" + uri);
        CommonUtils.traceLog("info", "Projection:" + projection);
        CommonUtils.traceLog("info", "Selection:" + selection);
        CommonUtils.traceLog("info", "SelectionArgs:" + selectionArgs);
        CommonUtils.traceLog("info", "SortOrder:" + sortOrder);
        return null;
    }
}
