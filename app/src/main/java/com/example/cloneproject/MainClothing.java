package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainClothing extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clothing);
    }
    public void womensPage(View view){
        Intent intent = new Intent(this,WomensFashion.class);
        startActivity(intent);
    }
    public void mensPage(View view){
        Intent intent = new Intent(this,MensFashion.class);
        startActivity(intent);
    }
    public void kidsPage(View view){
        Intent intent = new Intent(this,KidsFashion.class);
        startActivity(intent);
    }
    public void babysPage(View view){
        Intent intent = new Intent(this,BabyFashion.class);
        startActivity(intent);
    }
}
