package com.alexmiragall.wakepc.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro on 15/07/2014.
 */
public class ImageStorageHelper {

    public static String saveImage(Context mContext, String nameFolder, String namePhoto, Bitmap bitmap, boolean useFileDir, boolean addToGallery) {
        if (bitmap != null) {
            File file;
            if(!useFileDir)
                file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            else
                file = mContext.getExternalFilesDir(null);
            if (file != null) {
                try {
                    File fileDir = new File(file, nameFolder);
                    if (!fileDir.exists())
                        fileDir.mkdirs();
                    File filePhoto = new File(fileDir, namePhoto);
                    filePhoto.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(filePhoto);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                    if (addToGallery)
                        addImageToGallery(filePhoto.getAbsolutePath(), mContext);

                    return filePhoto.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static List<String> getAllPathsDirectory(String directory) {
        List<String> filenames = new ArrayList<String>();
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (file != null) {
            File dir = new File(file, directory);

            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File f : files)
                        if (f.isFile() && f.getName().endsWith(".jpg") || f.getName().endsWith(".mp4"))
                            filenames.add(f.getAbsolutePath());
                }
            }
        }
        return filenames;
    }

    private static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


}
