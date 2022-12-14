package com.example.cuoiki_android_lythuyet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuoiki_android_lythuyet.databinding.ActivityListKeeperSavedBinding;
import com.example.cuoiki_android_lythuyet.models.Keepers;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ListKeeperSaved extends AppCompatActivity {

    private ActivityListKeeperSavedBinding binding;

    private DatabaseReference keeperRef, userRef;
    private FirebaseAuth mAuth;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityListKeeperSavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        keeperRef = FirebaseDatabase.getInstance().getReference().child("Keepers");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    @Override
    public void onStart() {
        super.onStart();

        //get intent
        Intent intent = getIntent();
        Pet pet = (Pet) intent.getSerializableExtra("petsaved");

        binding.imageView5.setOnClickListener(v -> onBackPressed());

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
                                    Picasso.get().load(image[0]).placeholder(R.drawable.pet2).into(holder.profile_image);
                                } else {
                                    holder.profile_image.setImageResource(R.drawable.pet2);
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
}