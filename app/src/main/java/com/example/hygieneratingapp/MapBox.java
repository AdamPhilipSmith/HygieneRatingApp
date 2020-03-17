package com.example.hygieneratingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
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
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
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
    public void onMapReady(@NonNull MapboxMap mapboxMap){
        map = mapboxMap;
        mapboxMap.setStyle(Style.OUTDOORS, this);

        Intent receiveIntent = this.getIntent();


        String latString = receiveIntent.getStringExtra("latString");
        Log.d("myLog", latString);
        myPositionLat = parseDouble(latString);
        String lngString = receiveIntent.getStringExtra("lngString");
        myPositionLng = parseDouble(lngString);


        latArray = receiveIntent.getStringArrayExtra("latArray");
        longArray = receiveIntent.getStringArrayExtra("lngArray");
        Log.d("MyLogLatitudeArray2",String.valueOf(latArray[0]));
        Log.d("MyLogLongitudeArray2",String.valueOf(longArray[0]));





        //String lat = startingIntent.getStringExtra("lat");
        Log.d("myLog", latString);
        //Double latDouble = parseDouble(lat);

        //String lng = startingIntent.getStringExtra("lng");
        //Double lngDouble = parseDouble(lng);



        mapboxMap.setCameraPosition(
                new CameraPosition.Builder()
                   .target(new LatLng(myPositionLat,myPositionLng))
                    .zoom(12.0)
                    .build()
        );

    }

    @Override
    public void onStyleLoaded(@NonNull Style style){

        //TODO Needs heavy refactoring if I have time

        SymbolManager sm = new SymbolManager(mapView,map,style);


        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(new LatLng(myPositionLat, myPositionLng))
                .withIconImage("embassy-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol = sm.create(symbolOptions);

        Double Dlat = parseDouble(latArray[0]);
        Double Dlong = parseDouble(longArray[0]);

        Log.d("MyLogParsedDoubleLat", String.valueOf(Dlat));
        Log.d("MyLogParsedDoubleLong", String.valueOf(Dlong));

        SymbolManager sm2 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions2 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat, Dlong))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);



        Symbol symbol2 = sm2.create(symbolOptions2);

        Double Dlat2 = parseDouble(latArray[1]);
        Double Dlong2 = parseDouble(longArray[1]);

        SymbolManager sm3 = new SymbolManager(mapView,map,style);


        SymbolOptions symbolOptions3 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat2, Dlong2))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol3 = sm3.create(symbolOptions3);

        Double Dlat3 = parseDouble(latArray[2]);
        Double Dlong3 = parseDouble(longArray[2]);

        SymbolManager sm4 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions4 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat3, Dlong3))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol4 = sm4.create(symbolOptions4);

        Double Dlat4 = parseDouble(latArray[3]);
        Double Dlong4 = parseDouble(longArray[3]);

        SymbolManager sm5 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions5 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat4, Dlong4))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol5 = sm5.create(symbolOptions5);

        Double Dlat5 = parseDouble(latArray[4]);
        Double Dlong5 = parseDouble(longArray[4]);

        SymbolManager sm6 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions6 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat5, Dlong5))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol6 = sm6.create(symbolOptions6);

        Double Dlat6 = parseDouble(latArray[5]);
        Double Dlong6 = parseDouble(longArray[5]);

        SymbolManager sm7 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions7 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat6, Dlong6))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol7 = sm7.create(symbolOptions7);

        Double Dlat7 = parseDouble(latArray[6]);
        Double Dlong7 = parseDouble(longArray[6]);

        SymbolManager sm8 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions8 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat7, Dlong7))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol8 = sm8.create(symbolOptions8);

        Double Dlat8 = parseDouble(latArray[7]);
        Double Dlong8 = parseDouble(longArray[7]);

        SymbolManager sm9 = new SymbolManager(mapView,map,style);


        SymbolOptions symbolOptions9 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat8, Dlong8))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol9 = sm9.create(symbolOptions9);

        Double Dlat9 = parseDouble(latArray[8]);
        Double Dlong9 = parseDouble(longArray[8]);

        SymbolManager sm10 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions10 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat9, Dlong9))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol10 = sm10.create(symbolOptions10);

        Double Dlat10 = parseDouble(latArray[9]);
        Double Dlong10 = parseDouble(longArray[9]);

        SymbolManager sm11 = new SymbolManager(mapView,map,style);

        SymbolOptions symbolOptions11 = new SymbolOptions()
                .withLatLng(new LatLng(Dlat10, Dlong10))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol11 = sm11.create(symbolOptions11);


    }

    @Override
    public void onStart(){
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
