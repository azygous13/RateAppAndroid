package com.cakii.rateappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cakii.rateapp.RateApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RateApp.init()
                .setDebug(true)
                .monitor(this);
    }
}
