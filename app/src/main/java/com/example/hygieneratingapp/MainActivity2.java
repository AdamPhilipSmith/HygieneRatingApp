package com.example.hygieneratingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Button button;
    String postCode[] = new String[10];
    String name[] = new String[10];
    String address[] = new String[10];
    String distance[] = new String[10];
    String ratingDate[] = new String[10];
    String lat[] = new String[10];
    String lng[] = new String[10];
    String locations;
    String GsonString;
    String StrLatitude[] = new String[10];
    String StrLongitude[] = new String[10];

    int rating[] = new int[10];



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent startingIntent = getIntent();

        //Gets the text sent over from Main Activity.
        String message = startingIntent.getStringExtra("webString");

        JSONArray aryJSONStrings = null;


        //Parses through the received info, assigning it to the relevant headers.
        try {
            aryJSONStrings = new JSONArray(message);


            for (int i = 0; i < aryJSONStrings.length(); i++) {
                boolean newLineRequired = false;
                boolean newLineRequired2 = false;
                String addressTemp = "";


                //Creates new Gson object in order to parse the Lat and Long to then send over to the MapBox
                GsonString = String.valueOf(aryJSONStrings.getJSONObject(i));

                GSON gson = new Gson().fromJson(GsonString, GSON.class);

                StrLatitude[i] = gson.Location.Latitude;
                StrLongitude[i] = gson.Location.Longitude;

                // Gets all locations and adds them to a string
                locations += (aryJSONStrings.getJSONObject(i).getString("Location"));
                Log.d("MylogLocations", locations);

                name[i] = aryJSONStrings.getJSONObject(i).getString("BusinessName");

                postCode[i] = aryJSONStrings.getJSONObject(i).getString("PostCode");

                //Checks if the address line has been supplied and only adds it to the view if so.
                if (!aryJSONStrings.getJSONObject(i).getString("AddressLine1").equals("")) {
                    addressTemp += aryJSONStrings.getJSONObject(i).getString("AddressLine1");

                    //Boolean to check if 'new line' is required.
                    newLineRequired = true;
                }

                if (!aryJSONStrings.getJSONObject(i).getString("AddressLine2").equals("")) {

                    if (newLineRequired == true) {
                        addressTemp += "\n";
                    }

                    addressTemp += aryJSONStrings.getJSONObject(i).getString("AddressLine2");
                    newLineRequired2 = true;
                }
                if (!aryJSONStrings.getJSONObject(i).getString("AddressLine3").equals("")) {
                    if (newLineRequired2 == true) {
                        addressTemp += "\n";
                    }
                    addressTemp += aryJSONStrings.getJSONObject(i).getString("AddressLine3");

                }

                address[i] = addressTemp;

                ratingDate[i] = aryJSONStrings.getJSONObject(i).getString("RatingDate");

                //Checks to see if 'DistanceKM' exists before trying to access. Stops crash.
                if (aryJSONStrings.getJSONObject(i).has("DistanceKM")) {


                    String distanceLong = aryJSONStrings.getJSONObject(i).getString("DistanceKM");

                    //Cuts down the decimal places of distance
                    distance[i] = distanceLong.substring(0, 4);
                }

                //Following 'IF' statements assign correct image to the rating
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("0")) {
                    rating[i] = (R.drawable.r0);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("1")) {
                    rating[i] = (R.drawable.r1);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("2")) {
                    rating[i] = (R.drawable.r2);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("3")) {
                    rating[i] = (R.drawable.r3);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("4")) {
                    rating[i] = (R.drawable.r4);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("5")) {
                    rating[i] = (R.drawable.r5);

                }
                if (aryJSONStrings.getJSONObject(i).getString("RatingValue").equals("-1")) {
                    rating[i] = (R.drawable.r6);

                }

            }

            //Catches JSON exceptions and prints a stack trace if so.
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSON error", "JSON error");
        }


        //Adds addresses to a hash map with the relevant keys
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", name[i]);
            map.put("pcode", postCode[i]);

            map.put("address", address[i]);


            map.put("ratingDate", "Date Rated :" + ratingDate[i]);


            //Again checks that 'DistanceKM' exists, if it doesn't then nothing is added to the app
            try {
                if (aryJSONStrings.getJSONObject(0).has("DistanceKM")) {
                    map.put("distance", distance[i] + "km away");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            map.put("rating", Integer.toString(rating[i]));

            list.add(map);

        }


        // Keys used in Hashmap
        String[] from = {"rating", "name", "pcode", "address", "ratingDate", "distance"};

        // Ids of views in listview_layout
        int[] to = {R.id.rating, R.id.name, R.id.pcode, R.id.address, R.id.ratingDate, R.id.distance};


        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), list, R.layout.listview, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);


        // Setting the adapter to the listView
        listView.setAdapter(adapter);


        //Gets lat and long values from MainActivity 1
        Intent receiveIntent2 = this.getIntent();


        final String latString = receiveIntent2.getStringExtra("latString");
        final String lngString = receiveIntent2.getStringExtra("lngString");


        //Button for opening up the Map View and sending across the coordinates
        button = (Button) findViewById(R.id.mapbox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity2.this, MapBox.class);


                //Sends Business Coordinates to Map Box
                i.putExtra("latArray", StrLatitude);
                i.putExtra("lngArray", StrLongitude);

                //Sends local Coordinates to Map Box
                i.putExtra("latString", latString);
                i.putExtra("lngString", lngString);

                startActivity(i);


            }


        });

    }

    //Classes to allow parsing of Latitude and Longitude with Gson.
    public class GSON {
        Location Location;
    }

    class Location {
        String Latitude;
        String Longitude;
    }

}

