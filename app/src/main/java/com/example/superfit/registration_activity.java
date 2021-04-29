package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class registration_activity extends AppCompatActivity {

    AwesomeValidation validation;
    EditText etName, etEmail, etCode, etRepeatCode;
    Based based;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        etName = (EditText) findViewById(R.id.username);
        etEmail = (EditText) findViewById(R.id.email);
        etCode = (EditText) findViewById(R.id.code);
        etRepeatCode = (EditText) findViewById(R.id.repeatcode);

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        based = new Based(this);
        validation.addValidation(this, R.id.username, RegexTemplate.NOT_EMPTY, R.string.not_empty_name_error);
        validation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.not_empty_email_error);
        validation.addValidation(this, R.id.code, R.id.repeatcode, R.string.passwords_not_confirm);
        validation.addValidation(this, R.id.code, "[1-4]{4}", R.string.not_valid_code);



    }
    public void registration(){
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String code = etCode.getText().toString();
        String Repeatcode = etRepeatCode.getText().toString();

        SQLiteDatabase database = based.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Based.USERNAME, name);
        contentValues.put(Based.EMAIL, email);
        contentValues.put(Based.CODE, code);

        database.insert(Based.TABLE_CONTACTS, null, contentValues);
    }
    public void Sign_up_reg(View view){
        if (validation.validate()){

            registration();
        }
//        Intent intent = new Intent(registration_activity.this, main_activity.class);
//        startActivity(intent);
    }
    public void Sign_in_reg(View view){
        Intent intent = new Intent(registration_activity.this, autorization_activity.class);
        startActivity(intent);
    }
}