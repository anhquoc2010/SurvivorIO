package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cuoiki_android_lythuyet.fragments.HomeFragment;
import com.example.cuoiki_android_lythuyet.models.Booking;

public class KeeperDetailActivity extends AppCompatActivity {
    Button btn_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeper_detail);

        btn_book = (Button) findViewById(R.id.btn_book);

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KeeperDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }
}