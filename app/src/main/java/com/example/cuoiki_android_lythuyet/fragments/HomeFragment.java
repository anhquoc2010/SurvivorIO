package com.example.cuoiki_android_lythuyet.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuoiki_android_lythuyet.CreatePetActivity;
import com.example.cuoiki_android_lythuyet.PetListActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.cvPets.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PetListActivity.class);
            startActivity(intent);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}