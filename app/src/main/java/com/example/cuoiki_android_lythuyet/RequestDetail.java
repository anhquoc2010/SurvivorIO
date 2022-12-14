package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.cuoiki_android_lythuyet.databinding.ActivityRequestDetailBinding;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.squareup.picasso.Picasso;

public class RequestDetail extends AppCompatActivity {

    ActivityRequestDetailBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
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