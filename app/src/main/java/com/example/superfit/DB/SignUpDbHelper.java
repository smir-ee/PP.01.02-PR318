package com.example.superfit.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.superfit.DB.SignUpContract.SignUp;

public class SignUpDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = SignUpDbHelper.class.getSimpleName();
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;

    public SignUpDbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_REG_USERS_TABLE = "CREATE TABLE " + SignUp.TABLE_NAME + " ("
                + SignUp._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SignUp.COLUMN_NAME + " TEXT NOT NULL, "
                + SignUp.COLUMN_EMAIL + " TEXT NOT NULL, "
                + SignUp.COLUMN_CODE + " INTEGER NOT NULL);";

                db.execSQL(SQL_CREATE_REG_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
