package com.example.cuoiki_android_lythuyet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cuoiki_android_lythuyet.data.MemoryData;
import com.example.cuoiki_android_lythuyet.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!MemoryData.getData(this).isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("uid", MemoryData.getData(this));
            startActivity(intent);
            finish();
        }

        binding.btnLogin.setOnClickListener(v -> loginUser(binding.editTextUsername.getText().toString(), binding.editTextPasswork.getText().toString()));

        binding.btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String textEmail, String textPassword) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(LoginActivity.this, task -> {
            if (task.isSuccessful()) {
                MemoryData.saveData(auth.getUid(), LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Log.w("TAG", "loginWithEmail:failure", task.getException());
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}