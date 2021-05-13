package com.example.superfit.loadScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;
import com.example.superfit.mainScreen.MainScreen;
import com.example.superfit.R;
import com.example.superfit.mainScreen.User;
import com.google.gson.Gson;

public class InputCode extends AppCompatActivity {
    String code = "";
    String userName;
    SharedPreferences sPrefs;
    User user;
    TextView login;
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_code);
        sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName = getIntent().getStringExtra("User");
        login = findViewById(R.id.login);

        Gson gson = new Gson();
        String json = sPrefs.getString(userName, "Fail");
        user = gson.fromJson(json, User.class);

        login.setText(user.getName());

        HideActionBarAndTransparentStatusBar.hide(getWindow());

    }

    public void onClickSignIn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
            if (code.equals(user.getCode())){
                sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("lastUser", user.getName());
                ed.apply();

                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this, "Неправильный код", Toast.LENGTH_SHORT).show();
                code = "";
            }
        }
    }

}