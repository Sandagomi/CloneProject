package com.example.cloneproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComputerImage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ComputerImageAdapter mAdapter;

   // private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<UploadComputer> mUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_image);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       //mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("upload");


       mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadComputer upload = postSnapshot.getValue(UploadComputer.class);

                    upload.setmKey(postSnapshot.getKey());

                    mUploads.add(upload);


                }

                mAdapter = new ComputerImageAdapter(ComputerImage.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
            //    mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Toast.makeText(ComputerImage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
              //  mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
}


