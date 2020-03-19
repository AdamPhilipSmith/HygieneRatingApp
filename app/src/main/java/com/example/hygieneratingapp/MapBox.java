package com.example.hygieneratingapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import static java.lang.Double.parseDouble;


public class MapBox extends AppCompatActivity implements
        OnMapReadyCallback, Style.OnStyleLoaded {

    private MapView mapView;
    private MapboxMap map;
    private Double myPositionLat;
    private Double myPositionLng;
    String[] latArray = new String[10];
    String[] longArray = new String[10];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main3);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        map = mapboxMap;
        mapboxMap.setStyle(Style.OUTDOORS, this);


        //Gets the local coordinates and the address coordinates from MainActivity2
        Intent receiveIntent = this.getIntent();


        String latString = receiveIntent.getStringExtra("latString");
        Log.d("myLog", latString);
        myPositionLat = parseDouble(latString);
        String lngString = receiveIntent.getStringExtra("lngString");
        myPositionLng = parseDouble(lngString);


        latArray = receiveIntent.getStringArrayExtra("latArray");
        longArray = receiveIntent.getStringArrayExtra("lngArray");
        Log.d("MyLogLatitudeArray2", String.valueOf(latArray[0]));
        Log.d("MyLogLongitudeArray2", String.valueOf(longArray[0]));
        Log.d("myLog", latString);

        mapboxMap.setCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(myPositionLat, myPositionLng))
                        .zoom(12.0)
                        .build()
        );

    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

        //TODO Add functionality to show PostCode and Searched Name OR just get rid of the button



        SymbolManager sm = new SymbolManager(mapView, map, style);


        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(new LatLng(myPositionLat, myPositionLng))
                .withIconImage("ranger-station-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

            Symbol symbol = sm.create(symbolOptions);


            //Iterates through the lat and long arrays, generating a marker for each one on the map
            for (int i=0; i<10; i++) {
            Double Dlat = parseDouble(latArray[i]);
            Double Dlong = parseDouble(longArray[i]);

            //Log.d("MyLogParsedDoubleLat", String.valueOf(Dlat));
            //Log.d("MyLogParsedDoubleLong", String.valueOf(Dlong));

            SymbolManager sm2 = new SymbolManager(mapView, map, style);

            SymbolOptions symbolOptions2 = new SymbolOptions()
                    .withLatLng(new LatLng(Dlat, Dlong))
                    .withIconImage("restaurant-15")
                    .withIconColor("#334af5")
                    .withIconSize(1f);


            Symbol symbol2 = sm2.create(symbolOptions2);
        }



    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
