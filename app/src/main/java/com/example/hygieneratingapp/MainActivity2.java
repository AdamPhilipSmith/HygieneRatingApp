package com.example.hygieneratingapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent startingIntent = getIntent();
        //Bundle bundle = getIntent().getExtras();
        String message = startingIntent.getStringExtra("t");


        TextView txtView = (TextView) findViewById(R.id.Display2);
        txtView.setText(message);
    }

}