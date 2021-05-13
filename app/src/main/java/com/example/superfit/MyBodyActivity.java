package com.example.superfit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;
import com.example.superfit.mainScreen.User;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class MyBodyActivity extends AppCompatActivity {
    private TextView weight, height;
    String userName;
    SharedPreferences sPrefs;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_body);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName = sPrefs.getString("lastUser", "NoOne");

        Gson gson = new Gson();
        String json = sPrefs.getString(userName, "Fail");
        user = gson.fromJson(json, User.class);
        weight.setText(user.getWeight());
        height.setText(user.getHeight());
    }

    public void onClickEditWeight(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyBodyDialog);
        LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.my_body_edit_text, null);
        builder.setView(view);
        TextView label = view.findViewById(R.id.label);
        label.setText("Weight");
        EditText editText = view.findViewById(R.id.editText);
        builder.setTitle("Change your weight")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        weight.setText(editText.getText() + " kg");
                        user.setWeight(editText.getText().toString());
                        SharedPreferences.Editor ed = sPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        ed.putString(userName, json);
                        ed.apply();
                    }
                });
        builder.show();
    }

    public void onClickEditHeight(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyBodyDialog);
        LinearLayout view1 = (LinearLayout) getLayoutInflater().inflate(R.layout.my_body_edit_text, null);
        builder.setView(view1);
        TextView label1 = view1.findViewById(R.id.label);
        label1.setText("Height");
        EditText editText = view1.findViewById(R.id.editText);
        builder.setTitle("Change your height")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        height.setText(editText.getText() + " cm");
                        user.setHeight(editText.getText().toString());
                        SharedPreferences.Editor ed = sPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        ed.putString(userName, json);
                        ed.apply();
                    }
                });
        builder.show();
    }
}