package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cuoiki_android_lythuyet.databinding.ActivityScreen2Binding;

public class ScreenActivity2 extends AppCompatActivity {

    ActivityScreen2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignup.setOnClickListener(v -> {
            Intent i = new Intent(ScreenActivity2.this,SignupActivity.class);
            startActivity(i);
        });

        binding.btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(ScreenActivity2.this,LoginActivity.class);
            startActivity(i);
        });
    }
}

