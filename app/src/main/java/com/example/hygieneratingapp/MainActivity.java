package com.example.hygieneratingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    String[] requiredPermissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION


    };
    EditText input;
    private double lat;
    private double lng;
    private String stringLat = "test";
    private String stringLng = "test2";
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean ok = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this,requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok = false;
            }
        }




        //Allows Networking to be done in  the same thread
        StrictMode.ThreadPolicy policy  = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }


    public void Search(View v){
        input = (EditText) findViewById(R.id.editText);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //Gets GPS Location
        boolean ok = true;
        for (int i = 0; i < requiredPermissions.length; i++) {
            int result = ActivityCompat.checkSelfPermission(this,requiredPermissions[i]);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ok = false;
            }
        }

        if (!ok) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 1);
            System.exit(0);
        } else {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener(){
                @Override
                public void onLocationChanged(Location location){
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    stringLat = Double.toString(lat);
                    stringLng = Double.toString(lng);

                    String test = ( ""+ stringLat + stringLng );
                    ((TextView) findViewById(R.id.testBox)).setText(test);
                    ((TextView) findViewById(R.id.latTextView)).setText(""+lat);
                    ((TextView) findViewById(R.id.lngTextView)).setText(""+lng);

                }
                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) { }
                @Override
                public void onProviderEnabled(String s){ }
                @Override
                public void onProviderDisabled(String s){ }
            });
        }

        //Connects to database.
        if (networkInfo !=null && networkInfo.isConnected()){

            try{





                TextView latTextView = findViewById(R.id.latTextView);
                TextView lngTextView = findViewById(R.id.lngTextView);
                String stringLat = latTextView.getText().toString();
                String stringLng = lngTextView.getText().toString();


                String test = ("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_location&lat=" + stringLat + "&long=" + stringLng );
                URL url = new URL("http://sandbox.kriswelsh.com/hygieneapi/hygiene.php?op=search_location&lat=" + lat + "&long=" + lng );

                //Test

                URLConnection connection = url.openConnection();
                InputStreamReader ins = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(ins);

                String responseBody = "";
                String line = "";

                while ((line = in.readLine()) != null){
                    responseBody = responseBody + line;
                }
                in.close();
                input.setText(responseBody);
                //input.setText("Result!");
            }
            catch (IOException ioe){
                input.setText("ERROR1");
                ioe.printStackTrace();
            }

        } else {
            input.setText("ERROR2");
        }
    }
}
