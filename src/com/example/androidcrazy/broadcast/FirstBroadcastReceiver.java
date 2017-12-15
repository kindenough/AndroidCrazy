
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
public class FirstBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果这里需要完成个很耗时的操作，应该考虑通过Intent启动一个Service来操作
        CommonUtils.traceLog(CommonUtils.INFO, "Context: " + context);
        CommonUtils.traceLog(CommonUtils.INFO, "Intent: " + intent);
        Toast.makeText(context, intent.getExtras().getString("msg"), 1000)
                .show();
        Bundle bundle = new Bundle();
        bundle.putString("first", "第一个BroadcastReceiver存入的消息");
        setResultExtras(bundle);
        // 终止继续传播
//        abortBroadcast();
    }
}
