package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner myFirstSpinner = (Spinner) findViewById(R.id.spinner1);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources(). getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myFirstSpinner.setAdapter(myAdapter);


    }
}
