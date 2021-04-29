package com.example.superfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class recipes_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);
    }
    public void onBACK_reciples(View view){
        Intent intent = new Intent (recipes_activity.this, main_activity.class );
        startActivity(intent);
    }
}
