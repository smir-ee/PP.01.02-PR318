package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.superfit.DB.SignUpContract;
import com.example.superfit.DB.SignUpDbHelper;

public class SignInActivity extends AppCompatActivity {

    EditText userName;
    SignUpDbHelper dbHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        userName = findViewById(R.id.UN);

        dbHelper = new SignUpDbHelper(this);

        intent = new Intent(this, CodeActivity.class);
    }

    public void onSignUpClick(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onSignInClick(View view){
        if (validate())
            checkEmail();
    }

    public void checkEmail(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] select = {SignUpContract.SignUp.COLUMN_EMAIL};
        Cursor cursor = db.query(SignUpContract.SignUp.TABLE_NAME, select, null, null, null, null, null);
        int emailColumnIndex = cursor.getColumnIndex(SignUpContract.SignUp.COLUMN_EMAIL);
        while (cursor.moveToNext()){
            String currentEmail = cursor.getString(emailColumnIndex);
            if (userName.getText().toString().equals(currentEmail)){
                intent.putExtra("email", userName.getText().toString());
                startActivity(intent);
                break;
            }
            else {
                Toast.makeText(this, "There is no such Email in our database", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean validate (){
        if (userName.getText().toString().equals("")){
            Toast.makeText(this, "User Name field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}