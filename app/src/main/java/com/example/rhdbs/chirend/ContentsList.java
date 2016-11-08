package com.example.rhdbs.chirend;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

    private static final String TAG_RESULTS = "result";

    private static final String TAG_TEXT1 = "content";
    private static final String TAG_TEXT2 = "position";
    private static final String TAG_TEXT3 = "age";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentslist);
        list = (ListView) findViewById(R.id.listview);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://naneg93.dothome.co.kr/b.php");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),Last_page.class);
                String str = personList.get(i).get(TAG_TEXT1);
                intent.putExtra("content", str);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {

                JSONObject c = peoples.getJSONObject(i);
                String text1 = c.getString(TAG_TEXT1);
                String text2 = c.getString(TAG_TEXT2);
                String text3 = c.getString(TAG_TEXT3);
                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_TEXT1, text1);
                persons.put(TAG_TEXT2, text2);
                persons.put(TAG_TEXT3, text3);
                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ContentsList.this, personList, R.layout.list_view,
                    new String[]{TAG_TEXT1, TAG_TEXT2, TAG_TEXT3},
                    new int[]{R.id.text1, R.id.text2, R.id.text3}

            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
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
