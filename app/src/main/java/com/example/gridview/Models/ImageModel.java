package com.example.gridview.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageModel {


    @SerializedName("original")
    String ImageUrl;


    public int getPosition() {
        return position;
    }

    int  position;

    public Bitmap getBitMap() {
        return bitMap;
    }

   Bitmap bitMap;
    public  ImageModel()
    {

    }

    public ImageModel(String imageUrl,Bitmap bitMap,int position) {
        ImageUrl = imageUrl;
        this.bitMap=bitMap;
        this.position=position;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
