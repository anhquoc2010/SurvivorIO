package com.example.cuoiki_android_lythuyet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cuoiki_android_lythuyet.PetListActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.adapter.MiniPetAdapter;
import com.example.cuoiki_android_lythuyet.databinding.FragmentHomeBinding;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private ArrayList<Pet> pets;
    private MiniPetAdapter petAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        binding.cvPets.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PetListActivity.class);
            startActivity(intent);
        });

        binding.cvPeople.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Chức năng đang được phát triển", Toast.LENGTH_SHORT).show();
        });

        binding.cvVerifications.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Chức năng đang được phát triển", Toast.LENGTH_SHORT).show();
        });

        binding.cvLocation.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Chức năng đang được phát triển", Toast.LENGTH_SHORT).show();
        });

        databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.hasChild("name")) {
                    binding.textHome.setText("⛅ Hello " + snapshot.child("name").getValue().toString() + "✌");
                } else {
                    Toast.makeText(getActivity(), "Please update your profile information...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Inflate the layout for this fragment

        addPets();

        petAdapter = new MiniPetAdapter(getContext(), pets);
        binding.rvPets.setAdapter(petAdapter);
        binding.rvPets.setHasFixedSize(true);

        listQuotes();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Tag.setTag("info");
    }

    //list quotes
    private void listQuotes() {
        String[] quotes = getResources().getStringArray(R.array.quotes);
        int random = (int) (Math.random() * quotes.length);
        binding.quoteText.setText(quotes[random]);
    }

    private void addPets() {
        pets = new ArrayList<>();
        pets.add(new Pet("Kileonmusk", "Hasky", true, 6, 45.5, 111.11, "Red", "gaugau", R.drawable.pet1));
        pets.add(new Pet("Shibaxianua", "Chihuahua", false, 12, 100.2, 150.12, "B&W", "grrrrr", R.drawable.pet2));
        pets.add(new Pet("Hanakem Cheese", "Maddog", false, 3, 15.6, 30.77, "White", "angang", R.drawable.pet3));
        pets.add(new Pet("Betting Helloyboy", "Beandog", true, 2, 140.2, 70.2, "Gray", "ecec", R.drawable.pet4));
        pets.add(new Pet("Brusk", "Cherry", false, 1, 140.2, 70.2, "Orange", "kiiiii", R.drawable.pet5));
        pets.add(new Pet("Kinding", "Calmdog", true, 3, 140.2, 70.2, "Green", "kaka", R.drawable.pet6));
        pets.add(new Pet("Hem", "England Short", true, 4, 140.2, 70.2, "Light Yellow", "hihi", R.drawable.pet3));
    }
}