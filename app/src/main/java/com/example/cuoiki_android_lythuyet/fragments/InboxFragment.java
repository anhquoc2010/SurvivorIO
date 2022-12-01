package com.example.cuoiki_android_lythuyet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cuoiki_android_lythuyet.databinding.FragmentInboxBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InboxFragment extends Fragment {

    private FragmentInboxBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInboxBinding.inflate(inflater, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();


        // Inflate the layout for this fragment
        return binding.getRoot();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
//                .setQuery(databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()), String.class)
//                .build();
//
//        FirebaseRecyclerAdapter<String, InboxViewHolder> adapter = new FirebaseRecyclerAdapter<String, InboxViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull InboxViewHolder holder, int position, @NonNull String model) {
//                final String userIDs = getRef(position).getKey();
//                final String[] retImage = {"default_image"};
//                databaseReference.child("Users").child(userIDs).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()) {
//                            firebaseAuth.get
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                })
//            }
//
//            @NonNull
//            @Override
//            public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//        };
//    }
}