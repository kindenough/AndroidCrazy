
package com.example.androidcrazy.contentProvider;

import com.example.androidcrazy.util.CommonUtils;

import android.database.ContentObserver;
import android.os.Handler;

/**
 * 功能描述：数据库监听器 类似于
 * 数据库技术中的触发器(Trigger)，当ContentObserver所观察的Uri发生变化时，便会触发它。触发器分为表触发器、行触发器，
 * 相应地ContentObserver也分为“表“ContentObserver、“行”ContentObserver
 * 
 * @author 360BUY
 * @created 2013-1-21 下午12:07:04
 * @date 2013-1-21 下午12:07:04
 */
public class MyContentObserver extends ContentObserver {

    public MyContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        CommonUtils.traceLog(CommonUtils.INFO, "==========onChange===========");
    }

}
