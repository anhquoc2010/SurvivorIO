package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.cuoiki_android_lythuyet.adapter.PetAdapter;
import com.example.cuoiki_android_lythuyet.databinding.ActivityPetListBinding;
import com.example.cuoiki_android_lythuyet.models.Pet;

import java.util.ArrayList;

public class PetListActivity extends AppCompatActivity {

    private ActivityPetListBinding binding;
    private ArrayList<Pet> pets;
    private PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addPets();

        petAdapter = new PetAdapter(this, pets);
        binding.rvPet.setAdapter(petAdapter);
        binding.rvPet.setHasFixedSize(true);
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