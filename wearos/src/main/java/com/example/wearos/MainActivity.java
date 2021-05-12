package com.example.wearos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    SharedPreferences sf;
    EditText login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sf = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        login = findViewById(R.id.etUserName);
        String userName = sf.getString("q", "Fail");

        // Enables Always-on
        setAmbientEnabled();
    }
    public void onClickSignIn(View v){
        if (!isEmptyET(login)){
            Intent intent = new Intent(this, PasswordActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Поля UserName пустое", Toast.LENGTH_SHORT).show();
        }

    }
    
    public boolean isEmptyET(EditText editText){
        return editText.getText().toString().equals("");
    }
}