package com.example.gridview.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResults {
    @SerializedName("images_results")
    List<ImageModel> imageResults;

    public ImageResults(List<ImageModel> imageResults) {
        this.imageResults = imageResults;
    }

    public ImageResults() {

    }

    public List<ImageModel> getImageResults() {
        return imageResults;
    }


}
