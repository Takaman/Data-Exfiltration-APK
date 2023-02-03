package com.example.a2207project;

import static android.content.Context.BATTERY_SERVICE;
import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

public class LocationInfo {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Context context;
    private Location location;
    private BatteryManager batteryManager;
    Location mLocation;
    public LocationInfo(Context context)
    {
        this.context = context;
        String androidID = MainActivity.androidID;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LocationInfo.this.location = location;
                Log.d("Locationinfo","Locationinfo changed: "+ location);
            }
        };
    }
    @SuppressLint("MissingPermission")
    public Location getLocation()
    {
        /*locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener,null);*/
        return location;
    }
    @SuppressLint("MissingPermission")
    public void startLocationUpdates()
    {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 , 0,locationListener);
    }


    public int getBatteryLevel()
    {
        BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }
}
