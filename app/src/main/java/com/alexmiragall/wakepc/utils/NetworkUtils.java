package com.alexmiragall.wakepc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Alex on 03/07/14.
 */
public class NetworkUtils {
    public static boolean checkInternetConnection(Context ctx){

        ConnectivityManager comManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = comManager.getActiveNetworkInfo();
        if(info == null)
            return false;
        if(!info.isConnected())
            return false;
        if(!info.isAvailable())
            return false;

        return true;
    }
}
