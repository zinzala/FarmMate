package com.example.customerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerActivity extends AppCompatActivity {
    Button btnMarket;
    CardView cardWeather,cardReminder,cardNews,cardPrice,cardCrop,cardHealth,cardScheme,cardHelp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_farmer);
        btnMarket = findViewById(R.id.btnMarket);
        cardWeather = findViewById(R.id.cardWeather);
        cardReminder = findViewById(R.id.cardReminder);
        cardNews = findViewById(R.id.cardNews);
        cardPrice = findViewById(R.id.cardPrice);
        cardCrop = findViewById(R.id.cardCrop);
        cardHealth = findViewById(R.id.cardHealth);
        cardScheme = findViewById(R.id.cardScheme);
        cardHelp = findViewById(R.id.cardHelp);


        cardHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,ActivityHelp.class);
                startActivity(i);
            }
        });

        cardScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,ActivityCropScheme.class);
                startActivity(i);
            }
        });

        cardHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,ActivityCropHealthMain.class);
                startActivity(i);
            }
        });

        cardCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,CropDetailMain.class);
                startActivity(i);
            }
        });


        cardPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,ActivityCropPrices.class);
                startActivity(i);
            }
        });

        cardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,NewsActivity.class);
                startActivity(i);
            }
        });
        cardWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,WeatherActivity.class);
                startActivity(i);

            }
        });
        cardReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,MyCustomeCalender.class);
                startActivity(i);
            }
        });


        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmerActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}