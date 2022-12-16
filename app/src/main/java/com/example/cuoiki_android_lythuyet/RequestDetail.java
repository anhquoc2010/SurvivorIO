package com.example.cuoiki_android_lythuyet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.databinding.ActivityRequestDetailBinding;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RequestDetail extends AppCompatActivity {

    ActivityRequestDetailBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference userRef, keeperRef;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityRequestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Tag.setTagOnline("online");

        mAuth = FirebaseAuth.getInstance();
        keeperRef = FirebaseDatabase.getInstance().getReference().child("Keepers");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Intent intent = getIntent();
        String keeperID = intent.getStringExtra("visit_user_id");
        String name = intent.getStringExtra("visit_user_name");
        String image = intent.getStringExtra("visit_image");
        Bookings bookings = (Bookings) intent.getSerializableExtra("bookings");

        binding.imgBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.tvPriceDetail.setText(bookings.getPrice());
        binding.textView10.setText(bookings.getPet());
        Picasso.get().load(image).placeholder(R.drawable.pet2).into(binding.imgvDetail);
        binding.tvNameRequestDetail.setText(name);
        binding.tvStatus.setText(bookings.getStatus());
        binding.tvCalendar.setText(bookings.getCalendar());
        String status = bookings.getStatus();
        if (status.equals("Pending")) {
            binding.tvStatus.setHighlightColor(R.color.yellowsoft);
        } else {
            binding.tvStatus.setBackgroundResource(R.color.greensoft);
        }

        keeperRef.child(keeperID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final String distance = dataSnapshot.child("distance").getValue().toString();
                    final String review = dataSnapshot.child("review").getValue().toString();
                    final String star = dataSnapshot.child("star").getValue().toString();

                    binding.tvDistance.setText(distance);
                    binding.tvReview.setText(review);
                    binding.tvStar.setText(star);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.btnCancle.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Tag.setTagOnline("");
    }
}