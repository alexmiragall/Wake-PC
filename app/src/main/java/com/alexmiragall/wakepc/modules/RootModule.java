package com.alexmiragall.wakepc.modules;

import android.content.Context;
import android.view.LayoutInflater;

import com.alexmiragall.wakepc.ApplicationWakePC;
import com.alexmiragall.wakepc.storage.Preferences;
import com.alexmiragall.wakepc.storage.WakePCPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
@Module(
        includes = {
                //ExecutorModule.class, TvShowsModule.class,
        },
        injects = {
                ApplicationWakePC.class
        }, library = true)
public final class RootModule {


    private final Context context;


    public RootModule(Context context) {
        this.context = context;
    }

    @Singleton @Provides
    Preferences providePreferences() {
        return new Preferences(context);
    }

    @Singleton @Provides
    WakePCPreferences provideWakePCPreferences(Preferences prefs) {
        return new WakePCPreferences(prefs);
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }


    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
