package com.example.pavani.expediachallenge;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;


public class HotelDetails extends AppCompatActivity implements OnMapReadyCallback {

    ImageView imageView;
    TextView discountTxt;
    TextView roomsLeftTxt;
    Hotel hotel;
    RatingBar ratingBar;
    TextView hotel_address;
    TextView hotel_name;
    private GoogleMap mMap;
    TextView priceToShowUsers;
    TextView strikethroughPriceToShowUsers;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        imageView = (ImageView) findViewById(R.id.hotelImage);
        discountTxt = (TextView) findViewById(R.id.discountPercentage);
        roomsLeftTxt = (TextView) findViewById(R.id.roomsLeft);
        hotel_address = (TextView) findViewById(R.id.hotel_address);
        hotel_name = (TextView) findViewById(R.id.hotel_name);
        priceToShowUsers = (TextView) findViewById(R.id.hotel_price);
        strikethroughPriceToShowUsers = (TextView) findViewById(R.id.strike_price);
        hotel = (Hotel) getIntent().getParcelableExtra("sel_hotel");

        //Image set
        Picasso.with(this).load(hotel.getImgURL()).into(imageView);

        //Hotel Name and Address
        hotel_name.setText(hotel.getHotelName());
        hotel_address.setText("Situated in " + hotel.getHotelAddress());

        //Discount Percentage
        discountTxt.setText(hotel.getDiscountPercent() + "%");
        roomsLeftTxt.setText(hotel.getRoomsLeft() +  " Rooms Left");

        //Star Rating setting
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(hotel.getHotelStarRating()));
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        //price to show users
        priceToShowUsers.setText("$" + hotel.getPriceToShowUsers() + "/night");

        //Strike through price
        strikethroughPriceToShowUsers.setText("$" + hotel.getStrikethroughPriceToShowUsers());
        strikethroughPriceToShowUsers.setPaintFlags(strikethroughPriceToShowUsers.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        //Google map view
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.change_loc)
        {
            Intent intent = new Intent(HotelDetails.this,Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hotelLocation = new LatLng(Double.parseDouble(hotel.getLatitude()), Double.parseDouble(hotel.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(hotelLocation).title(hotel.getHotelName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((hotelLocation), 12.0f));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng arg0)
            {
                Intent intent = new Intent(HotelDetails.this, MapWithMarker.class);
                intent.putExtra("sel_hotel", hotel);
                startActivity(intent);
            }
        });
    }
}
