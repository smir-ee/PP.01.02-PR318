package com.example.superfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class autorization_activity extends AppCompatActivity {

    Based based;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autorization_screen);

        username = (EditText) findViewById(R.id.usernameaut);
    }
    public void Sign_in_aut(View view){
        String text = username.getText().toString();
        //if (text == )
        Intent intent = new Intent (autorization_activity.this, one_autorization_avtivity.class );
        startActivity(intent);
    }
    public void Sign_up_aut(View view){
        Intent intent = new Intent (autorization_activity.this, registration_activity.class );
        startActivity(intent);
    }

}
