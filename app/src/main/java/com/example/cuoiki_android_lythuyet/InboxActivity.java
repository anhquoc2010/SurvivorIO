package com.example.cuoiki_android_lythuyet;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiki_android_lythuyet.adapter.MessageAdapter;
import com.example.cuoiki_android_lythuyet.databinding.ActivityInboxBinding;
import com.example.cuoiki_android_lythuyet.models.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InboxActivity extends AppCompatActivity {

    ActivityInboxBinding binding;

    private String messageRecieverId, getMessageRecievername, messageSenderId;
    private FirebaseAuth mauth;
    private DatabaseReference RootRef;
    private final List<Messages> messagesList = new ArrayList<>();
    private MessageAdapter messageAdapter;

    private String savecurrentTime, savecurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mauth = FirebaseAuth.getInstance();
        messageSenderId = mauth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        messageRecieverId = getIntent().getExtras().get("visit_user_id").toString();
        getMessageRecievername = getIntent().getExtras().get("visit_user_name").toString();

        messageAdapter = new MessageAdapter(messagesList);

        binding.recyclerView.setAdapter(messageAdapter);

        binding.back.setOnClickListener(v -> onBackPressed());

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        savecurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        savecurrentTime = currentTime.format(calendar.getTime());

        binding.name.setText(getMessageRecievername);

        Displaylastseen();

        binding.send.setOnClickListener(v -> SendMessage());

        RootRef.child("Messages").child(messageSenderId).child(messageRecieverId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Messages messages = dataSnapshot.getValue(Messages.class);
                messagesList.add(messages);
                messageAdapter.notifyDataSetChanged();
                binding.recyclerView.smoothScrollToPosition(binding.recyclerView.getAdapter().getItemCount());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Displaylastseen() {
        RootRef.child("Users").child(messageRecieverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("userState").hasChild("state")) {
                    String state = dataSnapshot.child("userState").child("state").getValue().toString();
                    String date = dataSnapshot.child("userState").child("date").getValue().toString();
                    String time = dataSnapshot.child("userState").child("time").getValue().toString();

                    if (state.equals("online")) {
                        binding.dateTime.setText("online");
                    } else if (state.equals("offline")) {
                        binding.dateTime.setText("Last seen: " + date + " " + time);
                    }
                } else {
                    binding.dateTime.setText("offline");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SendMessage() {
        String messagetext = binding.message.getText().toString();
        if (TextUtils.isEmpty(messagetext)) {
            Toast.makeText(this, "Please enter message first..", Toast.LENGTH_SHORT).show();
        } else {
            String messageSenderRef = "Messages/" + messageSenderId + "/" + messageRecieverId;
            String messageReceiverRef = "Messages/" + messageRecieverId + "/" + messageSenderId;

            DatabaseReference Usermessagekeyref = RootRef.child("Messages").child(messageSenderId).child(messageRecieverId).push();
            String messagePushID = Usermessagekeyref.getKey();
            Map messageTextBody = new HashMap();
            messageTextBody.put("message", messagetext);
            messageTextBody.put("type", "text");
            messageTextBody.put("from", messageSenderId);
            messageTextBody.put("to", messageRecieverId);
            messageTextBody.put("messageID", messagePushID);
            messageTextBody.put("time", savecurrentTime);
            messageTextBody.put("date", savecurrentDate);

            Map messageBodyDetails = new HashMap();
            messageBodyDetails.put(messageSenderRef + "/" + messagePushID, messageTextBody);
            messageBodyDetails.put(messageReceiverRef + "/" + messagePushID, messageTextBody);

            RootRef.updateChildren(messageBodyDetails).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Toast.makeText(ChatActivity.this,"Message sent Successfully...",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InboxActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
                binding.message.setText("");
            });
        }
    }
}