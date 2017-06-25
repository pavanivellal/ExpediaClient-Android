package com.example.pavani.expediachallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity{

    Spinner location_dropdown;
    Button btn_save;
    String result = "san-francisco-hotels.json";
    String title = "San Francisco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        location_dropdown = (Spinner) findViewById(R.id.locations);
        btn_save = (Button) findViewById(R.id.save);

        addSaveBtnListener();

    }


    public void addLocationsDropdown()
    {
        List<String> locations = new ArrayList<>();
        locations.add("San Francisco");
        locations.add("Chicago");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, locations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_dropdown.setAdapter(dataAdapter);

    }

    public void addSaveBtnListener()
    {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(location_dropdown.getSelectedItem().equals("San Francisco"))
                {
                    result = "san-francisco-hotels.json";
                    title = "San Francisco";
                }
                else if(location_dropdown.getSelectedItem().equals("Chicago"))
                {
                    result = "chicago-hotels.json";
                    title = "Chicago";
                }

                Intent intent = new Intent(Settings.this, MainActivity.class);
                intent.putExtra("sel_city",result);
                intent.putExtra("title",title);
                startActivity(intent);

            }
        });
    }
}