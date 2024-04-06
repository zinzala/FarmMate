package com.example.customerapp;

import android.annotation.SuppressLint;
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

public class CropDetailMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CropAdapter cropAdapter;
    private List<Crop> cropList;
    private ProgressBar loadingIndicator;
    private TextView t1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_crop_detail_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cropList = new ArrayList<>();
        cropAdapter = new CropAdapter(cropList);
        recyclerView.setAdapter(cropAdapter);

        loadingIndicator = findViewById(R.id.loadingIndicator);
        t1 = findViewById(R.id.loadingText);

        loadingIndicator.setVisibility(View.VISIBLE);

        fetchCropsFromServer();
    }

    private void fetchCropsFromServer() {
        String url = "https://adimnfarmmate.000webhostapp.com/php/code.php";

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
                                String description = jsonObject.getString("cropDescription");
                                String imageUrl = jsonObject.getString("imageUrl");

                                Crop crop = new Crop(id, category, name, description, imageUrl);
                                cropList.add(crop);
                            }
                            cropAdapter.notifyDataSetChanged();
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