package com.gateon.stackoverflowquery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://stackexchange.com/oauth/dialog?client_id=" + getString(R.string.client_id) + "&scope=no_expiry,write_access&redirect_uri=https://stackexchange.com/oauth/login_success");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(Uri.parse(url).getFragment() != null && !Uri.parse(url).getFragment().isEmpty()) {
                    String token = Uri.parse(url).getFragment().replace("access_token=", "");

                    Intent intent = new Intent();
                    intent.putExtra("token", token);
                    Webview.this.setResult(Activity.RESULT_OK, intent);
                }
                return false;
            }
        });

        setContentView(webView);
    }

}
