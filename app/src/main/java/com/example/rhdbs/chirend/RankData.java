package com.example.rhdbs.chirend;

/**
 * Created by rhdbs on 2016-11-09.
 */

public class RankData {
    String rank, contents_id, count;
    static String contents_name;

    public RankData(String rank, String contents_id, String count) {
        this.rank = rank;
        this.contents_id = contents_id;
        this.count = count;
    }

    public static void setContents_name(String name){
        contents_name = name;
    }
}
