package com.example.a2207project;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Telephony;

import java.util.Date;

public class MessageScrape {

    public static String scrapeMessage (ContentResolver contentResolver) {
        StringBuilder builder = new StringBuilder();

        Cursor cursor = contentResolver.query(Telephony.Sms.CONTENT_URI,
                null, null, null, Telephony.Sms.DATE + " DESC");

        if (cursor != null && cursor.getCount() > 0) {

            int number = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
            int date = cursor.getColumnIndex(Telephony.Sms.DATE);
            int content = cursor.getColumnIndex(Telephony.Sms.BODY);
            int type = cursor.getColumnIndex(Telephony.Sms.TYPE);

            while (cursor.moveToNext()) {
                String smsDate = cursor.getString(date);
                String phoneNumber = cursor.getString(number);
                String smsBody = cursor.getString(content);
                String smsType = cursor.getString(type);
                Date smsDayTime = new Date(Long.parseLong(smsDate));
                String dir = null;
                switch (Integer.parseInt(smsType)) {
                    case Telephony.Sms.MESSAGE_TYPE_INBOX:
                        dir = "INBOX";
                        break;
                    case Telephony.Sms.MESSAGE_TYPE_SENT:
                        dir = "SENT";
                        break;
                    case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                        dir = "OUTBOX";
                        break;
                    default:
                        break;
                }

                builder.append("SMS Date: " + smsDayTime +
                        "\nAddress Number: " + phoneNumber +
                        "\nSMS Type: " + dir +
                        "\nSMS Body:\n" + smsBody + "\n\n"
                );
            }
        }

        cursor.close();
        return builder.toString();
    }
}
