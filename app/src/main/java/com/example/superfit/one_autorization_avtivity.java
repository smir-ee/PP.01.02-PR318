package com.example.superfit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class one_autorization_avtivity extends AppCompatActivity {
    Button bt1, bt2, bt3 , bt4, bt5, bt6, bt7, bt8, bt9;
    TextView textView;
    String name, code;
    int[] randomArray;
    int FINAL;
    String FINALM = "1234";
    String FINALRES = "";
    int FINALSHET = 1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_autorization_screen);

        Bundle arguments = getIntent().getExtras();
        name = arguments.get("username").toString();
        textView = (TextView) findViewById(R.id.one_autorization_text);
        code = arguments.get("code").toString();
        FINALM = code;
        textView.setText(name);

        bt1 = (Button) findViewById(R.id.aut_bt_1);
        bt2 = (Button) findViewById(R.id.aut_bt_2);
        bt3 = (Button) findViewById(R.id.aut_bt_3);
        bt4 = (Button) findViewById(R.id.aut_bt_4);
        bt5 = (Button) findViewById(R.id.aut_bt_5);
        bt6 = (Button) findViewById(R.id.aut_bt_6);
        bt7 = (Button) findViewById(R.id.aut_bt_7);
        bt8 = (Button) findViewById(R.id.aut_bt_8);
        bt9 = (Button) findViewById(R.id.aut_bt_9);

        random();
        text(bt1, randomArray[0]);
        text(bt2, randomArray[1]);
        text(bt3, randomArray[2]);
        text(bt4, randomArray[3]);
        text(bt5, randomArray[4]);
        text(bt6, randomArray[5]);
        text(bt7, randomArray[6]);
        text(bt8, randomArray[7]);
        text(bt9, randomArray[8]);

    }
    public void onBACK(View view){
        Intent  intent = new Intent(one_autorization_avtivity.this, autorization_activity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.aut_bt_1: FINAL = randomArray[0]; break;
            case R.id.aut_bt_2: FINAL = randomArray[1]; break;
            case R.id.aut_bt_3: FINAL = randomArray[2]; break;
            case R.id.aut_bt_4: FINAL = randomArray[3]; break;
            case R.id.aut_bt_5: FINAL = randomArray[4]; break;
            case R.id.aut_bt_6: FINAL = randomArray[5]; break;
            case R.id.aut_bt_7: FINAL = randomArray[6]; break;
            case R.id.aut_bt_8: FINAL = randomArray[7]; break;
            case R.id.aut_bt_9: FINAL = randomArray[8]; break;
        }
        random();
        text(bt1, randomArray[0]);
        text(bt2, randomArray[1]);
        text(bt3, randomArray[2]);
        text(bt4, randomArray[3]);
        text(bt5, randomArray[4]);
        text(bt6, randomArray[5]);
        text(bt7, randomArray[6]);
        text(bt8, randomArray[7]);
        text(bt9, randomArray[8]);


            FINALRES +=  FINAL;

        if (  FINALM.equals(FINALRES)){
            Intent  intent = new Intent(one_autorization_avtivity.this, main_activity.class);
            startActivity(intent);

        }
        if (FINALSHET == 4){
            FINALSHET = 0;
            FINALRES = "";
        }
//        Toast toast = Toast.makeText(getApplicationContext(), ""+ FINALRES, Toast.LENGTH_LONG);
//        toast.show();

        FINALSHET++;

    }

    public void text(Button bt, int c){
         bt.setText(""+c);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void random()
    {
        final int N = 9;
        ArrayList<Integer> arrayList = new ArrayList<>(N);
        Random random = new Random();
        //Общая идея довольно проста – генерируем первое число, добавляем его в массив,
        //генерируем второе число, проверяем его наличие в массиве (с помощью цикла, например),
        //если сгенерированное число уже содержится в массиве, генерируем следующее, если не
        //содержится – добавляем его туда.
        while (arrayList.size() < N) {
            int i = random.nextInt(N) + 1;
            if (!arrayList.contains(i)) {
                arrayList.add(i);
            }
        }
        randomArray = arrayList.stream().mapToInt(i -> i).toArray();

    }

}
