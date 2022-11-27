package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.cuoiki_android_lythuyet.databinding.ActivityMainBinding;
import com.example.cuoiki_android_lythuyet.fragments.BookingFragment;
import com.example.cuoiki_android_lythuyet.fragments.HomeFragment;
import com.example.cuoiki_android_lythuyet.fragments.InboxFragment;
import com.example.cuoiki_android_lythuyet.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}