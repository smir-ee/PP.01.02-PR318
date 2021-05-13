package com.example.superfit;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class detail_activity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_body_screen);
    }
    public void editKG(View view){
        LayoutInflater li1 = LayoutInflater.from(context);
        View alertView = li1.inflate(R.layout.alert1, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(alertView);
        final EditText kgText = (EditText) findViewById(R.id.input_text_kg);
        alertDialog
                .setCancelable(false)
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
    public void editCM(View view){
        LayoutInflater li1 = LayoutInflater.from(context);
        View alertView = li1.inflate(R.layout.alert2, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(alertView);
        final EditText kgText = (EditText) findViewById(R.id.input_text_cm);
        alertDialog
                .setCancelable(false)
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
