package com.example.rhdbs.chirend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rhdbs on 2016-11-09.
 */

public class JsonDbByGoLim {
    public static String[] columnName;
    public static List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    HashMap<String, String> datamap = new HashMap<String, String>();

    public JsonDbByGoLim(){

    }
    public JsonDbByGoLim(String columnName[]){
        this.columnName = columnName;
    }

    public void getJSON(String jsonObj) throws JSONException {
        try {
            JSONArray jarray = new JSONArray(jsonObj);   // JSONArray 생성
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                for(String key : columnName){
                    datamap.put(key, jObject.getString(key));
                    data.add(datamap);
                }
            }
        } catch (Exception e){
            //return null;
            e.printStackTrace();
        }
        //return this.data;
    }

    public String toString() {
        String str = "{[";
        for(Map<String, String> i : data){
            for(Map.Entry<String, String> s : i.entrySet()){
                str += "{\""+s.getKey()+"\":";
                str += "\""+s.getValue()+"\"}";
            }
            str += ",";
        }
        str += "]}";
        return str;
    }
}
