package com.example.superfit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Main_Based extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "base";
    public static final String TABLE_CONTACTS = "basemain";

    public static final String ID ="_id";
    public static final String MAINID ="_id";
    public static final String KG ="kg";
    public static final String CM ="cm";

    public Main_Based(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + ID + " integer primary key," + MAINID + " text, " + KG + " text, " + CM + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
//    public void basedCreate(SQLiteDatabase db){
//        db.execSQL("drop table if exists " + TABLE_CONTACTS);
//
//        onCreate(db);
//    }
//    public void onName(SQLiteDatabase db, int id_pos, String name){
////        db.execSQL("create table " + TABLE_CONTACTS + "(" + ID + " integer primary key," + USERNAME + " text, " + EMAIL + " text, " + CODE + " text" + ")");
//        String query = "SELECT " + Based.ID + ", " + Based.USERNAME + " FROM " + Based.TABLE_CONTACTS;
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//        cursor.moveToPosition(id_pos);
//        //int item_id = cursor.getInt(cursor.getColumnIndex(Based.ID));
//        name = cursor.getString(cursor.getColumnIndex(Based.USERNAME));
//        cursor.close();
//
//    }
}
