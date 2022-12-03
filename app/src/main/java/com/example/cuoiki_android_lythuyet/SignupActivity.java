package com.example.cuoiki_android_lythuyet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        binding.btnSignin.setOnClickListener(v -> SendUserToLoginActivity());

        binding.buttonSignup.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            String email = binding.editTextEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String username = binding.editTextUsername.getText().toString();

            //check sign up username, password, email, retype password, size password, email valid
            if (TextUtils.isEmpty(email)) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Please enter email...", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Please enter password...", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(username)) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Please enter username...", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        binding.progressBar.setVisibility(View.GONE);
                        String deviceToken = FirebaseMessaging.getInstance().getToken().toString();
                        String currentUserID = firebaseAuth.getCurrentUser().getUid();
                        RootRef.child("Users").child(currentUserID).setValue("");
                        RootRef.child("Users").child(currentUserID).child("device_token").setValue(deviceToken)
                                .addOnCompleteListener(task1 -> {
                                    SendUserToMainActivity();
                                    Toast.makeText(SignupActivity.this, "Account created Successfully...", Toast.LENGTH_SHORT).show();
                                });
                        RootRef.child("Users").child(currentUserID).child("name").setValue(username);
                        RootRef.child("Contacts").child(currentUserID).child("name").setValue(username);
                        RootRef.child("Contacts").child(currentUserID).child("status").setValue("");
                        RootRef.child("Contacts").child(currentUserID).child("image").setValue("");

                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        String errorMessage = task.getException().toString();
                        Toast.makeText(SignupActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(SignupActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}