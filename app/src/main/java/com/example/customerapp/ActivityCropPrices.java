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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityCropPrices extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CropAdapterPrices cropAdapter;

    private ProgressBar loadingIndicator;
    private TextView t1;

    private List<Crop> cropList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_crop_prices);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingIndicator = findViewById(R.id.loadingIndicator);
        t1 = findViewById(R.id.loadingText);

        loadingIndicator.setVisibility(View.VISIBLE);

        fetchCropsFromServer();
    }

    private void fetchCropsFromServer() {
        String url = "https://adimnfarmmate.000webhostapp.com/php/codeprices.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Set<String> uniqueDistrictNames = new HashSet<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String districtName = jsonObject.getString("district");

                                // Add unique district names to the set
                                uniqueDistrictNames.add(districtName);
                            }

                            // Create a list from the set
                            List<String> districtList = new ArrayList<>(uniqueDistrictNames);

                            // Create CropAdapter with unique district names
                            cropAdapter = new CropAdapterPrices(districtList);
                            recyclerView.setAdapter(cropAdapter);
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
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}