package com.example.rhdbs.chirend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by rhdbs on 2016-10-13.
 */

public class ShowTypeRst extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtyperst);
        setTitle("6세, 남자아이");

        WebView webview = (WebView) findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient()); // 액티비티에서 webview 사용할 수 있게 설정
        WebSettings set = webview.getSettings(); //웹뷰를 제어가히 위한 객체 생성 확대축소등 다양하게 제어
        set.setJavaScriptEnabled(true); //웹뷰가 자바스크립트를 실행하게 설정
        //set.setBuiltInZoomControls(true); //웹뷰가 확대 축소 가능
        webview.loadUrl("http://naneg93.dothome.co.kr/chart.php?age=5&sex=1"); //웹뷰로 해당 사이트를 출력
    }
}
