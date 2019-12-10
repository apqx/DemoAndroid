package me.apqx.demo.web;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.squareup.leakcanary.HahaHelper;

import java.util.HashMap;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;

public class WebActivity extends Activity {
    private HashMap<String, Boolean> map = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView webView = findViewById(R.id.wv_web);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true表示当前程序处理
                //返回值是false表示当前WebView处理
                //如果不覆写此方法，则由系统决定那个app执行网页
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                LogUtil.INSTANCE.d("js " + url);
//                if (url.equals("http://ithome.com/")) {
//                    LogUtil.INSTANCE.d("js in " + url);
//                    view.loadUrl("javascript:(function(){ document.body.style.paddingTop = '10px'})();");
//                }
            }
        });

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);


        webView.loadUrl("https://m.ithome.com/");

    }
}
