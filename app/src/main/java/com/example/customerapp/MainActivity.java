package com.example.customerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button  buttonAddress;
    ImageView buttonCart,buttonAllOrders;
    ArrayList<ListData> arrayList;
    String apiURL = "http://192.168.63.122:8018/Market_place/harshadApp/public/api/product/list";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // added
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("CustomerApp", MODE_PRIVATE);
        buttonCart = findViewById(R.id.btnCart);
        buttonAddress = findViewById(R.id.btnSelectAddress);
        buttonAllOrders = findViewById(R.id.btnAllOrders);
        checkPermissions();
        if (sharedPreferences.getString("login", "false").equals("false")) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        arrayList = new ArrayList<>();

        fetchData();

        buttonAllOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListOrders.class);
                startActivity(intent);
            }
        });

        buttonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddressDetails.class);
                startActivity(intent);
            }
        });

        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });
    }
//  added
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                performLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void performLogout() {
        // Clear the stored login credentials
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("login");
        editor.remove("name");
        editor.remove("email");
        editor.apply();

        // Navigate back to the login screen
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finishAffinity();
    }



    public void parseJSON(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject stu = jsonArray.getJSONObject(i);
                String id = stu.getString("id");
                String name = stu.getString("name");
                String des = stu.getString("description");
                String image = stu.getString("image");
                String price = stu.getString("price");
                arrayList.add(new ListData(id, name, des, image, price));
            }
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(arrayList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
}