package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CreatePetActivity extends AppCompatActivity {
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);
        btnNext = findViewById(R.id.btn_next_behaviorActi);
        btnNext.setOnClickListener(view ->{
            startActivity(new Intent(CreatePetActivity.this, CreateBehaviorPetActivity.class));
        });
    }
}