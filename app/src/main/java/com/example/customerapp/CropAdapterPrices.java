package com.example.customerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class CropAdapterPrices extends RecyclerView.Adapter<CropAdapterPrices.CropViewHolder> {
    private List<String> districtList;
    private List<Crop> cropList; // List of unique district names
    private Context context;

    public CropAdapterPrices(List<String> districtList) {
        this.districtList = districtList;
    }



    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_price_recyclerview, parent, false);
        return new CropViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        String districtName = districtList.get(position);
        holder.textCropName.setText(districtName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityPriceDetail.class);
                intent.putExtra("cropName",districtName);
                context.startActivity(intent);
            }
        });

        // You can add onClickListener if needed
    }

    @Override
    public int getItemCount() {
        return districtList.size();
    }

    static class CropViewHolder extends RecyclerView.ViewHolder {
        TextView textCropName;

        CropViewHolder(View itemView) {
            super(itemView);

            textCropName = itemView.findViewById(R.id.textCropNameDetail);
        }
    }
}
