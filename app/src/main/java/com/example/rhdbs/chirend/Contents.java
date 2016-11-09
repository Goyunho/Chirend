package com.example.rhdbs.chirend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rhdbs on 2016-11-07.
 */

public class Contents implements Parcelable {
    private String id, name, image;
    private boolean check = false;

    public  Contents(){

    }
    public Contents(String id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    protected Contents(Parcel in) {
        id = in.readString();
        name = in.readString();
        image = in.readString();
        check = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeByte((byte) (check ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contents> CREATOR = new Creator<Contents>() {
        @Override
        public Contents createFromParcel(Parcel in) {
            return new Contents(in);
        }

        @Override
        public Contents[] newArray(int size) {
            return new Contents[size];
        }
    };

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

    public static Bitmap getImage(String urlAddress){
        Bitmap bitmap = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 이거랑
        StrictMode.setThreadPolicy(policy); // 이게 있어야 conn.connect()에서 에러가 안남!!! 중요!!!
        try {
            URL url = new URL(urlAddress);
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
