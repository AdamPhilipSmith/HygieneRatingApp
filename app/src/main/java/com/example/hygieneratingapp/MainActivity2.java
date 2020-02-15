package com.example.hygieneratingapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity2 extends Activity {

    String test3 = "";
    String postCode[] = new String [10];
    String name[] = new String [10];
    int rating [] = new int [10];

    int[] ratings = new int[]{
            R.drawable.r0,
            R.drawable.r1,
            R.drawable.r2,
            R.drawable.r3,
            R.drawable.r4,
            R.drawable.r5,
    };

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

            name[i]= aryJSONStrings.getJSONObject(i).getString("BusinessName");
            postCode[i] = aryJSONStrings.getJSONObject(i).getString("PostCode");


            // Changes Rating to 'Exempt' if it is '-1'.

            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("0")){
                rating[i]= (R.drawable.r0);

            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("1")){
                rating[i] = (R.drawable.r1);

            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("2")){
                rating[i] = (R.drawable.r2);

            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("3")){
                rating [i] = (R.drawable.r3);

            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("4")){
                rating [i] = (R.drawable.r4);

            }
            if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("5")){
                rating [i] = (R.drawable.r5);

            }if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("-1")){
                rating [i] = (R.drawable.r6);

            }




            }






            //else test3 += aryJSONStrings.getJSONObject(i).getString("RatingValue");


            //test3 += ("\n\n\n");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", "Name : " + name[i]);
            map.put("pcode", "Post Code : " + postCode[i]);

            map.put("rating",  Integer.toString(rating[i]));
            list.add(map);

        }

        // Keys used in Hashmap
        String[] from = { "rating","name","pcode" };

        // Ids of views in listview_layout
        int[] to = { R.id.rating,R.id.name,R.id.pcode};




        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), list, R.layout.activity_main2, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        Log.d("e", "f" + Arrays.toString(name));
        Log.d("e", "f" + Arrays.toString(from));
        Log.d("e", "f" + Arrays.toString(to));
        listView.setAdapter(adapter);
    }








        //TextView txtView = (TextView) findViewById(R.id.Display2);
        //txtView.setText(test3);
    }

