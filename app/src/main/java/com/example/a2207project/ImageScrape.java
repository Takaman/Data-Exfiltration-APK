package com.example.a2207project;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageScrape {

    public static List<File> scrapeImages(ContentResolver contentResolver) {
        List<File> fileList = new ArrayList<File>();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    String imagePath = "";
                    int pathIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (pathIndex >= 0) {
                        imagePath = cursor.getString(pathIndex);
                        File file = new File(imagePath);
                        fileList.add(file);
                    }
                }
            }
        } else {
            return null;
        }
        return fileList;
    }
}
