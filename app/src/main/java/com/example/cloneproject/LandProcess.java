package com.example.cloneproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LandProcess extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;

    private Button buttonSelect;
    private Button buttonUpload;
    private EditText editTextNameImage;
    private ImageView imageViewUploads;
    private ProgressBar mprogressBar;

    private Uri mimageUri;


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

       // image upload process

        buttonSelect = findViewById(R.id.buttonIP);
        buttonUpload = findViewById(R.id.buttonUpload);
        editTextNameImage = findViewById(R.id.editTextIP);
        imageViewUploads = findViewById(R.id.imageView4);
        mprogressBar = findViewById(R.id.progressBar);



        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChooser();

            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        editTextNameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





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

        ButtonSB = (Button) findViewById(R.id.ButtonSB);

        lands = new ArrayList<Lands>();


        ButtonSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String lPlace = editTextLP.getText().toString().trim();
                String sSpinner = secondSpinner. getSelectedItem().toString();

                if(!TextUtils.isEmpty(lPlace)) {

                 //unique id generator
                    String landId = databaseLands.push().getKey();
                 // creates a land object
                    Lands lands = new Lands(landId,lPlace,sSpinner);
                 //save the land
                    databaseLands.child(landId).setValue(lands);
                 //setting land name text box to blank again
                    editTextLP.setText("");

                    Toast.makeText (getApplicationContext(), "Land saved Successfully", Toast.LENGTH_LONG) .show();

                } else {

                   Toast.makeText(getApplicationContext(), "Enter the LandPlace", Toast.LENGTH_LONG) .show();
                }



            }
        });













    }





            private void openFileChooser() {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);





            }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {


                mimageUri = data.getData();

                


            }














    }
}
