package com.example.hygieneratingapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity2 extends Activity {

    String test3 = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent startingIntent = getIntent();
        //Bundle bundle = getIntent().getExtras();
        String message = startingIntent.getStringExtra("t");

        JSONArray aryJSONStrings = null;
        try {
            aryJSONStrings = new JSONArray(message);


        for (int i=0; i<aryJSONStrings.length(); i++) {
            test3 += ("Address = ");
            test3 += aryJSONStrings.getJSONObject(i).getString("BusinessName");
            test3 += ("\nPost Code = ");
            test3 += aryJSONStrings.getJSONObject(i).getString("PostCode");
            test3 += ("\nRating = ");

            // Changes Rating to 'Exempt' if it is '-1'.
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("-1")){
                test3 += "Exempt";
            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("-1")){
                //layout.addView(image);

            }

            else test3 += aryJSONStrings.getJSONObject(i).getString("RatingValue");


            test3 += ("\n\n\n");
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }













        TextView txtView = (TextView) findViewById(R.id.Display2);
        txtView.setText(test3);
    }

}