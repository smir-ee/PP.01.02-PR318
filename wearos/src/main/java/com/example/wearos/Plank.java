package com.example.wearos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Plank extends AppCompatActivity {
    int fragment = 0;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plank);
        StartFragment startFragment = new StartFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, startFragment);
        ft.commit();

        exit = findViewById(R.id.exit);
    }

    public void onClickTest(View v){
        switch (fragment){
            case 0:
                StartFragment startFragment = new StartFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, startFragment);
                ft.commit();
                fragment++;
                exit.setVisibility(View.VISIBLE);
                exit.setClickable(true);
                Thread logoTimer = new Thread(){
                    public void run(){
                        try
                        {
                            int logoTimer = 0;
                            while(logoTimer < 5000){
                                sleep(100);
                                logoTimer += 100;
                            };
                            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            TimerFragment fragment2 = new TimerFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.frame, fragment2);
                            ft1.commit();
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                logoTimer.start();

                break;
            case 1:
                LastFragment stop = new LastFragment();
                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.frame, stop);
                ft2.commit();
                Thread logoTimer2 = new Thread(){
                    public void run(){
                        try
                        {
                            int logoTimer = 0;
                            while(logoTimer < 2000){
                                sleep(100);
                                logoTimer += 100;
                            };
                            StartFragment startFragment = new StartFragment();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.frame, startFragment);
                            ft.commit();
                            fragment = 0;
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                logoTimer2.start();
                break;
        }
    }
    public void onClickExit(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}