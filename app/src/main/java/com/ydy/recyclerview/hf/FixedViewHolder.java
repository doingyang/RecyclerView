package com.ydy.recyclerview.hf;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public final class FixedViewHolder extends RecyclerView.ViewHolder {

    public static final int VIEW_TYPE_HEADER = -1;
    public static final int VIEW_TYPE_FOOTER = -2;

    FixedViewHolder(View itemView) {
        super(itemView);
    }
}
