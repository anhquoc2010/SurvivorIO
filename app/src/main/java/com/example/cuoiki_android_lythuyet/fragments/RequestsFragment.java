package com.example.cuoiki_android_lythuyet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.adapter.BookingAdapter;
import com.example.cuoiki_android_lythuyet.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment {
    private RecyclerView rcvBooking;
    private BookingAdapter bookingAdapter;
    LinearLayout itemRequest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        rcvBooking = view.findViewById(R.id.rcvOwnerRequest);

        bookingAdapter = new BookingAdapter(getContext(), getListBooking());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvBooking.setLayoutManager(linearLayoutManager);
        rcvBooking.setAdapter(bookingAdapter);

        // Inflate the layout for this fragment
        return view;
    }
    private List<Booking> getListBooking(){
        List<Booking> listBookings = new ArrayList<>();
        listBookings.add(new Booking("Ho Quang Phuc", R.drawable.avt4, 120.0, 8,"8/10/2022 - 10.00"));
        listBookings.add(new Booking("Huynh Tan Luc", R.drawable.avt2, 100.0, 8,"16/10/2022 - 8.00"));
        listBookings.add(new Booking("Le Anh Quoc", R.drawable.avt3, 110.0, 8,"6/11/2022 - 10.00"));
        listBookings.add(new Booking("Tran Thi Kim Ngan", R.drawable.avt1, 90.0, 8,"21/11/2022 - 9.00"));
        listBookings.add(new Booking("Tran Duc Manh", R.drawable.avt5, 90.0, 8,"19/12/2022 - 8.00"));

        return listBookings;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bookingAdapter != null)
            bookingAdapter.release();
    }
}