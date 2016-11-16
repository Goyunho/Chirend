package com.example.rhdbs.chirend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ContentsList extends AppCompatActivity {
    String myJSON;
    public static Context mContext;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_POSITION = "position";
    private static final String TAG_AGE = "age";
    private static final String TAG_URL = "url";
    private static final String TAG_ID = "no";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;
    ListView list;
    CstAdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentslist);
        mContext = this;

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("contents_name"));
        String contents_id = intent.getStringExtra("contents_id");
        list = (ListView) findViewById(R.id.listview);

        personList = new ArrayList<HashMap<String, String>>();
        getData("http://naneg93.dothome.co.kr/contentsList.php?contents_id="+contents_id);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Last_page.class);
                String content = personList.get(i).get(TAG_CONTENT);
                String position = personList.get(i).get(TAG_POSITION);
                String age = personList.get(i).get(TAG_AGE);
                String img_url = personList.get(i).get(TAG_URL);
                String item_no = personList.get(i).get(TAG_ID);

                intent.putExtra("content", content);
                ///////////////////////////////////////////////////
                intent.putExtra("position",position);
                intent.putExtra("age", age);
                intent.putExtra("img", img_url);
                intent.putExtra("item_no", item_no);
                //intent.putExtra("img", (Bitmap) Contents.getImage(img_url));
                startActivity(intent);
            }
        });
    }

    protected void showList() {
        adapter = new CstAdater();
        list.setAdapter(adapter);
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String content = c.getString(TAG_CONTENT);
                String position = c.getString(TAG_POSITION);
                String age = c.getString(TAG_AGE);
                String url = c.getString(TAG_URL);
                String item_no = c.getString(TAG_ID);
                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_URL, url);
                persons.put(TAG_CONTENT, content);
                persons.put(TAG_POSITION, position);
                persons.put(TAG_AGE, age);
                persons.put(TAG_ID, item_no);
                personList.add(persons);
                adapter.add(Contents.getImage(url) ,content, position, age);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "showList Error!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {

                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
