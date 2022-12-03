package com.example.cuoiki_android_lythuyet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuoiki_android_lythuyet.R;
import com.example.cuoiki_android_lythuyet.models.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {


    private Context mContext;
    private List<Booking> bookingList;

    public BookingAdapter(Context mContext, List<Booking> bookingList) {
        this.mContext = mContext;
        this.bookingList = bookingList;
    }

    public void setData(List<Booking> list){
        this.bookingList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_booking,parent,false);
        return  new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        if(booking == null){
            return;
        }
        holder.tvNameBooking.setText(booking.getNameBooking());
        holder.tvPriceBooking.setText(booking.getPriceBooking().toString());
        holder.tvDistanceBooking.setText(booking.getDistanceBooking().toString());
        holder.tvPreviewBooking.setText(booking.getPreviewBooking().toString());
        holder.tvStarBooking.setText(booking.getStarBooking().toString());
        holder.imgvBooking.setImageResource(booking.getImgBooking());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rcvBooking.setLayoutManager(linearLayoutManager);

    }

    @Override
    public int getItemCount() {
        if (bookingList != null){
            return bookingList.size();
        }
        return 0;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder{

        TextView tvNameBooking, tvPriceBooking, tvPreviewBooking, tvDistanceBooking, tvStarBooking;
        ImageView imgvBooking;
        RecyclerView rcvBooking;
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameBooking = itemView.findViewById(R.id.tv_name_booking);
            tvPriceBooking = itemView.findViewById(R.id.tv_price_booking);
            tvPreviewBooking = itemView.findViewById(R.id.tv_preview_booking);
            tvDistanceBooking = itemView.findViewById(R.id.tv_distance_booking);
            tvStarBooking = itemView.findViewById(R.id.tv_star_booking);
            imgvBooking = itemView.findViewById(R.id.imgv_booking);
            rcvBooking = itemView.findViewById(R.id.rcvBooking);

        }
    }

}
