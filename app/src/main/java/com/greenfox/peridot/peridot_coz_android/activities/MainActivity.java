package com.greenfox.peridot.peridot_coz_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.greenfox.peridot.peridot_coz_android.R;

public class MainActivity extends AppCompatActivity {

    // User primary key used by Retrofit - TO BE REVIEWED
    private final static String USER_ID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}
