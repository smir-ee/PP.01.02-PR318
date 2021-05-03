package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;

import java.util.Timer;
import java.util.TimerTask;

public class splash_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Intent intent = new Intent(this, autorization_activity.class);
        startActivity(intent);
        finish();
    }

    
}