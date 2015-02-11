package com.alexmiragall.wakepc.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.alexmiragall.wakepc.ApplicationWakePC;
import com.alexmiragall.wakepc.modules.ActivityModule;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
public abstract class BaseActivity extends ActionBarActivity {


    private ObjectGraph activityScopeGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }


    /**
     * Method used to resolve dependencies provided by Dagger modules. Inject an object to provide
     * every @Inject annotation contained.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }


    /**
     * Get a list of Dagger modules with Activity scope needed to this Activity.
     *
     * @return modules with new dependencies to provide.
     */
    protected abstract List<Object> getModules();


    /**
     * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
     * declared one in the activity. This new graph will be destroyed once the activity lifecycle
     * finish.
     * <p/>
     * This is the key of how to use Activity scope dependency injection.
     */
    private void injectDependencies() {
        ApplicationWakePC application = (ApplicationWakePC) getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = application.plus(activityScopeModules);
        inject(this);
    }


    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     */
    private void injectViews() {
        ButterKnife.inject(this);
    }
}
