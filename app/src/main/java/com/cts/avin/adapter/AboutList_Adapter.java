package com.cts.avin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.cts.avin.R;
import com.cts.avin.data.main.Rows;
import com.cts.avin.viewmodel.AboutList_ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutList_Adapter extends RecyclerView.Adapter<AboutList_Adapter.ItemViewHolder> {
    private AboutListItemSelected_Listener itemSelectedListener;
    private final List<Rows> data = new ArrayList<>();
    @Inject
    Context mContext;

    public AboutList_Adapter(AboutList_ViewModel viewModel, LifecycleOwner lifecycleOwner, AboutListItemSelected_Listener itemSelectedListener) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutlist_itemview, parent, false);
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

        ItemViewHolder(View itemView, AboutListItemSelected_Listener itemSelectedListener) {
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
