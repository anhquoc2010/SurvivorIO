package com.example.cuoiki_android_lythuyet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.InboxActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.databinding.FragmentInboxBinding;
import com.example.cuoiki_android_lythuyet.models.Contacts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InboxFragment extends Fragment {

    private FragmentInboxBinding binding;
    private DatabaseReference chatRef, userRef;
    private FirebaseAuth mAuth;
    private String userID;

    public InboxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInboxBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        chatRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Contacts> options = new FirebaseRecyclerOptions.Builder<Contacts>()
                .setQuery(chatRef.orderByChild("name"), Contacts.class).build();

        FirebaseRecyclerAdapter<Contacts, ChatViewHolder> adapter = new FirebaseRecyclerAdapter<Contacts, ChatViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ChatViewHolder holder, int position, @NonNull Contacts model) {
                if (!getRef(position).getKey().equals(userID)) {
                    final String userid = getRef(position).getKey();
                    final String[] image = {"default_image"};
                    userRef.child(userid).addValueEventListener(new ValueEventListener() {
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
                                holder.userstatus.setText("Last seen: " + "\n" + "Date " + " Time");

                                if (dataSnapshot.child("userState").hasChild("state")) {
                                    String state = dataSnapshot.child("userState").child("state").getValue().toString();
                                    String date = dataSnapshot.child("userState").child("date").getValue().toString();
                                    String time = dataSnapshot.child("userState").child("time").getValue().toString();

                                    if (state.equals("online")) {
                                        holder.userstatus.setText("online");
                                    } else if (state.equals("offline")) {
                                        holder.userstatus.setText("Last seen: " + "\n" + date + " " + time);
                                    }
                                } else {
                                    holder.userstatus.setText("offline");
                                }

                                holder.itemView.setOnClickListener(v -> {
                                    Intent chatIntent = new Intent(getContext(), InboxActivity.class);
                                    chatIntent.putExtra("visit_user_id", userid);
                                    chatIntent.putExtra("visit_user_name", name);
                                    chatIntent.putExtra("visit_image", image[0]);
                                    startActivity(chatIntent);
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    holder.itemView.setVisibility(View.GONE);
                    holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }

            @NonNull
            @Override
            public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.inbox_item, parent, false);
                return new ChatViewHolder(view);
            }
        };

        binding.rvInbox.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView username, userstatus;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.iv_avatar);
            username = itemView.findViewById(R.id.tv_name);
            userstatus = itemView.findViewById(R.id.tv_message);
        }
    }
}