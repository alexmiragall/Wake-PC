package com.alexmiragall.wakepc.presenters;

import com.alexmiragall.wakepc.model.PC;
import com.alexmiragall.wakepc.model.WakeOnLan;
import com.alexmiragall.wakepc.storage.WakePCPreferences;

import java.util.List;

/**
 * Created by Alejandro Miragall Arnal on 18/01/2015.
 */
public class WakePCPresenter {
    private final WakePCPreferences prefs;
    WakeOnLan wakeOnLan;
    View view;

    public WakePCPresenter(WakePCPreferences prefs, WakeOnLan wakeOnLan) {
        this.wakeOnLan = wakeOnLan;
        this.prefs = prefs;
    }

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void powerOnPC(final PC pc) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                wakeOnLan.wakeUp(pc.getIp(), pc.getMac());
            }
        }).start();

    }

    public void editPC(Integer position, PC pc) {
        view.openEditionFragment(position, pc);
    }

    public void resume() {
        view.updateListView(prefs.getPcs());


    }



    public interface View {
        public void openEditionFragment(Integer position, PC pc);

        void updateListView(List<PC> pcs);
    }
}
