package com.example.superfit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.sql.*;

public class autorization_activity extends AppCompatActivity {

    AwesomeValidation validation;
    Based based;
    EditText username;
    //int ID = 1;
    int IDIndex, nameIndex, codeIndex;
    String name, ID, code;
    String text;

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        based = new Based(this);
        //database.isOpen();
        //based.onOpen(database);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autorization_screen);
        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.usernameaut, RegexTemplate.NOT_EMPTY, R.string.not_empty_name_error);
        username = (EditText) findViewById(R.id.usernameaut);
        database = based.getWritableDatabase();
    }
    public void Sign_in_aut (View view){
        Cursor cursor = database.query(Based.TABLE_CONTACTS, null, null, null, null, null, null);
        //text = username.getText().toString();
        if (cursor.moveToLast()) {
            IDIndex = cursor.getColumnIndex(Based.ID);
            ID = cursor.getString(IDIndex);
            nameIndex = cursor.getColumnIndex(Based.USERNAME);
            name = cursor.getString(nameIndex);
            codeIndex = cursor.getColumnIndex(Based.CODE);
            code = cursor.getString(codeIndex);
            cursor.close();
            if (validation.validate()) {
                text = username.getText().toString();
                //read();
                //username.setText(name);
                //String name = String.valueOf(nameIndex);
                if (name.equals(text)) {
                    Intent intent = new Intent(autorization_activity.this, one_autorization_avtivity.class);
                    intent.putExtra("username", text);
                    intent.putExtra("code", code);
                    startActivity(intent);
                }
            }
        }


    }
    public void Sign_up_aut(View view){
        Intent intent = new Intent (autorization_activity.this, registration_activity.class );
        startActivity(intent);
    }


//
//            //based.basedCreate(database);
//
//            Bundle arguments = getIntent().getExtras();
//            //String name = arguments.get("hello").toString();
//            based.onName(database, ID, name);
//            if (text == name){
//                Intent intent = new Intent (autorization_activity.this, one_autorization_avtivity.class );
//                intent.putExtra("username", name);
//                startActivity(intent);
//            }






}
