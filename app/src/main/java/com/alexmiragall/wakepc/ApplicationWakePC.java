package com.alexmiragall.wakepc;

import android.app.Application;

import com.alexmiragall.wakepc.modules.RootModule;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
public class ApplicationWakePC extends Application{

    ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }


    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }


    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }
}
