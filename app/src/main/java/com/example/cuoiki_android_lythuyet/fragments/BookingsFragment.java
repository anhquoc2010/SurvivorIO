package com.example.cuoiki_android_lythuyet.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.KeeperDetailActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.RequestDetail;
import com.example.cuoiki_android_lythuyet.adapter.BookingAdapter;
import com.example.cuoiki_android_lythuyet.databinding.FragmentBookingsBinding;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookingsFragment extends Fragment {
    
    FragmentBookingsBinding binding;
    private DatabaseReference bookingRef, userRef;
    private FirebaseAuth mAuth;
    private String userID; 
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBookingsBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        bookingRef = FirebaseDatabase.getInstance().getReference().child("Bookings");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        FirebaseRecyclerOptions<Bookings> options = new FirebaseRecyclerOptions.Builder<Bookings>()
                .setQuery(bookingRef, Bookings.class).build();

        FirebaseRecyclerAdapter<Bookings, BookingViewHolder> adapter = new FirebaseRecyclerAdapter<Bookings, BookingViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final BookingViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Bookings model) {
                bookingRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot databkSnapshot) {
                        final String bookingID = getRef(position).getKey();
                        Log.d("lamon", "onBindViewHolder: " + bookingID);
                        Log.d("lamon", "onBindViewHolder: " + userID);
                        Log.d("lamon", "onBindViewHolder: " + databkSnapshot.child(bookingID).child("userReceive").getValue());
                        holder.status.setText(databkSnapshot.child(bookingID).child("status").getValue().toString());
                        holder.date.setText(databkSnapshot.child(bookingID).child("calendar").getValue().toString());
                        holder.price.setText(databkSnapshot.child(bookingID).child("price").getValue().toString());
                        if (databkSnapshot.child(bookingID).child("userReceive").getValue().toString().equals(userID)) {
                            final String userReceiveId = databkSnapshot.child(bookingID).child("userReceive").getValue().toString();
                            final String[] image = {"default_image"};
                            userRef.child(userReceiveId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Log.d("hehehe", "onDataChange: " + dataSnapshot.hasChild("image"));
                                        if (dataSnapshot.hasChild("image")) {
                                            image[0] = dataSnapshot.child("image").getValue().toString();
                                            Picasso.get().load(image[0]).placeholder(R.drawable.avt2).into(holder.profile_image);
                                        } else {
                                            holder.profile_image.setImageResource(R.drawable.avt2);
                                        }
                                        final String name = dataSnapshot.child("name").getValue().toString();

                                        holder.username.setText(name);

                                        holder.itemView.setOnClickListener(v -> {
                                            Intent keeperIntent = new Intent(getActivity(), RequestDetail.class);
                                            keeperIntent.putExtra("visit_user_id", userReceiveId);
                                            keeperIntent.putExtra("visit_user_name", name);
                                            keeperIntent.putExtra("visit_image", image[0]);
                                            keeperIntent.putExtra("bookings", model);
                                            startActivity(keeperIntent);
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {
                            holder.itemView.setVisibility(View.GONE);
                            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                            params.height = 0;
                            params.width = 0;
                            holder.itemView.setLayoutParams(params);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public BookingsFragment.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.owner_request_row_item, parent, false);
                return new BookingsFragment.BookingViewHolder(view);
            }
        };

        binding.rcvBooking.setAdapter(adapter);
        adapter.startListening();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView name, username, status, date, response, price;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.tv_status_item);
            date = itemView.findViewById(R.id.tvCalendar);
            profile_image = itemView.findViewById(R.id.img_request_booking);
            username = itemView.findViewById(R.id.tvName);
            response = itemView.findViewById(R.id.tvResponse);
            price = itemView.findViewById(R.id.tvPrice);
        }
    }
    
//    private List<Booking> getListBooking(){
//        List<Booking> listBookings = new ArrayList<>();
//        listBookings.add(new Booking("Ho Quang Phuc", R.drawable.avt4, 50.0, 12,"10/10/2022 - 9.00", "Pending"));
//        listBookings.add(new Booking("Huynh Tan Luc", R.drawable.avt2, 60.0, 15,"16/10/2022 - 8.00", "Confirmed"));
//        listBookings.add(new Booking("Le Anh Quoc", R.drawable.avt3, 70.0, 8,"5/11/2022 - 10.00", "Confirmed"));
//        listBookings.add(new Booking("Tran Thi Kim Ngan", R.drawable.avt1, 80.0, 9,"15/11/2022 - 9.00", "Pending"));
//        listBookings.add(new Booking("Tran Duc Manh", R.drawable.avt5, 90.0, 10,"17/12/2022 - 9.00", "Pending"));
//
//        return listBookings;
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (bookingAdapter != null)
//            bookingAdapter.release();
//    }
}