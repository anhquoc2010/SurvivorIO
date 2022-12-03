package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.cuoiki_android_lythuyet.models.Booking;

public class RequestDetail extends AppCompatActivity {
    ImageView imgbtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);


        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Booking booking = (Booking) bundle.get("object_booking");

        imgbtnBack = findViewById(R.id.img_btn_back);
        imgbtnBack.setOnClickListener(view ->{
            onBackPressed();
        });
    }
}