package com.example.cuoiki_android_lythuyet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cuoiki_android_lythuyet.PetListActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.databinding.FragmentHomeBinding;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();

        binding.cvPets.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PetListActivity.class);
            startActivity(intent);
        });
        // Inflate the layout for this fragment

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Tag.setTag("info");
    }
}