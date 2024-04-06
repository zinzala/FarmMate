package com.example.customerapp;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

public class ActivityCropHealth extends AppCompatActivity {

    /*MaterialToolbar toolbar;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_crop_health);
        /*toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        // Retrieve crop ID from intent extras
        String cropId = getIntent().getStringExtra("id");
        String category = getIntent().getStringExtra("category");
        String cropName = getIntent().getStringExtra("cropName");
        String cropDescription = getIntent().getStringExtra("cropDescription");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Initialize views
        ImageView imageCropDetail = findViewById(R.id.imageCropDetail);
        TextView textCropNameDetail = findViewById(R.id.textCropNameDetail);
        TextView textCropDescriptionDetail = findViewById(R.id.textCropDescriptionDetail);
        TextView textcatagory = findViewById(R.id.subtitleTextView);

        // Load image using Glide or Picasso
        Glide.with(ActivityCropHealth.this).load(imageUrl).into(imageCropDetail);

        // Set crop details to views
        textCropNameDetail.setText(cropName);
        textCropDescriptionDetail.setText(cropDescription);
        textcatagory.setText(category);


        // Make network request to fetch crop details
        fetchCropDetails(cropId);
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle navigation click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void fetchCropDetails(String cropId) {

    }
}