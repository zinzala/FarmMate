package com.example.customerapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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

public class ActivityCropHealthMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CropAdapterHealth cropAdapterHealth;
    private List<CropHealth> cropListHealth;
    private ProgressBar loadingIndicator;
    private TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_crop_health_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cropListHealth = new ArrayList<>();
        cropAdapterHealth = new CropAdapterHealth(cropListHealth);
        recyclerView.setAdapter(cropAdapterHealth);

        loadingIndicator = findViewById(R.id.loadingIndicator);
        t1 = findViewById(R.id.loadingText);

        loadingIndicator.setVisibility(View.VISIBLE);

        fetchCropsFromServer();
    }

    private void fetchCropsFromServer() {
        String url = "https://adimnfarmmate.000webhostapp.com/php/codehealth.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String category = jsonObject.getString("Category");
                                String name = jsonObject.getString("cropName");
                                String description = jsonObject.getString("cropHealth");
                                String imageUrl = jsonObject.getString("imageUrl");

                                CropHealth crop = new CropHealth(id, category, name, description, imageUrl);
                                cropListHealth.add(crop);
                            }
                            cropAdapterHealth.notifyDataSetChanged();
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