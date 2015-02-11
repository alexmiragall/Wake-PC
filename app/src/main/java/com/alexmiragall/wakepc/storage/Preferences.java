package com.alexmiragall.wakepc.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alejandro Miragall on 5/02/14.
 */
public class Preferences {
    private SharedPreferences prefs;
    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    private void getPrefs() {
        if(prefs == null){
            prefs = context.getSharedPreferences(PreferencesConstants.PREFS, Context.MODE_PRIVATE);
        }
    }
    public String read(String what){
        getPrefs();
        return prefs.getString(what,"");
    }

    public String read(String what, String defaultString){
        getPrefs();
        return prefs.getString(what, defaultString);
    }

    public void write(String where, String what){
        getPrefs();
        prefs.edit().putString(where,what).commit();
    }

    public Integer readInteger(String what){
        getPrefs();
        return prefs.getInt(what,0);
    }

    public void writeInteger(String where, Integer what){
        getPrefs();
        prefs.edit().putInt(where,what).commit();
    }

    public Long readLong(String what){
        getPrefs();
        return prefs.getLong(what,0);
    }

    public void writeLong(String where, Long what){
        getPrefs();
        prefs.edit().putLong(where,what).commit();
    }

    public boolean readBoolean(String what){
        getPrefs();
        return prefs.getBoolean(what, false);
    }

    public void writeBoolean(String where, boolean what){
        getPrefs();
        prefs.edit().putBoolean(where,what).commit();
    }

    public ArrayList<Integer> readIntegerArrayList(String what){
        getPrefs();
        String savedString = prefs.getString(what, "");
        List<String> st = Arrays.asList(savedString.split("\\s*,\\s*"));
        ArrayList<Integer> toreturn = new ArrayList<Integer>();
        for (int i = 0; i < st.size(); i++) {
            try {
                toreturn.add(Integer.parseInt(st.get(i)));
            }catch (Exception e){
            }
        }
        return toreturn;
    }

    public void writeIntegerArrayList(String where, ArrayList<Integer> what){
        getPrefs();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < what.size(); i++) {
            str.append(what.get(i)).append(",");
        }
        prefs.edit().putString(where, str.toString()).commit();
    }

    public void removePreferences() {
        getPrefs();
        prefs.edit().clear().commit();
    }

}
