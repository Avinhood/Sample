package com.cts.avin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cts.avin.R;
import com.cts.avin.data.main.Rows;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutListAdapter extends RecyclerView.Adapter<AboutListAdapter.ItemViewHolder> {
    private AboutListItemSelectedListener itemSelectedListener;
    List<Rows> data = new ArrayList<>();

    public AboutListAdapter(AboutListItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
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

        ItemViewHolder(View itemView, AboutListItemSelectedListener itemSelectedListener) {
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
            Glide.with(ivImage.getContext()).load(data.getImageHref()).centerInside()
                    .placeholder(R.drawable.ic_launcher_background).into(ivImage);

        }
    }

    public List<Rows> getData() {
        return data;
    }
    public void setData(List<Rows> data) {
        this.data = data;
    }

}
