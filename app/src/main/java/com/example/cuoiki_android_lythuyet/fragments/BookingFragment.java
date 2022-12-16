package com.example.cuoiki_android_lythuyet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.databinding.FragmentBookingBinding;
import com.example.cuoiki_android_lythuyet.tag.Tag;

public class BookingFragment extends Fragment {

    FragmentBookingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBookingBinding.inflate(inflater, container, false);

        if (Tag.getTagBooking().equals("toRequest")) {
            binding.topNavigation.setSelectedItemId(R.id.requests_page);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RequestsFragment())
                    .addToBackStack(null)
                    .commit();
            Tag.setTagBooking("");
        } else {
            binding.topNavigation.setSelectedItemId(R.id.bookings_page);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new BookingsFragment())
                    .addToBackStack(null)
                    .commit();
        }

        binding.topNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bookings_page:
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new BookingsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.requests_page:
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new RequestsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        });

        binding.topNavigation.setOnItemReselectedListener(item -> {
            // Handle navigation item reselection
            switch (item.getItemId()) {
                case R.id.bookings_page:
                case R.id.requests_page:
                    break;
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}