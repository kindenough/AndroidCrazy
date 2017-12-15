
package com.example.androidcrazy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.androidcrazy.R;

public class FileDownThread extends Thread {

    public static final String GAMES_FOLDER = "/androidcrazy/";
    public static final int MSG_FINISH = 1; // 下载完成  
    public static final int MSG_FAILURE = 0;// 下载失败

    private Context context;
    private Handler handler;
    private String httpUrl;
    private String fileName;
    private String appName;
    private Message msg;

    private String savePath = null;
    private File downLoadFile = null;

    private NotificationManager mNotifManager;
    private Notification mDownNotification;
    private RemoteViews mContentView; // 下载进度View  
    private PendingIntent mDownPendingIntent;

    public FileDownThread(Context context, Handler handler,
            String httpUrl, String fileName, String appName) {
        this.context = context;
        this.handler = handler;
        this.httpUrl = httpUrl;
        this.fileName = fileName;
        this.appName = appName;
        mNotifManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        msg = new Message();

    }

    @Override
    public void run() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                savePath = Environment.getExternalStorageDirectory() + GAMES_FOLDER;
                File tmpFile = new File(savePath);
                if (!tmpFile.exists()) {
                    tmpFile.mkdir();
                }
                downLoadFile = new File(savePath + fileName);

                mDownNotification = new Notification(
                        android.R.drawable.stat_sys_download, "【" + appName + "】下载中...", System
                                .currentTimeMillis());
                mDownNotification.flags = Notification.FLAG_ONGOING_EVENT;
                mDownNotification.flags = Notification.FLAG_AUTO_CANCEL;
                mContentView = new RemoteViews(context.getPackageName(),
                        R.layout.download_notification_layout);
                mContentView.setImageViewResource(R.id.download_icon,
                        android.R.drawable.stat_sys_download);
                mDownPendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
                boolean downSuc = downloadFile(httpUrl, downLoadFile);
                if (downSuc) {
                    msg.what = MSG_FINISH;
                    Notification notification = new Notification(
                            android.R.drawable.stat_sys_download_done, "【" + appName + "】下载完成",
                            System.currentTimeMillis());
                    notification.flags = Notification.FLAG_ONGOING_EVENT;
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(downLoadFile),
                            "application/vnd.android.package-archive");
                    PendingIntent contentIntent = PendingIntent.getActivity(
                            context, 0, intent, 0);
                    notification.setLatestEventInfo(context, "【" + appName + "】下载完成", null,
                            contentIntent);
                    mNotifManager.notify(R.drawable.ic_launcher, notification);
                } else {
                    msg.what = MSG_FAILURE;
                    Notification notification = new Notification(
                            android.R.drawable.stat_sys_download_done, "【" + appName + "】下载失败",
                            System.currentTimeMillis());
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    PendingIntent contentIntent = PendingIntent.getActivity(
                            context, 0, new Intent(), 0);
                    notification.setLatestEventInfo(context, "【" + appName + "】下载失败", null,
                            contentIntent);
                    mNotifManager.notify(R.drawable.ic_launcher, notification);
                }

            } else {
                Toast.makeText(context, Environment.getExternalStorageState(),
                        Toast.LENGTH_SHORT).show();
                msg.what = MSG_FAILURE;
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg.what = MSG_FAILURE;
        } finally {
            handler.sendMessage(msg);
        }
    }

    public boolean downloadFile(String httpUrl, File saveFilePath) {
        int fileSize = -1;
        int downFileSize = 0;
        boolean result = false;
        int progress = 0;
        HttpURLConnection conn = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(httpUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (null == conn) {
                return false;
            }
            // 读取超时时间 毫秒级  
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                fileSize = conn.getContentLength();
                is = conn.getInputStream();
                fos = new FileOutputStream(saveFilePath);
                byte[] buffer = new byte[1024];
                int i = 0;
                int tempProgress = -1;
                while ((i = is.read(buffer)) != -1) {
                    downFileSize = downFileSize + i;
                    // 下载进度  
                    progress = (int) (downFileSize * 100.0 / fileSize);
                    fos.write(buffer, 0, i);

                    synchronized (this) {
                        if (downFileSize == fileSize) {
                            // 下载完成  
                            mNotifManager.cancel(R.id.download_icon);
                        } else if (tempProgress != progress) {
                            // 下载进度发生改变，则发送Message  
                            mContentView.setTextViewText(R.id.progress_percent,
                                    progress + "%");
                            mContentView.setProgressBar(R.id.download_progress,
                                    100, progress, false);
                            mDownNotification.contentView = mContentView;
                            mDownNotification.contentIntent = mDownPendingIntent;
                            mNotifManager.notify(R.id.download_icon,
                                    mDownNotification);
                            tempProgress = progress;
                        }
                    }
                }

                result = true;
            } else {
                result = false;
                throw new IllegalAccessError("httpUrl地址请求放回码：" + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }
}
