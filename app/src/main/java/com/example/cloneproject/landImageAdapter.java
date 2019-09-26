package com.example.cloneproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class landImageAdapter extends RecyclerView.Adapter<landImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<imageUploadLand> imageUploadLand;


    public landImageAdapter (Context mContext, List<imageUploadLand> imageUploadLand ) {

        mContext = mContext;
        imageUploadLand = imageUploadLand;
    }



    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_land_image_view,parent, false);
        return new ImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        imageUploadLand uploadCurrent = imageUploadLand.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get().load(uploadCurrent.getMimageUrl()).fit().centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageUploadLand.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

            public TextView textViewName;
            public ImageView imageView;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);

                textViewName = itemView.findViewById(R.id.view_name);
                imageView    = itemView.findViewById(R.id.image_view_upload);
            }
        }








}
