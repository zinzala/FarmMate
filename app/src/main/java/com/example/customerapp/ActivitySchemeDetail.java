package com.example.customerapp;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ActivitySchemeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scheme_detail);

        // Retrieve crop ID from intent extras
        String cropId = getIntent().getStringExtra("id");
        String category = getIntent().getStringExtra("category");
        String cropName = getIntent().getStringExtra("cropName");
        String cropDescription = getIntent().getStringExtra("cropDescription");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Initialize views
        ImageView imageCropDetail = findViewById(R.id.imageCropDetail);
        TextView textCropNameDetail = findViewById(R.id.subtitleTextView);
        TextView textcatagory = findViewById(R.id.textCropDescriptionDetail);

        // Load image using Glide or Picasso
        Glide.with(ActivitySchemeDetail.this).load(imageUrl).into(imageCropDetail);

        // Set crop details to views
        textCropNameDetail.setText(cropName);
        textcatagory.setText(category);


        // Make network request to fetch crop details
        fetchCropDetails(cropId);
    }

    private void fetchCropDetails(String cropId) {
    }
}