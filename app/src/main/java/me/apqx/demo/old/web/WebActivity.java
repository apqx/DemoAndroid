package me.apqx.demo.old.web;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.apqx.demo.old.tools.LogUtil;
import me.apqx.demo.R;

public class WebActivity extends Activity {
    @BindView(R.id.wv_web)
    WebView webView;
    private HashMap<String, Boolean> map = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);
//        WebView webView = findViewById(R.id.wv_web);
        ButterKnife.bind(this);
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
                LogUtil.INSTANCE.d("js " + url);
//                if (url.equals("http://ithome.com/")) {
//                    LogUtil.INSTANCE.d("js in " + url);
                view.loadUrl("javascript:(function(){ document.body.style.paddingTop = '50px'})();");
//                }
            }
        });

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        // 如果要播放Flash，需要加上这一句
        webSetting.setPluginState(WebSettings.PluginState.ON);
        // 设置可以支持缩放
        webSetting.setSupportZoom(false);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置出现缩放工具
        webSetting.setBuiltInZoomControls(true);
        // 扩大比例的缩放
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
//        webView.loadUrl("https://www.cfd139.com.cn/zt/hy/mobile/lp6/m-common16.html?utm_source=jt&utm_medium=hq&utm_campaign=wzl2");
//        webView.loadUrl("https://www.cfd139.com/zt/hy/mobile/lp6/m-common26.html?utm_source=jt&utm_medium=sy&utm_campaign=banner");
        webView.loadUrl("https://btcccfd.com/JT1/?utm_source=JT1&utm_medium=BTHQ_2.5.2");
//        webView.loadUrl("javascript:(function(){ document.body.style.paddingTop = '50px'})();");
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
