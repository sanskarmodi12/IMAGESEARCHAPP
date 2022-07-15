package com.example.gridview.Interfaces;

import com.example.gridview.Models.ImageResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Myapi {

    @GET("search.json?tbm=isch&api_key=15e7c687bd3355b6b655debcdc2d51d23abc74eb6d4e1dad8ceb3c52ea058776")
    Call<ImageResults> getImageResults(@Query("q") String name,@Query("ijn") int page);


}
