package com.alexmiragall.wakepc.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
@Module(library = true) public final class ActivityModule {
    private final Activity activity;


    public ActivityModule(Activity activity) {
        this.activity = activity;
    }


    @ActivityContext @Provides
    Context provideActivityContext() {
        return activity;
    }
}
