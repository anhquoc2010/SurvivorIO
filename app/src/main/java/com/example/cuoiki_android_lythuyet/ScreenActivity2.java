package com.example.cuoiki_android_lythuyet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.databinding.ActivityScreen2Binding;

public class ScreenActivity2 extends AppCompatActivity {

    ActivityScreen2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignup.setOnClickListener(v -> {
            Intent i = new Intent(ScreenActivity2.this, SignupActivity.class);
            startActivity(i);
        });

        binding.btnSignin.setOnClickListener(v -> {
            Intent i = new Intent(ScreenActivity2.this, LoginActivity.class);
            startActivity(i);
        });
    }
}

