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
        deviceInfo.append("\n OS version: "+ System.getProperty("os.version")+ "(" + Build.VERSION.INCREMENTAL + ")");
        deviceInfo.append("\n OS API level:" + Build.VERSION.SDK_INT);
        deviceInfo.append("\n Device: "+ Build.DEVICE);
        deviceInfo.append("\n Brand+Manufacturer: " + Build.BRAND +" " +Build.MANUFACTURER);
        deviceInfo.append("\n Model (and Product): "+ Build.MODEL + " ("+ Build.PRODUCT+")");

        return deviceInfo.toString();
    }
}
