
package com.example.androidcrazy.activity;

import java.io.File;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidcrazy.R;
import com.example.androidcrazy.util.DialogUtils;
import com.example.androidcrazy.util.FileDownThread;
import com.example.androidcrazy.util.FileUtils;

public class DownloadActivity extends Activity {

    // 火热推荐 下载进度
    private File apkFile = null;
    private String httpUrl = null;
    private String gameName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);
        Button btn = (Button) findViewById(R.id.download_btn);
        final String name = "神庙逃亡";
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils
                        .showCheckDialog(DownloadActivity.this,
                                "确定要下载【" + name + "】?",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        // 安卓版神庙逃亡下载地址
                                        httpUrl = "http://market.nduoa.com/apk/download/428154?from=androidCrazy";
                                        gameName = "shenMiaoTaoWang.apk";
                                        final FileDownThread downloadThread = new FileDownThread(
                                                DownloadActivity.this,
                                                new Handler() {
                                                    @Override
                                                    public void handleMessage(Message msg) {
                                                        if (msg.what == FileDownThread.MSG_FINISH) {
                                                            // 下载成功后自动弹出安装
                                                            apkFile = new File(
                                                                    Environment
                                                                            .getExternalStorageDirectory()
                                                                            + FileDownThread.GAMES_FOLDER
                                                                            + gameName);
                                                            if (apkFile != null) {
                                                                DialogUtils
                                                                        .showCheckDialog(
                                                                                DownloadActivity.this,
                                                                                "开始安装" + name + "?",
                                                                                new DialogInterface.OnClickListener() {
                                                                                    public void onClick(
                                                                                            DialogInterface dialog,
                                                                                            int whichButton) {
                                                                                        FileUtils
                                                                                                .openFile(
                                                                                                        DownloadActivity.this,
                                                                                                        apkFile);
                                                                                    }
                                                                                },
                                                                                DialogUtils.negativeListener);
                                                            }
                                                        } else if (msg.what == FileDownThread.MSG_FAILURE) {
                                                            Toast.makeText(DownloadActivity.this,
                                                                    "【" + name
                                                                            + "】下载失败！",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                },
                                                httpUrl,
                                                gameName,
                                                name);

                                        downloadThread.start();

                                    }
                                },
                                DialogUtils.negativeListener);
            }
        });
    }
}
