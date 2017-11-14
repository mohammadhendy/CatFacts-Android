package com.mohammadhendy.catfacts.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup parent, @LayoutRes int resLayout) {
        super(LayoutInflater.from(parent.getContext()).inflate(resLayout, parent, false));
        bindViews(itemView);
    }

    protected final void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    public abstract void bind(E item);

    protected Context getContext() {
        return itemView.getContext();
    }

}
