package com.greatted.tedexam.model;

public class ImageItem {
    private int itemId;
    private String title;
    private String img_url;

    public ImageItem(String title, String url){
        this.title = title;
        this.img_url = url;
    }

    public void setItemId(int id){
        this.itemId = id;
    }

    public int getItemId(){
        return this.itemId;
    }

    public String getImg_url(){
        return img_url;
    }

    public String getTitle(){
        return  title;
    }


    public String toString(){
        return  title+" / "+img_url;
    }
}
