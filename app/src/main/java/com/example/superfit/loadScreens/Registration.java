package com.example.superfit.loadScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;
import com.example.superfit.mainScreen.MainScreen;
import com.example.superfit.R;
import com.example.superfit.mainScreen.User;
import com.google.gson.Gson;

public class Registration extends AppCompatActivity {
    EditText etUserName, etEmail, etCode, etRepeatCode;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etUserName = findViewById(R.id.userName);
        etEmail = findViewById(R.id.email);
        etCode = findViewById(R.id.code);
        etRepeatCode = findViewById(R.id.repeatCode);

        HideActionBarAndTransparentStatusBar.hide(getWindow());
    }

    public void onClickSignIn(View view) {
        finish();
    }
    public void onClickSignUp(View v){
        if (etRepeatCode.getText().toString().equals("") ||
                etUserName.getText().toString().equals("") ||
                etCode.getText().toString().equals("")||
                etEmail.getText().toString().equals("")){
            Toast.makeText(this, "У вас есть не заполненные поля", Toast.LENGTH_SHORT).show();
        }
        else {
            if (etEmail.getText().toString().contains("@")){
                if (etCode.getText().toString().equals(etRepeatCode.getText().toString())){
                    User user = new User(etUserName.getText().toString(),
                            etEmail.getText().toString(),
                            etCode.getText().toString(), true);
                    sPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor ed = sPref.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    ed.putString(etUserName.getText().toString(), json);
                    ed.putString("lastUser", etUserName.getText().toString());
                    ed.apply();

                    Intent intent = new Intent(this, MainScreen.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Введенные коды не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(this, "Неправильно введен адрес электронной почты", Toast.LENGTH_SHORT).show();
        }

    }
}