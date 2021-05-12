package com.example.superfit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class exercises_activity extends AppCompatActivity {
    ListView listView;
    exercises_item lv_item;
    ArrayList<exercises_item> exe_list = new ArrayList<>();
    final int[] image = new int[] {R.drawable.exercises_image1, R.drawable.exercises_image2, R.drawable.exercises_image3, R.drawable.exercises_image4, R.drawable.exercises_image5};
    final String[] name = new String[] {"Push-Ups", "Plank", "Squats", "Crunch", "Running"};
    final String[] text = new String[]{"Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.", "The plank strengthens the abdominals, back and shoulders.",
            "Сonsidered a vital exercise for increasing the strength and size of the lower body.", "It involves the entire abs, but primarily it works the rectus abdominis muscle.",
            "It develops endurance, strengthens the legs and the cardiovascular system."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_screen);
        listView = findViewById(R.id.lv_exercises);

        for (int i = 0; i <=4; i++)
        {
            lv_item = new exercises_item();
            int imageL = image[i];
            lv_item.setImage(imageL);
            String nameL = name[i];
            lv_item.setName(nameL);
            String textL = text[i];
            lv_item.setText(textL);
            exe_list.add(lv_item);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                }
            }
        });

    }
}
