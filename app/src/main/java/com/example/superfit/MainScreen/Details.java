package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.superfit.DB.SignUpDbHelper;
import com.example.superfit.R;
import com.example.superfit.DB.SignUpContract.BodyParameters;

public class Details extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog weightDialog;

    TextView txtWeight, txtHeight;
    EditText weight, height;

    SignUpDbHelper dbHelper;
    SQLiteDatabase db_write, db_read;

    String read_weight, read_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().hide();
        this.getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        builder = new AlertDialog.Builder(Details.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);

        dbHelper = new SignUpDbHelper(this);
        db_write = dbHelper.getWritableDatabase();
        db_read = dbHelper.getReadableDatabase();

        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);

        //readWH();

        if (read_weight == null){
            read_weight = "Undefined";
        }
        if (read_height == null){
            read_height = "Undefined";
        }

        txtWeight.setText(read_weight);
        txtHeight.setText(read_height);
    }

    public void onChangeWeightClick(View v){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_weight, null);

        weight = view.findViewById(R.id.etWeight);

        builder.setView(view);
        builder.setTitle("Change your weight");
        builder.setCancelable(true);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtWeight.setText(weight.getText().toString() + " kg");
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        weightDialog = builder.create();
        weightDialog.show();
    }

    public void onChangeHeightClick(View v){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_height, null);

        height = view.findViewById(R.id.etHeight);

        builder.setView(view);
        builder.setTitle("Change your height");
        builder.setCancelable(true);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtHeight.setText(height.getText().toString() + " cm");
                writeWH();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        weightDialog = builder.create();
        weightDialog.show();
    }

    public void writeWH(){
        ContentValues values = new ContentValues();
        values.put(BodyParameters.COLUMN_WEIGHT, Integer.parseInt(weight.getText().toString()));
        values.put(BodyParameters.COLUMN_HEIGHT, Integer.parseInt(height.getText().toString()));

        long newRowId = db_write.insert(BodyParameters.TABLE_NAME, null, values);

        if (newRowId == -1){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void readWH(){

        String SQL_SELECT_WEIGHT = "SELECT "+ BodyParameters.COLUMN_WEIGHT + " FROM "+ BodyParameters.TABLE_NAME + ";";

        Cursor cursor = db_read.rawQuery(SQL_SELECT_WEIGHT, null);
        read_weight = cursor.getString(1);
        cursor.close();

        String SQL_SELECT_HEIGHT = "SELECT "+ BodyParameters.COLUMN_HEIGHT + " FROM "+ BodyParameters.TABLE_NAME + ";";
        Cursor cursor1 = db_read.rawQuery(SQL_SELECT_HEIGHT, null);
        read_height = cursor1.getString(2);
        cursor1.close();
    }
}