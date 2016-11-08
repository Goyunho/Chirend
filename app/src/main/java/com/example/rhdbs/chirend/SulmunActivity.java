package com.example.rhdbs.chirend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.widget.ImageView.ScaleType.FIT_CENTER;

/**
 * Created by rhdbs on 2016-10-13.
 */

public class SulmunActivity extends AppCompatActivity {
    NumberPicker age_picker;
    RadioGroup gender_RG;
    int NPValue;
    String sex;
    Contents[] cont;
    GridLayout grid;
    ImageButton[] imageB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sulmun);
        setTitle("설문조사");

        age_picker = (NumberPicker) findViewById(R.id.age);
        age_picker.setMinValue(3);
        age_picker.setMaxValue(13);

        gender_RG = (RadioGroup) findViewById(R.id.sex);
        grid = (GridLayout) findViewById(R.id.grid);

        String jsondata = getData("http://naneg93.dothome.co.kr/imageload.php/");
        try {
            getJSON(jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        imageB = new ImageButton[cont.length];
        for(int i=0; i < cont.length; i++){
            imageB[i] = new ImageButton(this);
            imageB[i].setId(Integer.parseInt(cont[i].getId()));
            imageB[i].setImageBitmap(cont[i].getImage());
            imageB[i].setScaleType(ImageButton.ScaleType.CENTER_CROP);
            grid.addView(imageB[i]);
        }
    }

    private class RstType implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), ShowTypeRst.class)); // 로딩이 끝난후 이동할 Activity
        }
    }

    public void showRst(View v) {
        Intent intent = new Intent(this, ShowTypeRst.class);
        NPValue = age_picker.getValue();
        intent.putExtra("age", Integer.toString(NPValue));
        RadioButton rb = (RadioButton) findViewById(gender_RG.getCheckedRadioButtonId());
        if (rb.getText().toString().equals("남자아이")) {
            sex = "0";
        } else {
            sex = "1";
        }
        intent.putExtra("sex", sex);
        startActivity(intent);
    }

    public void getJSON(String jsonObj) throws JSONException {
        try {
            JSONArray jarray = new JSONArray(jsonObj);   // JSONArray 생성
            cont = new Contents[jarray.length()];
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String id = jObject.getString("id");
                String name = jObject.getString("name");
                String image = jObject.getString("image");

                cont[i] = new Contents(id, name, image);
            }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "getJSON Error!", Toast.LENGTH_LONG).show();
        }
    }

    public String getData(String urlAddress) {
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
            //
            Toast.makeText(getApplicationContext(), "Error getData!", Toast.LENGTH_LONG).show();
        }
        return data;
    }

}
