
package com.example.androidcrazy.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidcrazy.R;

public class WebViewActivity extends Activity {

    private EditText urlText = null;

    private WebView webView = null;

    private ProgressBar progressBar = null;

    private String HTTP_PRIFIX = "http://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        urlText = (EditText) findViewById(R.id.urltext);
        progressBar = (ProgressBar) findViewById(R.id.webProgressBar);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);//支持js

        Button btn = (Button) findViewById(R.id.btn_go);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = urlText.getText().toString();
                if (!urlStr.startsWith(HTTP_PRIFIX)) {
                    urlStr = HTTP_PRIFIX + urlStr;
                }
                if (URLUtil.isNetworkUrl(urlStr)) {
                    webView.loadUrl(urlStr);
                } else {
                    Toast.makeText(WebViewActivity.this, "不可打开的网页", 2000).show();
                }
            }
        });
        // 添加网页加载回调
        webView.setWebViewClient(new WebViewClient() {

            // 网页内页面跳转还在webview中，不打开系统浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 页面开始打开
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(android.view.View.VISIBLE);
                Toast.makeText(WebViewActivity.this, "onPageStarted", 2000).show();
            }

            // 页面打开完成
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(android.view.View.GONE);
                Toast.makeText(WebViewActivity.this, "onPageFinished", 2000).show();
            }

            // 页面打开出错
            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                    String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                progressBar.setVisibility(android.view.View.GONE);
                Toast.makeText(WebViewActivity.this, "onReceivedError", 2000).show();
            }

        });
    }
}
