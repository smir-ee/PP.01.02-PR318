package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.DB.SignUpDbHelper;
import com.example.superfit.MainScreen.MainScreen;

public class CodeActivity extends AppCompatActivity {

    TextView email;
    String e_mail, password = "";
    SignUpDbHelper dbHelper;
    Intent intent;
    int userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        getSupportActionBar().hide();

        email = findViewById(R.id.emailCodeAc);
        e_mail = getIntent().getStringExtra("email");
        dbHelper = new SignUpDbHelper(this);
        userPassword = getIntent().getExtras().getInt("password");

        email.setText(e_mail);
        intent = new Intent(this, MainScreen.class);
    }

    public void backClick(View view){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.b1:
                password += "1";
                break;
            case R.id.b2:
                password += "2";
                break;
            case R.id.b3:
                password += "3";
                break;
            case R.id.b4:
                password += "4";
                break;
            case R.id.b5:
                password += "5";
                break;
            case R.id.b6:
                password += "6";
                break;
            case R.id.b7:
                password += "7";
                break;
            case R.id.b8:
                password += "8";
                break;
            case R.id.b9:
                password += "9";
                break;
        }

        if (password.length() == 4){
            if (userPassword == Integer.parseInt(password)){
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Invalid password. Correct password is: " + userPassword, Toast.LENGTH_SHORT).show();
                password = "";
            }
        }
    }
}