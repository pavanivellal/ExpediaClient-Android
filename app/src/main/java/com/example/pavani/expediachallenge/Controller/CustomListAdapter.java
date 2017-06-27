package com.example.pavani.expediachallenge.Controller;

/**
 * Created by Pavani Vellal on 6/21/17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavani.expediachallenge.Model.Hotel;
import com.example.pavani.expediachallenge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Hotel> {

    ArrayList<Hotel> hotels;
    Context context;
    int resource;

    public CustomListAdapter( Context context, int resource, ArrayList<Hotel> hotels) {
        super(context, resource, hotels);
        this.hotels = hotels;
        this.context = context;
        this.resource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.hotel_fragment,null,true  );
        }

        Hotel hotel = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewHotel);
        Picasso.with(context).load(hotel.getImgURL()).into(imageView);

        TextView name = (TextView) convertView.findViewById(R.id.hotel_name);
        name.setText(hotel.getHotelName());

        TextView price = (TextView) convertView.findViewById(R.id.hotel_price);
        price.setText("$" + hotel.getPriceToShowUsers());

        TextView guest_rating = (TextView) convertView.findViewById(R.id.guest_rating);
        guest_rating.setText(hotel.getHotelGuestRating());

        TextView guest_rating_txt = (TextView) convertView.findViewById(R.id.guest_rating_txt);
        guest_rating_txt.setText("of 5 - guest rating");

        return convertView;
    }
}
