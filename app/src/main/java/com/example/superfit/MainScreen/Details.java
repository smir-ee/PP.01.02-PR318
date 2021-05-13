package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.R;

public class Details extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog weightDialog;

    TextView txtWeight, txtHeight;

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

        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
    }

    public void onChangeWeightClick(View v){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_weight, null);
        EditText weight;

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
        EditText height;

        height = view.findViewById(R.id.etHeight);

        builder.setView(view);
        builder.setTitle("Change your height");
        builder.setCancelable(true);

        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtHeight.setText(height.getText().toString() + " cm");
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
}