package com.example.cloneproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LandImageView extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private landImageAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<imageUploadLand> imageUploadLands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_image_view);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        imageUploadLands = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for ( DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        imageUploadLand upload = postSnapshot.getValue(imageUploadLand.class);
                        imageUploadLands.add(upload);
                    }

                    mAdapter = new landImageAdapter(LandImageView.this,imageUploadLands);

                    mRecyclerView .setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(LandImageView.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });





    }
}
