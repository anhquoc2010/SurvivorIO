package com.example.cuoiki_android_lythuyet.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.KeeperDetailActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.RequestDetail;
import com.example.cuoiki_android_lythuyet.models.Keeper;
import com.example.cuoiki_android_lythuyet.models.Pet;

import java.util.ArrayList;
import java.util.List;

public class KeeperAdapter extends RecyclerView.Adapter<KeeperAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Keeper> keepersList;

    public KeeperAdapter(Context context, ArrayList<Keeper> keepersList) {
        this.context = context;
        this.keepersList = keepersList;
    }

    @NonNull
    @Override
    public KeeperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_keeper_saved, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Keeper keeper = keepersList.get(position);
        Pet pet = (Pet) holder.intentPet.getSerializableExtra("petsaved");;

        if (keeper == null) {
            return;
        }
        holder.tv_name_booking.setText(keeper.getName());
        holder.tv_price_booking.setText(keeper.getPrice() + " $");
        holder.tv_distance_booking.setText(keeper.getDistance());
        holder.tv_star_booking.setText(String.valueOf(keeper.getStar()));
        holder.tv_preview_booking.setText(keeper.getPreview());
        holder.imgv_booking.setImageResource(keeper.getImageKeeper());
        holder.itemBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDeTail(keeper, pet);
            }
        });
    }

    private void onClickGotoDeTail(Keeper keeper, Pet pet) {
        Intent intent = new Intent(context, KeeperDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_keeper", keeper);
        bundle.putSerializable("object_pet", pet);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (keepersList != null) {
            return keepersList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_booking;
        TextView tv_distance_booking;
        TextView tv_star_booking;
        TextView tv_preview_booking;
        TextView tv_price_booking;
        ImageView imgv_booking;
        LinearLayout itemBooking;
        Intent intentPet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //get intent
            intentPet = ((KeeperDetailActivity) context).getIntent();

            tv_name_booking = itemView.findViewById(R.id.tv_name_booking);
            tv_distance_booking = itemView.findViewById(R.id.tv_distance_booking);
            tv_star_booking = itemView.findViewById(R.id.tv_star_booking);
            tv_preview_booking = itemView.findViewById(R.id.tv_preview_booking);
            tv_price_booking = itemView.findViewById(R.id.tv_price_booking);
            imgv_booking = itemView.findViewById(R.id.imgv_booking);

            itemBooking = itemView.findViewById(R.id.item_keeper);
        }
    }
}
