package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuoiki_android_lythuyet.fragments.InboxFragment;
import com.example.cuoiki_android_lythuyet.models.Booking;
import com.google.android.material.button.MaterialButton;
import com.google.type.Color;
import com.google.type.ColorOrBuilder;

public class RequestDetail extends AppCompatActivity {
    ImageView imgbtnBack;
    MaterialButton btncancle, btnConfirm;
    TextView tvName, tvPrice, tvStatus, tvMessage;
    ImageView imgDetail, imgMessage;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);

        tvName = findViewById(R.id.tvName_request_detail);
        tvPrice = findViewById(R.id.tv_price_detail);
        tvStatus = findViewById(R.id.tv_status);
        tvMessage = findViewById(R.id.tv_message);
        imgDetail = findViewById(R.id.imgv_detail);
        imgMessage = findViewById(R.id.imgv_message);
        btncancle = findViewById(R.id.btn_cancle);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Booking booking = (Booking) bundle.get("object_booking");

        imgbtnBack = findViewById(R.id.img_btn_back);
        imgbtnBack.setOnClickListener(view ->{
            onBackPressed();
        });
        tvName.setText(booking.getName());
        tvPrice.setText(booking.getPrice()+" $");
        tvStatus.setText(booking.getStatus());
        imgDetail.setImageResource(booking.getImgBooking());
        String status = booking.getStatus();
        if (status.equals("Pending")){
            tvStatus.setHighlightColor(R.color.yellowsoft);
        }else{
            tvStatus.setBackgroundResource(R.color.greensoft);
        }
        btncancle.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}