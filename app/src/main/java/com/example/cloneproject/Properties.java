package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Properties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);


        Button ButtonLands = (Button) findViewById(R.id.ButtonLands);
        ButtonLands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),LandProcess.class);
                startActivity(startIntent);
            }
        });


        Button buttonApartment = (Button) findViewById(R.id.ButtonApartment);
        buttonApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ApartmentProcess.class);
                startActivity(startIntent);
            }
        });
    }
}
