package com.example.a2207project;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class NetworkScrape {

    public static String getWifiInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        String bssid = wifiInfo.getBSSID();
        int networkId = wifiInfo.getNetworkId();
        String ipAddress = getIpAddress(wifiInfo.getIpAddress());
        return String.format("WIFI ID: %d\nWIFI SSID: %s\nWIFI BSSID: %s\nIP Address: %s", networkId, ssid, bssid, ipAddress);
    }

    private static String getIpAddress(int ipAddress) {
        return Formatter.formatIpAddress(ipAddress);
    }
}