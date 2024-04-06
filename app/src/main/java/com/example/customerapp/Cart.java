package com.example.customerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cart extends AppCompatActivity {

    TextView textViewCartData;
    SharedPreferences sharedPreferences;
    Button buttonConfirm, buttonRemove;

    int pricePerKM = 5;
    String urlConfirm =
            "http://192.168.63.122:8018/Market_place/harshadApp/public/api/users/cart/confirm?user_email=";

    String urlRemove =
            "http://192.168.63.122:8018/Market_place/harshadApp/public/api/users/cart/clear?user_email=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textViewCartData = findViewById(R.id.textCartData);

        sharedPreferences = getSharedPreferences("CustomerApp", MODE_PRIVATE);
        buttonConfirm = findViewById(R.id.btnConfirmOrder);
        buttonRemove = findViewById(R.id.btnClearCart);
        fetchData();
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest(view, urlConfirm + sharedPreferences.getString("email", ""));
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest(view, urlRemove + sharedPreferences.getString("email", ""));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void sendRequest(View v, String apiUrl) {
        Log.e("url", apiUrl);
        v.setEnabled(false);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                v.setEnabled(true);
                if (response.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Operation success", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "Operation failed", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                v.setEnabled(true);
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }

    public void parseJSON(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("cart");
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject stu = jsonArray1.getJSONObject(i);
                String id = stu.getString("id");
                String name = stu.getString("name");
                String des = stu.getString("description");
                String numItem = stu.getString("numItem");
                String price = stu.getString("price");
                textViewCartData.append("Item: " + name + "\nPrice: " + price + "$\nNumber of Item: " + numItem + "\n\n");
            }

        } catch (JSONException e) {
            Log.e("error", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://192.168.63.122:8018/Market_place/harshadApp/public/api/users/cart/list?user_email="
                        + sharedPreferences.getString("email", ""), new Response.Listener<String>() {
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
}