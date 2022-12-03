package com.example.cuoiki_android_lythuyet.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.KeeperDetailActivity;
import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.models.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {


    private Context mContext;
    private List<Booking> bookingList;

    public BookingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Booking> list){
        this.bookingList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_request_row_item,parent,false);
        return  new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        if(booking == null){
            return;
        }
        holder.tvNameBooking.setText(booking.getName());
        holder.tvPriceBooking.setText(booking.getPrice()+ " $");
        holder.tvCalendarBooking.setText(booking.getCalendar()+" M");
        holder.tvResponseBooking.setText(booking.getResponceBooking()+"responces");
        holder.imgvBooking.setImageResource(booking.getImgBooking());
        holder.itemBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDeTail(booking);
            }
        });
    }
    private void onClickGotoDeTail(Booking booking){
        Intent intent = new Intent(mContext, KeeperDetailActivity.class);
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

        TextView tvNameBooking, tvPriceBooking, tvResponseBooking, tvCalendarBooking;
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
//tvNameBooking     tvName
//tvResponseBooking     tvResponse
//tvPriceBooking            tvPrice
//tvCalendarBooking        tvCalendar
//imgvBooking          img_request_booking
        }
    }

}
