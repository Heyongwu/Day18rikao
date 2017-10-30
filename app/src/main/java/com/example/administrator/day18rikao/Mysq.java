package com.example.administrator.day18rikao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 何永武 on 2017/9/19.
 */

public class Mysq  extends SQLiteOpenHelper{
    public Mysq(Context context){
        super(context,"Db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,title varchar(20),picUrl varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
