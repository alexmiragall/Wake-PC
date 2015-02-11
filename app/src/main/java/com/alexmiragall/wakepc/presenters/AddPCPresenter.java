package com.alexmiragall.wakepc.presenters;

import com.alexmiragall.wakepc.model.PC;
import com.alexmiragall.wakepc.model.WakeOnLan;
import com.alexmiragall.wakepc.storage.WakePCPreferences;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
public class AddPCPresenter {
    WakeOnLan wakeOnLan;
    WakePCPreferences preferences;
    View view;

    public AddPCPresenter(WakePCPreferences preferences, WakeOnLan wakeOnLan) {
        this.wakeOnLan = wakeOnLan;
        this.preferences = preferences;
    }

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void addPC(PC pc) {
        preferences.addPC(pc);
    }

    public void deletePC(Integer position) {
        preferences.deletePC(position);
    }

    public void editPC(Integer id, PC pc) {
        preferences.editPC(id, pc);
    }

    public interface View {

    }
}
