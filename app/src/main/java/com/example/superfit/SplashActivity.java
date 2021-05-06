package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    private long ms = 0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean paused = false;
    Thread thread;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        intent = new Intent(this, SignInActivity.class);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        thread = new Thread(){
            public void run(){
                try{
                    while (splashActive && ms<splashTime){
                        if (!paused){
                            ms+=100;
                        }
                        sleep(100);
                    }
                }
                catch (Exception e){

                }
                finally {
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}