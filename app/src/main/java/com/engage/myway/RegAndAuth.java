package com.engage.myway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegAndAuth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_and_auth);
        getSupportFragmentManager().beginTransaction().replace(R.id.register_frame_layout,new LogInFragment()).commit();
    }
}