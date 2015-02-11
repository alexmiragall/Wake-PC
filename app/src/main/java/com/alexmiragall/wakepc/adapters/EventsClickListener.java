package com.alexmiragall.wakepc.adapters;

import android.view.View;

/**
 * Created by Alejandro Miragall Arnal on 22/01/2015.
 */
public interface EventsClickListener {
    public void onItemClickListener(int position);
    public void onItemSubViewClickListener(int position, int viewId, View view);
}
