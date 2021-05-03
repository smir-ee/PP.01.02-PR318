package com.example.superfit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class autorization_activity extends AppCompatActivity {

    AwesomeValidation validation;
    Based based;
    EditText username;
    int ID = 1;
    String name;
    int nameIndex;
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
//        username = (EditText) findViewById(R.id.usernameaut);
//        based = new Based(this);
//        validation = new AwesomeValidation(ValidationStyle.BASIC);
//        validation.addValidation(this, R.id.usernameaut, RegexTemplate.NOT_EMPTY, R.string.not_empty_name_error);
//        database = based.getWritableDatabase();
//        based.onOpen(database);


    }
    public void read(){
        Cursor cursor = database.query(Based.TABLE_CONTACTS, null, null, null, null, null, null);
        text = username.getText().toString();
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(Based.ID);
             nameIndex = cursor.getColumnIndex(Based.USERNAME);
            int emailIndex = ((Cursor) cursor).getColumnIndex(Based.EMAIL);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", email = " + cursor.getString(emailIndex));
                name = cursor.getString(nameIndex);
                if (text == name){
                    break;
                }
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        //name = cursor.getString(nameIndex);
        cursor.close();
    }
    public void Sign_in_aut(View view){
        if (validation.validate()){

            read();
            //String name = String.valueOf(nameIndex);
            if (text == name){
                Intent intent = new Intent (autorization_activity.this, one_autorization_avtivity.class );
                intent.putExtra("username", name);
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


    }
    public void Sign_up_aut(View view){
        Intent intent = new Intent (autorization_activity.this, registration_activity.class );
        startActivity(intent);
    }

}
