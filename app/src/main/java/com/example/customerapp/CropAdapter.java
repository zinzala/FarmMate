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

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.CropViewHolder> {
    private List<Crop> cropList;
    private Context context;

    public CropAdapter(List<Crop> cropList) {
        this.cropList = cropList;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_crop_detail_recycler_view, parent, false);
        return new CropViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        Crop crop = cropList.get(position);
        holder.textCropName.setText(crop.getName());
        holder.textCropDescription.setText(crop.getCategory());
        Glide.with(context).load(crop.getImageUrl()).into(holder.imageCrop);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityCropDetails.class);
                intent.putExtra("id", crop.getId());
                intent.putExtra("category", crop.getCategory());
                intent.putExtra("cropName", crop.getName());
                intent.putExtra("cropDescription", crop.getDescription());
                intent.putExtra("imageUrl", crop.getImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    static class CropViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCrop;
        TextView textCropName;
        TextView textCropDescription;

        CropViewHolder(View itemView) {
            super(itemView);
            imageCrop = itemView.findViewById(R.id.imageCropDetail);
            textCropName = itemView.findViewById(R.id.textCropNameDetail);
            textCropDescription = itemView.findViewById(R.id.textCropDescriptionDetail);
        }
    }
}
