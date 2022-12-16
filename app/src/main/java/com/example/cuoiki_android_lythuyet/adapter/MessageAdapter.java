package com.example.cuoiki_android_lythuyet.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.models.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> UserMessageList;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;

    public MessageAdapter(List<Messages> UserMessageList) {
        this.UserMessageList = UserMessageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        mAuth = FirebaseAuth.getInstance();

        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String messagesenderid = mAuth.getCurrentUser().getUid();
        Messages messages = UserMessageList.get(position);

        String fromuserid = messages.getFrom();
        String frommessagetype = messages.getType();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fromuserid);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("image")) {
                    String receiverprofileimage = dataSnapshot.child("image").getValue().toString();
                    Picasso.get().load(receiverprofileimage).placeholder(R.drawable.pet2).into(holder.receiverprofileimage);
                } else {
                    holder.receiverprofileimage.setImageResource(R.drawable.pet2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.receivermessagetext.setVisibility(View.GONE);
        holder.receiverprofileimage.setVisibility(View.GONE);
        holder.sendermessagetext.setVisibility(View.GONE);
        if (frommessagetype.equals("text")) {
            if (fromuserid.equals(messagesenderid)) {
                holder.sendermessagetext.setVisibility(View.VISIBLE);
                holder.sendermessagetext.setText(messages.getMessage() + "\n \n" + messages.getTime() + " - " + messages.getDate());
            } else {
                holder.receivermessagetext.setVisibility(View.VISIBLE);
                holder.receiverprofileimage.setVisibility(View.VISIBLE);
                holder.receivermessagetext.setText(messages.getMessage() + "\n \n" + messages.getTime() + " - " + messages.getDate());
            }
        }

        if (fromuserid.equals(messagesenderid)) {
            holder.sendermessagetext.setOnLongClickListener(v -> {
                if (UserMessageList.get(position).getType().equals("text")) {
                    CharSequence options[] = new CharSequence[]
                            {
                                    "Delete for me", "Delete for everyone", "Cancel"
                            };

                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                    builder.setTitle("Delete Message?");
                    builder.setItems(options, (dialog, which) -> {
                        if (which == 0) {
                            deleteSentMessage(position, holder);
                        } else if (which == 1) {
                            deleteMessageForEveryone(position, holder);
                        } else if (which == 2) {
                            //for cancel do not do anything
                        }

                    });

                    builder.show();
                }
                return true;
            });
        } else {
            holder.receivermessagetext.setOnLongClickListener(v -> {
                if (UserMessageList.get(position).getType().equals("text")) {
                    CharSequence options[] = new CharSequence[]
                            {
                                    "Delete for me", "Cancel"
                            };

                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                    builder.setTitle("Delete Message?");
                    builder.setItems(options, (dialog, which) -> {
                        if (which == 0) {
                            deleteReceiveMessage(position, holder);
                        } else if (which == 1) {
                            //for cancel do not do anything
                        }

                    });

                    builder.show();
                }
                return true;
            });
        }
    }

    private void deleteSentMessage(final int position, final MessageViewHolder holder) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("Messages").child(UserMessageList.get(position).getFrom())
                .child(UserMessageList.get(position).getTo()).child(UserMessageList.get(position).getMessageID())
                .removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        notifyItemRemoved(position);
                        UserMessageList.remove(position);
                        notifyItemRangeChanged(position, UserMessageList.size());
                        Toast.makeText(holder.itemView.getContext(), "Message deleted...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void deleteReceiveMessage(final int position, final MessageViewHolder holder) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("Messages").child(UserMessageList.get(position).getTo())
                .child(UserMessageList.get(position).getFrom()).child(UserMessageList.get(position).getMessageID())
                .removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        notifyItemRemoved(position);
                        UserMessageList.remove(position);
                        notifyItemRangeChanged(position, UserMessageList.size());
                        Toast.makeText(holder.itemView.getContext(), "Message deleted...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void deleteMessageForEveryone(final int position, final MessageViewHolder holder) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("Messages").child(UserMessageList.get(position).getFrom())
                .child(UserMessageList.get(position).getTo()).child(UserMessageList.get(position).getMessageID())
                .removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference();
                        rootRef1.child("Messages").child(UserMessageList.get(position).getTo())
                                .child(UserMessageList.get(position).getFrom()).child(UserMessageList.get(position).getMessageID())
                                .removeValue().addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        notifyItemRemoved(position);
                                        UserMessageList.remove(position);
                                        notifyItemRangeChanged(position, UserMessageList.size());
                                        Toast.makeText(holder.itemView.getContext(), "Message deleted...", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(holder.itemView.getContext(), "Error: " + task1.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        if (UserMessageList != null) {
            return UserMessageList.size();
        } else {
            return 0;
        }
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView sendermessagetext, receivermessagetext;
        public ImageView receiverprofileimage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            receivermessagetext = itemView.findViewById(R.id.sender_message);
            sendermessagetext = itemView.findViewById(R.id.receiver_message);
            receiverprofileimage = itemView.findViewById(R.id.sender_message_image);
        }
    }

}
