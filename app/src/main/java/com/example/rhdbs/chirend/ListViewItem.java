package com.example.rhdbs.chirend;

import android.graphics.Bitmap;

/**
 * Created by rhdbs on 2016-11-09.
 */

public class ListViewItem {
    private Bitmap iconBitmap ;
    private String titleStr ;
    private String posStr;
    private String ageStr;

    public void setIcon(Bitmap icon) {
        iconBitmap = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setPosition(String pos) {
        posStr = pos ;
    }
    public void setAge(String age){
        ageStr = age;
    }

    public Bitmap getIcon() {
        return this.iconBitmap ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getPosition() {
        return this.posStr ;
    }
    public String getAge() {
        return this.ageStr;
    }
}
