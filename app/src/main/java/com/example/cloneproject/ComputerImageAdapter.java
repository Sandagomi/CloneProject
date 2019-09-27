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

public class ComputerImageAdapter extends RecyclerView.Adapter <ComputerImageAdapter.ComputerImageViewHolder>{

    private Context mContext;
    private List<UploadComputer> mUploads;

    public ComputerImageAdapter(Context context, List<UploadComputer> uploads) {
        mContext = context;
        mUploads = uploads;
    }

   @NonNull
    @Override
    public ComputerImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_computer_item, parent, false);
        return new ComputerImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComputerImageViewHolder holder, int position) {
        UploadComputer uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getmComputerName());
        holder.textViewDes.setText(uploadCurrent.getmComputerDes());
        holder.textViewContactNo.setText(uploadCurrent.getmComputerCNo());

        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ComputerImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;
        public TextView textViewDes;
        public TextView textViewContactNo;

        public ComputerImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_computer_name);
            imageView = itemView.findViewById(R.id.image_view_upload_computer);
            textViewDes = itemView.findViewById(R.id.text_view_computer_des);
            textViewContactNo= itemView.findViewById(R.id.text_view_computer_CNo);
        }
    }
}
