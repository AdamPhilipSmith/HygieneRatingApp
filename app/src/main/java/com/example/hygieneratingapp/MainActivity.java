package com.example.hygieneratingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity {


    String[] requiredPermissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION


    };
    EditText input;
    private double lat;
    private double lng;
    Button button;
    Button button2;
    //private double lat2d;
    TextView display;
    private Uri imageUri;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addListenerOnButton();

        boolean ok = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this,requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok = false;
            }
        }

        //Gets GPS Location
        boolean ok2 = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this,requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok2 = false;
            }
        }

        if (!ok2) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 1);
            System.exit(0);

        } else {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener(){
                @Override
                public void onLocationChanged(Location location){
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                }
                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) { }
                @Override
                public void onProviderEnabled(String s){ }
                @Override
                public void onProviderDisabled(String s){ }
            });
        }


        //Allows Networking to be done in  the same thread
        StrictMode.ThreadPolicy policy  = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }


    //Enables new screen to display once a button is pushed
    //public void addListenerOnButton() {
        //final Context context = this;

        //button = (Button) findViewById(R.id.button2);


        //button.setOnClickListener(new View.OnClickListener() {

            //@Override
            //public void onClick(View arg0) {

                //Intent intent = new Intent(context, MainActivity2.class);
                //startActivity(intent);

            //}

        //});


    //}


    public void Search(View v){


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        //TODO Maybe remove below
        //Gets GPS Location
        boolean ok2 = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this,requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok2 = false;
            }
        }

        if (!ok2) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 1);
            System.exit(0);
        } else {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener(){
                @Override
                public void onLocationChanged(Location location){
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                }
                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) { }
                @Override
                public void onProviderEnabled(String s){ }
                @Override
                public void onProviderDisabled(String s){ }
            });
        }

        //Connects to database using different search String depending on the button pushed.
        if (networkInfo !=null && networkInfo.isConnected()){

            try{

                Button one = findViewById(R.id.SearchByLocation);
                Button two = findViewById(R.id.SearchByPostCode);
                Button three = findViewById(R.id.SearchByName);
                Button four = findViewById(R.id.showRecent);
               URL url = null;

                if (one.isPressed() == true) {
                    url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_location&lat=" + lat + "&long=" + lng);
                    //String test = ("test" + lat + lng );
                    //((TextView) findViewById(R.id.testBox)).setText(test);
                }

                if (two.isPressed() == true) {
                    EditText input = (EditText) findViewById(R.id.enterPostcode);
                    String inputS = input.getText().toString();

                    url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_postcode&postcode=" + inputS);
                }

                if (three.isPressed() == true) {
                    EditText input = (EditText) findViewById(R.id.enterName);
                    String inputX = input.getText().toString();

                    // Converts spaces to '%20' to allow search
                    String inputY = inputX.replaceAll(" " , "%20");



                    url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_name&name=" + inputY);
                    //String test = (inputY );
                    //((TextView) findViewById(R.id.testBox)).setText(test);
                }

                if (four.isPressed() == true) {


                    url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=show_recent");
                }
                //Test

                URLConnection connection = url.openConnection();
                InputStreamReader ins = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(ins);

                String responseBody = "";
                String line = "";

                //Gets all the information from the database and stores it in a String
                while ((line = in.readLine()) != null){
                    responseBody = responseBody + line;
                }
                in.close();


                //Parses the String for the relevant information
                LinearLayout layout = (LinearLayout)findViewById(R.id.Display4);
                //imageUri = Uri.parse("android.resource://" + getPackageName()
                       // + "/drawable/" + "h0");

                JSONArray aryJSONStrings = new JSONArray(responseBody);
                String test3 = "";
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
                //String response = obj.getString("id");
                //String test = "test";

                //Sets intent so that the results can be displayed on the second page
                //Intent SendBoth = new Intent(Intent.ACTION_SEND);
                Intent sendText = new Intent(this, MainActivity2.class);
                sendText.putExtra("t", test3);
                //sendText.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(sendText);
                //input.setText(responseBody);
            }
            catch (IOException ioe){
                input.setText("ERROR1");
                ioe.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("ERROR3");
            }

        } else {
            input.setText("ERROR2");
        }
    }


}
