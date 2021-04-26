package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class authorization_next extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_next);

        back=findViewById(R.id.arrow_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(authorization_next.this,AuthorizationActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (password.length() < 4) {
            switch (v.getId())
            {
                case R.id.num_1:
                    password += "1";
                    break;
                case R.id.num_2:
                    password += "2";
                    break;
                case R.id.num_3:
                    password += "3";
                    break;
                case R.id.num_4:
                    password += "4";
                    break;
                case R.id.num_5:
                    password += "5";
                    break;
                case R.id.num_6:
                    password += "6";
                case R.id.num_7:
                    password += "7";
                    break;
                case R.id.num_8:
                    password += "8";
                    break;
                case R.id.num_9:
                    password += "9";
                    break;
            }
            Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        }
    }
}