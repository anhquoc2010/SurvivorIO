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

import com.example.cuoiki_android_lythuyet.PetListActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.RequestDetail;
import com.example.cuoiki_android_lythuyet.databinding.FragmentRequestsBinding;
import com.example.cuoiki_android_lythuyet.models.Bookings;
import com.example.cuoiki_android_lythuyet.tag.Tag;
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

public class RequestsFragment extends Fragment {

    FragmentRequestsBinding binding;
    private DatabaseReference bookingRef, userRef;
    private FirebaseAuth mAuth;
    private String userID;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRequestsBinding.inflate(inflater, container, false);

        binding.progressBar.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        bookingRef = FirebaseDatabase.getInstance().getReference().child("Bookings");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        binding.btnCreateRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetListActivity.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();

        Tag.setTag("request");

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
                        Log.d("lamon", "onBindViewHolder: " + databkSnapshot.child(bookingID).child("userSend").getValue());
                        if (databkSnapshot.child(bookingID).child("userSend").getValue().toString().equals(userID)) {
                            count++;
                            final String userSendId = databkSnapshot.child(bookingID).child("userSend").getValue().toString();
                            final String userReceiveId = databkSnapshot.child(bookingID).child("userReceive").getValue().toString();
                            final String[] image = {"default_image"};
                            holder.status.setText(databkSnapshot.child(bookingID).child("status").getValue().toString());
                            holder.date.setText(databkSnapshot.child(bookingID).child("calendar").getValue().toString());
                            holder.price.setText(databkSnapshot.child(bookingID).child("price").getValue().toString());

                            userRef.child(userSendId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        final String name = dataSnapshot.child("name").getValue().toString();

                                        holder.user_send_name.setText(name);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            userRef.child(userReceiveId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Log.d("hehehe", "onDataChange: " + dataSnapshot.hasChild("image"));
                                        if (dataSnapshot.hasChild("image")) {
                                            image[0] = dataSnapshot.child("image").getValue().toString();
                                            Picasso.get().load(image[0]).placeholder(R.drawable.pet2).into(holder.profile_image);
                                        } else {
                                            holder.profile_image.setImageResource(R.drawable.pet2);
                                        }
                                        final String name = dataSnapshot.child("name").getValue().toString();

                                        holder.username.setText(name);

                                        holder.itemView.setOnClickListener(v -> {
                                            Intent keeperIntent = new Intent(getContext(), RequestDetail.class);
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
                            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
                            params.height = 0;
                            params.width = 0;
                            params.setMargins(0, 0, 0, 0);
                            holder.itemView.setLayoutParams(params);
                            if (count == 0) {
                                binding.tvNotification.setVisibility(View.VISIBLE);
                            } else {
                                binding.tvNotification.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                binding.progressBar.setVisibility(View.GONE);
            }

            @NonNull
            @Override
            public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.owner_request_row_item, parent, false);
                return new BookingViewHolder(view);
            }
        };

        binding.rcvOwnerRequest.setAdapter(adapter);
        adapter.startListening();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView name, username, status, date, price, user_send_name;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.tv_status_item);
            date = itemView.findViewById(R.id.tvCalendar);
            profile_image = itemView.findViewById(R.id.img_request_booking);
            username = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            user_send_name = itemView.findViewById(R.id.tvResponse);
        }
    }

}