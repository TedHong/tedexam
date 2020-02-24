package com.greatted.tedexam.util;

import android.content.Context;

import com.greatted.tedexam.Common.Constants;
import com.greatted.tedexam.interfaces.OnLoadingCallBack;
import com.greatted.tedexam.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

// 파서와 DB를 관리하는 싱글턴 클래스

public class DataManager {

    private static Context mContext;
    private DBHelper dbHelper;

    private static class SingletonHolder{
        public static final DataManager INSTANCE = new DataManager();
    }
    public static DataManager getInstance(Context c)
    {
        if(c != null) mContext = c;

        return  SingletonHolder.INSTANCE;
    }

    public static DataManager getInstance()
    {
        return  SingletonHolder.INSTANCE;
    }


    List<ImageItem> imageItems = new ArrayList<>();

    private DataManager(){

    }

    public void setDBHelper(DBHelper db)
    {
        this.dbHelper = db;
    }

    public void initDB(OnLoadingCallBack callback)
    {
        ImageInfoAsync async = new ImageInfoAsync(); // 이미지 정보 추출 작업을 하는 AsyncTask 선언
        async.setContext(mContext);
        async.setUrl(Constants.URL_GETTYIMAGE);
        async.setLoadingCallback(callback); // 정보 추출 완료시 호출 될 Callback 지정
        async.execute(); //실행

    }

    public void addImageItemsToDB(List<ImageItem> items){
        if(dbHelper != null) dbHelper.addImageItems(items);
    }

    public List<ImageItem> getImageItemsFromDB()
    {
        if(imageItems.size() < 1) imageItems = dbHelper.getImageItems();

        return imageItems;
    }

    public ImageItem getImageItem(int index)
    {
        if(imageItems.size() < 1) imageItems = dbHelper.getImageItems();

        return imageItems.get(index);
    }


}
