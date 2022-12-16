package com.example.cuoiki_android_lythuyet;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.databinding.ActivityEditProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    StorageReference userprofilestoragereference;
    String photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setVisibility(View.VISIBLE);

        ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        // There are no request codes
                        Log.d("hehehe", "onActivityResult: " + result.getData().getData());
                        Uri uri = result.getData().getData();
                        Log.d("hehehe", "onActivityResult: " + uri);
                        StorageReference filepath = userprofilestoragereference.child(firebaseAuth.getCurrentUser().getUid() + ".jpg");
                        Log.d("hehehe", "onActivityResult: " + filepath);
                        filepath.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                            final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                            firebaseUri.addOnSuccessListener(uri1 -> {
                                final String downloadUrl = uri1.toString();
                                databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("image")
                                        .setValue(downloadUrl).addOnCompleteListener((OnCompleteListener<Void>) task -> {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(EditProfile.this, "Image saved in database successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                String message = task.getException().toString();
                                                Toast.makeText(EditProfile.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            });
                        });
                    }
                });

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userprofilestoragereference = FirebaseStorage.getInstance().getReference().child("Profile Image");

        binding.buttonSignup.setOnClickListener(v -> UpdateSettings());

        binding.imageView5.setOnClickListener(v -> {
            onBackPressed();
        });

        RetrieveUserInfo();

        binding.imageView19.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            Intent galleryIntent = new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult.launch(galleryIntent);
        });
    }

    private void RetrieveUserInfo() {
        databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists() && dataSnapshot.hasChild("name") && dataSnapshot.hasChild("image") && dataSnapshot.hasChild("email")) {
                    String retrieveusername = dataSnapshot.child("name").getValue().toString();
                    String retrieveuseremail = dataSnapshot.child("email").getValue().toString();
                    String retrieveuserimage = dataSnapshot.child("image").getValue().toString();

                    photoUri = retrieveuserimage;
                    binding.editTextUsername.setText(retrieveusername);
                    binding.editTextEmail.setText(retrieveuseremail);
                    Log.d("1", retrieveuserimage);
                    Picasso.get().load(retrieveuserimage).placeholder(R.drawable.pet2).into(binding.imageView19);
                    Log.d("2", String.valueOf(binding.imageView19));
                } else if (dataSnapshot.exists() && dataSnapshot.hasChild("name") && dataSnapshot.hasChild("email")) {
                    String retrieveusername = dataSnapshot.child("name").getValue().toString();
                    String retrieveuseremail = dataSnapshot.child("email").getValue().toString();

                    binding.editTextUsername.setText(retrieveusername);
                    binding.editTextEmail.setText(retrieveuseremail);
                } else if (dataSnapshot.exists() && dataSnapshot.hasChild("name")) {
                    String retrieveusername = dataSnapshot.child("name").getValue().toString();

                    binding.editTextUsername.setText(retrieveusername);
                } else {
                    Toast.makeText(EditProfile.this, "Please update your profile information", Toast.LENGTH_SHORT).show();
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void UpdateSettings() {
        String setusername = binding.editTextUsername.getText().toString();

        if (TextUtils.isEmpty(setusername)) {
            Toast.makeText(EditProfile.this, "Please write your username first...", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", firebaseAuth.getCurrentUser().getUid());
            profileMap.put("name", setusername);
            profileMap.put("image", photoUri);
            databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).updateChildren(profileMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(EditProfile.this, "Your profile has been updated...", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    String errormessage = task.getException().toString();
                    Toast.makeText(EditProfile.this, "Error: " + errormessage, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RetrieveUserInfo();
    }
}