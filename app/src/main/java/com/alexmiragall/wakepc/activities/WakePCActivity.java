package com.alexmiragall.wakepc.activities;

import android.os.Bundle;

import com.alexmiragall.wakepc.R;
import com.alexmiragall.wakepc.fragments.AddPCFragment;
import com.alexmiragall.wakepc.fragments.WakePCFragment;
import com.alexmiragall.wakepc.modules.WakePCModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
public class WakePCActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_pc);
        if(savedInstanceState == null)
        getSupportFragmentManager().beginTransaction().add(R.id.container, new WakePCFragment()).commit();

    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<Object>();
        modules.add(new WakePCModule());
        return modules;
    }
}
