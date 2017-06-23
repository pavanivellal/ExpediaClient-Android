package com.example.pavani.expediachallenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.hotels_list_view);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://techblog.expedia.com/utility/san-francisco-hotels.json");
            }
        });

    }



    class ReadJSON extends AsyncTask<String,Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("hotels");
                for(int i = 0; i < jsonArray.length(); i++)
                {

                    JSONObject  hotelObject = jsonArray.getJSONObject(i);
                    //Get Hotel Address from hotel object
                    JSONObject hotelAddressObj = hotelObject.getJSONObject("hotelAddress");
                    String hotelAddress = hotelAddressObj.getString("firstAddressLine") + ", " + hotelAddressObj.getString("city") + ", " + hotelAddressObj.getString("postalCode") + ", " + hotelAddressObj.getString("countryAlpha3Code");

                    //create arraylist of Hotels only if available is "true"
                    if(hotelObject.getString("available").equals("true")) {
                        arrayList.add(new Hotel(
                                hotelObject.getString("hotelName"),
                                hotelObject.getString("hotelGuestRating"),
                                hotelObject.getString("hotelStarRating"),
                                hotelObject.getString("reviewTotal"),
                                hotelObject.getString("hotelDescription"),
                                hotelObject.getString("longitude"),
                                hotelObject.getString("latitude"),
                                hotelAddress,
                                hotelObject.getJSONObject("lowRateInfo").getString("priceToShowUsers"),
                                hotelObject.getJSONObject("lowRateInfo").getString("strikethroughPriceToShowUsers"),
                                hotelObject.getString("largeThumbnailUrl"),
                                hotelObject.getJSONObject("lowRateInfo").getString("discountPercent"),
                                hotelObject.getString("roomsLeftAtThisRate")



                        ));
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
