package com.example.rhdbs.chirend;

import android.os.StrictMode;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rhdbs on 2016-11-09.
 */

public class MakedModulesByGoLim {
    public static String getData(String urlAddress) {
        String data = "";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // 이거랑
        StrictMode.setThreadPolicy(policy); // 이게 있어야 conn.connect()에서 에러가 안남!!! 중요!!!
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 접속
            if (conn != null) {
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //    데이터 읽기
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));//"utf-8","euc-kr"
                    while (true) {
                        String line = br.readLine();
                        if (line == null) break;
                        data += line + '\n';
                    }
                    br.close(); // 스트림 해제
                }
                conn.disconnect(); // 연결 끊기
            }
        } catch (Exception e) {
            //            Toast.makeText(getApplicationContext(), "Error getData!", Toast.LENGTH_LONG).show();
            data = "error getData";
        }
        return data;
    }
}
