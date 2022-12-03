package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.cuoiki_android_lythuyet.databinding.ActivityMainBinding;
import com.example.cuoiki_android_lythuyet.fragments.BookingFragment;
import com.example.cuoiki_android_lythuyet.fragments.HomeFragment;
import com.example.cuoiki_android_lythuyet.fragments.InboxFragment;
import com.example.cuoiki_android_lythuyet.fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.fragment_container, new HomeFragment())
                .addToBackStack(null)
                .commit();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_page:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.booking_page:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new BookingFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.inbox_page:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new InboxFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.profile_page:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        });

        binding.bottomNavigation.setOnItemReselectedListener(item -> {
            // Handle navigation item reselection
            switch (item.getItemId()) {
                case R.id.home_page:
                case R.id.booking_page:
                case R.id.inbox_page:
                case R.id.profile_page:
                    break;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            sendUserToStartActivity();
        } else {
            updateUserStatus("online");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            updateUserStatus("offline");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            updateUserStatus("offline");
        }
    }

    private void sendUserToStartActivity() {
        Intent loginIntent = new Intent(MainActivity.this, ScreenActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    private void updateUserStatus(String state) {
        String saveCurrentTime, saveCurrentDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("time", saveCurrentTime);
        hashMap.put("date", saveCurrentDate);
        hashMap.put("state", state);

        currentUserId = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(currentUserId).child("userState").updateChildren(hashMap);
    }
}