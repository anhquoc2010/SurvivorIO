package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class CreateBehaviorPetActivity extends AppCompatActivity {
    ImageView btn_back;
    MaterialButton btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_behavior_pet);
        btn_back = findViewById(R.id.btn_back_to_createpet);
        btn_next = findViewById(R.id.btn_next_add_BehaviorCare);
        btn_back.setOnClickListener(view->{
            startActivity(new Intent(CreateBehaviorPetActivity.this, CreatePetActivity.class));
        });
        btn_next.setOnClickListener(view->{
            startActivity(new Intent(CreateBehaviorPetActivity.this, PetDetailActivity.class));
        });
    }
}