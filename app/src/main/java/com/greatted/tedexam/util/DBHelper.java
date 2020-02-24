package com.greatted.tedexam.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.greatted.tedexam.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

// SQLite 에 데이터를 저장 & 로드하는 SQLiteOpenHelper 클래스
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DB_KAKAOEXAM";
    private static final String TABLE_NAME = "image_table";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";

    private Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.mContext = context;
        SQLiteDatabase db = getWritableDatabase();
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBHelper : ", "onCreate");
        String q = String.format("CREATE TABLE IF NOT EXISTS %s ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT )", TABLE_NAME, KEY_TITLE, KEY_URL);
        Log.d("DBHelper : ", "onCreate : "+ q);
        sqLiteDatabase.execSQL(q);

        Toast.makeText(mContext, "Table 생성완료", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addImageItems(List<ImageItem> items){
        SQLiteDatabase db = getWritableDatabase();
        for (ImageItem item : items)
        {
            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, item.getTitle());
            values.put(KEY_URL, item.getImg_url());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();

        //Toast.makeText(mContext, "저장 완료 : "+ items.size(), Toast.LENGTH_SHORT).show();

    }

    public List<ImageItem> getImageItems()
    {
        List<ImageItem> result = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            ImageItem item = new ImageItem(cursor.getString(1),cursor.getString(2));
            item.setItemId(cursor.getInt(0));
            result.add(item);
        }

        return  result;
    }

}
