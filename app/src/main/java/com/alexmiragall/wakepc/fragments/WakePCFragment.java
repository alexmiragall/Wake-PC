package com.alexmiragall.wakepc.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alexmiragall.wakepc.R;
import com.alexmiragall.wakepc.adapters.BaseRecyclerAdapter;
import com.alexmiragall.wakepc.adapters.EventsClickListener;
import com.alexmiragall.wakepc.adapters.pcadapter.PCViewHolder;
import com.alexmiragall.wakepc.model.PC;
import com.alexmiragall.wakepc.presenters.WakePCPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WakePCFragment extends BaseFragment implements WakePCPresenter.View {
    @InjectView(R.id.fragment_wake_pc_recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.fragment_wake_pc_empty_txt)
    TextView txtEmpty;

    @Inject
    WakePCPresenter wakePCPresenter;
    List<PC> pcsList = new ArrayList<>();

    private BaseRecyclerAdapter<PC, PCViewHolder> mAdapter;

    public WakePCFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
       // menu.add(0, 0, 0, "Add");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
        //    openEditionFragment(null, null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_wake_pc;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wakePCPresenter.setView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseRecyclerAdapter<PC, PCViewHolder>(pcsList, new PCViewHolder.Factory(new EventsClickListener() {
            @Override
            public void onItemClickListener(int position) {
                wakePCPresenter.editPC(position, pcsList.get(position));
            }

            @Override
            public void onItemSubViewClickListener(int position, int viewId, View view) {
                PC pc = pcsList.get(position);
                switch (viewId) {
                    case R.id.item_pc_power_btn:
                        wakePCPresenter.powerOnPC(pc);
                        break;
                    case R.id.item_pc_edit_btn:
                        wakePCPresenter.editPC(position, pc);
                        break;
                }
            }
        }));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        wakePCPresenter.resume();
    }

    @Override
    public void openEditionFragment(Integer position, PC pc) {
        getFragmentManager().beginTransaction().replace(R.id.container, AddPCFragment.newInstance(position, pc)).addToBackStack(null).commit();
    }

    @Override
    public void updateListView(List<PC> pcs) {
        pcsList.clear();
        pcsList.addAll(pcs);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fragment_wake_pc_add_btn)
    public void onClickAdd() {
        wakePCPresenter.editPC(null, null);
    }
}
