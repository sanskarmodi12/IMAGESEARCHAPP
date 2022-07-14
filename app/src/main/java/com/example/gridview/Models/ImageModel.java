package com.example.gridview.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageModel {


    @SerializedName("original")
    String ImageUrl;
    public  ImageModel()
    {

    }

    public ImageModel(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
