package com.alexmiragall.wakepc.utils;

import android.content.Context;

/**
 * Created by Alex on 27/05/14.
 */
public class ConverterDpToPx {

    public static int convertDpToPx(Context context,int dp) {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }
}
