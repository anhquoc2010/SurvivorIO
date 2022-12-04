package com.example.cuoiki_android_lythuyet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuoiki_android_lythuyet.adapter.KeeperAdapter;
import com.example.cuoiki_android_lythuyet.databinding.ActivityListKeeperSavedBinding;
import com.example.cuoiki_android_lythuyet.models.Keepers;
import com.example.cuoiki_android_lythuyet.models.Keeper;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListKeeperSaved extends AppCompatActivity {

    private ActivityListKeeperSavedBinding binding;
    private ArrayList<Keeper> keepers;
    private KeeperAdapter keeperAdapter;

    private DatabaseReference keeperRef, userRef;
    private FirebaseAuth mAuth;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListKeeperSavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        keeperRef = FirebaseDatabase.getInstance().getReference().child("Keepers");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

//        addKeepers();
//        keeperAdapter = new KeeperAdapter(this, keepers);
//        binding.lvKeeperSaved.setAdapter(keeperAdapter);
//        binding.lvKeeperSaved.setHasFixedSize(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        //get intent
        Intent intent = getIntent();
        Pet pet = (Pet) intent.getSerializableExtra("petsaved");

        FirebaseRecyclerOptions<Keepers> options = new FirebaseRecyclerOptions.Builder<Keepers>()
                .setQuery(keeperRef, Keepers.class).build();

        FirebaseRecyclerAdapter<Keepers, ListKeeperSaved.KeeperViewHolder> adapter = new FirebaseRecyclerAdapter<Keepers, ListKeeperSaved.KeeperViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ListKeeperSaved.KeeperViewHolder holder, int position, @NonNull Keepers model) {
                if (!getRef(position).getKey().equals(userID)) {
                    final String userid = getRef(position).getKey();
                    final String[] image = {"default_image"};
                    userRef.child(userid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Log.d("hehehe", "onDataChange: " + dataSnapshot.hasChild("image"));
                                if (dataSnapshot.hasChild("image")) {
                                    image[0] = dataSnapshot.child("image").getValue().toString();
                                    Picasso.get().load(image[0]).placeholder(R.drawable.profile_circle_inactive).into(holder.profile_image);
                                } else {
                                }
                                final String name = dataSnapshot.child("name").getValue().toString();

                                holder.username.setText(name);

                                holder.itemView.setOnClickListener(v -> {
                                    Intent keeperIntent = new Intent(ListKeeperSaved.this, KeeperDetailActivity.class);
                                    keeperIntent.putExtra("visit_user_id", userid);
                                    keeperIntent.putExtra("visit_user_name", name);
                                    keeperIntent.putExtra("visit_image", image[0]);
                                    keeperIntent.putExtra("keepersaved", model);
                                    keeperIntent.putExtra("petsaved", pet);
                                    startActivity(keeperIntent);
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @Override
            public ListKeeperSaved.KeeperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(ListKeeperSaved.this).inflate(R.layout.item_list_keeper_saved, parent, false);
                return new ListKeeperSaved.KeeperViewHolder(view);
            }
        };

        binding.lvKeeperSaved.setAdapter(adapter);
        adapter.startListening();
    }

    public static class KeeperViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView username, distance, star, review, price;

        public KeeperViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.imgv_booking);
            username = itemView.findViewById(R.id.tv_name_booking);
            distance = itemView.findViewById(R.id.tv_distance_booking);
            star = itemView.findViewById(R.id.tv_star_booking);
            review = itemView.findViewById(R.id.tv_preview_booking);
            price = itemView.findViewById(R.id.tv_price_booking);
        }
    }

//    private void addKeepers() {
//        keepers = new ArrayList<>();
//        keepers.add(new Keeper(R.drawable.pet1,"Lực", "10km away", 5.0, "6 preview", 45));
//        keepers.add(new Keeper(R.drawable.pet2,"Ngân", "10km away", 5.0, "5 preview", 35));
//        keepers.add(new Keeper(R.drawable.pet3,"Mạnh", "10km away", 5.0, "1 preview", 15));
//        keepers.add(new Keeper(R.drawable.pet4,"Quốc", "10km away", 5.0, "8 preview", 65));
//        keepers.add(new Keeper(R.drawable.pet5,"Phúc", "10km away", 5.0, "1 preview", 35));
//    }
}