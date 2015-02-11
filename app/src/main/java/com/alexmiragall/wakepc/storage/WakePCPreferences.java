package com.alexmiragall.wakepc.storage;

import com.alexmiragall.wakepc.model.PC;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Alejandro Miragall Arnal on 22/01/2015.
 */
public class WakePCPreferences {
    private final Preferences prefs;
    private static List<PC> pcs;
    private final Type listType;

    public WakePCPreferences(Preferences prefs) {
        this.prefs = prefs;
        listType = new TypeToken<List<PC>>()
        {
        }.getType();
    }

    public List<PC> getPcs() {

        if(pcs == null) {
            String json = this.prefs.read("PCS");
            if(json != null)
                pcs = new Gson().fromJson(json, listType);
        }

        if(pcs == null)
            pcs = new ArrayList<>();

        return pcs;
    }

    public void addPC(PC pc) {
        pcs = getPcs();
        pcs.add(pc);
        prefs.write("PCS", new Gson().toJson(pcs, listType));
    }

    public void deletePC(Integer position) {
        pcs = getPcs();
        pcs.remove(position.intValue());
        prefs.write("PCS", new Gson().toJson(pcs, listType));
    }

    public void editPC(Integer position, PC pc) {
        pcs = getPcs();
        pcs.remove(position.intValue());
        pcs.add(position, pc);
        prefs.write("PCS", new Gson().toJson(pcs, listType));
    }
}
