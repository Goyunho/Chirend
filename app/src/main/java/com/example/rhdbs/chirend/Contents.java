package com.example.rhdbs.chirend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rhdbs on 2016-11-07.
 */

public class Contents {
    private String id, name, image;
    private boolean check = false;

    public  Contents(){

    }
    public Contents(String id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public void setAttr(String id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getImageUrl(){
        return this.image;
    }

    public void switchChk(){
        if(this.check == true) check=false;
        else this.check=true;
    }

    public boolean getChk(){
        return this.check;
    }

    public Bitmap getImage(){
        Bitmap bitmap = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 이거랑
        StrictMode.setThreadPolicy(policy); // 이게 있어야 conn.connect()에서 에러가 안남!!! 중요!!!
        try {
            URL url = new URL(this.image);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 접속
            if (conn != null) {
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //    데이터 읽기
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }
                conn.disconnect(); // 연결 끊기
            }
        } catch (Exception e) {
            //
            //Toast.makeText(getApplicationContext(), "Error getImage!", Toast.LENGTH_LONG).show();
            return null;
        }
        return bitmap;
    }

}
