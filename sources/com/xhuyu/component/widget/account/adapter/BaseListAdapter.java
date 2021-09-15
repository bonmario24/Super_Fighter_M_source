package com.xhuyu.component.widget.account.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private List<T> data;

    public abstract BaseHolder<T> createHolder(int i);

    public abstract View createView(int i, ViewGroup viewGroup);

    public BaseListAdapter(List<T> data2) {
        this();
        this.data.addAll(data2);
    }

    public BaseListAdapter() {
        this.data = new ArrayList();
    }

    public void appendData(List<T> data2) {
        this.data.addAll(data2);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return this.data;
    }

    public void resetData(List<T> data2) {
        this.data.clear();
        appendData(data2);
    }

    public void deleteData(int position) {
        if (position >= 0 && position < getCount()) {
            this.data.remove(position);
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    public T getItem(int position) {
        return this.data.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder;
        if (convertView == null) {
            convertView = createView(position, parent);
            holder = createHolder(position);
            holder.initView(convertView);
            convertView.setTag(holder);
        } else if (convertView.getTag() instanceof BaseHolder) {
            holder = (BaseHolder) convertView.getTag();
        } else {
            holder = createHolder(position);
            holder.initView(convertView);
            convertView.setTag(holder);
        }
        holder.bindView(position, getItem(position));
        return convertView;
    }
}
