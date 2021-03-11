package com.example.rajme.triviaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RAJME on 4/20/2020.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private static String DB_NAME = "trivia.db";
    private static String CREATE_TABLE = "CREATE TABLE USERS ( USER_NAME varchar(255), USER_EMAIL varchar(255), USER_PASS varchar(255), USER_SCORE int )";
    private static String DROP_TABLE = "DROP TABLE IF EXISTS USERS";
    private static int DB_VERSION = 3;
    private Context context;


    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Boolean emailpass(String email, String pass){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from USERS Where USER_EMAIL=? and USER_PASS=?", new String[]{email,pass});
        if (cursor.getCount()>0) return true;
        else return false;
    }


}
