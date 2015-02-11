package com.alexmiragall.wakepc.adapters.pcadapter;

import android.view.View;
import android.widget.TextView;

import com.alexmiragall.wakepc.R;
import com.alexmiragall.wakepc.adapters.BaseRecyclerAdapter;
import com.alexmiragall.wakepc.adapters.EventsClickListener;
import com.alexmiragall.wakepc.model.PC;

import butterknife.InjectView;

/**
 * Created by Alejandro Miragall Arnal on 19/01/2015.
 */
public class PCViewHolder extends BaseRecyclerAdapter.ViewHolder<PC> {
    @InjectView(R.id.item_pc_name_txt)
    TextView txtName;
    @InjectView(R.id.item_pc_desc_txt)
    TextView txtDesc;
    @InjectView(R.id.item_pc_edit_btn)
    View btnEdit;
    @InjectView(R.id.item_pc_power_btn)
    View btnPower;


    private final EventsClickListener events;

    public PCViewHolder(View itemView, EventsClickListener events) {
        super(itemView);
        this.events = events;
    }

    @Override
    public void bindView(PC item) {
        txtName.setText(item.getName());
        txtDesc.setText("Mac address: " + item.getMac() + "\nIp address: " + item.getIp());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events.onItemClickListener(getPosition());
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events.onItemSubViewClickListener(getPosition(), btnEdit.getId(), btnEdit);
            }
        });
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events.onItemSubViewClickListener(getPosition(), btnPower.getId(), btnPower);
            }
        });
    }

    public static class Factory implements BaseRecyclerAdapter.Factory {
        private final EventsClickListener listener;

        public Factory(EventsClickListener listener) {
            this.listener = listener;
        }

        @Override
        public int getLayout() {
            return R.layout.item_pc;
        }
        @Override
        public Object build(View view) {
            return new PCViewHolder(view, listener);
        }
    }
}
