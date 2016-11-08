package com.example.rhdbs.chirend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Last_page extends ContentsList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_page);
        TextView text1 = (TextView) findViewById(R.id.t1);

        Intent intent = getIntent();
        text1.setText(intent.getStringExtra("content"));
    }
}
