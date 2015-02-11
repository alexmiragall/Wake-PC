package com.alexmiragall.wakepc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexmiragall.wakepc.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Alejandro Miragall Arnal on 19/01/2015.
 */
public class BaseRecyclerAdapter<T, V extends BaseRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<V> {
    private List<T> items;
    private Factory<V> factory;

    public BaseRecyclerAdapter(List<T> items, Factory<V> factory) {
        this.items = items;
        this.factory = factory;
    }

    @Override
    final public V onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(factory.getLayout(), viewGroup, false);
        v.setTag(items.get(i));
        V vh = (V) factory.build(v);
        return vh;
    }

    @Override
    final public void onBindViewHolder(V viewHolder, int i) {
        viewHolder.bindView(items.get(i));
    }

    @Override
    final public int getItemCount() {
        return items.size();
    }

    public interface Factory<V> {
        public V build(View view);
        public int getLayout();
    }

    abstract public static class ViewHolder<T> extends RecyclerView.ViewHolder {
        T item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (T) itemView.getTag();
            ButterKnife.inject(this, itemView);
        }

        public abstract void bindView(T item);

        public T getItem() {
            return item;
        }

    }
}
