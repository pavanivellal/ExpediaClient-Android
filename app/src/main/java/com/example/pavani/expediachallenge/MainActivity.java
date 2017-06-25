package com.example.pavani.expediachallenge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hotel> arrayList;
    ListView lv;
    private String contentBaseURL = "https://techblog.expedia.com/utility/";
    private String contentURL = "san-francisco-hotels.json";
    private String imgBaseURL = "http://images.travelnow.com";
    private String title = "San Francisco";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.hotels_list_view);

        if(getIntent().getStringExtra("sel_city") != null) {
            contentURL = getIntent().getStringExtra("sel_city");
        }
        if(getIntent().getStringExtra("title") != null) {
            title = getIntent().getStringExtra("title");
        }

        getSupportActionBar().setTitle(title + " Hotels");

        new ReadJSON().execute(contentBaseURL+contentURL);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.change_loc)
        {
            Intent intent = new Intent(MainActivity.this,Settings.class);
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

    class ReadJSON extends AsyncTask<String,Integer, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(MainActivity.this, "Please Wait",null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            loading.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("hotels");
                for(int i = 0; i < jsonArray.length(); i++)
                {

                    JSONObject  hotelObject = jsonArray.getJSONObject(i);

                    //create arraylist of Hotels only if available is "true"
                    if(hotelObject.getString("available").equals("true")) {

                        Hotel addHotel = new Hotel();

                        if(hotelObject.has("hotelName"))
                            addHotel.hotelName = hotelObject.getString("hotelName");

                        if(hotelObject.has("hotelGuestRating"))
                            addHotel.hotelGuestRating = hotelObject.getString("hotelGuestRating");

                        if(hotelObject.has("hotelStarRating"))
                            addHotel.hotelStarRating = hotelObject.getString("hotelStarRating");

                        if(hotelObject.has("reviewTotal"))
                            addHotel.hotelDescription = hotelObject.getString("reviewTotal");

                        if(hotelObject.has("hotelDescription"))
                            addHotel.hotelDescription = hotelObject.getString("hotelDescription");

                        if(hotelObject.has("longitude"))
                            addHotel.longitude = hotelObject.getString("longitude");

                        if(hotelObject.has("latitude"))
                            addHotel.latitude = hotelObject.getString("latitude");

                        if(hotelObject.has("hotelAddress"))
                        {
                            JSONObject hotelAddressObj = hotelObject.getJSONObject("hotelAddress");
                            String hotelAddress = hotelAddressObj.getString("firstAddressLine") + ", " + hotelAddressObj.getString("city") + ", " + hotelAddressObj.getString("postalCode") + ", " + hotelAddressObj.getString("countryAlpha3Code");
                            addHotel.hotelAddress = hotelAddress;

                        }

                        if(hotelObject.has("lowRateInfo"))
                        {
                            if(hotelObject.getJSONObject("lowRateInfo").has("priceToShowUsers"))
                            {
                                addHotel.priceToShowUsers = hotelObject.getJSONObject("lowRateInfo").getString("priceToShowUsers");
                            }

                            if(hotelObject.getJSONObject("lowRateInfo").has("strikethroughPriceToShowUsers"))
                            {
                                addHotel.strikethroughPriceToShowUsers = hotelObject.getJSONObject("lowRateInfo").getString("strikethroughPriceToShowUsers");
                            }

                            if(hotelObject.getJSONObject("lowRateInfo").has("discountPercent"))
                            {
                                addHotel.discountPercent = hotelObject.getJSONObject("lowRateInfo").getString("discountPercent");
                            }
                        }

                        if(hotelObject.has("largeThumbnailUrl"))
                        {
                            addHotel.imgURL = imgBaseURL + hotelObject.getString("largeThumbnailUrl");
                        }

                        if(hotelObject.has("roomsLeftAtThisRate"))
                        {
                            addHotel.roomsLeft = hotelObject.getString("roomsLeftAtThisRate");
                        }


                        arrayList.add(addHotel);
                    }

                }

            }catch (Exception e) {
                e.printStackTrace();
            }

            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.hotel_fragment, arrayList
            );

            lv.setAdapter(adapter);

            // Set what happens when a list view item is clicked
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Hotel selectedHotel = arrayList.get(position);
                    Intent intent = new Intent(MainActivity.this, HotelDetails.class);
                    intent.putExtra("sel_hotel", selectedHotel);
                    startActivity(intent);
                }

            });

        }
    }


    private static String readURL(String strURL) {

        StringBuilder sb = new StringBuilder();
        try {
                //Creating URL object
                URL url = new URL(strURL);

                URLConnection urlConnection = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }

            } catch (Exception e) {

            }

            return sb.toString();

    }

}
