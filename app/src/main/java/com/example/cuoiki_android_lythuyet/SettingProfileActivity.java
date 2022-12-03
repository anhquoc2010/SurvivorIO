package com.example.cuoiki_android_lythuyet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
            firebaseAuth.signOut();
            Intent intent = new Intent(SettingProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}