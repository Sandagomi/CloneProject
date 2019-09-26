package com.example.cloneproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class AddItemsToClothing extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mBottonChooseImage;
    private Button mButtonUpload;
    private EditText itemID;
    private EditText title;
    private EditText description;
    private EditText price;
    private EditText contactNo;
    private EditText email;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Spinner Category;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    Items items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_to_clothing);

        final Spinner spinner = findViewById(R.id.spinner11);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mBottonChooseImage = findViewById(R.id.btnChoose);
        mButtonUpload = findViewById(R.id.btnSave);
        Category = findViewById(R.id.spinner11);
        itemID = findViewById(R.id.txtID);
        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        price = findViewById(R.id.txtPrice);
        contactNo = findViewById(R.id.txtContactNo);
        email = findViewById(R.id.txtEmail);
        mImageView = findViewById(R.id.imgView11);
        mProgressBar = findViewById(R.id.progressBar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Items");

        mBottonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        //items = new Items();

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"Upload in progress",Toast.LENGTH_LONG).show();
                }else {
                    uploadFile();
                }


                //mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Items");

                //items.setR_ItemID(itemID.getText().toString().trim());
                //items.setR_title(title.getText().toString().trim());
                //items.setR_decsription(description.getText().toString().trim());
                //items.setR_price(price.getText().toString().trim());
                //items.setR_contactNo(Integer.parseInt(contactNo.getText().toString().trim()));
                //items.setR_email(email.getText().toString().trim());

                //mDatabaseRef.child(items.getR_ItemID()).setValue(items);
                //Toast.makeText(getApplicationContext(),"Adding Success",Toast.LENGTH_LONG).show();
                //ClearData();


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() !=null){
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void uploadFile(){
        if (mImageUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            +"."+getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                  .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          Handler handler = new Handler();
                          handler.postDelayed(new Runnable() {
                              @Override
                              public void run() {
                                  mProgressBar.setProgress(0);
                              }
                          },500);

                          fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                              @Override
                              public void onSuccess(Uri uri) {
                                  Toast.makeText(AddItemsToClothing.this,"Upload successful",Toast.LENGTH_LONG).show();
                                  Upload upload = new Upload(itemID.getText().toString().trim(),
                                          uri.toString());
                                  upload.setmItemID(itemID.getText().toString().trim());
                                  upload.setmTitle(title.getText().toString().trim());
                                  upload.setmDescription(description.getText().toString().trim());
                                  upload.setmPrice(price.getText().toString().trim());
                                  upload.setmContactNum(Integer.parseInt(contactNo.getText().toString().trim()));
                                  upload.setmEmail(email.getText().toString().trim());
                                  String uploadID = mDatabaseRef.push().getKey();
                                  mDatabaseRef.child(uploadID).setValue(upload);
                                  ClearData();

                              }
                          });

                      }
                  })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddItemsToClothing.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                             double pogress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                             mProgressBar.setProgress((int) pogress);


                        }
                    });

        }else {
            Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
        }

    }

    public void ClearData(){
        itemID.setText("");
        title.setText("");
        description.setText("");
        price.setText("");
        contactNo.setText("");
        email.setText("");
    }
}
