package com.example.cuoiki_android_lythuyet.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cuoiki_android_lythuyet.EditProfile;
import com.example.cuoiki_android_lythuyet.LoginActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.adapter.GalleryAdapter;
import com.example.cuoiki_android_lythuyet.adapter.MiniPetAdapter;
import com.example.cuoiki_android_lythuyet.databinding.FragmentProfileBinding;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.example.cuoiki_android_lythuyet.models.SinhVien;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ArrayList<Pet> pets;
    private MiniPetAdapter petAdapter;
    ArrayList<Integer> images;
    GalleryAdapter galleryAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String currentUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        binding.ibLogout.setOnClickListener(v -> {
            //alert dialog
            CharSequence options[] = new CharSequence[]
                    {
                            "Sign out", "Cancel"
                    };

            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Are you sure you want to sign out?");
            builder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    updateUserStatus("offline");
                    firebaseAuth.signOut();
                    sendUserToLoginActivity();
                    Toast.makeText(getActivity(), "Contacts logged out Successfully...", Toast.LENGTH_SHORT).show();
                } else if (which == 1) {
                    //do nothing
                }
            });

            builder.show();
        });

        binding.cvEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfile.class);
            startActivity(intent);
        });

        databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.hasChild("name") && snapshot.hasChild("email") && snapshot.hasChild("image")) {
                    Picasso.get().load(snapshot.child("image").getValue().toString()).into(binding.ivAvatarProfile);
                    binding.tvNameProfile.setText(snapshot.child("name").getValue().toString());
                    binding.tvMailProfile.setText(snapshot.child("email").getValue().toString());
                } else if (snapshot.exists() && snapshot.hasChild("name") && snapshot.hasChild("email")) {
                    binding.tvNameProfile.setText(snapshot.child("name").getValue().toString());
                    binding.tvMailProfile.setText(snapshot.child("email").getValue().toString());
                } else if (snapshot.exists() && snapshot.hasChild("name")) {
                    binding.tvNameProfile.setText(snapshot.child("name").getValue().toString());
                    binding.tvMailProfile.setText(firebaseAuth.getCurrentUser().getEmail());
                } else {
                    Toast.makeText(getActivity(), "Please update your profile information...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addPets();
        addGallery();

        binding.tvPetsCount.setText(getResources().getString(R.string.count, 5));
        binding.tvFriendsCount.setText(getResources().getString(R.string.count, 20));

        petAdapter = new MiniPetAdapter(getContext(), pets);
        binding.rvPets.setAdapter(petAdapter);
        binding.rvPets.setHasFixedSize(true);

        galleryAdapter = new GalleryAdapter(getContext(), images);
        binding.rvGalleryProfile.setAdapter(galleryAdapter);
        binding.rvGalleryProfile.setHasFixedSize(true);

        return binding.getRoot();
    }

    private void addPets() {
        pets = new ArrayList<>();
        pets.add(new Pet("Kileonmusk", "Hasky", true, 6, 45.5, 111.11, "Red", "gaugau", R.drawable.pet1));
        pets.add(new Pet("Shibaxianua", "Chihuahua", false, 12, 100.2, 150.12, "B&W", "grrrrr", R.drawable.pet2));
        pets.add(new Pet("Hanakem Cheese", "Maddog", false, 3, 15.6, 30.77, "White", "angang", R.drawable.pet3));
        pets.add(new Pet("Betting Helloyboy", "Beandog", true, 2, 140.2, 70.2, "Gray", "ecec", R.drawable.pet4));
        pets.add(new Pet("Brusk", "Cherry", false, 1, 140.2, 70.2, "Gray", "kiiiii", R.drawable.pet5));
    }

    private void addGallery() {
        images = new ArrayList<>();
        images.add(R.drawable.pet1);
        images.add(R.drawable.pet2);
        images.add(R.drawable.pet3);
        images.add(R.drawable.pet4);
        images.add(R.drawable.pet5);
        images.add(R.drawable.pet6);
        images.add(R.drawable.pet3);
        images.add(R.drawable.pet1);
        images.add(R.drawable.pet4);
    }

    private void sendUserToLoginActivity() {
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        getActivity().finish();
    }

    private void updateUserStatus(String state) {
        String saveCurrentTime, saveCurrentDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("time", saveCurrentTime);
        hashMap.put("date", saveCurrentDate);
        hashMap.put("state", state);

        currentUserId = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(currentUserId).child("userState").updateChildren(hashMap);
    }
}