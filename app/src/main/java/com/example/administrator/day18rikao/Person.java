package com.example.administrator.day18rikao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何永武 on 2017/9/19.
 */

public class Person {

    private final SQLiteDatabase liteDatabase;

    public Person(Context context){
        Mysq mysq = new Mysq(context);
        liteDatabase = mysq.getWritableDatabase();
    }
    public void add(String title,String picUrl){
        liteDatabase.execSQL("insert into user (title,picUrl) values (?,?)",new Object[]{title,picUrl});
    }
    public List<News> file(){
        ArrayList<News> files = new ArrayList<>();
        Cursor cursor = liteDatabase.rawQuery("select * from user",null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String picUrl = cursor.getString(cursor.getColumnIndex("picUrl"));

            News news = new News();
            news.setTitle(title);
            news.setPicUrl(picUrl);
            files.add(news);
        }

        return files;
    }

}
