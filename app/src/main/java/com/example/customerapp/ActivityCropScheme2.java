package com.example.customerapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityCropScheme2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CropAdapterScheme cropAdapter;
    private List<CropScheme> cropList;
    private ProgressBar loadingIndicator;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_crop_scheme2);

        // Receive the button number from MainActivity
        int buttonNumber = getIntent().getIntExtra("button_number", 0);
        // Now you can use the buttonNumber as needed in your activity

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cropList = new ArrayList<>();
        cropAdapter = new CropAdapterScheme(cropList);
        recyclerView.setAdapter(cropAdapter);

        loadingIndicator = findViewById(R.id.loadingIndicator);
        t1 = findViewById(R.id.loadingText);

        // Show loading indicator
        loadingIndicator.setVisibility(View.VISIBLE);

        // Set URL based on the button number
        String url = getUrlForButtonNumber(buttonNumber);

        fetchCropsFromServer(url);
    }

    private String getUrlForButtonNumber(int buttonNumber) {
        switch (buttonNumber) {
            case 1:
                return "https://adimnfarmmate.000webhostapp.com/php/sc1.php";
            case 2:
                return "https://adimnfarmmate.000webhostapp.com/php/sc2.php";
            case 3:
                return "https://adimnfarmmate.000webhostapp.com/php/sc3.php";
            case 4:
                return "https://adimnfarmmate.000webhostapp.com/php/sc4.php";
            default:
                return "";
        }
    }

    private void fetchCropsFromServer(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String category = jsonObject.getString("Purpose");
                                String name = jsonObject.getString("Scheme");
                                String imageUrl = jsonObject.getString("imageUrl");

                                CropScheme crop = new CropScheme(id, category, name, imageUrl);
                                cropList.add(crop);
                            }
                            cropAdapter.notifyDataSetChanged();
                            // Hide loading indicator when data is loaded
                            t1.setVisibility(View.GONE);
                            loadingIndicator.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        // Hide loading indicator in case of error
                        loadingIndicator.setVisibility(View.GONE);
                        t1.setVisibility(View.GONE);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}