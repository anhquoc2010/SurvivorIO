package com.example.cuoiki_android_lythuyet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cuoiki_android_lythuyet.adapter.KeeperAdapter;
import com.example.cuoiki_android_lythuyet.databinding.ActivityListKeeperSavedBinding;
import com.example.cuoiki_android_lythuyet.models.Keeper;

import java.util.ArrayList;

public class ListKeeperSaved extends AppCompatActivity {

    private ActivityListKeeperSavedBinding binding;
    private ArrayList<Keeper> keepers;
    private KeeperAdapter keeperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListKeeperSavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addKeepers();
        keeperAdapter = new KeeperAdapter(this, keepers);
        binding.lvKeeperSaved.setAdapter(keeperAdapter);
        binding.lvKeeperSaved.setHasFixedSize(true);
    }

    private void addKeepers() {
        keepers = new ArrayList<>();
        keepers.add(new Keeper(R.drawable.pet1,"Lực", "10km away", 5, "6 preview", 45));
        keepers.add(new Keeper(R.drawable.pet1,"Ngân", "10km away", 5, "6 preview", 45));
        keepers.add(new Keeper(R.drawable.pet1,"Mạnh", "10km away", 5, "6 preview", 45));
        keepers.add(new Keeper(R.drawable.pet1,"Quốc", "10km away", 5, "6 preview", 45));
        keepers.add(new Keeper(R.drawable.pet1,"Phúc", "10km away", 5, "6 preview", 45));
    }
}