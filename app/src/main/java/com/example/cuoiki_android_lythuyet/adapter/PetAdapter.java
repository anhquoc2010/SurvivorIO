package com.example.cuoiki_android_lythuyet.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.ListKeeperSaved;
import com.example.cuoiki_android_lythuyet.PetDetailActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Pet> petsList;

    public PetAdapter(Context context, ArrayList<Pet> petsList) {
        this.context = context;
        this.petsList = petsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gán View
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Gán dữ liệu
        Pet pet = petsList.get(position);

        holder.tvName.setText(pet.getName());
        holder.tvSpecies.setText(pet.getSpecies());
        holder.tvColor.setText(pet.getColor());
        holder.tvAge.setText(String.valueOf(pet.getAge()));
        if (pet.isGender()) {
            holder.ivGender.setImageResource(R.drawable.male_black_24dp);
        } else {
            holder.ivGender.setImageResource(R.drawable.female_black_24dp);
        }
        holder.ivAvatar.setImageResource(pet.getImageResourceID());

        holder.layoutPetItem.setOnClickListener(v -> {
            if (Tag.getTag().equals("info")) {
                onClickDetailPet(pet);
            } else if (Tag.getTag().equals("request")) {
                onClickRequest(pet);
            }

        });

        holder.layoutPetItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence options[] = new CharSequence[]
                        {
                                "Delete", "Cancel"
                        };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure you want to delete?");
                builder.setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        removeItem(position);
                        Toast.makeText(context, "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                    } else if (which == 1) {
                        //do nothing
                    }
                });

                builder.show();
                return false;
            }
        });
    }

    private void onClickRequest(Pet pet) {
        Intent intent = new Intent(context, ListKeeperSaved.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("petsaved", pet);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.fade_out);
    }

    private void onClickDetailPet(Pet pet) {
        Intent intent = new Intent(context, PetDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("pet", pet);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in, R.anim.fade_out);
    }

    @Override
    public int getItemCount() {
        if (petsList != null) {
            return petsList.size();
        } else
            return 0;
    }
    public void release(){
        context = null;
    }

    public void removeItem(int index){
        petsList.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvSpecies;
        TextView tvColor;
        TextView tvAge;
        ImageView ivGender;
        ImageView ivAvatar;
        ConstraintLayout layoutPetItem;

        ImageView ivMark;

        FirebaseAuth mAuth;
        DatabaseReference mDatabase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            // Ánh xạ view
            tvName = itemView.findViewById(R.id.tv_name);
            tvSpecies = itemView.findViewById(R.id.tv_species);
            tvColor = itemView.findViewById(R.id.tv_color);
            tvAge = itemView.findViewById(R.id.tv_age);
            ivGender = itemView.findViewById(R.id.iv_gender);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            ivMark = itemView.findViewById(R.id.iv_mark);
            ivMark.setTag("unmarked");
            ivMark.setOnClickListener(v -> {
                if (ivMark.getTag().equals("unmarked")) {
                    ivMark.setImageResource(R.drawable.favorite_black_36dp);
                    ivMark.setTag("marked");
                } else {
                    ivMark.setImageResource(R.drawable.favorite_border_black_36dp);
                    ivMark.setTag("unmarked");
                }
            });

            layoutPetItem = itemView.findViewById(R.id.single_pet_layout);
        }
    }
}
