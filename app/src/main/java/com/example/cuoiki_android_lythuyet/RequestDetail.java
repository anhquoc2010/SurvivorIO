package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuoiki_android_lythuyet.databinding.ActivityRequestDetailBinding;
import com.example.cuoiki_android_lythuyet.fragments.InboxFragment;
import com.example.cuoiki_android_lythuyet.models.Booking;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.google.android.material.button.MaterialButton;
import com.google.type.Color;
import com.google.type.ColorOrBuilder;
import com.squareup.picasso.Picasso;

public class RequestDetail extends AppCompatActivity {

    ActivityRequestDetailBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String name = intent.getStringExtra("visit_user_name");
        String image = intent.getStringExtra("visit_image");
        Bookings bookings = (Bookings) intent.getSerializableExtra("bookings");

        binding.imgBtnBack.setOnClickListener(view ->{
            onBackPressed();
        });
        binding.tvPriceDetail.setText(bookings.getPrice()+" $");
        binding.textView7.setText(bookings.getName());
        binding.textView10.setText(bookings.getPet());
        Picasso.get().load(image).placeholder(R.drawable.avt3).into(binding.imgvDetail);
        binding.tvNameRequestDetail.setText(name);
        binding.tvStatus.setText(bookings.getStatus());
        binding.tvCalendar.setText(bookings.getCalendar());
        String status = bookings.getStatus();
        if (status.equals("Pending")){
            binding.tvStatus.setHighlightColor(R.color.yellowsoft);
        }else{
            binding.tvStatus.setBackgroundResource(R.color.greensoft);
        }
        binding.btnCancle.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}