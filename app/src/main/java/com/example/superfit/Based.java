package com.example.superfit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Based extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "base";
    public static final String TABLE_CONTACTS = "basedb";

    public static final String ID ="_id";
    public static final String USERNAME ="username";
    public static final String EMAIL ="email";
    public static final String CODE ="code";


    public Based(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(""CREATE TABLE "+TABLE_CONTACTS+" ("+ ID + " INTEGER PRIMARY KEY, "+ USERNAME + " TEXT, " + EMAIL+ " TEXT " + CODE + " TEXT+ ")");
        db.execSQL("create table " + TABLE_CONTACTS + "(" + ID + " integer primary key," + USERNAME + " text, " + EMAIL + " text, " + CODE + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
}
