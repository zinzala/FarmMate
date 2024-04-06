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

public class ActivityPriceDetail extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private CropAdapterPrices2 cropAdapter1;
    private List<CropPrices> cropList;
    private ProgressBar loadingIndicator;
    private TextView t2;
    private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_price_detail);

        String cropName = getIntent().getStringExtra("cropName");
        t1 = findViewById(R.id.textTitle);
        t1.setText("Market Prices List Of " + cropName);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        loadingIndicator = findViewById(R.id.loadingIndicator);
        t2 = findViewById(R.id.loadingText);

        loadingIndicator.setVisibility(View.VISIBLE);


        cropList = new ArrayList<>();
        cropAdapter1 = new CropAdapterPrices2(cropList);
        recyclerView1.setAdapter(cropAdapter1);

        fetchCropsFromServer(cropName);
    }

    private void fetchCropsFromServer(final String cropName) {
        String url = "https://adimnfarmmate.000webhostapp.com/php/codeprices.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String category = jsonObject.getString("crop");
                                String name = jsonObject.getString("district");
                                String id1 = jsonObject.getString("minimum");
                                String id2 = jsonObject.getString("maximum");

                                // Check if the district name is the specified crop name
                                if (name.equals(cropName)) {
                                    CropPrices crop = new CropPrices(id, category, name, id1, id2);
                                    cropList.add(crop);
                                }
                            }
                            t2.setVisibility(View.GONE);
                            loadingIndicator.setVisibility(View.GONE);
                            cropAdapter1.notifyDataSetChanged();
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