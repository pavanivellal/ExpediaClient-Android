package com.example.pavani.expediachallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapWithMarker extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotel = (Hotel) getIntent().getParcelableExtra("sel_hotel");
        setContentView(R.layout.activity_map_with_marker);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hotelLocation = new LatLng(Double.parseDouble(hotel.getLatitude()), Double.parseDouble(hotel.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(hotelLocation).title(hotel.getHotelName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom((hotelLocation), 12.0f));
    }
}
