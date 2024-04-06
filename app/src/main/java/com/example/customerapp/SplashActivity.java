package com.example.customerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  // REMOVE title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        Intent iMain = new Intent(SplashActivity.this,Login.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(iMain);
                finish();
            }
        },4000);
    }
}