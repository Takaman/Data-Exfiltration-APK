package com.example.a2207project;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CallLog;

import java.util.Date;

public class CallLogScrape {

    public static String scrapeCallLogs (ContentResolver contentResolver) {
        StringBuilder builder = new StringBuilder();

        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");

        if (cursor != null && cursor.getCount() > 0) {

            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()) {
                String phNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date(Long.parseLong(callDate));
                String callDuration = cursor.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }

                builder.append("Phone Number: " + phNumber +
                        "\nCall Type: " + dir +
                        "\nDate: " + callDayTime +
                        "\nCall Duration: " + callDuration + "\n");

            }

            builder.append("\n");

        }

        cursor.close();
        return builder.toString();
    }

}
