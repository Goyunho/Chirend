package com.example.rhdbs.chirend;

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
    public String getImage(){
        return this.image;
    }

    public void switchChk(){
        if(check == true) check=false;
        else check=true;
    }

}
