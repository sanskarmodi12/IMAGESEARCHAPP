package com.example.gridview.Interfaces;

import com.example.gridview.Models.ImageResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Myapi {

    @GET("search.json?tbm=isch&api_key=e4712ef89f377efdf86eace66c2a492efaa34d3b77156132b74157a1a77eb9d1")
    Call<ImageResults> getImageResults(@Query("q") String name,@Query("ijn") int page);


}
