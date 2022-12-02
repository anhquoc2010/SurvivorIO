package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cuoiki_android_lythuyet.data.MemoryData;
import com.example.cuoiki_android_lythuyet.databinding.ActivitySettingProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SettingProfileActivity extends AppCompatActivity {

    ActivitySettingProfileBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogout.setOnClickListener(v -> {
            MemoryData.clearData(SettingProfileActivity.this);
            firebaseAuth.signOut();
            Intent intent = new Intent(SettingProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}