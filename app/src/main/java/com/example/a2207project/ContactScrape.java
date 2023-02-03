package com.example.a2207project;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactScrape {

    public static String scrapeContacts(ContentResolver contentResolver) {
        StringBuilder builder = new StringBuilder();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contactName = "";
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                if (nameIndex >= 0) {
                    contactName = cursor.getString(nameIndex);
                }

                String contactId = "";
                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                if (idIndex >= 0) {
                    contactId = cursor.getString(idIndex);
                }

                builder.append(contactName + ": ");

                Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);

                if (phoneCursor != null && phoneCursor.getCount() > 0) {
                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = "";
                        int phoneIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        if (phoneIndex >= 0) {
                            phoneNumber = phoneCursor.getString(phoneIndex);
                        }
                        builder.append(phoneNumber);
                    }
                }

                if (phoneCursor != null) {
                    phoneCursor.close();
                }

                builder.append("\n");
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return builder.toString();
    }
}
