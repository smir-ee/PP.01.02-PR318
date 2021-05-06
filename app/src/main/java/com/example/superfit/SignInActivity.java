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
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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
        String[] select = {SignUpContract.SignUp.COLUMN_EMAIL, SignUpContract.SignUp.COLUMN_CODE};
        Cursor cursor = db.query(SignUpContract.SignUp.TABLE_NAME, select, null, null, null, null, null);
        int emailColumnIndex = cursor.getColumnIndex(SignUpContract.SignUp.COLUMN_EMAIL);
        int codeColumnIndex = cursor.getColumnIndex(SignUpContract.SignUp.COLUMN_CODE);
        boolean isCorrect = false;

        while (cursor.moveToNext()){
            String currentEmail = cursor.getString(emailColumnIndex);
            int currentPassword = cursor.getInt(codeColumnIndex);

            if (userName.getText().toString().equals(currentEmail)){
                isCorrect = true;
                intent.putExtra("email", userName.getText().toString());
                intent.putExtra("password", currentPassword);
                startActivity(intent);
            }
        }
        if (!isCorrect){
            Toast.makeText(this, "There is no such Email in our database", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate (){
        if (userName.getText().toString().equals("")){
            Toast.makeText(this, "User Name field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!userName.getText().toString().contains("@")){
            Toast.makeText(this, "Email is missing the '@' character", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}