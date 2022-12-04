package com.example.cuoiki_android_lythuyet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.adapter.BookingAdapter;
import com.example.cuoiki_android_lythuyet.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingsFragment extends Fragment {
    private RecyclerView rcvBooking;
    private BookingAdapter bookingAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        rcvBooking = view.findViewById(R.id.rcvBooking);
        bookingAdapter = new BookingAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvBooking.setLayoutManager(linearLayoutManager);

        bookingAdapter.setData(getListBooking());
        rcvBooking.setAdapter(bookingAdapter);

        // Inflate the layout for this fragment
        return view;
    }
    private List<Booking> getListBooking(){
        List<Booking> listBookings = new ArrayList<>();
        listBookings.add(new Booking("Phuc", R.drawable.ahinhanhdemo, 70.0, 8,"10/12/2022 - 10.00", "Pending"));
        listBookings.add(new Booking("Luc", R.drawable.ahinhanhdemo, 70.0, 8,"10/12/2022 - 10.00", "Confirmed"));
        listBookings.add(new Booking("Quoc", R.drawable.ahinhanhdemo, 70.0, 8,"10/12/2022 - 10.00", "Confirmed"));
        listBookings.add(new Booking("Ngan", R.drawable.ahinhanhdemo, 70.0, 8,"10/12/2022 - 10.00", "Pending"));
        listBookings.add(new Booking("Manh", R.drawable.ahinhanhdemo, 70.0, 8,"10/12/2022 - 10.00", "Pending"));

        return listBookings;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bookingAdapter != null)
            bookingAdapter.release();
    }
}