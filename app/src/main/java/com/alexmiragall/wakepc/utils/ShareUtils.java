package com.alexmiragall.wakepc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Alejandro on 22/03/14.
 */
public class ShareUtils {
    public static final String TYPE_TWITTER = "twi";
    public static final String TYPE_FACEBOOK = "facebook.katana";
    public static final String TYPE_ALL = "all";

    private static File imageFile;

    public static String saveScreenshot(Activity context) {
        View v1 = context.getWindow().getDecorView().getRootView();
        return saveScreenshot(context, v1);
    }

    public static String saveScreenshot(Context context, View viewToCapture) {
        // image naming and path  to include sd card  appending name you choose for file
        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "musicness_score.jpg";

        // create bitmap screen capture
        Bitmap bitmap;

        viewToCapture.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(viewToCapture.getDrawingCache());
        viewToCapture.setDrawingCacheEnabled(false);

        OutputStream fout = null;
        imageFile = new File(mPath);

        try {
            fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mPath;
    }

    public static void share(Context context, String type, String title, String text) {
        share(context, type, null, title, text);
    }

    public static void shareWithBitmap(Context context, String type, Bitmap image, String title, String text) {
        String uri = ImageStorageHelper.saveImage(context, "tempShareFolder", "temp_image.jpg", image, true, false);
        share(context, type, uri, title, text);
    }

    public static void share(Context context, String type, String path, String title, String text) {

        Intent intent = getIntentType(context, type, path, title, text);
        if (intent == null) {
            intent = new Intent(Intent.ACTION_SEND);

            if (path == null)
                intent.setType("plain/text");
            else {
                intent.setType("image/jpeg");
                Uri imageUri = Uri.parse("file://" + path);
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            }

            if (!TextUtils.isEmpty(text))
                intent.putExtra(Intent.EXTRA_TEXT, text);
            if (!TextUtils.isEmpty(title))
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
        }

        context.startActivity(Intent.createChooser(intent, "Share"));
    }

    public static void sendEmail(Context context, String destinatary, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(destinatary) +
                "?subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(text);
        Uri uri = Uri.parse(uriText);

        send.setData(uri);
        context.startActivity(Intent.createChooser(send, "Send mail..."));
    }

    public static void openWeb(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void rateApp(Context context) {
        final Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(rateAppIntent);
    }

    public static String appStoreUrl(Context context) {
        return "http://play.google.com/store/apps/details?id=" + context.getPackageName();
    }

    private static Intent getIntentType(Context context, String type, String path, String title, String text) {
        boolean found = false;
        Intent share = new Intent(Intent.ACTION_SEND);
        if (path == null)
            share.setType("text/plain");
        if (path != null) {
            share.setType("image/jpeg");
        }

        if (path != null) {
            Uri imageUri = Uri.parse("file://" + path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
        }
        if (!TextUtils.isEmpty(text))
            share.putExtra(Intent.EXTRA_TEXT, text);
        if (!TextUtils.isEmpty(title))
            share.putExtra(Intent.EXTRA_SUBJECT, title);

        if (!type.equals(TYPE_ALL)) {
            // gets the list of intents that can be loaded.
            List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(share, 0);
            if (!resInfo.isEmpty()) {
                for (ResolveInfo info : resInfo) {
                    if (info.activityInfo.packageName.toLowerCase().contains(type) ||
                            info.activityInfo.name.toLowerCase().contains(type)) {
                        share.setPackage(info.activityInfo.packageName);
                        found = true;
                        break;
                    }
                }

                return share;
            }
        }
        return share;
    }
}
