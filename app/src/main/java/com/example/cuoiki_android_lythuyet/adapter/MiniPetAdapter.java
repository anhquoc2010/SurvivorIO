package com.example.cuoiki_android_lythuyet.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.models.Pet;

import java.util.ArrayList;

public class MiniPetAdapter extends RecyclerView.Adapter<MiniPetAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Pet> petsList;

    public MiniPetAdapter(Context context, ArrayList<Pet> petsList) {
        this.context = context;
        this.petsList = petsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gán View
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mini_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Gán dữ liệu
        Pet pet = petsList.get(position);

        holder.tvName.setText(pet.getName());
        holder.ivAvatar.setImageResource(pet.getImageResourceID());

        holder.layoutPetItem.setOnClickListener(v -> {
            onClickDetailPet(pet);
        });
    }

    private void onClickDetailPet(Pet pet) {
//        Intent intent = new Intent(context, PetDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("pet", pet);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.fade_out);
    }

    @Override
    public int getItemCount() {
        if (petsList != null) {
            return petsList.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivAvatar;
        ConstraintLayout layoutPetItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view
            tvName = itemView.findViewById(R.id.tv_name);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            layoutPetItem = itemView.findViewById(R.id.single_pet_layout);
        }
    }
}
