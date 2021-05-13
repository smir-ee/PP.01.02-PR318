package com.example.superfit.loadScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.superfit.R;
import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
        Thread logoTimer = new Thread(){
            public void run(){
                try
                {
                    int logoTimer = 0;
                    while(logoTimer < 5000){
                        sleep(100);
                        logoTimer += 100;
                    };
                    sf =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String lastUser = sf.getString("lastUser", "NoOne");

                    if (lastUser.equals("NoOne")){
                        startActivity(new Intent("com.example.SIGNIN"));
                    }
                    else{
                        startActivity(new Intent("com.example.MAINSCREEN"));
                    }

                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}