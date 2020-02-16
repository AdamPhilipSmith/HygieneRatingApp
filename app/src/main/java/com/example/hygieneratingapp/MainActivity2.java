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


    String postCode[] = new String [10];
    String name[] = new String [10];
    String addressLine1[] = new String [10];
    String addressLine2[] = new String [10];
    String addressLine3[] = new String [10];
    String distance[] = new String [10];

    int rating [] = new int [10];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent startingIntent = getIntent();

        String message = startingIntent.getStringExtra("t");

        JSONArray aryJSONStrings = null;
        try {
            aryJSONStrings = new JSONArray(message);


        for (int i=0; i<aryJSONStrings.length(); i++) {

            name[i]= aryJSONStrings.getJSONObject(i).getString("BusinessName");
            postCode[i] = aryJSONStrings.getJSONObject(i).getString("PostCode");
            addressLine1[i] = aryJSONStrings.getJSONObject(i).getString("AddressLine1");
            addressLine2[i] = aryJSONStrings.getJSONObject(i).getString("AddressLine2");
            addressLine3[i] = aryJSONStrings.getJSONObject(i).getString("AddressLine3");
            //distance[i] = aryJSONStrings.getJSONObject(i).getString("DistanceKM");


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
            map.put("name", name[i]);
            map.put("pcode", postCode[i]);
            map.put("addressLine1", addressLine1[i]);
            map.put("addressLine2", addressLine2[i]);
            map.put("addressLine3", addressLine3[i]);
            map.put("distance", distance[i] + "km away");




            map.put("rating",  Integer.toString(rating[i]));
            list.add(map);

        }

        // Keys used in Hashmap
        String[] from = { "rating","name","pcode", "addressLine1","addressLine2","addressLine3" };

        // Ids of views in listview_layout
        int[] to = { R.id.rating,R.id.name,R.id.pcode, R.id.addressLine1, R.id.addressLine2, R.id.addressLine3};

        //Intent startingIntent2 = getIntent();

        //String message2 = startingIntent2.getStringExtra("s");

        //Button one = findViewById(R.id.SearchByLocation);

        //if (message2.equals("test")){
           // String[] from2 = { "rating","name","pcode", "addressLine1","addressLine2","addressLine3", "distance" };
            //int[] to2 = { R.id.rating,R.id.name,R.id.pcode, R.id.addressLine1, R.id.addressLine2, R.id.addressLine3, R.id.distance};

           // SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), list, R.layout.listview, from2, to2);

            // Getting a reference to listview of main.xml layout file
            //ListView listView = ( ListView ) findViewById(R.id.listview);

            // Setting the adapter to the listView

            //listView.setAdapter(adapter);
        //}

        //else {

            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), list, R.layout.listview, from, to);

            // Getting a reference to listview of main.xml layout file
            ListView listView = (ListView) findViewById(R.id.listview);

            // Setting the adapter to the listView

            listView.setAdapter(adapter);
       // }
    }


    }

