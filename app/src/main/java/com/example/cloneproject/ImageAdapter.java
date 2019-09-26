package com.example.cloneproject;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
        private Context mContext;
        private List<Upload> mUploads;
        private OnItemClickListener mListener;

        public ImageAdapter(Context context,List<Upload> uploads){
            mContext = context;
            mUploads = uploads;
        }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Upload uploadCurrent = mUploads.get(position);
            holder.textViewName.setText(uploadCurrent.getmTitle());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.imageUploaded);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                     mListener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select Action");
            MenuItem doViewDetail = contextMenu.add(Menu.NONE,1,1,"View Details");
            MenuItem doUpdate = contextMenu.add(Menu.NONE,2,2,"Update");
            MenuItem doDelete = contextMenu.add(Menu.NONE,3,3,"Delete");

            doViewDetail.setOnMenuItemClickListener(this);
            doUpdate.setOnMenuItemClickListener(this);
            doDelete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){

                    switch (menuItem.getItemId()){
                        case 1:
                            mListener.onViewDetailClick(position);
                            return true;
                        case 2:
                            mListener.onUpdateClick(position);
                            return true;
                        case 3:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
            void onItemClick(int position);

            void onViewDetailClick(int position);

            void onUpdateClick(int position);

            void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;

    }
}

