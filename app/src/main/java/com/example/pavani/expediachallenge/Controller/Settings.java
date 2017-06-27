package com.example.pavani.expediachallenge.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pavani.expediachallenge.R;

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

    public static class NetworkError extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_network_error);
        }
    }
}
