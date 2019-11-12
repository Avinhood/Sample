package com.cts.avin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cts.avin.R;
import com.cts.avin.data.main.Rows;
import com.cts.avin.di.modules.GlideApp;
import com.cts.avin.listener.ItemSelectedListener;
import com.cts.avin.viewmodel.MainListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainListAdapter  extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> {

    private ItemSelectedListener itemSelectedListener;
    private final List<Rows> data = new ArrayList<>();
    @Inject
    Context mContext;

    public MainListAdapter(MainListViewModel viewModel, LifecycleOwner lifecycleOwner, ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
        viewModel.getMainListData().observe(lifecycleOwner, listData -> {
            data.clear();
            if (listData != null) {
                data.addAll(listData.getRows());
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item_view, parent, false);
        return new ItemViewHolder(view, itemSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    final class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        private Rows data;

        ItemViewHolder(View itemView, ItemSelectedListener itemSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (data != null) {
                    itemSelectedListener.onItemSelected(data);
                }
            });
        }

        void bind(Rows data) {
            this.data = data;
            tvName.setText(data.getTitle());
            tvDesc.setText(data.getDescription());
           // Glide.with(ivImage.getContext()).load(data.getImageHref()).centerInside().into(ivImage);
//            GlideApp.with(mContext).load(data.getImageHref()).centerInside().into(ivImage);

        }
    }
}
