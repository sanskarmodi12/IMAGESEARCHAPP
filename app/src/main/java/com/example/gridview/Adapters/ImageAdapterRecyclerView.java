package com.example.gridview.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gridview.Models.ImageModel;
import com.example.gridview.Models.ImageResults;
import com.example.gridview.R;

import java.util.List;

public class ImageAdapterRecyclerView extends RecyclerView.Adapter<ImageAdapterRecyclerView.viewHolder> {
    private Context context;
    List<ImageModel> imageModels;
    int spanCount;

    public ImageAdapterRecyclerView(Context context, ImageResults imageResults, int spanCount) {
        this.context = context;
        this.imageModels = imageResults.getImageResults();
        this.spanCount=spanCount;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context=parent.getContext();
        View view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_image,parent,false);


        view.getLayoutParams().height = parent.getMeasuredWidth() / spanCount;
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        ImageView imageView= holder.imageView;
        Glide.with(context).load(imageModels.get(position).getImageUrl()).into(imageView);


    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }


  public  class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.card_imag);

        }
    }

}


