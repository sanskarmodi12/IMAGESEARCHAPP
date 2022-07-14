package com.example.gridview.Interfaces;

import com.example.gridview.Models.ImageResults;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Myapi {

    @GET("search.json?q=Apple&tbm=isch&ijn=0&api_key=15e7c687bd3355b6b655debcdc2d51d23abc74eb6d4e1dad8ceb3c52ea058776")
    Call<ImageResults> getImageResults();


}
