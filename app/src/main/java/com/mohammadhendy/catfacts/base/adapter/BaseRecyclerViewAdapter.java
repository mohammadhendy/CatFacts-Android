package com.mohammadhendy.catfacts.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public abstract class BaseRecyclerViewAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    /**
     * Contains the list of objects that represent the data.
     */
    protected List<T> mItems;

    public BaseRecyclerViewAdapter() {
        this(new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(T[] items) {
        this((items != null) ? new ArrayList<T>(Arrays.asList(items)) : new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(List<T> items) {
        this.mItems = (items != null) ? items : new ArrayList<T>();
    }

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    public T getItem(int position) {
        return mItems.get(position);
    }

    public List<T> getItems() {
        return mItems;
    }

    /**
     * Adds the specified object at the end of this adapter.
     *
     * @param object the object to add.
     */
    public void add(T object) {
        if (null != object) {
            mItems.add(object);
            notifyItemInserted(mItems.size() - 1);
        }
    }

    /**
     * Adds the specified object at the end of this adapter.
     *
     * @param object the object to add.
     */
    public void addFront(T object) {
        if (null != object) {
            mItems.add(0, object);
            notifyItemInserted(0);
        }
    }

    /**
     * Adds the objects in the specified collection to the end of this adapter. The
     * objects are added in the order in which they are returned from the
     * collection's iterator.
     *
     * @param collection the collection of objects.
     */
    public void addAll(Collection<T> collection) {
        if (null != collection && !collection.isEmpty()) {
            final int prevItemCount = mItems.size();
            mItems.addAll(collection);
            final int newItemCount = mItems.size();
            notifyItemRangeInserted(prevItemCount, newItemCount - prevItemCount);
        }
    }


    public void addAllBefore(Collection<T> collection) {
        if (null != collection && !collection.isEmpty()) {
            final int prevItemCount = mItems.size();
            mItems.addAll(collection);
            final int newItemCount = mItems.size();
            notifyItemRangeInserted(0, newItemCount);
        }
    }

    public void insert(int position, T object) {
        mItems.add(position, object);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Removes all elements from this adapter, leaving it empty.
     */
    public void clear() {
        final int itemCount = mItems.size();
        mItems.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public List<T> swapItems(List<T> newItems) {
        if (mItems == newItems) {
            return null;
        }
        List<T> oldItems = mItems;
        if (newItems != null) {
            mItems = newItems;
            notifyDataSetChanged();
        } else {
            mItems = new ArrayList<T>();
            notifyDataSetChanged();
        }
        return oldItems;
    }
}
