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
        Double latDouble = parseDouble(latString);
        String lngString = receiveIntent.getStringExtra("lngString");
        Double lngDouble = parseDouble(lngString);



        //String lat = startingIntent.getStringExtra("lat");
        Log.d("myLog", latString);
        //Double latDouble = parseDouble(lat);

        //String lng = startingIntent.getStringExtra("lng");
        //Double lngDouble = parseDouble(lng);



        mapboxMap.setCameraPosition(
                new CameraPosition.Builder()
                   .target(new LatLng(latDouble,lngDouble))
                    .zoom(12.0)
                    .build()
        );

    }

    @Override
    public void onStyleLoaded(@NonNull Style style){

        SymbolManager sm = new SymbolManager(mapView,map,style);


        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(new LatLng(53.746, -2.665))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol = sm.create(symbolOptions);

        SymbolOptions symbolOptions2 = new SymbolOptions()
                .withLatLng(new LatLng(53.756, -2.675))
                .withIconImage("restaurant-15")
                .withIconColor("#334af5")
                .withIconSize(1.5f);

        Symbol symbol2 = sm.create(symbolOptions2);

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
