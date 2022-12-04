package com.example.cuoiki_android_lythuyet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {

    FragmentBookingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBookingBinding.inflate(inflater, container, false);

        getChildFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.fragment_container, new BookingsFragment())
                .addToBackStack(null)
                .commit();

        binding.topNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bookings_page:
                    getChildFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new BookingsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.requests_page:
                    getChildFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            )
                            .replace(R.id.fragment_container, new RequestsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}