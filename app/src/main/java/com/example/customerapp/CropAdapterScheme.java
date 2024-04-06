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

public class CropAdapterScheme extends RecyclerView.Adapter<CropAdapterScheme.CropViewHolder> {
    private List<CropScheme> cropList;
    private Context context;

    public CropAdapterScheme(List<CropScheme> cropList) {
        this.cropList = cropList;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_scheme_recyclerview, parent, false);
        return new CropViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        CropScheme crop = cropList.get(position);
        holder.textCropName.setText(crop.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySchemeDetail.class);
                intent.putExtra("id", crop.getId());
                intent.putExtra("category", crop.getCategory());
                intent.putExtra("cropName", crop.getName());
                intent.putExtra("imageUrl",crop.getImageUrl());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    static class CropViewHolder extends RecyclerView.ViewHolder {

        TextView textCropName;

        CropViewHolder(View itemView) {
            super(itemView);
            textCropName = itemView.findViewById(R.id.textCropNameDetail);
        }
    }
}
