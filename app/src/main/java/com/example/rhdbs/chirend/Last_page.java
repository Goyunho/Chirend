package com.example.rhdbs.chirend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Last_page extends ContentsList {
    JsonDbByGoLim contentData = new JsonDbByGoLim(new String[]{"map_url"});
    String content, position, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_page);

        TextView text1 = (TextView) findViewById(R.id.t1);
        TextView text2 = (TextView) findViewById(R.id.t2);
        TextView text3 = (TextView) findViewById(R.id.t3);
        ImageView imgV = (ImageView) findViewById(R.id.last_page_icon);

        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        position = intent.getStringExtra("position");
        age = intent.getStringExtra("age");
        text1.setText(content);
        text2.setText(position);
        text3.setText(age);
        imgV.setImageBitmap(Contents.getImage(intent.getStringExtra("img")));
        String item_no = intent.getStringExtra("item_no");

        String json = MakedModulesByGoLim.getData("http://naneg93.dothome.co.kr/last_page_con_info.php?item_no="+item_no);
        try {
            contentData.getJSON(json);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "getJSON Error!", Toast.LENGTH_SHORT).show();
        }

        setTitle(intent.getStringExtra("content"));
        showWV_map();
    }
    public void show_Item(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://shopping.naver.com/search/all.nhn?query="+content+"&cat_id=&frm=NVSHATC"));
        startActivity(intent);

    }
    public void show_Item_review(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://daum.net"));
        startActivity(intent);

    }
    public void showWV_map(){
        WebView webview = (WebView) findViewById(R.id.wv_map);

        webview.setWebViewClient(new WebViewClient()); // 액티비티에서 webview 사용할 수 있게 설정
        WebSettings set = webview.getSettings(); //웹뷰를 제어가히 위한 객체 생성 확대축소등 다양하게 제어
        set.setJavaScriptEnabled(true); //웹뷰가 자바스크립트를 실행하게 설정
        webview.loadUrl(contentData.dataList.get(0).get("map_url")); //웹뷰로 해당 사이트를 출력
    }
}