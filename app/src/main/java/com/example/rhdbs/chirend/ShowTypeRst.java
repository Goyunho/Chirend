package com.example.rhdbs.chirend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhdbs on 2016-10-13.
 */

public class ShowTypeRst extends AppCompatActivity{
    String age, sex;
    HashMap<String, Contents> conts = new HashMap<String, Contents>();
    TextView rnk[][] = new TextView[3][5];
    HashMap<String, RankData> rnkData = new HashMap<String, RankData>();

    protected void onCreate(Bundle savedInstanceState) {
        //초기설정
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtyperst);

        //이전 액티비티에서 값 받아옴
        Intent intent = getIntent();
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");
        conts = (HashMap<String, Contents>) intent.getSerializableExtra("Contents");
        //타이틀변경
        if(sex.equals("0")){
            setTitle(age+"세, 남자아이");
        } else{
            setTitle(age+"세, 여자아이");
        }

        //1번카드 차트 뿌려줌
        showChart();
        //2번카드 실시간 순위 뿌려줌
        showRank();
    }

    public void nextActList(View v) {
        Intent intent = new Intent(this, ContentsList.class);
        String contents_info[] = (String[]) v.getTag();
        intent.putExtra("contents_id", contents_info[0]);
        intent.putExtra("contents_name", contents_info[1]);
        startActivity(intent);
    }

    public void showChart(){
        WebView webview = (WebView) findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient()); // 액티비티에서 webview 사용할 수 있게 설정
        WebSettings set = webview.getSettings(); //웹뷰를 제어가히 위한 객체 생성 확대축소등 다양하게 제어
        set.setJavaScriptEnabled(true); //웹뷰가 자바스크립트를 실행하게 설정
        webview.loadUrl("http://naneg93.dothome.co.kr/chart.php?age="+age+"&sex="+sex); //웹뷰로 해당 사이트를 출력
    }

    public void showRank(){
        HashMap<String, JsonDbByGoLim> rankTable = new HashMap<>();
        rankTable.put("rt", new JsonDbByGoLim(new String[]{"rank", "contents_id", "count"}));
        rankTable.put("dy", new JsonDbByGoLim(new String[]{"rank", "contents_id", "count"}));
        rankTable.put("wk", new JsonDbByGoLim(new String[]{"rank", "contents_id", "count"}));


        //실시간
        rnk[0][0] = (TextView) findViewById(R.id.tvRow1_rt);
        rnk[0][1] = (TextView) findViewById(R.id.tvRow2_rt);
        rnk[0][2] = (TextView) findViewById(R.id.tvRow3_rt);
        rnk[0][3] = (TextView) findViewById(R.id.tvRow4_rt);
        rnk[0][4] = (TextView) findViewById(R.id.tvRow5_rt);
        //일간
        rnk[1][0] = (TextView) findViewById(R.id.tvRow1_daily);
        rnk[1][1] = (TextView) findViewById(R.id.tvRow2_daily);
        rnk[1][2] = (TextView) findViewById(R.id.tvRow3_daily);
        rnk[1][3] = (TextView) findViewById(R.id.tvRow4_daily);
        rnk[1][4] = (TextView) findViewById(R.id.tvRow5_daily);
        //주간
        rnk[2][0] = (TextView) findViewById(R.id.tvRow1_weakly);
        rnk[2][1] = (TextView) findViewById(R.id.tvRow2_weakly);
        rnk[2][2] = (TextView) findViewById(R.id.tvRow3_weakly);
        rnk[2][3] = (TextView) findViewById(R.id.tvRow4_weakly);
        rnk[2][4] = (TextView) findViewById(R.id.tvRow5_weakly);

        for(Map.Entry<String, JsonDbByGoLim> col : rankTable.entrySet()){
            String key = col.getKey();
            JsonDbByGoLim value = col.getValue();
            String json = MakedModulesByGoLim.getData("http://naneg93.dothome.co.kr/requestRank.php?type="+key+"&age="+age+"&sex="+sex);
            try {
                if(key.equals("rt"))
                    getJSON_tvRank(json, 0);
                else if(key.equals("dy"))
                    getJSON_tvRank(json, 1);
                else if(key.equals("wk"))
                    getJSON_tvRank(json, 2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void getJSON_tvRank(String jsonObj, int col) throws JSONException {
        try {
            JSONArray jarray = new JSONArray(jsonObj);   // JSONArray 생성
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                int rank = jObject.getInt("rank");
                if(0 < rank && rank < 6) {
                    String contents_id = jObject.getString("contents_id");
                    //String count = jObject.getString("count");
                    String contents_name = conts.get(contents_id).getName();
                    rnk[col][rank-1].setText(contents_name);
                    rnk[col][rank-1].setTag(new String[]{contents_id, contents_name});
                }
            }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "getJSON_rvRank Error!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
