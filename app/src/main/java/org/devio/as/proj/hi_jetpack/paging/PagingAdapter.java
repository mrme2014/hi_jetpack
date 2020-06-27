package org.devio.as.proj.hi_jetpack.paging;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PagingAdapter extends AbsPagedListAdapter<String, RecyclerView.ViewHolder> {

    public PagingAdapter() {
        super(new DiffUtil.ItemCallback<String>() {

            @Override
            public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return newItem == oldItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        TextView itemView = new TextView(parent.getContext());
        itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, 100));
        return new  RecyclerView.ViewHolder(itemView){};
    }

    @Override
    protected void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        ((TextView)holder.itemView).setText(getItem(position));
    }
}
