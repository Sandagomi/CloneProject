package com.example.cloneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Electronic extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic);

        button1 = (Button) findViewById(R.id.computerButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComputer();
            }
        });

        button2 = (Button) findViewById(R.id.buttonMobilePhone);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMobilePhone();
            }
        });

    }

    public void openComputer(){
        Intent intent = new Intent(this,Computer.class);
        startActivity(intent);
    }

    public void openMobilePhone(){
        Intent intent = new Intent(this,MobilePhone.class);
        startActivity(intent);
    }
}
