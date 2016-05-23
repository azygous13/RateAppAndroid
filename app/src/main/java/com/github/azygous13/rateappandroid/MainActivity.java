package com.github.azygous13.rateappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.azygous13.rateapp.RateApp;

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
