package com.example.superfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class main_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
    }
    public void Sign_out_main(View view){
        Intent intent = new Intent(main_activity.this, autorization_activity.class);
        startActivity(intent);
    }
    public void Recipes(View view){
        Intent intent = new Intent (main_activity.this, recipes_activity.class );
        startActivity(intent);
    }
}
