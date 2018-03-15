package com.example.greentech_android.crossworkerstask.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.greentech_android.crossworkerstask.R;
import com.example.greentech_android.crossworkerstask.interfaces.OnMyItemClickListener;
import com.example.greentech_android.crossworkerstask.models.MyItem;

import java.util.List;

/**
 * Created by Serry on 3/15/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<MyItem> myItemList;
    private Context context;
    private OnMyItemClickListener onMyItemClickListener;

    public MyRecyclerViewAdapter(List<MyItem> myItemList, Context context, OnMyItemClickListener onMyItemClickListener) {
        this.myItemList = myItemList;
        this.context = context;
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvItem.setText(myItemList.get(position).getName());
        Glide.with(context).load(myItemList.get(position).getPhotoUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.pbItem.setVisibility(View.GONE);
                holder.ivItem.setImageResource(R.drawable.ic_launcher_background);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.pbItem.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.ivItem);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyItemClickListener.onMyItemClick(myItemList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return myItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItem;
        ProgressBar pbItem;
        TextView tvItem;
        CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            ivItem = itemView.findViewById(R.id.iv_item);
            tvItem = itemView.findViewById(R.id.tv_item);
            pbItem = itemView.findViewById(R.id.pb_item);
        }
    }
}
