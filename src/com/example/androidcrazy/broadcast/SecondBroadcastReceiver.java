
package com.example.androidcrazy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidcrazy.util.CommonUtils;

/**
 * 功能描述： 广播接收器
 * 
 * @author zhufeifei
 * @created 2013-1-22 上午11:03:48
 */
public class SecondBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果这里需要完成个很耗时的操作，应该考虑通过Intent启动一个Service来操作
        CommonUtils.traceLog(CommonUtils.INFO, "Context: " + context);
        CommonUtils.traceLog(CommonUtils.INFO, "Intent: " + intent);
        Bundle bundle = getResultExtras(true);
        Toast.makeText(context, bundle.getString("first"),
                3000).show();
    }

}
