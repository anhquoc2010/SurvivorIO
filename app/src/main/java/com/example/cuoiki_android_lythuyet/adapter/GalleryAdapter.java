package com.example.cuoiki_android_lythuyet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.R;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Integer> galleryList;

    public GalleryAdapter(Context context, ArrayList<Integer> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gán View
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liệu
        int image = galleryList.get(position);

        holder.ivItem.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        if (galleryList != null) {
            return galleryList.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            ivItem = itemView.findViewById(R.id.iv_gallery_item);
        }
    }
}
