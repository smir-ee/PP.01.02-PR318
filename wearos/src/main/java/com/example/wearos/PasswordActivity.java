package com.example.wearos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    String code = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }
    public void onClickNum(View view){

        switch (view.getId()){
            case R.id.num1:
                code += "1";
                break;
            case R.id.num2:
                code += "2";
                break;
            case R.id.num3:
                code += "3";
                break;
            case R.id.num4:
                code += "4";
                break;
            case R.id.num5:
                code += "5";
                break;
            case R.id.num6:
                code += "6";
                break;
            case R.id.num7:
                code += "7";
                break;
            case R.id.num8:
                code += "8";
                break;
            case R.id.num9:
                code += "9";
                break;
        }
        if (code.length() == 4){
            Intent intent = new Intent(this, Plank.class);
            startActivity(intent);
            finish();
            }
        }
    }
