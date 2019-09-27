package com.example.cloneproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApartmentProcess extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private EditText apartmentName;
    private Spinner  aplaces;
    private Button   aSelectImage;
    private EditText aNameImage;
    private ProgressBar aProgressBar;
    private Button   aimageUpload;
    private ImageView aImageView;
    private Button    aSubmit;
    private TextView aShowUploads;
    private ArrayList<Apartments> apartments;
    private Uri aimageUri;
    private EditText apartmentPrice;

 //   private StorageReference aStorageRef;
   // private StorageTask aUploadTask;
  //  private DatabaseReference databaseApartments;

    private StorageReference mStorageRef;
    private StorageTask      mUploadTask;
    private DatabaseReference  databaseLands;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_process);


       //refer ids to the page keys

       apartmentName = findViewById(R.id.editTextNameAp);
       aplaces       = findViewById(R.id.spinner3);
       aSelectImage  = findViewById(R.id.buttonIPAp);
       aNameImage    = findViewById(R.id.editTextIPAp);
       aProgressBar  = findViewById(R.id.progressBar2);
       aimageUpload  = findViewById(R.id.buttonUploadAp);
       apartmentPrice = findViewById(R.id.editTextAPrice);
       aImageView    = findViewById(R.id.imageView4);
       aSubmit       = findViewById(R.id.ButtonSBAp);
       aShowUploads  = findViewById(R.id.text_view_show_uploads_Ap);
       mStorageRef   = FirebaseStorage.getInstance().getReference("Apartments");
       databaseLands = FirebaseDatabase.getInstance().getReference("Apartments");




        aSelectImage .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChooser();

            }
        });

          aimageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    uploadFile();




            }
        });

        aNameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        aShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openImagesActivity();
            }
        });




        // places spinner

        Spinner thirdSpinner = (Spinner) findViewById(R.id.spinner3);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ApartmentProcess.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.places));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdSpinner.setAdapter(myAdapter);





        // edit text part regarding the apartment place

        databaseLands= FirebaseDatabase.getInstance().getReference("Apartments");
        apartmentName = (EditText) findViewById(R.id.editTextNameAp);
        aplaces = (Spinner) findViewById(R.id.spinner3);

        aSubmit = (Button) findViewById(R.id.ButtonSBAp);

        apartments = new ArrayList<Apartments>();

        aSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String aName = apartmentName.getText().toString().trim();
                String tSpinner = aplaces.getSelectedItem().toString();
                double aprice = Double.parseDouble(apartmentPrice.getText().toString().trim());

                if(!TextUtils.isEmpty(aName)) {

                    //unique id generator
                    String apartmentId = databaseLands.push().getKey();
                    // creates a apartment object
                    Apartments apartments = new Apartments(apartmentId,aName,tSpinner,aprice);
                    //save the apartment
                    databaseLands.child(apartmentId).setValue(apartments);
                    //setting apartment name text box to blank again
                    apartmentName.setText("");

                    apartmentPrice.setText("");

                    Toast.makeText (getApplicationContext(), "Apartment saved Successfully", Toast.LENGTH_LONG) .show();

                } else {

                    Toast.makeText(getApplicationContext(), "Enter the Apartment Place", Toast.LENGTH_LONG) .show();
                }



            }
        });

    }


    //image selector process

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


            aimageUri = data.getData();

            Picasso.get().load(aimageUri).into(aImageView);



        }


    }



    // image upload button process


    private String getFileExtension (Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime  = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {

        if ( aimageUri != null) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension( aimageUri));

            mUploadTask = fileReference.putFile( aimageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    aProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(ApartmentProcess.this, "Upload Successful", Toast.LENGTH_LONG).show();
                            imageUploadLand upload = new imageUploadLand(aNameImage.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());

                            String uploadId = databaseLands.push().getKey();
                            databaseLands .child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ApartmentProcess.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount() );
                            aProgressBar.setProgress((int)progress);

                        }
                    });

        }else {

            Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
        }

    }



    private void openImagesActivity() {

        Intent intent = new Intent (this, imageUploadApartment.class );
        startActivity(intent);

    }
























}
