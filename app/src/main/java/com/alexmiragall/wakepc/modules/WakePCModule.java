package com.alexmiragall.wakepc.modules;

import com.alexmiragall.wakepc.activities.WakePCActivity;
import com.alexmiragall.wakepc.fragments.AddPCFragment;
import com.alexmiragall.wakepc.fragments.WakePCFragment;
import com.alexmiragall.wakepc.model.WakeOnLan;
import com.alexmiragall.wakepc.presenters.AddPCPresenter;
import com.alexmiragall.wakepc.presenters.WakePCPresenter;
import com.alexmiragall.wakepc.storage.WakePCPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
@Module(injects = {
        WakePCActivity.class, WakePCFragment.class, AddPCFragment.class
}, library = true, complete = false)
public class WakePCModule {

    public WakePCModule() {
    }

    @Provides
    WakePCPresenter provideWakePCPresenter(WakePCPreferences prefs, WakeOnLan wakeOnLan) {
        return new WakePCPresenter(prefs, wakeOnLan);
    }

    @Provides
    AddPCPresenter provideAddPCPresenter(WakePCPreferences prefs, WakeOnLan wakeOnLan) {
        return new AddPCPresenter(prefs, wakeOnLan);
    }
}
