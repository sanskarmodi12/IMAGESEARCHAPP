package com.example.gridview.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gridview.Models.ImageModel;
import com.example.gridview.Models.ImageResults;
import com.example.gridview.R;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    List<ImageModel>imageModels;

    public ImageAdapter(Context context, ImageResults data) {
        this.context = context;
        this.imageModels=data.getImageResults();
    }

    @Override
    public int getCount() {
        return imageModels.size();
//        return 10;
    }

    @Override
    public Object getItem(int position) {
        ImageModel imageModel=imageModels.get(position);
        return imageModel.getImageUrl();

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(context);
//        imageView.setImageResource(R.drawable.ic_launcher_background);
        Glide.with(context).load(imageModels.get(position).getImageUrl()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));


        return imageView;
    }
}
