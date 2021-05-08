package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUserName;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.userName);


        sPref =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("lastUser", "NoOne");
        ed.apply();

        HideActionBarAndTransparentStatusBar.hide(getWindow());
    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void onClickSignIn(View view) {

        String userName = sPref.getString(etUserName.getText().toString(), "Fail");
        if (userName.equals("Fail"))
            Toast.makeText(this, "Такого пользователя не существует",
                    Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(this, InputCode.class);
            intent.putExtra("User", etUserName.getText().toString());
            startActivity(intent);
        }

    }
}