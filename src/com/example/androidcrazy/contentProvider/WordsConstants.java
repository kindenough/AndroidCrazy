
package com.example.androidcrazy.contentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

public class WordsConstants {

    public static final String AUTHORITY = "com.example.androidcrazy.providers.dictprocider";

    public static final class Word implements BaseColumns {

        public static final String _ID = "_id";

        public static final String WORD = "word";

        public static final String DETAIL = "detail";

        public static final Uri TABLE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table");

        public static final Uri ROW_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/row");

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    }

}
