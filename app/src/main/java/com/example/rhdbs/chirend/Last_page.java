package com.example.rhdbs.chirend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class Last_page extends ContentsList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_page);

        TextView text1 = (TextView) findViewById(R.id.t1);
        TextView text2 = (TextView) findViewById(R.id.t2);
        TextView text3 = (TextView) findViewById(R.id.t3);
        ImageView imgV = (ImageView) findViewById(R.id.last_page_icon);

        Intent intent = getIntent();
        text1.setText(intent.getStringExtra("content"));
        text2.setText(intent.getStringExtra("position"));
        text3.setText(intent.getStringExtra("age"));
        imgV.setImageBitmap((Bitmap) intent.getParcelableExtra("img"));

        setTitle(intent.getStringExtra("content"));
        showWV_map();
    }
    public void bb1(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(intent);

    }
    public void bb2(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://daum.net"));
        startActivity(intent);

    }
    public void showWV_map(){
        WebView webview = (WebView) findViewById(R.id.wv_map);

        webview.setWebViewClient(new WebViewClient()); // 액티비티에서 webview 사용할 수 있게 설정
        WebSettings set = webview.getSettings(); //웹뷰를 제어가히 위한 객체 생성 확대축소등 다양하게 제어
        set.setJavaScriptEnabled(true); //웹뷰가 자바스크립트를 실행하게 설정
        webview.loadUrl("https://www.google.co.kr/maps/place/%EB%BD%80%EB%A1%9C%EB%A1%9C%ED%85%8C%EB%A7%88%ED%8C%8C%ED%81%AC+%EB%8F%99%ED%83%84%EC%A0%90/@37.202803,127.0666433,17z/data=!3m1!4b1!4m5!3m4!1s0x357b443ba6216e43:0xd4d522470aeca050!8m2!3d37.202803!4d127.068832"); //웹뷰로 해당 사이트를 출력
    }
}