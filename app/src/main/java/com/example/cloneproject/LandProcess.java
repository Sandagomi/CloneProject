package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandProcess extends AppCompatActivity {

    EditText editTextLN;
    EditText editTextLP;
    Button   ButtonSB;
    DatabaseReference databaseLands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_process);

        databaseLands = FirebaseDatabase.getInstance().getReference("Lands");
        editTextLN = (EditText) findViewById(R.id.editTextLN);
        editTextLP = (EditText) findViewById(R.id.editTextLP);

        ButtonSB = (Button) findViewById(R.id.ButtonSB);


        ButtonSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lName = editTextLN.getText().toString(). trim();
                String lPlace = editTextLP.getText().toString().trim();

                if(!TextUtils.isEmpty(lName)) {

                    editTextLN.setText("");

                    Toast.makeText (getApplicationContext(), "Land saved Successfully", Toast.LENGTH_LONG) .show();

                } else {

                   Toast.makeText(getApplicationContext(), "Enter the LandPlace", Toast.LENGTH_LONG) .show();
                }


                if(!TextUtils.isEmpty(lPlace)) {

                    editTextLN.setText("");

                    Toast.makeText (getApplicationContext(), "Land saved Successfully", Toast.LENGTH_LONG) .show();

                } else {

                    Toast.makeText(getApplicationContext(), "Enter the LandPlace", Toast.LENGTH_LONG) .show();
                }

            }
        });
    }
}
