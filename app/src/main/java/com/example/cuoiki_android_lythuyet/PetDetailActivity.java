package com.example.cuoiki_android_lythuyet;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.adapter.GalleryAdapter;
import com.example.cuoiki_android_lythuyet.databinding.ActivityPetDetailBinding;
import com.example.cuoiki_android_lythuyet.models.Pet;
import com.example.cuoiki_android_lythuyet.tag.Tag;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class PetDetailActivity extends AppCompatActivity {

    ActivityPetDetailBinding binding;
    ArrayList<Integer> images;
    GalleryAdapter galleryAdapter;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityPetDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Tag.setTagOnline("online");

        getBundle();
        createChips();
        addGallery();

        galleryAdapter = new GalleryAdapter(this, images);
        binding.rvPetGallery.setAdapter(galleryAdapter);
        binding.rvPetGallery.setHasFixedSize(true);

        binding.ibBack.setOnClickListener(v -> onBackPressed());
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            Pet pet = (Pet) bundle.getSerializable("pet");
            binding.tvPetName.setText(pet.getName());
            binding.tvPetSpeciesAge.setText(getResources().getString(R.string.species_age, pet.getSpecies(), pet.getAge()));
            binding.tvPetDescription.setText(pet.getDescription());
            binding.ivBackgroundPetDetail.setImageResource(pet.getImageResourceID());
            if (pet.isGender()) {
                binding.ivPetGender.setImageResource(R.drawable.male_black_24dp);
            } else {
                binding.ivPetGender.setImageResource(R.drawable.female_black_24dp);
            }
            binding.tvPetWeight.setText(getResources().getString(R.string.weight_value, pet.getWeight()));
            binding.tvPetHeight.setText(getResources().getString(R.string.height_value, pet.getHeight()));
            binding.tvPetColor.setText(getResources().getString(R.string.color_value, pet.getColor()));
        }
    }

    private void createChips() {
        String[] behaviors = {"Playful", "Shy", "Loyal", "Active", "Calm", "Independent", "Affectionate", "Intelligent", "Protective", "Loving", "Energetic", "Gentle", "Adventurous", "Lively", "Sweet", "Cute", "Cuddly", "Funny", "Lovable", "Affectionate"};

        for (String behavior : behaviors) {
            Chip chip = new Chip(this);
            chip.setText(behavior);
            binding.chipGroupPetBehavior.addView(chip);
        }
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
        images.add(R.drawable.pet5);
        images.add(R.drawable.pet1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Tag.setTagOnline("");
    }
}