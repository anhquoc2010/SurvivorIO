package com.example.cuoiki_android_lythuyet.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.KeeperDetailActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.RequestDetail;
import com.example.cuoiki_android_lythuyet.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {


    private Context mContext;
    private List<Booking> bookingList;

    public BookingAdapter(Context mContext, List<Booking> bookingList) {
        this.mContext = mContext;
        this.bookingList = bookingList;
    }
    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.owner_request_row_item,parent,false);
        return  new BookingViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Booking booking = bookingList.get(position);
        if(booking == null){
            return;
        }
        holder.tvNameBooking.setText(booking.getName());
        holder.tvPriceBooking.setText(booking.getPrice()+ " $");
        holder.tvCalendarBooking.setText(booking.getCalendar()+"");
        holder.tvResponseBooking.setText(booking.getResponceBooking()+" responces");
        holder.imgvBooking.setImageResource(booking.getImgBooking());
        holder.tvStatus.setText(booking.getStatus());
        String status = booking.getStatus();
        if (status.equals("Pending")){
            holder.tvStatus.setHighlightColor(R.color.yellowsoft);
        }else{
            holder.tvStatus.setBackgroundResource(R.color.greensoft);
        }
        holder.itemBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDeTail(booking);
            }
        });
        
        holder.itemBooking.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence options[] = new CharSequence[]
                        {
                                "Delete", "Cancel"
                        };

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Are you sure you want to delete?");
                builder.setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        removeItem(position);
                        Toast.makeText(mContext, "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                    } else if (which == 1) {
                        //do nothing
                    }
                });

                builder.show();
                return false;
            }
        });
    }

    public void removeItem(int index){
        bookingList.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    private void onClickGotoDeTail(Booking booking){
        Intent intent = new Intent(mContext, RequestDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_booking", booking);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void release(){
        mContext = null;
    }

    @Override
    public int getItemCount() {
        if (bookingList != null){
            return bookingList.size();
        }
        return 0;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder{

        TextView tvNameBooking, tvPriceBooking, tvResponseBooking, tvCalendarBooking, tvStatus;
        ImageView imgvBooking;
        RecyclerView rcvBooking;
        LinearLayout itemBooking;
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameBooking = itemView.findViewById(R.id.tvName);
            tvPriceBooking = itemView.findViewById(R.id.tvPrice);
            tvResponseBooking = itemView.findViewById(R.id.tvResponse);
            tvCalendarBooking = itemView.findViewById(R.id.tvCalendar);
            imgvBooking = itemView.findViewById(R.id.img_request_booking);
            rcvBooking = itemView.findViewById(R.id.rcvBooking);
            itemBooking = itemView.findViewById(R.id.item_request_booking);
            tvStatus = itemView.findViewById(R.id.tv_status_item);
//tvNameBooking     tvName
//tvResponseBooking     tvResponse
//tvPriceBooking            tvPrice
//tvCalendarBooking        tvCalendar
//imgvBooking          img_request_booking
        }
    }

}
