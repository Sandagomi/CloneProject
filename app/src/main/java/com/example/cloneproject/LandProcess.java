package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LandProcess extends AppCompatActivity {


    EditText editTextLP;
    Button   ButtonSB;
    Spinner  secondSpinner;
    ListView listViewLands;

    ArrayList <Lands>  lands;
    DatabaseReference databaseLands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_process);

    // places spinner

        Spinner mySecondSpinner = (Spinner) findViewById(R.id.spinner2);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(LandProcess.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.places));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySecondSpinner.setAdapter(myAdapter);


     // edit text part regarding the land place

        databaseLands = FirebaseDatabase.getInstance().getReference("Lands");
        editTextLP = (EditText) findViewById(R.id.editTextLP);
        secondSpinner = (Spinner) findViewById(R.id.spinner2);
        listViewLands = (ListView) findViewById(R.id.listViewLands);
        ButtonSB = (Button) findViewById(R.id.ButtonSB);

        lands = new ArrayList<Lands>();


        ButtonSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String lPlace = editTextLP.getText().toString().trim();
                String sSpinner = secondSpinner. getSelectedItem().toString();

                if(!TextUtils.isEmpty(lPlace)) {


                    String landId = databaseLands.push().getKey();

                    Lands lands = new Lands(landId,lPlace,sSpinner);

                    databaseLands.child(landId).setValue(lands);

                    editTextLP.setText("");

                    Toast.makeText (getApplicationContext(), "Land saved Successfully", Toast.LENGTH_LONG) .show();

                } else {

                   Toast.makeText(getApplicationContext(), "Enter the LandPlace", Toast.LENGTH_LONG) .show();
                }



            }
        });
    }
}
