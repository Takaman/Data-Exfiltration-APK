package com.example.a2207project;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.TestLooperManager;
import android.telephony.TelephonyManager;

public class DeviceScrape {
    public static String getDeviceInfo(){

        StringBuilder deviceInfo = new StringBuilder();
        deviceInfo.append("\nOS version: "+ System.getProperty("os.version")+ "(" + Build.VERSION.INCREMENTAL + ")");
        deviceInfo.append("\nOS API level:" + Build.VERSION.SDK_INT);
        deviceInfo.append("\nDevice: "+ Build.DEVICE);
        deviceInfo.append("\nDevice ID: "+ Build.ID);
        deviceInfo.append("\nBrand: " + Build.BRAND);
        deviceInfo.append("\nManufacturer: " + Build.MANUFACTURER);
        deviceInfo.append("\nModel: "+ Build.MODEL);
        deviceInfo.append("\nProduct: "+ Build.PRODUCT);

        return deviceInfo.toString();
    }
}