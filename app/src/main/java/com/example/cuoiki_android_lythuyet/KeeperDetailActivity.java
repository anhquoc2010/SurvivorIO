package com.example.cuoiki_android_lythuyet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.databinding.ActivityKeeperDetailBinding;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.example.cuoiki_android_lythuyet.models.Keepers;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KeeperDetailActivity extends AppCompatActivity {

    ActivityKeeperDetailBinding binding;
    private FirebaseAuth mauth;
    private DatabaseReference RootRef;
    private String currentUserID, keeperID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityKeeperDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Tag.setTagOnline("online");

        mauth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
        currentUserID = mauth.getCurrentUser().getUid();

        keeperID = getIntent().getExtras().get("visit_user_id").toString();
        Pet pet = (Pet) getIntent().getSerializableExtra("petsaved");
        Keepers keeper = (Keepers) getIntent().getSerializableExtra("keepersaved");

        RootRef.child("Users").child(keeperID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("image")) {
                    String receiverprofileimage = dataSnapshot.child("image").getValue().toString();
                    Picasso.get().load(receiverprofileimage).placeholder(R.drawable.pet2).into(binding.ivAvatarProfile);
                } else {
                    binding.ivAvatarProfile.setImageResource(R.drawable.pet2);
                }
                binding.tvNameProfile.setText(dataSnapshot.child("name").getValue().toString());
                if (dataSnapshot.hasChild("email")) {
                    binding.tvMailProfile.setText(dataSnapshot.child("email").getValue().toString());
                }
                binding.tvPetsCount.setText(String.valueOf((int) (Math.floor(Math.random() * 10) + 1)));
                binding.tvFriendsCount.setText(String.valueOf((int) (Math.floor(Math.random() * 10) + 1)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.tvNameProfile.getText().toString();

                //get now time

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm a");
                String saveCurrentDatetime = currentDate.format(calendar.getTime());
                String status = "Pending";
                Bookings bookings = new Bookings(name, status, saveCurrentDatetime, pet.getName(), keeper.getPrice(), currentUserID, keeperID);
                Log.d("hehehehe", "onClick: " + bookings.toString());
                RootRef.child("Bookings").push().setValue(bookings);
                Tag.setTagBooking("toRequest");
                Intent intent = new Intent(KeeperDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Tag.setTagOnline("");
    }
}