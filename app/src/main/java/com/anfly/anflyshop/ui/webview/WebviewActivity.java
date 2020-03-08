package com.anfly.anflyshop.ui.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.anfly.anflyshop.R;
import com.anfly.anflyshop.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weview);
        ButterKnife.bind(this);

        String url = new Bundle().getString(Constants.URL);
        webView.loadUrl(Constants.BASE_SHOP_URL + url);
        webView.setWebViewClient(new WebViewClient());

    }
}
