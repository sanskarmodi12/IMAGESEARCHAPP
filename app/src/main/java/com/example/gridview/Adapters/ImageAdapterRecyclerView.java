package com.example.gridview.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gridview.DisplayImage;
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
        int w=parent.getMeasuredWidth();



        view.getLayoutParams().height = parent.getMeasuredWidth() / spanCount;
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String imageUrl=imageModels.get(position).getImageUrl();


        ImageView imageView= holder.imageView;
        Glide.with(context).load(imageModels.get(position).getImageUrl()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayImage.class);
                intent.putExtra("imageUrl",imageUrl);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, imageView, ViewCompat.getTransitionName(imageView));
               context.startActivity(intent, options.toBundle());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(imageModels==null)
            return 0;
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


