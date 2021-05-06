package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.superfit.DB.SignUpContract;
import com.example.superfit.DB.SignUpDbHelper;
import com.example.superfit.MainScreen.MainScreen;

public class SignUpActivity extends AppCompatActivity {

    private SignUpDbHelper dbHelper;
    EditText UserName, Email, Code, RepeatCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dbHelper = new SignUpDbHelper(this);

        UserName = findViewById(R.id.UserName);
        Email = findViewById(R.id.Email);
        Code = findViewById(R.id.Code);
        RepeatCode = findViewById(R.id.RepeatCode);
    }

    public void registerClick (View v){
        if (validate()){
            registerUser();
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }

    public void onSignInClick(View view){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void registerUser(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SignUpContract.SignUp.COLUMN_NAME, UserName.getText().toString());
        values.put(SignUpContract.SignUp.COLUMN_EMAIL, Email.getText().toString());
        values.put(SignUpContract.SignUp.COLUMN_CODE, Integer.parseInt(Code.getText().toString()));

        long newRowId = db.insert(SignUpContract.SignUp.TABLE_NAME, null, values);

        if (newRowId == -1){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Success. User code = " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate(){
        String password, email, sub1 = "0", sub2 = "@";
        if (UserName.getText().toString().equals("")){
            Toast.makeText(this, "User Name field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Email.getText().toString().equals("")){
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Code.getText().toString().equals("")){
            Toast.makeText(this, "Code field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (RepeatCode.getText().toString().equals("")){
            Toast.makeText(this, " Repeat Code field is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(Code.getText().toString()) != Integer.parseInt(RepeatCode.getText().toString())){
            Toast.makeText(this, "Repeat Code field does not match Code field", Toast.LENGTH_SHORT).show();
            return false;
        }
        password = Code.getText().toString();
        email = Email.getText().toString();
        if (password.contains(sub1)){
            Code.setText("");
            RepeatCode.setText("");
            Toast.makeText(this, "It is prohibited to input zero", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.contains(sub2)){
            Toast.makeText(this,"There is no '@' in E-mail address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() != 4){
            Toast.makeText(this, "Length of password has to be 4", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}