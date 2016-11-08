package com.example.rhdbs.chirend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoadingActivity extends AppCompatActivity {
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        next = (Button) findViewById(R.id.next);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1000); // 3초 후에 hd Handler 실행

    }
    private class splashhandler implements Runnable{
        public void run() {
            next.setVisibility(android.view.View.VISIBLE);
        }
    }

    public void toSulmun(View v){
        //startActivity(new Intent(getApplication(), SulmunActivity.class)); // 로딩이 끝난후 이동할 Activity
        startActivity(new Intent(getApplication(), ContentsList.class)); // 로딩이 끝난후 이동할 Activity
        LoadingActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
    }
}
