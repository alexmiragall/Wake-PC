package com.alexmiragall.wakepc.fragments;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.alexmiragall.wakepc.R;
import com.alexmiragall.wakepc.model.PC;
import com.alexmiragall.wakepc.presenters.AddPCPresenter;
import com.getbase.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class AddPCFragment extends BaseFragment implements AddPCPresenter.View {

    public static final String TAG = "AddPCFragment";
    private Integer id;

    @InjectView(R.id.fragment_add_pc_name_edit)
    EditText editName;
    @InjectView(R.id.fragment_add_pc_mac_edit)
    EditText editMac;
    @InjectView(R.id.fragment_add_pc_ip_edit)
    EditText editIp;

    @Inject
    AddPCPresenter addPCPresenter;
    private PC pc;
    private boolean edit = false;

    public static AddPCFragment newInstance(Integer position, PC pc) {
        AddPCFragment fragment = new AddPCFragment();
        if(position != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("PC", pc);
            bundle.putInt("ID", position);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public AddPCFragment() {
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pc = (PC) getArguments().get("PC");
            id = (Integer) getArguments().get("ID");
            edit = true;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        if(edit)
            menu.add(0, 0, 0, "Delete PC");
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            deletePC();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_pc;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(edit)
            paintPC(pc);
    }

    @OnClick(R.id.fragment_add_pc_add_btn)
    public void onClickAdd() {
        addPC();
    }

    private void paintPC(PC pc) {
        editName.setText(pc.getName());
        editIp.setText(pc.getIp());
        editMac.setText(pc.getMac());
    }

    private void addPC() {
        String name = editName.getText().toString();
        String mac = editMac.getText().toString();
        String ip = editIp.getText().toString();
        PC pc = new PC(name, mac, null, ip, 0, false);
        if(!edit)
            addPCPresenter.addPC(pc);
        else
            addPCPresenter.editPC(id, pc);
        getFragmentManager().popBackStack();
    }


    private void deletePC() {
        addPCPresenter.deletePC(id);
        getFragmentManager().popBackStack();
    }
}
