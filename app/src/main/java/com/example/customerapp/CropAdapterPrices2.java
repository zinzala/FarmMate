package com.example.customerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CropAdapterPrices2 extends RecyclerView.Adapter<CropAdapterPrices2.CropViewHolder> {
    private List<CropPrices> cropList;
    private Context context;

    public CropAdapterPrices2(List<CropPrices> cropList) {
        this.cropList = cropList;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_price_recyclerview1, parent, false);
        return new CropViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        CropPrices crop = cropList.get(position);
        holder.textCropName.setText(crop.getCategory());
        holder.minimum.setText(crop.getDescription());
        holder.maximum.setText(crop.getDescription2());

    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    static class CropViewHolder extends RecyclerView.ViewHolder {
        TextView textCropName;
        TextView minimum;
        TextView maximum;

        CropViewHolder(View itemView) {
            super(itemView);

            textCropName = itemView.findViewById(R.id.textCropNameDetail);
            minimum = itemView.findViewById(R.id.textMinimumPrice);
            maximum = itemView.findViewById(R.id.textMaximumPrice);

        }
    }
}
